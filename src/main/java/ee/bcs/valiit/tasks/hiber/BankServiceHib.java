package ee.bcs.valiit.tasks.hiber;

import ee.bcs.valiit.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Service
public class BankServiceHib {

    @Autowired
    private CustomerRepositoryHib customerRepositoryHib;
    @Autowired
    private AccountRepositoryHib accountRepositoryHib;
    @Autowired
    private TransactionRepositoryHib transactionRepositoryHib;



    //    siia defineerime kõik panga meetodid
    @Transactional
    public void createCustomer(@RequestBody Customer customer) {
        isValidIsikukood(customer.getIsikukood());
        CustomersHib customersHib = new CustomersHib(customer);
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
    public void dep(int ac1, Transaction transaction) {
        isValidAmount(transaction.getAmount());
        isValidAccountNr(ac1);
        AccountsHib accountsHib = accountRepositoryHib.getOne(ac1);
        TransactionsHib transactionsHib = new TransactionsHib(accountsHib, transaction, "Deposit");
        transactionRepositoryHib.save(transactionsHib);
        BigDecimal saldo = accountsHib.getSaldo();
        saldo = saldo.add(transaction.getAmount());
        accountsHib.setSaldo(saldo);
        accountRepositoryHib.save(accountsHib);

    }

    @Transactional
    public void with(int ac1, Transaction transaction) {
        isValidAmount(transaction.getAmount());
        isValidAccountNr(ac1);
        isEnoughMoney(transaction.getAmount(),ac1);
        AccountsHib accountsHib = accountRepositoryHib.getOne(ac1);
        TransactionsHib transactionsHib = new TransactionsHib(accountsHib, transaction, "Withdraw");
        transactionRepositoryHib.save(transactionsHib);
        BigDecimal saldo = accountsHib.getSaldo();
        saldo = saldo.subtract(transaction.getAmount());
        accountsHib.setSaldo(saldo);
        accountRepositoryHib.save(accountsHib);

    }

//
//    @Transactional
//    public void with(int ac1, Transaction transaction) {
//        BigDecimal x1;
//        isValidAmount(transaction.getAmount());
//        isValidAccountNr(ac1, transaction);
//        x1 = bankRepository.valiSaldo(ac1, transaction);
//        isEnoughMoney(x1, transaction);
//        bankRepository.updateTransactionsWit(ac1, transaction, "Withdraw");
//        bankRepository.uuendaSaldoWit(ac1, x1, transaction);
//    }
//
//    @Transactional
//    public void transfer(int ac1, int ac2, Transaction transaction) {
//        isValidAmount(transaction.getAmount());
//        isValidAccountNr(ac1, transaction);
//        isValidAccountNr(ac2, transaction);
//        BigDecimal x1 = bankRepository.valiSaldo(ac1, transaction);
//        BigDecimal x2 = bankRepository.valiSaldo(ac2, transaction);
//        isEnoughMoney(x1, transaction);
//        bankRepository.updateTransactionsTransferCR(ac1, ac2, transaction, "Transfer outgoing");
//        bankRepository.updateTransactionsTransferDB(ac2, ac1, transaction, "Transfer incoming");
//        bankRepository.uuendaSaldoWit(ac1, x1, transaction);
//        bankRepository.uuendaSaldoDep(ac2, x2, transaction);
//    }
//
//    @Transactional
//    public Customer showCustomer(int id) {
//        isValidCustomer(id);
//        return bankRepository.findTransactions(bankRepository.findAccounts(bankRepository.findCustomer(id)));
//    }
//
//    @Transactional
//    public List<Customer> showAllCustomers() {
//        return bankRepository.showAllCustomers(bankRepository.findListCustomers());
//    }

    @Transactional
    public void isValidAmount(BigDecimal num) {
        if (num.compareTo(new BigDecimal("0")) <= 0) {
            throw new BankException("Summa on negatiivne");
        }
    }

    @Transactional
    public void isValidAccountNr(int num) {
        if (accountRepositoryHib.findById(num).isEmpty()){
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
        if (customerRepositoryHib.findById(id).isEmpty()){
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

        if (customerRepositoryHib.countCustomersHibByIsikukoodContains(isikukood)==1) {
            throw new BankException("Sellise isikukoodiga inimene on juba andmebaasis olemas");

        }
    }

}
