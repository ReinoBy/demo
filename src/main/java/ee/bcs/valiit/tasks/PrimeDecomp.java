package ee.bcs.valiit.tasks;

import java.util.HashMap;

public class PrimeDecomp {

    public static void main(String[] args) {

//        System.out.println(factors(933555431));

    }



    public static String factors(int n) {
        String string = "";
        int x = (int)Math.sqrt(n);
        if (isPrime(n)){
            string+="(" + n + ")";
        } else {
            for (int i = 2; i <= x; i++) {
                int count = 0;
                if (isPrime(i) && n % i == 0) {
                    while (n % i == 0) {
                        count++;
                        n /= i;
                    }
                    if (count > 1) {
                        string += "(" + i + "**" + count + ")";
                    } else if (count == 1) {
                        string += "(" + i + ")";
                    }
                }
                if(i==x&&n>1){
                    string+="(" + n + ")";
                }
            }
        }

        return string;

    }

    public static boolean isPrime(int x){
        for (int i= 2; i<=Math.sqrt(x); i++){
            if (x%i==0){
                return false;
            }
        }
        return true;
    }

}
