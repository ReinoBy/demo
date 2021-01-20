package ee.bcs.valiit.tasks;



public class KPrimes {

//    A natural number is called k-prime if it has exactly k prime factors, counted with multiplicity.
//    Complete the function count_Kprimes (or countKprimes, count-K-primes, kPrimes) which is given
//    parameters k, start, end (or nd) and returns an array (or a list or a string depending on the
//    language - see "Solution" and "Sample Tests") of the k-primes between start (inclusive) and end (inclusive)

    public static void main(String[] args) {
//        countKprimes(2,0,100);
    }
    public static long[] countKprimes(int k, long start, long end) {
        // your code
        long len = end-start;
        long[] list = new long[5];
        int count = 0;
        int countI = 0;
        int inCount =0;
        int inI = 0;
        int x = (int)Math.sqrt(len);
        for ( int i = 4; i < len; i++){
            count = 0;
            inI = i;

            for (int j = 2; j < i; j++) {
                    if (isPrime(j) && i % j == 0) {
                        while (inI % j == 0) {
                            inI /= j;
                            inCount++;
                        }
                        count++;
                    }
                    if(count>2 || inCount>2){
                        break;
                    }
                }
            if (inCount==2&&count==1 || inCount==1&&count==2){
                list[countI]=i;
                countI++;
            }

        }


        return list;
    }
//    public static int puzzle(int s) {
//        // your code
//
//    }

    public static boolean isPrime(int x){
        for (int i= 2; i<=Math.sqrt(x); i++){
            if (x%i==0){
                return false;
            }
        }
        return true;
    }
}
