package ee.bcs.valiit.tasks;

import ee.bcs.valiit.tasks.hiber.AccountsHib;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {

    private int accountNr;
    private int ownerId;
    private BigDecimal saldo;
    private List<Transaction> transactions;


    public Account(AccountsHib accountsHib) {
        this.setAccountNr(accountsHib.getAccountNr());
        this.setSaldo(accountsHib.getSaldo());
        this.setOwnerId(accountsHib.getCustomersHib().getUserID());

    }
    public Account() {
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getAccountNr() {
        return accountNr;
    }

    public void setAccountNr(int accountNr) {
        this.accountNr = accountNr;
    }



    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
