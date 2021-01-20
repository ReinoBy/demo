package ee.bcs.valiit.tasks;


import java.util.*;

public class Lesson3Hard {

    public static void main(String[] args) {

//       System.out.println(findAndPrintDivisors(7775460));

    }

    public static int evenFibonacci(int n) {
        int number = 0;
        int järgmine = 1;
        int vahe = 0;
        int sum = 0;

        if (n < 2) {
            System.out.println(n);
            return n;
        }
        for (int i = 0; i <= n - 2; i++) {
            vahe = number + järgmine;
            number = järgmine;
            järgmine = vahe;
            if (vahe % 2 == 0) {
                sum += vahe;
            }
        }
        System.out.println(sum);
        return sum;
    }

    public static void randomGame() {
        // TODO kirjuta mäng mis võtab suvalise numbri 0-100, mille kasutaja peab ära arvama
        // iga kord pärast kasutaja sisestatud täis arvu peab programm ütlema kas number oli suurem või väiksem
        // ja kasutaja peab saama uuesti arvata
        // numbri ära aramise korral peab programm välja trükkima mitu katset läks numbri ära arvamiseks

        Random random = new Random();
        int i = random.nextInt(100);
        Scanner scanner = new Scanner(System.in);
        int j = 0;
        int count = 0;
        while (true) {
            System.out.println("Sisesta arv: ");
            j = scanner.nextInt();
            scanner.nextLine();
            count++;
            if (i > j) {
                System.out.println("Number on suurem");
            } else if (i < j) {
                System.out.println("Number on väiksem");
            } else {
                break;
            }
        }
        System.out.println("Õige vastus oli " + i + " ja sul läks " + count + " katset");

    }



    public static String morseCode(String text) {
        // TODO kirjuta programm, mis tagastab sisestatud teksti morse koodis (https://en.wikipedia.org/wiki/Morse_code)
        // Kasuta sümboleid . ja -

//        Map<String , String > morse = new HashMap<>();
//
//        morse.put("a", ".- ");
//        morse.put("b", "-... ");
//        morse.put("c", "-.-. ");
//        morse.put("d", "-.. ");
//        morse.put("e", ". ");
//        morse.put("f", "..-. ");
//
//        int len = text.length();
//        String morseSõna="";
//
//        for (int i=0; i<len; i++ ) {
//            morseSõna += morse.get(text.substring(i, i+1));
//        }
//
//                System.out.println(morseSõna);
//        return morseSõna;
//        int len = text.length();
//
//        for (int i=0; i<len; i++ ){
//            switch (text.substring(i,i+1)){
//                case "a":
//                    System.out.print(".- ");
//                    break;
//                case "b":
//                    System.out.print("-... ");
//                    break;
//                case "c":
//                    System.out.print("-.-. ");
//                    break;
//                case "d":
//                    System.out.print("-.. ");
//                    break;
//                case "e":
//                    System.out.print(". ");
//                    break;
//                case "f":
//                    System.out.print("..-. ");
//                    break;
//                default:
//                    System.out.print( " Seda tähte ei leidnud");
//                    break;
//            }
        Map<Character, String> map = new HashMap<>();
        map.put('a', ".-");
        map.put('d', "-..");
        map.put('i', "..");
        map.put('k', "-.-");
        char[] letters = text.toCharArray();
        String result = "";
        for (int j = 0; j < text.length(); j++) {
            result += map.get((letters[j]));
        }
        return result;

    }
//    lisaülesanne codewars https://www.codewars.com/kata/554ca54ffa7d91b236000023

    public static int[] deleteNth(int[] list, int n) {
        if ( n== 0 ){
            return new int[0];
        } else {
            Integer[] list2 = Arrays.stream(list).boxed().toArray(Integer[]::new);
            List<Integer> arrayList = new ArrayList<>(Arrays.asList(list2));
            int count;
            for (int i = 0; i < arrayList.size(); i++) {
                Integer x = arrayList.get(i);
                count = 1;
                int j = i + 1;
                while (j < arrayList.size()) {
                    Integer y = arrayList.get(j);
                    if (x.equals(y)) {
                        count++;
                        if (count > n) {
                            arrayList.remove(j);
                        } else {
                            j++;
                        }
                    } else {
                        j++;
                    }
                }

            }
            int[] array = arrayList.stream().mapToInt(i -> i).toArray();
            return array;
        }
    }


//    lisaülesanne codewars https://www.codewars.com/kata/59ccf051dcc4050f7800008f/train/java

    public static String buddyPairs(long first, long second) {
        for (long x = first; x < second; x++) {
            long sum1;
            sum1 = findAndPrintDivisors(x);
            long sum2 = sum1 - 1;
            long sum3;
            sum3 = findAndPrintDivisors(sum2);
            if ((sum3 - 1) == x && (sum3-1)<sum2) {
                return "(" + (sum3 - 1) + " " + sum2 + ")";
            }
        }
        return "Nothing";
    }

    public static long factorSum(long y) {
        long sum = 0;
        for (long i = 1; i < y; i++) {
            if (y % i == 0) {
                sum = sum + i;
            }

        }

        return sum;
    }


    public static long findAndPrintDivisors(long n){
        long maxD = (long)Math.sqrt(n);
        long sum=0;
        for (int i=0; i<=maxD; i++){
            if (n%i==0) {
                if (n/i == i) {
                    sum += i;
                } else {
                    sum+=i;
                    sum = sum+(n/i);
                }
            }

        }
        sum-=n;
        return sum;
    }

//    lisaülesanne camelCase codewars

    public static String toCamelCase(String s){
        String[] i = s.replace("_","-").split("-");
        int len = i.length;
        String loplik = "";
        loplik+=(i[0]);
        for (int j = 1; j<len; j++ ){
            loplik+=(i[j].substring(0,1).toUpperCase() + i[j].substring(1).toLowerCase());
        }
        return loplik;

    }

    //    lisaülesanne camelCase codewars - lahendamata

    public static String toJadenCase(String phrase){
        String[] i = phrase.split(" ");
        int len = i.length;
        String loplik = "";
        for (int j = 0; j<len; j++ ){
            loplik+=(i[j].substring(0,1).toUpperCase() + i[j].substring(1).toLowerCase() + " ");
        }
        return loplik;

    }

    //    lisaülesanne Interlaced Spiral Cipher codewars - https://www.codewars.com/kata/5a24a35a837545ab04001614/train/java


        public static String encode(String s) {

            double pikkus = s.length();
            int sqrtS = (int)Math.ceil(Math.sqrt(pikkus));
            String[][] tabel = new String[sqrtS][sqrtS];
            if (sqrtS >1){
                int count = 0;
                int tsCount = 0;
                int siseTsuklid = 0;
                if(sqrtS%2==0){
                    siseTsuklid = (sqrtS+1)/2;
                } else {
                    siseTsuklid = sqrtS/2;
                }


                for ( int i = 0; i< siseTsuklid; i++){
                    cipher(tabel, i, sqrtS, count, s);
                    count += (sqrtS)*4-4;
                    sqrtS-=2;
                }
            }
            String decoded = "";
            for (int i = 0; i< tabel.length; i++){
                for (int j= 0; j< tabel.length; j++){
                    if (tabel[i][j]==null){
                        decoded += " ";
                    } else {
                        decoded += tabel[i][j];
                    }
                }
            }
            return decoded;
        }


        public static String [][] cipher( String[][] tabel, int t, int kuljePikkus, int count, String s){
            for ( int i = 0; i< kuljePikkus-1; i++){
                int pikkus = s.length();
                int kulg = kuljePikkus-1;
                int k = 0;
                int j = i;
                tabel[k+t][j+t] = s.substring(count,count+1);
                count++;
                j=kulg;
                k+=i;
                tabel[k+t][j+t] = s.substring(count,count+1);
                count++;
                j-=i;
                k=kulg;
                tabel[k+t][j+t] = s.substring(count,count+1);
                count++;
                j=0;
                k=(kulg-i);
                tabel[k+t][j+t] = s.substring(count,count+1);
                count++;
                if(count+1 ==pikkus){
                    k = kulg;
                    j = k;
                    tabel[k][j] = s.substring(count,count+1);
                    count++;
                }
                if (count==pikkus){
                    break;
                }
            }
            return tabel;
        }

        public static String decode(String s) {
            String message = "";
            String returnMessage = "";
            double pikkus = s.length();
            int sqrtS = (int)Math.ceil(Math.sqrt(pikkus));
            if (sqrtS >1) {
                String[][] coded = new String[sqrtS][sqrtS];
                int x=0;
                for (int i = 0; i< sqrtS; i++){
                    for (int j= 0; j< sqrtS; j++){
                        coded [i][j]= s.substring(x,x+1);
                        x++;
                    }
                }
                int count = 0;
                int tsCount = 0;
                int siseTsuklid = 0;
                if (sqrtS % 2 == 0) {
                    siseTsuklid = (sqrtS + 1) / 2;
                } else {
                    siseTsuklid = sqrtS / 2;
                }
                for ( int i = 0; i< siseTsuklid; i++){
                    returnMessage+=deCipher(coded, i, sqrtS, count, pikkus, message);
                    count += (sqrtS)*4-4;
                    sqrtS-=2;
                }
            }
        return returnMessage;
        }

    public static String deCipher( String[][] tabel, int t, int kuljePikkus, int count, double pikkus, String message){
        String returnMessage = message;
        for ( int i = 0; i< kuljePikkus-1; i++){
            int kulg = kuljePikkus-1;
            int k = 0;
            int j = i;
            returnMessage+=tabel[k+t][j+t];
            count++;
            j=kulg;
            k+=i;
            returnMessage+=tabel[k+t][j+t];
            count++;
            j-=i;
            k=kulg;
            returnMessage+=tabel[k+t][j+t];
            count++;
            j=0;
            k=(kulg-i);
            returnMessage+=tabel[k+t][j+t];
            count++;
            if(count+1 ==pikkus){
                k = kulg;
                j = k;
                returnMessage+=tabel[k][j];
                count++;
            }
            if (count==pikkus){
                break;
            }
        }
        return returnMessage;
    }




//        public static

}
