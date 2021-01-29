package ee.bcs.valiit.tasks;

import ee.bcs.valiit.SolutionBankingController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createCustomer(@RequestBody Customer customer) {
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

    public void createAccount(@RequestBody Account account) {
        String sql = "INSERT INTO accounts (owner_id, saldo) VALUES (:muutuja1, :muutuja2)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("muutuja1", account.getOwnerId());
        paramMap.put("muutuja2", new BigDecimal("0"));
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateTransactionsDep (int ac1, Transaction transaction, String type) {
        String sql = "INSERT INTO transactions (accountnr, amount, amount_db, tüüp, stamp, comment) VALUES (:muutuja1, :muutuja02, :muutuja2,:muutuja3, :muutuja4, :muutuja05)";
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssms").format(new java.util.Date());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("muutuja1", ac1);
        paramMap.put("muutuja02", transaction.getAmount());
        paramMap.put("muutuja2", transaction.getAmount());
        paramMap.put("muutuja3", type);
        paramMap.put("muutuja4", timeStamp);
        paramMap.put("muutuja05", transaction.getComment());
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateTransactionsWit (int ac1, Transaction transaction, String type) {
        String sql = "INSERT INTO transactions (accountnr, amount, amount_cr, tüüp, stamp, comment) VALUES (:muutuja1, :muutuja02, :muutuja2,:muutuja3, :muutuja4, :muutuja05)";
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssms").format(new java.util.Date());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("muutuja1", ac1);
        paramMap.put("muutuja02", transaction.getAmount());
        paramMap.put("muutuja2", transaction.getAmount());
        paramMap.put("muutuja3", type);
        paramMap.put("muutuja4", timeStamp);
        paramMap.put("muutuja05", transaction.getComment());
        jdbcTemplate.update(sql, paramMap);
    }


    public void updateTransactionsTransferCR (int ac1, int ac2, Transaction transaction, String type) {
        String sql = "INSERT INTO transactions (accountnr, amount, amount_cr, account_db, tüüp, stamp, comment) VALUES (:muutuja1, :muutuja02, :muutuja2,:muutuja03, :muutuja3, :muutuja4, :muutuja05)";
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssms").format(new java.util.Date());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("muutuja1", ac1);
        paramMap.put("muutuja02", transaction.getAmount());
        paramMap.put("muutuja2", transaction.getAmount());
        paramMap.put("muutuja03", ac2);
        paramMap.put("muutuja3", type);
        paramMap.put("muutuja4", timeStamp+ac1);
        paramMap.put("muutuja05", transaction.getComment());
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateTransactionsTransferDB (int ac1, int ac2, Transaction transaction, String type) {
        String sql = "INSERT INTO transactions (accountnr, amount, amount_db, account_cr, tüüp, stamp, comment) VALUES (:muutuja1, :muutuja02, :muutuja2,:muutuja03, :muutuja3, :muutuja4, :muutuja05)";
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssms").format(new java.util.Date());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("muutuja1", ac1);
        paramMap.put("muutuja02", transaction.getAmount());
        paramMap.put("muutuja2", transaction.getAmount());
        paramMap.put("muutuja03", ac2);
        paramMap.put("muutuja3", type);
        paramMap.put("muutuja4", timeStamp+ac1);
        paramMap.put("muutuja05", transaction.getComment());
        jdbcTemplate.update(sql, paramMap);
    }


    public BigDecimal valiSaldo (int ac1, Transaction transaction){
        String sql3 = "SELECT saldo FROM accounts WHERE accountnr = :muutuja7 ";
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("muutuja7", ac1);
        return jdbcTemplate.queryForObject(sql3, paramMap3, BigDecimal.class);
    }

    public void uuendaSaldoDep(int ac1, BigDecimal x, Transaction transaction){
        String sql2 = "UPDATE accounts SET saldo = :muutuja5 WHERE accountnr = :muutuja6";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("muutuja6", ac1);
        paramMap2.put("muutuja5", x.add(transaction.getAmount()));
        jdbcTemplate.update(sql2, paramMap2);
    }

    public void uuendaSaldoWit(int ac1, BigDecimal x, Transaction transaction){
        String sql2 = "UPDATE accounts SET saldo = :muutuja5 WHERE accountnr = :muutuja6";
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("muutuja6", ac1);
        paramMap2.put("muutuja5", x.subtract(transaction.getAmount()));
        jdbcTemplate.update(sql2, paramMap2);
    }


    public Customer findCustomer(int id) {
        String sqlS1 = "SELECT * FROM customers WHERE user_id = :muutujaS1 ";
        Map<String, Object> paramMapS1 = new HashMap<>();
        paramMapS1.put("muutujaS1", id);
        return jdbcTemplate.queryForObject(sqlS1, paramMapS1, new BankRepository.CustomerRowMapper());
    }

    public int findCustomerIsikukood(String kood) {
        String sqlS1 = "SELECT count(*) FROM customers WHERE isikukood = :muutujaS1 ";
        Map<String, Object> paramMapS1 = new HashMap<>();
        paramMapS1.put("muutujaS1", kood);
        return jdbcTemplate.queryForObject(sqlS1,paramMapS1,Integer.class);
    }

    public List<Customer> findListCustomers(){
        String sqlL1 = "SELECT * FROM customers";
        return jdbcTemplate.query(sqlL1, new HashMap<>(), new CustomerRowMapper());
    }

    public List<Customer> showAllCustomers (List<Customer> customerList){
        for (Customer customer : findListCustomers()) {
            String sqlL2 = "SELECT * FROM accounts WHERE owner_id = :muutujaL1 ";
            Map<String, Object> paramMapL1 = new HashMap<>();
            paramMapL1.put("muutujaL1", customer.getUserID());
            List<Account> accountList = jdbcTemplate.query(sqlL2, paramMapL1, new AccountRowMapper());
            customer.setAccounts(accountList);
        }
        return customerList;
    }

    public Customer findAccounts(Customer customer){
        String sqlS2 = "SELECT * FROM accounts WHERE owner_id = :muutujaS2 ";
        Map<String, Object> paramMapS2 = new HashMap<>();
        paramMapS2.put("muutujaS2", customer.getUserID());
        List<Account> accountList = jdbcTemplate.query(sqlS2, paramMapS2, new AccountRowMapper());
        customer.setAccounts(accountList);
        return customer;
    }

    public Customer findTransactions(Customer customer) {
        for (Account account : customer.getAccounts()) {
            String sqlS3 = "SELECT * FROM transactions WHERE accountnr = :muutujaS3 ";
            Map<String, Object> paramMapS3 = new HashMap<>();
            paramMapS3.put("muutujaS3", account.getAccountNr());
            List<Transaction> transactionList = jdbcTemplate.query(sqlS3, paramMapS3, new TransactionRowMapper());
            account.setTransactions(transactionList);
        }
        return customer;
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
            account.setAccountNr(resultSet.getInt("accountnr"));
            account.setSaldo(resultSet.getBigDecimal("saldo"));
            account.setOwnerId(resultSet.getInt("owner_id"));
            return account;
        }
    }

    private class TransactionRowMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setAccountNumber(resultSet.getInt("accountnr"));
            transaction.setAccountCr(resultSet.getInt("account_cr"));
            transaction.setAccountDb(resultSet.getInt("account_db"));
            transaction.setAmount(resultSet.getBigDecimal("amount"));
            transaction.setAmountCr(resultSet.getBigDecimal("amount_cr"));
            transaction.setAmountDb(resultSet.getBigDecimal("amount_db"));
            transaction.setType(resultSet.getString("tüüp"));
            transaction.setTimeStamp(resultSet.getString("stamp"));
            transaction.setComment(resultSet.getString("comment"));

            return transaction;
        }
    }

}
