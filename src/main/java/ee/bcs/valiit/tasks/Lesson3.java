package ee.bcs.valiit.tasks;

import java.util.Arrays;
import java.util.List;

public class Lesson3 {
    public static void main(String[] args) {


        System.out.println(reverseString("mina olen"));

    }

    public static int sum(int[] x){
        // Todo liida kokku kõik numbrid massivis x
        int summa = 0;
        for (int i : x) {
            summa += i;
        }
        return summa;
    }

    public static int factorial(int x) {
        // TODO tagasta x faktoriaal.
        // Näiteks
        // x = 5
        // return 5*4*3*2*1 = 120
        int kordus= 1;
        for ( int i = 1; i<x+1; i++){
            kordus *= i;
        }

        return kordus;
    }

    public static int[] sort(int[] a) {
        // TODO sorteeri massiiv suuruse järgi.
        // TODO kasuta tsükleid, ära kasuta ühtegi olemasolevat sort funktsiooni
        int count = 0;
        for (int i= 1; i<a.length; i++){
            if (a[i]>a[i-1]){
                int vahepealne = a[i-1];
                a[i-1] = a[i];
                a[i]= vahepealne;
                i=0;
                count =0;
            }
            count ++;
            if (count == a.length){
                break;
            }
        }
        return a;
    }


    public static String reverseString(String a) {
        // TODO tagasta string tagurpidi
        StringBuilder uusString = new StringBuilder();
        int len = a.length();
        for (int i = len; i >0; i--){
            String x = a.substring(i-1, i);
            uusString.append(x);
        }
        String print = uusString.toString();
        return print;
    }

    public static boolean isPrime(int x){
        // TODO tagasta kas sisestatud arv on primaar arv (jagub ainult 1 ja iseendaga)

        for (int i= 2; i<x; i++){
            if (x%i==0){
                return false;
            }
        }
        return true;
    }
}
