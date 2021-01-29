package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    //    siia defineerime kõik panga meetodid
    @Transactional
    public void createCustomer(Customer customer) {
        isValidIsikukood(customer.getIsikukood());
        bankRepository.createCustomer(customer);

    }

    @Transactional
    public void createAccount(Account account) {
        isValidCustomer(account.getOwnerId());
        bankRepository.createAccount(account);
    }

    @Transactional
    public void dep(int ac1, Transaction transaction) {

        isValidAmount(transaction.getAmount());
        isValidAccountNr(ac1, transaction);
        bankRepository.updateTransactionsDep(ac1, transaction, "Deposit");
        bankRepository.uuendaSaldoDep(ac1, bankRepository.valiSaldo(ac1, transaction), transaction);

    }

    @Transactional
    public void with(int ac1, Transaction transaction) {
        BigDecimal x1;
        isValidAmount(transaction.getAmount());
        isValidAccountNr(ac1, transaction);
        x1 = bankRepository.valiSaldo(ac1, transaction);
        isEnoughMoney(x1, transaction);
        bankRepository.updateTransactionsWit(ac1, transaction, "Withdraw");
        bankRepository.uuendaSaldoWit(ac1, x1, transaction);
    }

    @Transactional
    public void transfer(int ac1, int ac2, Transaction transaction) {
        isValidAmount(transaction.getAmount());
        isValidAccountNr(ac1, transaction);
        isValidAccountNr(ac2, transaction);
        BigDecimal x1 = bankRepository.valiSaldo(ac1, transaction);
        BigDecimal x2 = bankRepository.valiSaldo(ac2, transaction);
        isEnoughMoney(x1, transaction);
        bankRepository.updateTransactionsTransferCR(ac1, ac2, transaction, "Transfer outgoing");
        bankRepository.updateTransactionsTransferDB(ac2, ac1, transaction, "Transfer incoming");
        bankRepository.uuendaSaldoWit(ac1, x1, transaction);
        bankRepository.uuendaSaldoDep(ac2, x2, transaction);
    }

    @Transactional
    public Customer showCustomer(int id) {
        isValidCustomer(id);
        return bankRepository.findTransactions(bankRepository.findAccounts(bankRepository.findCustomer(id)));
    }

    @Transactional
    public List<Customer> showAllCustomers() {
        return bankRepository.showAllCustomers(bankRepository.findListCustomers());
    }

    @Transactional
    public void isValidAmount(BigDecimal num) {
        if (num.compareTo(new BigDecimal("0")) <= 0) {
            throw new BankException("Summa on negatiivne");
        }
    }

    @Transactional
    public void isValidAccountNr(int num, Transaction transaction) {
        try {
            bankRepository.valiSaldo(num, transaction);
        } catch (EmptyResultDataAccessException e) {
            throw new BankException(num + " on vale konto number");
        }

    }

    public void isEnoughMoney(BigDecimal num, Transaction transaction) {
        if (num.compareTo(transaction.getAmount()) < 0) {
            throw new BankException("Kontol ei ole piisavalt raha");
        }

    }

    @Transactional
    public void isValidCustomer(int id) {
        try {
            bankRepository.findCustomer(id);
        } catch (EmptyResultDataAccessException e) {
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
        if (bankRepository.findCustomerIsikukood(isikukood) == 1) {
            throw new BankException("Sellise isikukoodiga inimene on juba andmebaasis olemas");

        }
    }

}
