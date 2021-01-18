package ee.bcs.valiit.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String customerName;
    private String accountNumber;
    private BigDecimal saldo;
    private ArrayList<Double> transactions;

    public Account(String customerName, String accountNumber, double transactions) {
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.saldo = new BigDecimal(0);
        this.transactions = new ArrayList<Double>();
        this.transactions.add(transactions);
    }

    public List<Double> getTransactions() {
        return transactions;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
