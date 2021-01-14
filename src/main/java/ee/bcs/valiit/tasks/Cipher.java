package ee.bcs.valiit.tasks;

import java.util.*;

public class Cipher {

        public static void main(String[] args) {


            System.out.println(encode("...transit gloria mundi[]"));
            System.out.println(decode(".ril.ian ag[]dtsuimotr n."));

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


    }


