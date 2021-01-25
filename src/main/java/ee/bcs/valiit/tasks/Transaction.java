package ee.bcs.valiit.tasks;

import java.math.BigDecimal;

public class Transaction {

    private String accountNumber;
    private BigDecimal amount;
    private BigDecimal amountCr;
    private BigDecimal amountDb;
    private String accountCr;
    private String accountDb;
    private String type;
    private String timeStamp;



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

    public String getAccountCr() {
        return accountCr;
    }

    public void setAccountCr(String accountCr) {
        this.accountCr = accountCr;
    }

    public String getAccountDb() {
        return accountDb;
    }

    public void setAccountDb(String accountDb) {
        this.accountDb = accountDb;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
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
