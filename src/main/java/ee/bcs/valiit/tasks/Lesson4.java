package ee.bcs.valiit.tasks;

import liquibase.pro.packaged.B;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;

public class Lesson4 {
    // Store account nr as a key and account balance as value
    static HashMap<String, BigDecimal> accountBalanceMap = new HashMap<>();


    public static void main(String[] args) {
        String viga = "Sellise numbriga kontot ei ole";
        String sis = "Sisesta konto number: ";
        Scanner scanner = new Scanner(System.in);
        printInstructions();
        while (true){
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")){
                break;
            } else if (line.equalsIgnoreCase( "0")){
                printInstructions();
            } else if (line.equalsIgnoreCase( "1")){
                System.out.println(sis);
                String kontoNr = scanner.nextLine();
                createAccount(kontoNr);

            }else if (line.equalsIgnoreCase( "2")){
                System.out.println(sis);
                String kontoNr = scanner.nextLine();
                if (kontrolliKontot(kontoNr)){
                    getBalance(kontoNr);
                } else {
                    System.out.println(viga);
                }


            }else if (line.equalsIgnoreCase( "3")){
                System.out.println(sis);
                String kontoNr = scanner.nextLine();
                if (kontrolliKontot(kontoNr)){
                    depositMoney(kontoNr);
                } else {
                    System.out.println(viga);
                }

            }else if (line.equalsIgnoreCase( "4")){
                System.out.println(sis);
                String kontoNr = scanner.nextLine();
                if (kontrolliKontot(kontoNr)){
                    withdrawMoney(kontoNr);
                } else {
                    System.out.println(viga);
                }

            }else if (line.equalsIgnoreCase( "5")){
                System.out.println(sis);
                String kontoNr = scanner.nextLine();
                if (kontrolliKontot(kontoNr)){
                    transfer(kontoNr);
                } else {
                    System.out.println(viga);
                }

            }
            // TODO 1
            // Add command: "createAccount ${accountNr}"
            // this has to store accountNr with 0 balance

            // TODO 2
            // Add command: "getBalance ${accountNr}"
            // this has to display account balance of specific acount
            // TODO 3
            // Add command: "depositMoney ${accountNr} ${amount}
            // this has to add specified amount of money to account
            // You have to check that amount is positive number
            // TODO 4
            // Add command: "withdrawMoney ${accountNr} ${amount}
            // This has to remove specified amount of money from account
            // You have to check that amount is positive number
            // You may not allow this transaction if account balance would become negative
            // TODO 5
            // Add command: "transfer ${fromAccount} ${toAccount} ${amount}
            // This has to remove specified amount from fromAccount and add it to toAccount
            // Your application needs to check that toAccount is positive
            // And from account has enough money to do that transaction
            else {
                System.out.println("Unknown command");
            }
        }
    }

    public static boolean kontrolliKontot(String accountNr){
        return accountBalanceMap.containsKey(accountNr);
    }

    public static void printInstructions(){
        System.out.println("0 - Kasutusjuhend");
        System.out.println("1 - Loo konto");
        System.out.println("2 - Näita konto saldot");
        System.out.println("3 - Raha kontole");
        System.out.println("4 - Raha väljavõtt");
        System.out.println("5 - Ülekanne teisele kontole");
    }

    public static void createAccount(String accountNr){
        accountBalanceMap.put(accountNr, new BigDecimal(0));
        System.out.println("Loodi konto numbriga: " + accountNr + " algsaldo 0");
    }

    public static void getBalance(String accountNr){
        System.out.println("Konto " + accountNr + " saldo on " + accountBalanceMap.get(accountNr));
    }

    public static void withdrawMoney(String accountNr ){
        BigDecimal saldo = accountBalanceMap.get(accountNr);
        System.out.println("Saldo on " + accountBalanceMap.get(accountNr));
        System.out.println("Sisesta soovitav raha summa");
        Scanner number = new Scanner(System.in);
        BigDecimal num = new BigDecimal(number.nextInt());
        if(accountBalanceMap.get(accountNr).compareTo(num) >0){
            saldo = saldo.subtract(num);
            accountBalanceMap.put(accountNr,saldo);
            System.out.println("Võtsid välja " + num + " eurot. Saldo on " + accountBalanceMap.get(accountNr));
        } else {
            System.out.println("Sul ei ole piisavalt raha kontol. Saldo jääk on " + accountBalanceMap.get(accountNr));
        }

    }

    public static void transfer(String accountNr){
        BigDecimal saldo = accountBalanceMap.get(accountNr);
        System.out.println("Sisesta konto number, millele soovid raha kanda ");
        Scanner nimi = new Scanner(System.in);
        String name = nimi.nextLine();
        BigDecimal saldo2 = accountBalanceMap.get(name);
        if(accountBalanceMap.containsKey(name)){
            System.out.println("Sisesta summa ");
            Scanner number = new Scanner(System.in);
            BigDecimal num = new BigDecimal(number.nextInt());
            if(accountBalanceMap.get(accountNr).compareTo(num) >0){
                saldo = saldo.subtract(num);
                accountBalanceMap.put(accountNr,saldo);
                saldo2 = saldo2.add(num);
                accountBalanceMap.put(name,saldo2);
                System.out.println("Kontole " + name + " kanti " + num + " eurot");
            } else {
                System.out.println("Sul ei ole piisavalt raha kontol ülekande tegemiseks. Saldo jääk on " + accountBalanceMap.get(accountNr));
            }


        }

    }

    public static void depositMoney(String accountNr){
        BigDecimal saldo = accountBalanceMap.get(accountNr);
        System.out.println("Sisesta deponeeritav raha summa");
        Scanner number = new Scanner(System.in);
        BigDecimal num = new BigDecimal(number.nextInt());
        saldo = saldo.add(num);
        accountBalanceMap.put(accountNr,saldo);
        System.out.println("Kontole " + accountNr + " kanti " + num + " eurot");
        System.out.println("Konto saldo on " + accountBalanceMap.get(accountNr));

    }


}
