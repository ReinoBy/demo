package ee.bcs.valiit.tasks.hiber;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ee.bcs.valiit.tasks.Transaction;
import ee.bcs.valiit.tasks.hiber.AccountsHib;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Entity
public class TransactionsHib {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;

    @ManyToOne
    @JoinColumn(name = "accountnr")
    private AccountsHib accountsHib;
    private BigDecimal amount;
    private BigDecimal amountCr;
    private BigDecimal amountDb;
    private Integer accountCr;
    private Integer accountDb;
    private String tüüp;
    private String comment;
    private String stamp;

    public TransactionsHib(AccountsHib accountsHib, Transaction transaction, String tüüp) {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssms").format(new java.util.Date());
        this.setAccountsHib(accountsHib);
        this.setAmount(transaction.getAmount());
        if (tüüp == "Deposit"|| tüüp == "Transfer incoming"){
            this.setAccountDb(accountsHib.getAccountNr());
            this.setAmountDb(transaction.getAmount());

        }
        if (tüüp == "Withdraw" || tüüp == "Transfer outgoing"){
            this.setAccountCr(accountsHib.getAccountNr());
            this.setAmountCr(transaction.getAmount());
        }
        this.setTimeStamp(timeStamp);
        this.setType(tüüp);
        this.setComment(transaction.getComment());
    }


    public long getId() {
        return transaction_id;
    }

    public void setId(Integer id) {
        this.transaction_id = id;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmountCr() {
        return amountCr;
    }

    public void setAmountCr(BigDecimal amountCr) {
        this.amountCr = amountCr;
    }

    public BigDecimal getAmountDb() {
        return amountDb;
    }

    public void setAmountDb(BigDecimal amountDb) {
        this.amountDb = amountDb;
    }

    public Integer getAccountCr() {
        return accountCr;
    }

    public void setAccountCr(Integer accountCr) {
        this.accountCr = accountCr;
    }

    public Integer getAccountDb() {
        return accountDb;
    }

    public void setAccountDb(Integer accountDb) {
        this.accountDb = accountDb;
    }

    public AccountsHib getAccountsHib() {
        return accountsHib;
    }

    public void setAccountsHib(AccountsHib accountsHib) {
        this.accountsHib = accountsHib;
    }

    public String getType() {
        return tüüp;
    }

    public void setType(String type) {
        this.tüüp = type;
    }

    public String getTimeStamp() {
        return stamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.stamp = timeStamp;
    }
}
