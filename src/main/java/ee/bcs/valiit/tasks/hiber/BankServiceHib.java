package ee.bcs.valiit.tasks.hiber;

import ee.bcs.valiit.tasks.*;
import liquibase.pro.packaged.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankServiceHib {

    @Autowired
    private CustomerRepositoryHib customerRepositoryHib;
    @Autowired
    private AccountRepositoryHib accountRepositoryHib;
    @Autowired
    private TransactionRepositoryHib transactionRepositoryHib;
    @Autowired
    private PasswordEncoder passwordEncoder;



    @Transactional
    public void createCustomer(@RequestBody Customer customer) {
        isValidIsikukood(customer.getIsikukood());
        CustomersHib customersHib = new CustomersHib(customer);
        customerRepositoryHib.save(customersHib);
    }

    @Transactional
    public void updateCustomer(@RequestBody Customer customer) {
        int id = customer.getUserID();
        CustomersHib customersHib = customerRepositoryHib.getOne(id);
        customersHib.setFirstName(customer.getFirstName());
        customersHib.setLastName(customer.getLastName());
        customersHib.setAadress(customer.getAadress());
        customersHib.setTelefon(customer.getTelefon());
        customersHib.setEmail(customer.getEmail());
        customerRepositoryHib.save(customersHib);
    }


    @Transactional
    public void createAccount(@RequestBody Customer customer) {
        isValidCustomer(customer.getUserID());
        CustomersHib customersHib = customerRepositoryHib.getOne(customer.getUserID());
        AccountsHib accountsHib = new AccountsHib(customersHib);
        accountRepositoryHib.save(accountsHib);
    }

    @Transactional
    public void dep(int ac1, int ac2, Transaction transaction, String tuup) {
        isValidAmount(transaction.getAmount());
        isValidAccountNr(ac1);
        AccountsHib accountsHib = accountRepositoryHib.getOne(ac1);
        TransactionsHib transactionsHib = new TransactionsHib(accountsHib, transaction, tuup, ac2);
        transactionRepositoryHib.save(transactionsHib);
        BigDecimal saldo = accountsHib.getSaldo();
        saldo = saldo.add(transaction.getAmount());
        accountsHib.setSaldo(saldo);
        accountRepositoryHib.save(accountsHib);

    }

    @Transactional
    public void with(int ac1, int ac2, Transaction transaction, String tuup) {
        isValidAmount(transaction.getAmount());
        isValidAccountNr(ac1);
        isEnoughMoney(transaction.getAmount(), ac1);
        AccountsHib accountsHib = accountRepositoryHib.getOne(ac1);
        TransactionsHib transactionsHib = new TransactionsHib(accountsHib, transaction, tuup, ac2);
        transactionRepositoryHib.save(transactionsHib);
        BigDecimal saldo = accountsHib.getSaldo();
        saldo = saldo.subtract(transaction.getAmount());
        accountsHib.setSaldo(saldo);
        accountRepositoryHib.save(accountsHib);

    }


    @Transactional
    public Customer findCustomer(int ac1) {
        isValidCustomer(ac1);
        CustomersHib customersHib = customerRepositoryHib.getOne(ac1);
        Customer customer = new Customer(customersHib);
        List<Account> accounts = new ArrayList<>();
        for (AccountsHib accountsHib : customersHib.getAccountsHibs()) {
            Account account = new Account(accountsHib);
            List<Transaction> trans = new ArrayList<>();
            for (TransactionsHib transactionsHib : accountsHib.getTransactionsHibs()) {
                trans.add(new Transaction(transactionsHib));
            }
            account.setTransactions(trans);
            accounts.add(account);
        }

        customer.setAccounts(accounts);
        return customer;
    }

    @Transactional
    public List<Account> findCustomerAccounts(int ac1) {
        isValidCustomer(ac1);
        CustomersHib customersHib = customerRepositoryHib.getOne(ac1);
        List<Account> accounts = new ArrayList<>();
        for (AccountsHib accountsHib : customersHib.getAccountsHibs()) {
            Account account = new Account(accountsHib);
            accounts.add(account);
        }

        return accounts;
    }

    @Transactional
    public List<Transaction> findAccount(int ac1) {
        isValidAccountNr(ac1);
        AccountsHib accountsHib = accountRepositoryHib.getOne(ac1);
        List<Transaction> trans = new ArrayList<>();
        for (TransactionsHib transactionsHib : accountsHib.getTransactionsHibs()) {
            trans.add(new Transaction(transactionsHib));
        }
        return trans;
}

    @Transactional
    public Transaction findTransaction(int ac1) {
        isValidTransaction(ac1);
        TransactionsHib transactionsHib = transactionRepositoryHib.getOne(ac1);
        Transaction transaction = new Transaction(transactionsHib);

        return transaction;
    }

    @Transactional
    public List<Customer> getCustomerList(){
        List<CustomersHib> customersHibList = customerRepositoryHib.findAll();
        List<Customer> customerList = new ArrayList<>();
        for(CustomersHib customersHib:customersHibList){
            Customer customer = new Customer(customersHib);
            customerList.add(customer);
        }
        return customerList;
    }



    //    Validation
    @Transactional
    public void isValidAmount(BigDecimal num) {
        if (num.compareTo(new BigDecimal("0")) <= 0) {
            throw new BankException("Summa on negatiivne");
        }
    }

    @Transactional
    public void isValidAccountNr(int num) {
        if (!accountRepositoryHib.existsById(num)) {
            throw new BankException("Sellist kontot ei ole olemas");
        }

    }

    @Transactional
    public void isValidTransaction(int ac1) {
        if (!transactionRepositoryHib.existsById(ac1)) {
            throw new BankException("Sellist kontot ei ole olemas");
        }


    }
    @Transactional
    public void isEnoughMoney(BigDecimal num, int ac1) {
        if (accountRepositoryHib.getOne(ac1).getSaldo().compareTo(num) < 0) {
            throw new BankException("Kontol ei ole piisavalt raha");
        }

    }

    @Transactional
    public void isValidCustomer(int id) {
        if (!customerRepositoryHib.existsById(id)) {
            throw new BankException("Sellist klienti ei ole olemas");
        }

    }

    @Transactional
    public void isValidIsikukood(String isikukood) {
        if (isikukood.length() != 11) {
            throw new BankException("Isikukoodis peab olema 11 numbrit");
        }
        int i = Integer.parseInt(isikukood.substring(0, 1));
        if (i > 6 || i < 3) {
            throw new BankException("Isikukood peab algama numbritega 3-6");
        }

        if (customerRepositoryHib.countCustomersHibByIsikukoodContains(isikukood) == 1) {
            throw new BankException("Sellise isikukoodiga inimene on juba andmebaasis olemas");

        }
    }


}
