package ee.bcs.valiit.tasks.hiber;

import ee.bcs.valiit.tasks.Account;
import ee.bcs.valiit.tasks.Customer;
import ee.bcs.valiit.tasks.Message;
import ee.bcs.valiit.tasks.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

//@RequestMapping("hibernate")

@RestController
public class UserControllerHib {

    @Autowired
    private UserdDetailServiceImpl userdDetailService;

// uue useri loomine
    @PostMapping("/user")
    public Message createUser(@RequestBody UserHib userHib) {
        return userdDetailService.newUser(userHib);
    }

//    // olemasoleva kliendi andmete muutmine
//    @PostMapping("/bank/customer/update")
//    public Message updateCustomer(@RequestBody Customer customer) {
//        bankServiceHib.updateCustomer(customer);
//        return new Message("Kliendi andmete muutmine õnnestus");
//
//    }
//
//    //    uue konto loomine
//    @PostMapping("/bank/account")
//    public Message createAccount(@RequestBody Customer customer) {
//        bankServiceHib.createAccount(customer);
//        return new Message("Konto loomine õnnestus");
//
//    }
//
//    //    deponeerimine
//    @PostMapping("/bank/dep/{account}")
//    public Message dep(@PathVariable("account") int ac1, @RequestBody Transaction transaction) {
//        bankServiceHib.dep(ac1,ac1, transaction, "Deposit");
//        return new Message("Raha sissemakse õnnestus");
//
//    }
//
//    //    välja võtmine
//    @PostMapping("/bank/with/{account}")
//    public Message  with(@PathVariable("account") int ac1, @RequestBody Transaction transaction) {
//        bankServiceHib.with(ac1,ac1, transaction, "Withdraw");
//        return new Message("Raha väljavõtt õnnestus");
//
//    }
//
//    //    ülekanne ühelt kontolt teisele
//    @Transactional
//    @PostMapping("/bank/tran/{account}/{account2}/")
//    public Message tran(@PathVariable("account") int ac1, @PathVariable("account2") int ac2, @RequestBody Transaction transaction) {
//        bankServiceHib.with(ac1, ac2, transaction, "Transfer outgoing");
//        bankServiceHib.dep(ac2, ac1, transaction, "Transfer incoming");
//        return new Message("Ülekanne õnnestus");
//
//
//    }
//
//    //    Ühe kliendi info küsimine
//    @GetMapping("/bank/customer/{ID}")
//    public Customer findCustomer(@PathVariable("ID") int ac1) {
//        return bankServiceHib.findCustomer(ac1);
//    }
//
//    //    Ühe kliendi kõikide kontode küsimine
//    @GetMapping("/bank/customer/accounts/{ID}")
//    public List<Account> findCustomerAccounts(@PathVariable("ID") int ac1) {
//        return bankServiceHib.findCustomerAccounts(ac1);
//    }
//
//    //    Ühe konto info küsimine
//    @GetMapping("/bank/account/{ID}")
//    public List<Transaction> findAccount(@PathVariable("ID") int ac1) {
//        return bankServiceHib.findAccount(ac1);
//    }
//
//    //    Ühe transactioni info küsimine
//    @GetMapping("/bank/transaction/{ID}")
//    public Transaction findTrans(@PathVariable("ID") int ac1) {
//        return bankServiceHib.findTransaction(ac1);
//    }
//
//    //    kõikide klientide nimekiri
//    @GetMapping("/bank/customer")
//    public List<Customer> getCustomers() {
//        return bankServiceHib.getCustomerList();
//    }
//
//

}
