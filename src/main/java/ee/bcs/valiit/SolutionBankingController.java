package ee.bcs.valiit;

import ee.bcs.valiit.tasks.Account;
import ee.bcs.valiit.tasks.Employees;
import ee.bcs.valiit.tasks.Lesson1MathUtil;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class SolutionBankingController {

    static HashMap<String, Account> accountMap = new HashMap<>();

    int sequence = 10000000;
    String accountNr = "EE" + sequence;


    // uue konto loomine
    @PostMapping("/bank/")
    public void create(@RequestBody Account account) {
        accountMap.put(accountNr, account);
        sequence++;
    }

    // kõikide kontode list

    @GetMapping("/bank/")
    public HashMap<String ,Account> show() {
        return accountMap;
    }

//    ühe konto vaatamine
    @GetMapping("/bank/account")
    public Account showOne(@RequestBody Account account) {
        String x = account.getAccountNr();
        return accountMap.get(x);
    }


    //    deponeerimine
//    @PostMapping("/bank/dep/")
//    public void dep(@RequestBody Account account, HashMap accountMap){
//        BigDecimal summa = account.getTransaction();
//        BigDecimal saldo = accountMap.get(account).getSaldo();
//        saldo = saldo.add(summa);
//        account.setSaldo(saldo);
//    }



}



////http://localhost:8080/deposit/123455/85
//    @GetMapping("deposit/{accountNr}/{sum}")
//    public String deposit(@PathVariable("accountNr") String aVariable, @PathVariable("sum") BigDecimal bVariable){
//
//        if(kontrolliKontot(aVariable) && bVariable.compareTo(new BigDecimal("0")) >0){
//            BigDecimal saldo = accountBalanceMap.get(aVariable);
//            saldo = saldo.add(bVariable);
//            accountBalanceMap.put(aVariable,saldo);
//            return "Kontole " + aVariable + " kanti " + bVariable + " eurot. Konto saldo on " + accountBalanceMap.get(aVariable);
//        }
//        return "Sellise numbriga kontot ei ole olemas või on summa negatiivne";
//    }
//
////http://localhost:8080/withdraw/123456/185
//
//    @GetMapping("withdraw/{accountNr}/{sum}")
//    public String withdraw(@PathVariable("accountNr") String aVariable, @PathVariable("sum") BigDecimal bVariable){
//
//        if(kontrolliKontot(aVariable) && bVariable.compareTo(new BigDecimal("0")) >0){
//            BigDecimal saldo = accountBalanceMap.get(aVariable);
//            if(bVariable.compareTo(saldo) >0) {
//                return "Sul ei ole piisavalt raha kontol. Konto jääk on " + accountBalanceMap.get(aVariable);
//            } else{
//                saldo = saldo.subtract(bVariable);
//                accountBalanceMap.put(aVariable,saldo);
//                return "Võtsid välja " + bVariable + " eurot. Saldo on " + accountBalanceMap.get(aVariable);
//            }
//        } else {
//            return "Sellise numbriga kontot ei ole olemas või on summa negatiivne";
//        }
//    }
//
//
//    @GetMapping("transfer/{accountNra}/{accountNrb}/{sum}")
//    public String transfer(@PathVariable("accountNra") String aAccount,
//                           @PathVariable("accountNrb") String bAccount,
//                           @PathVariable("sum") BigDecimal bVariable){
//
//        if(kontrolliKontot(aAccount)&& kontrolliKontot(bAccount) && bVariable.compareTo(new BigDecimal("0")) >0){
//            BigDecimal saldo = accountBalanceMap.get(aAccount);
//            BigDecimal saldo2 = accountBalanceMap.get(bAccount);
//
//            if(bVariable.compareTo(saldo) >0) {
//                return "Sul ei ole piisavalt raha kontol. Konto jääk on " + accountBalanceMap.get(aAccount);
//            } else{
//                saldo = saldo.subtract(bVariable);
//                accountBalanceMap.put(aAccount,saldo);
//                saldo2 = saldo2.add(bVariable);
//                accountBalanceMap.put(bAccount,saldo2);
//
//                return "Kontolt " + aAccount + " tehti kontole  "+ bAccount + " ülekanne summas " + bVariable + " eurot";
//            }
//        } else {
//            return "Sellise numbriga kontot ei ole olemas või on summa negatiivne";
//        }
//    }



