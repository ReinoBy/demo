package ee.bcs.valiit.tasks.hiber;

import ee.bcs.valiit.tasks.Customer;
import ee.bcs.valiit.tasks.hiber.AccountsHib;

import javax.persistence.*;
import java.util.List;

@Entity
public class CustomersHib {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_ID;
    private String firstName;
    private String lastName;
    private String isikukood;
    private String aadress;
    private String telefon;
    private String email;


    @OneToMany(mappedBy = "customersHib")
    private List<AccountsHib> accountsHibs;

    public CustomersHib(Customer customer) {
        this.setFirstName(customer.getFirstName());
        this.setLastName(customer.getLastName());
        this.setIsikukood(customer.getIsikukood());
        this.setAadress(customer.getAadress());
        this.setTelefon(customer.getTelefon());
        this.setEmail(customer.getEmail());
    }

    public CustomersHib() {

    }

    public List<AccountsHib> getAccountsHibs() {
        return accountsHibs;
    }

    public void setAccountsHibs(List<AccountsHib> accountsHibs) {
        this.accountsHibs = accountsHibs;
    }

    public int getUserID() {
        return user_ID;
    }

    public void setUserID(int userID) {
        this.user_ID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIsikukood() {
        return isikukood;
    }

    public void setIsikukood(String isikukood) {
        this.isikukood = isikukood;
    }

    public String getAadress() {
        return aadress;
    }

    public void setAadress(String aadress) {
        this.aadress = aadress;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
