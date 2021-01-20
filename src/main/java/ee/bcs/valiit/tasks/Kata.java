package ee.bcs.valiit.tasks;

import java.math.BigInteger;

public class Kata {
//    find highest scoring word.

    public static void main(String[] args) {
//        high("man i need a taxi up to ubud");
    }
    public static String high(String s) {

        String[] words = s.split(" ");
        int size = words.length;
        String aWord = "";
        int wordLen = 0;
        char wordChar = '0';
        int sum = 0;
        int maxSum = 0;
        String maxWord = "";
        for (int i = 0; i<size; i++){
            sum=0;
            aWord = words[i];
            wordLen = aWord.length();
            for ( int j=0; j<wordLen ; j++){
                wordChar = aWord.charAt(j);
                sum+=(wordChar-96);
            }
            if (sum>maxSum){
                maxSum=sum;
                maxWord=aWord;
            }
        }

        return maxWord;
    }

}
