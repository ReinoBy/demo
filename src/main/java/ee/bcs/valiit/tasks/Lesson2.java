package ee.bcs.valiit.tasks;

import liquibase.pro.packaged.S;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Lesson2 {

    public static void main(String[] args) {

        exercise5(27, 27);

    }

    // TODO loo 10 elemendile täisarvude massiv
    // TODO loe sisse konsoolist 10 täisarvu
    // TODO trüki arvud välja vastupidises järiekorras
    public static void exercise1() {
        int[] täisarvud = new int[10];
        Scanner scanner = new Scanner(System.in);
        for ( int i = 0; i<täisarvud.length; i++){
            täisarvud[i] = scanner.nextInt();
            scanner.nextLine();
        }
        for (int i=täisarvud.length; i>0; i--){
            System.out.println(täisarvud[i-1]);
        }

    }

    // TODO prindi välja x esimest paaris arvu
    // Näide:
    // Sisend 5
    // Väljund 2 4 6 8 10
    public static void exercise2(int x) {
        int number= 2;
        for (int i = 0; i<x; i++){
            System.out.println(number);
                number+=2;
            }
        }


    // TODO trüki välja korrutustabel mis on x ühikut lai ja y ühikut kõrge
    // TODO näiteks x = 3 y = 3
    // TODO väljund
    // 1 2 3
    // 2 4 6
    // 3 6 9
    // TODO 1 trüki korrutustabeli esimene rida (numbrid 1 - x)
    // TODO 2 lisa + " " print funktsiooni ja kasuta print mitte println
    // TODO 3 trüki seda sama rida y korda
    // TODO 4 Kuskile võiks kirjutada System.out.println(),
    //  et saada taebli kuju
    // TODO 5 võrdle ridu. Kas on mingi seaduspärasus ridade vahel,
    // mis on ja mis võiks olla. Äkki tuleb mõni idee

    public static void exercise3(int x, int y) {
        for (int i = 1; i< x+1; i++){
            for (int j = 1; j< y+1; j++){
                System.out.print(i*j + " ");
            }
            System.out.println("");
        }

    }

    // TODO
    // Fibonacci jada on fib(n) = fib(n-1) + fib(n-2);
    // 0, 1, 1, 2, 3, 5, 8, 13, 21
    // Tagasta fibonacci jada n element
    public static int exercise4(int n) {
        int number = 0;
        int järgmine = 1;
        int vahe=0;

        if (n<3) {
            System.out.println(n-1);
            return n-1;
        }
             for ( int i = 0; i<=n-3; i++){
                vahe = number + järgmine;
                number=järgmine;
                järgmine= vahe;
            }
        System.out.println(vahe);
        return vahe;
    }

    public static void exercise5( int x, int y) {
        // https://onlinejudge.org/index.php?option=onlinejudge&Itemid=8&page=show_problem&problem=36
        // TODO 1 (tee alamfunktsioon) mis leiab 3n+1 sequenci pikkuse
        // kui on paaris / 2 kui on paaritu *3+1
        // TODO 2 tee tsükkel mis leiab i -> j kõige suurema tsükkli pikkuse
            int suurimJada=0;
            for ( int i = x; i<=y ; i++){
                int result = alam(i);
                if(result>suurimJada){
                    suurimJada=alam(i);
                }
            }


        System.out.println("Numbrite "+x+" ja " + y + " maksimaalne tsükli pikkus on " + suurimJada);
    }

    public static int alam(int x){
        int count=1;
        for ( int i=0; i<1000;i++){
            if (x == 1){
                break;
            } else if (x%2!=0){
                x= 3*x +1;
                count ++;
            }
            x = x/2;
            count ++;
        }
        return count;
    }

}
