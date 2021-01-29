package ee.bcs.valiit.tasks.hiber;

import ee.bcs.valiit.tasks.*;
import ee.bcs.valiit.tasks.hiber.BankServiceHib;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("hibernate")

@RestController
public class BankingControllerHib {

    @Autowired
    private BankServiceHib bankServiceHib;

// uue customeri loomine
    @PostMapping("/bank/customer")
    public void createCustomer(@RequestBody Customer customer) {
        bankServiceHib.createCustomer(customer);
    }

    //    uue konto loomine
    @PostMapping("/bank/account")
    public void createAccount(@RequestBody Customer customer) {
        bankServiceHib.createAccount(customer);
    }

    //    deponeerimine
    @PostMapping("/bank/dep/{account}")
    public void dep(@PathVariable("account") int ac1, @RequestBody Transaction transaction) {
        bankServiceHib.dep(ac1, transaction);
    }

    //    välja võtmine
    @PostMapping("/bank/with/{account}")
    public void with(@PathVariable("account") int ac1, @RequestBody Transaction transaction) {
        bankServiceHib.with(ac1, transaction);
    }


//    @GetMapping("customers")
//    public CustomersHib displayCustomers(int id) {
//                CustomersHib customersHib = bankServiceHib.getOne(id);
//        for (AccountsHib accountsHib : customersHib.getAccountsHibs()) {
//            for (TransactionsHib transactionsHib : accountsHib.getTransactionsHibs()) {
//
//            }
//
//        }
//        return customersHib;
//    }

}
