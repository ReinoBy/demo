package ee.bcs.valiit.tasks;

import ee.bcs.valiit.tasks.hiber.TransactionsHib;

import java.math.BigDecimal;

public class Transaction {

    private int transactionID;
    private int accountNumber;
    private BigDecimal amount;
    private String type;
    private String timeStamp;
    private String comment;
    private BigDecimal amountCr;
    private BigDecimal amountDb;
    private Integer accountCr;
    private Integer accountDb;



    public Transaction(TransactionsHib transactionsHib) {
        this.setTransactionID(transactionsHib.getTransaction_id());
        this.setAmountDb(transactionsHib.getAmountDb());
        this.setAmountCr(transactionsHib.getAmountCr());
        this.setAccountCr(transactionsHib.getAccountCr());
        this.setAccountDb(transactionsHib.getAccountDb());
        this.setAccountNumber(transactionsHib.getAccountsHib().getAccountNr());
        this.setAmount(transactionsHib.getAmount());
        this.setType(transactionsHib.getType());
        this.setComment(transactionsHib.getComment());
        this.setTimeStamp(transactionsHib.getTimeStamp());
    }

    public Transaction() {

    }
    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
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


    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
