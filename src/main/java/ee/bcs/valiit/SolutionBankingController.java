package ee.bcs.valiit;

import ee.bcs.valiit.tasks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class SolutionBankingController {

    @Autowired
    private BankService bankService;

    // uue customeri loomine
    @PostMapping("/bank/customer")
    public void createCustomer(@RequestBody Customer customer) {
       bankService.createCustomer(customer);
    }

    //    uue konto loomine
    @PostMapping("/bank/account")
    public void createAccount(@RequestBody Account account) {
        bankService.createAccount(account);
    }

    //    deponeerimine
    @PostMapping("/bank/dep/{account}")
    public void dep(@PathVariable("account") String ac1, @RequestBody Transaction transaction) {
        bankService.dep(ac1, transaction);
    }

    //    raha välja võtmine
    @PostMapping("/bank/with/{account}")
    public void with(@PathVariable("account") String ac1, @RequestBody Transaction transaction) {
        bankService.with(ac1, transaction);
    }

    //    ülekanne õhelt kontolt teisele
    @PostMapping("/bank/tran/{account}/{account2}/")
    public void tran(@PathVariable("account") String ac1, @PathVariable("account2") String ac2, @RequestBody Transaction transaction) {
        bankService.transfer(ac1, ac2, transaction);
    }


    //    ühe customeri isikuandmete kuvamine
    @GetMapping("/bank/customer/{id}")
    public Customer show(@PathVariable("id") int id) {
        return bankService.showCustomer(id);
    }


    //    kõikide customeride tagastamine
    @GetMapping("/bank/customer/")
    public List<Customer> showCusts() {
        return bankService.showAllCustomers();

    }


}
