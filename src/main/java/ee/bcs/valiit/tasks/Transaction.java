package ee.bcs.valiit.tasks;

import java.math.BigDecimal;

public class Transaction {

    private int accountNumber;
    private BigDecimal amount;
    private BigDecimal amountCr;
    private BigDecimal amountDb;
    private Integer accountCr;
    private Integer accountDb;
    private String type;
    private String timeStamp;
    private String comment;


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
