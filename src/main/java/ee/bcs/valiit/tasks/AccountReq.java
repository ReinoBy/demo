package ee.bcs.valiit.tasks;

import ee.bcs.valiit.tasks.hiber.CustomersHib;
import ee.bcs.valiit.tasks.hiber.TransactionsHib;

import java.math.BigDecimal;
import java.util.List;

public class AccountReq {

    private int accountNr;
    private BigDecimal saldo;
    private CustomersHib customersHib;
    private List<TransactionsHib> transactionsHibs;


    public CustomersHib getCustomersHib() {
        return customersHib;
    }

    public void setCustomersHib(CustomersHib customersHib) {
        this.customersHib = customersHib;
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

    public List<TransactionsHib> getTransactionsHibs() {
        return transactionsHibs;
    }

    public void setTransactionsHibs(List<TransactionsHib> transactionsHibs) {
        this.transactionsHibs = transactionsHibs;
    }
}
