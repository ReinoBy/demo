package ee.bcs.valiit.tasks;

import ee.bcs.valiit.tasks.hiber.AccountsHib;
import ee.bcs.valiit.tasks.hiber.CustomersHib;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private int userID;
    private String firstName;
    private String lastName;
    private String isikukood;
    private String aadress;
    private String telefon;
    private String email;
    private List<Account> accounts;


    public Customer(CustomersHib customersHib) {
        this.setUserID(customersHib.getUserID());
        this.setFirstName(customersHib.getFirstName());
        this.setLastName(customersHib.getLastName());
        this.setIsikukood(customersHib.getIsikukood());
        this.setAadress(customersHib.getAadress());
        this.setTelefon(customersHib.getTelefon());
        this.setEmail(customersHib.getEmail());
        List<Account> acc = new ArrayList<>();
        for(AccountsHib accountsHib: customersHib.getAccountsHibs()){
            Account account = new Account(accountsHib);
            acc.add(account);
          }
        this.setAccounts(acc);

    }

    public Customer() {

    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts)  {
        this.accounts = accounts;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
