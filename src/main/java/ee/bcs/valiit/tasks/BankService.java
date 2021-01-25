package ee.bcs.valiit.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    //    siia defineerime kõik panga meetodid
    public void createCustomer(Customer customer) {
        bankRepository.createCustomer(customer);
    }

    public void createAccount(Account account) {
        bankRepository.createAccount(account);
    }

    public void dep(String ac1, Transaction transaction) {
        String dep = "Deposit";
        bankRepository.updateTransactionsDep(ac1, transaction, dep);
        bankRepository.uuendaSaldoDep(ac1, bankRepository.valiSaldo(ac1, transaction), transaction);
    }

    public void with(String ac1, Transaction transaction) {

        String trans = "Withdraw ";
        BigDecimal x1 = bankRepository.valiSaldo(ac1, transaction);

        if (transaction.getAmount().compareTo(new BigDecimal("0")) > 0 && x1.compareTo(transaction.getAmount()) > 0) {
            bankRepository.updateTransactionsWit(ac1, transaction, trans + "outgoing");
            bankRepository.uuendaSaldoWit(ac1, x1, transaction);

        } else {
            throw new RuntimeException("Summa oli kas negatiivne või ei ole kontol piisavalt raha");
        }
    }

    public void transfer(String ac1, String ac2, Transaction transaction) {

        String trans = "Transfer ";
        BigDecimal x1 = bankRepository.valiSaldo(ac1, transaction);
        BigDecimal x2 = bankRepository.valiSaldo(ac2, transaction);

        if (transaction.getAmount().compareTo(new BigDecimal("0")) > 0 && x1.compareTo(transaction.getAmount()) > 0) {
            bankRepository.updateTransactionsTransferCR(ac1, ac2, transaction, trans + "outgoing");
            bankRepository.updateTransactionsTransferDB(ac2, ac1, transaction, trans + "incoming");
            bankRepository.uuendaSaldoWit(ac1, x1, transaction);
            bankRepository.uuendaSaldoDep(ac2, x2, transaction);

        } else {

            throw new RuntimeException("Summa oli kas negatiivne või ei ole kontol piisavalt raha");

        }
    }

    public Customer showCustomer(int id) {
        return bankRepository.findTransactions(bankRepository.findAccounts(bankRepository.findCustomer(id)));
    }

    public List<Customer> showAllCustomers(){
        return bankRepository.showAllCustomers(bankRepository.findListCustomers());
    }
}
