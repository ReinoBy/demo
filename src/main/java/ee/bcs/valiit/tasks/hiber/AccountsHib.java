package ee.bcs.valiit.tasks.hiber;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class AccountsHib {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountnr;
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private CustomersHib customersHib;

    @OneToMany(mappedBy = "accountsHib")
    private List<TransactionsHib> transactionsHibs;

    public AccountsHib(CustomersHib customersHib) {
        this.setSaldo(new BigDecimal("0"));
        this.setCustomersHib(customersHib);
    }

    public AccountsHib() {

    }

    public List<TransactionsHib> getTransactionsHibs() {
        return transactionsHibs;
    }

    public void setTransactionsHibs(List<TransactionsHib> transactionsHibs) {
        this.transactionsHibs = transactionsHibs;
    }



    public CustomersHib getCustomersHib() {
        return customersHib;
    }

    public void setCustomersHib(CustomersHib customersHib) {
        this.customersHib = customersHib;
    }

    public int getAccountNr() {
        return accountnr;
    }

    public void setAccountNr(int accountNr) {
        this.accountnr = accountnr;
    }



    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

}
