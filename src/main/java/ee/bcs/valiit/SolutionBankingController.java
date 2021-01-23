package ee.bcs.valiit;

import ee.bcs.valiit.tasks.Account;
import ee.bcs.valiit.tasks.Customer;
import ee.bcs.valiit.tasks.Employees;
import ee.bcs.valiit.tasks.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class SolutionBankingController {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    // uue customeri loomine
    @PostMapping("/bank/customer")
    public void create(@RequestBody Customer customer) {
        String sql = "INSERT INTO customers (first_name, last_name, isikukood, aadress, telefon, email) VALUES (:muutuja1, :muutuja2,:muutuja3, :muutuja4,:muutuja5, :muutuja6)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("muutuja1", customer.getFirstName());
        paramMap.put("muutuja2", customer.getLastName());
        paramMap.put("muutuja3", customer.getIsikukood());
        paramMap.put("muutuja4", customer.getAadress());
        paramMap.put("muutuja5", customer.getTelefon());
        paramMap.put("muutuja6", customer.getEmail());

        jdbcTemplate.update(sql, paramMap);
    }

    //    uue konto loomine
    @PostMapping("/bank/account")
    public void create(@RequestBody Account account) {
        String sql = "INSERT INTO accounts (owner_id, saldo) VALUES (:muutuja1, :muutuja2)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("muutuja1", account.getOwnerId());
        paramMap.put("muutuja2", new BigDecimal("0"));
        jdbcTemplate.update(sql, paramMap);
    }


    //    deponeerimine
    @PostMapping("/bank/dep/{account}")
    public void dep(@PathVariable("account") String ac1, @RequestBody Transaction transaction) {
        String sql = "INSERT INTO transactions (account_nr, amount, amount_db, tüüp, stamp) VALUES (:muutuja1, :muutuja02, :muutuja2,:muutuja3, :muutuja4)";
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("muutuja1", ac1);
        paramMap.put("muutuja02", transaction.getAmount());
        paramMap.put("muutuja2", transaction.getAmount());
        paramMap.put("muutuja3", "Deposit");
        paramMap.put("muutuja4", timeStamp);
        jdbcTemplate.update(sql, paramMap);
        String sql3 = "SELECT saldo FROM accounts WHERE accountnr = :muutuja7 ";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("muutuja7", ac1);
        BigDecimal x = jdbcTemplate.queryForObject(sql3, paramMap3, BigDecimal.class);
        String sql2 = "UPDATE accounts SET saldo = :muutuja5 WHERE accountnr = :muutuja6";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("muutuja6", ac1);
        paramMap2.put("muutuja5", x.add(transaction.getAmount()));
        jdbcTemplate.update(sql2, paramMap2);
    }

    //    raha välja võtmine
    @PostMapping("/bank/with/{account}")
    public void with(@PathVariable("account") String ac1, @RequestBody Transaction transaction) {
        //            kutsun konto saldo
        String sql3 = "SELECT saldo FROM accounts WHERE accountnr = :muutuja7 ";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("muutuja7", ac1);
        BigDecimal x = jdbcTemplate.queryForObject(sql3, paramMap3, BigDecimal.class);
        if (transaction.getAmount().compareTo(new BigDecimal("0")) > 0 && x.compareTo(transaction.getAmount()) > 0) {
//            kannan summa transaction tabelisse
            String sql = "INSERT INTO transactions (account_nr, amount, amount_cr, tüüp, stamp) VALUES (:muutuja1, :muutuja02, :muutuja2,:muutuja3, :muutuja4)";
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("muutuja1", ac1);
            paramMap.put("muutuja02", transaction.getAmount());
            paramMap.put("muutuja2", transaction.getAmount());
            paramMap.put("muutuja3", "Withdraw");
            paramMap.put("muutuja4", timeStamp);
            jdbcTemplate.update(sql, paramMap);
//             uuendan konto saldot
            String sql2 = "UPDATE accounts SET saldo = :muutuja5 WHERE accountnr = :muutuja6";
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("muutuja6", ac1);
            paramMap2.put("muutuja5", x.subtract(transaction.getAmount()));
            jdbcTemplate.update(sql2, paramMap2);

        } else {
            throw new RuntimeException("Summa oli kas negatiivne või ei ole kontol piisavalt raha");
        }
    }

    //    ülekanne õhelt kontolt teisele
    @PostMapping("/bank/tran/{account}/{account2}/")
    public void tran(@PathVariable("account") String ac1, @PathVariable("account2") String ac2, @RequestBody Transaction transaction) {
        //            kutsun konto1 saldo
        String sql3 = "SELECT saldo FROM accounts WHERE accountnr = :muutuja7 ";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("muutuja7", ac1);
        BigDecimal x = jdbcTemplate.queryForObject(sql3, paramMap3, BigDecimal.class);

//        vaatan, kas ülekande summa on pos ja kontol piisvalt raha.
        if (transaction.getAmount().compareTo(new BigDecimal("0")) > 0 && x.compareTo(transaction.getAmount()) > 0) {

//            kannan summa transaction tabelisse konto1 jaoks
            String sqlT1 = "INSERT INTO transactions (account_nr, amount, amount_cr, tüüp, stamp, account_cr) VALUES (:muutujaT1, :muutujaT02, :muutujaT2,:muutujaT3, :muutujaT4, :muutujaT5)";
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
            Map<String, Object> paramMapT1 = new HashMap<>();
            paramMapT1.put("muutujaT1", ac1);
            paramMapT1.put("muutujaT02", transaction.getAmount());
            paramMapT1.put("muutujaT2", transaction.getAmount());
            paramMapT1.put("muutujaT3", "Transfer outgoing");
            paramMapT1.put("muutujaT4", timeStamp + "c");
            paramMapT1.put("muutujaT5", ac2);
            jdbcTemplate.update(sqlT1, paramMapT1);

            //            kannan summa transaction tabelisse konto2 jaoks
            String sqlT4 = "INSERT INTO transactions (account_nr, amount , amount_db, tüüp, stamp, account_db) VALUES (:muutujaT41, :muutujaT042, :muutujaT42, :muutujaT43, :muutujaT44, :muutujaT45)";
            Map<String, Object> paramMapT4 = new HashMap<>();
            paramMapT4.put("muutujaT41", ac2);
            paramMapT4.put("muutujaT042", transaction.getAmount());
            paramMapT4.put("muutujaT42", transaction.getAmount());
            paramMapT4.put("muutujaT43", "Transfer incoming");
            paramMapT4.put("muutujaT44", timeStamp + "d");
            paramMapT4.put("muutujaT45", ac1);
            jdbcTemplate.update(sqlT4, paramMapT4);

//            teen muudatuse konto1 saldos
            String sql2 = "UPDATE accounts SET saldo = :muutuja5 WHERE accountnr = :muutuja6";
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("muutuja6", ac1);
            paramMap2.put("muutuja5", x.subtract(transaction.getAmount()));
            jdbcTemplate.update(sql2, paramMap2);
            //            kutsun konto2 saldo
            String sql6 = "SELECT saldo FROM accounts WHERE accountnr = :muutuja14 ";
            Map<String, Object> paramMap6 = new HashMap<>();
            paramMap6.put("muutuja14", ac2);
            BigDecimal y = jdbcTemplate.queryForObject(sql6, paramMap6, BigDecimal.class);

//            teen muudatuse konto2 saldos
            String sql5 = "UPDATE accounts SET saldo = :muutuja13 WHERE accountnr = :muutuja12";
            Map<String, Object> paramMap5 = new HashMap<>();
            paramMap5.put("muutuja12", ac2);
            paramMap5.put("muutuja13", y.add(transaction.getAmount()));
            jdbcTemplate.update(sql5, paramMap5);

        } else {
            throw new RuntimeException("Summa oli kas negatiivne või ei ole kontol piisavalt raha");
        }
    }


    //    ühe customeri isikuandmete kuvamine
    @GetMapping("/bank/customer/{id}")
    public Customer show(@PathVariable("id") int id) {
//        leian kliendi
        String sqlS1 = "SELECT * FROM customers WHERE user_id = :muutujaS1 ";
        Map<String, Object> paramMapS1 = new HashMap<>();
        paramMapS1.put("muutujaS1", id);
        Customer result = jdbcTemplate.queryForObject(sqlS1, paramMapS1, new CustomerRowMapper());
//        leian kliendi kõik kontod listina
        String sqlS2 = "SELECT * FROM accounts WHERE owner_id = :muutujaS2 ";
        Map<String, Object> paramMapS2 = new HashMap<>();
        paramMapS2.put("muutujaS2", id);
        List<Account> accountList = jdbcTemplate.query(sqlS2, paramMapS2, new AccountRowMapper());
        result.setAccounts(accountList);
//        leian konto kõik transactionid
        for (Account account : accountList) {
            String sqlS3 = "SELECT * FROM transactions WHERE account_nr = :muutujaS3 ";
            Map<String, Object> paramMapS3 = new HashMap<>();
            paramMapS3.put("muutujaS3", account.getAccountNr());
            List<Transaction> transactionList = jdbcTemplate.query(sqlS3, paramMapS3, new TransactionRowMapper());
            account.setTransactions(transactionList);
        }
        return result;
    }


    //    kõikide customeride tagastamine
    @GetMapping("/bank/customer/")
    public List<Customer> showCusts() {
        String sqlL1 = "SELECT * FROM customers";
        List<Customer> result = jdbcTemplate.query(sqlL1, new HashMap<>(), new CustomerRowMapper());
        for (Customer customer : result) {
            String sqlL2 = "SELECT * FROM accounts WHERE owner_id = :muutujaL1 ";
            Map<String, Object> paramMapL1 = new HashMap<>();
            paramMapL1.put("muutujaL1", customer.getUserID());
            List<Account> accountList = jdbcTemplate.query(sqlL2, paramMapL1, new AccountRowMapper());
            customer.setAccounts(accountList);

        }
        return result;

    }


    private class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = new Customer();
            customer.setUserID(resultSet.getInt("user_id"));
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setLastName(resultSet.getString("last_name"));
            customer.setIsikukood(resultSet.getString("isikukood"));
            customer.setAadress(resultSet.getString("aadress"));
            customer.setTelefon(resultSet.getString("telefon"));
            customer.setEmail(resultSet.getString("email"));

            return customer;
        }


    }

    private class AccountRowMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            Account account = new Account();
            account.setAccountNr(resultSet.getString("accountnr"));
            account.setSaldo(resultSet.getBigDecimal("saldo"));
            account.setOwnerId(resultSet.getInt("owner_id"));
            return account;
        }
    }

    private class TransactionRowMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setAccountNumber(resultSet.getString("account_nr"));
            transaction.setAccountCr(resultSet.getString("account_cr"));
            transaction.setAccountDb(resultSet.getString("account_db"));
            transaction.setAmount(resultSet.getBigDecimal("amount"));
            transaction.setAmountCr(resultSet.getBigDecimal("amount_cr"));
            transaction.setAmountDb(resultSet.getBigDecimal("amount_db"));
            transaction.setType(resultSet.getString("tüüp"));
            transaction.setTimeStamp(resultSet.getString("stamp"));
            return transaction;
        }
    }

}

//    @GetMapping("/bank/")
//    public Map<String, Account> show() {
//        return accountMap;
//    }
//
//
//
//    //    withrdaw
//    @PostMapping("/bank/with/{account}")
//    public void with(@PathVariable("account") String ac1, @RequestBody Account account) {
//        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
//        if ((accountMap.get(ac1).getSaldo().compareTo(account.getTransaction()) > 0) &&
//                (account.getTransaction().compareTo(new BigDecimal("0")) > 0)) {
//            accountMap.get(ac1).setSaldo(accountMap.get(ac1).getSaldo().subtract(account.getTransaction()));
//            accountMap.get(ac1).setLogFile(timeStamp, account.getTransaction().multiply(new BigDecimal("-1")));
//
//        } else {
////            vea teade
//        }
//    }
//
//    @PostMapping("/bank/{account1}/{account2}")
//    public void tran(@PathVariable("account1") String ac1, @PathVariable("account2") String ac2, @RequestBody Account account) {
//        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
//        if ((accountMap.get(ac1).getSaldo().compareTo(account.getTransaction()) > 0) &&
//                (account.getTransaction().compareTo(new BigDecimal("0")) > 0)) {
//            accountMap.get(ac1).setSaldo(accountMap.get(ac1).getSaldo().subtract(account.getTransaction()));
//            accountMap.get(ac2).setSaldo(accountMap.get(ac2).getSaldo().add(account.getTransaction()));
//            accountMap.get(ac1).setLogFile(timeStamp, account.getTransaction().multiply(new BigDecimal("-1")));
//            accountMap.get(ac2).setLogFile(timeStamp, account.getTransaction());
//
//        } else {
////            vea teade
//        }
//
//    }







