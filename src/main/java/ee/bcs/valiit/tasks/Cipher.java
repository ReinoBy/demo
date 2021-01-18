package ee.bcs.valiit.tasks;


import java.math.BigDecimal;
import java.util.*;

public class Cipher {

        public static void main(String[] args) {

            System.out.println(encode("12345678901234567890123456789012345678901234567890123"));
            System.out.println(decode("15937152893715064893 04004    8460    28262  1628273951647395173"));

        }
        //    lisaülesanne Interlaced Spiral Cipher codewars - https://www.codewars.com/kata/5a24a35a837545ab04001614/train/java


        public static String encode(String s) {
//          leian stringi pikkuse ja kui selle ruutjuur ei ole täisarv, siis lisan juurde tühikuid
            double pikkus = s.length();
            if (Math.sqrt(pikkus) % 1 != 0) {
                while (Math.sqrt(pikkus) % 1 !=0){
                    pikkus++;
                }
                double vahe = pikkus - s.length();
                for (int i = 0; i<vahe; i++){
                    s+=" ";
                }
            }
//            leian maatriksi küljepikkuse ja teen tühja maatriksi.
            int sqrtS = (int)Math.ceil(Math.sqrt(pikkus));
            String[][] tabel = new String[sqrtS][sqrtS];
            if (sqrtS >1){
                int count = 0;

//              sisetsüklite arv - kui välimine ring on täidetud, siis mitu sisemist ringi on vaja täita
                int siseTsuklid = 0;
                if(sqrtS%2==0){
                    siseTsuklid = (sqrtS+1)/2;
                } else {
                    siseTsuklid = sqrtS/2;
                }

//              täidan maatriksi tsüklite kaupa, väljast sissepoole
                for ( int i = 0; i< siseTsuklid; i++){
                    cipher(tabel, i, sqrtS, count, s);
//                    count - mitu karakterit on juba tabelisse pandud
                    count += (sqrtS)*4-4;
//                    külgede arv väheneb kahe võrra
                    sqrtS-=2;
                }
            }
//            loon maatriksist ühe pika stringi
            String encoded = "";
            for (int i = 0; i< tabel.length; i++){
                for (int j= 0; j< tabel.length; j++){
                    if (tabel[i][j]==null){
                        encoded += " ";
                    } else {
                        encoded += tabel[i][j];
                    }
                }
            }

            return encoded;
        }

//          stringi sifreerimise meetod - spiraalselt sissepoole kirjutamine, int t - sisetsükli nr.
        public static String [][] cipher( String[][] tabel, int t, int kuljePikkus, int count, String s){
            for ( int i = 0; i< kuljePikkus-1; i++){
                int pikkus = s.length();
                int kulg = kuljePikkus-1;
                int k = 0;
                int j = i;
//                täidan maatriksi ühe välja kaupa - asukoht on funktsioon küljepikkusest, sisetsükli nr ja tsükli nr.
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
//                kui 9-sest maatriksist on ära täidetud kõik muu, siis sisemine ruut täidetakse siin.
                if(count+1 ==pikkus){
                    if (count ==8){
                        k = 1;
                    } else {
                        k = kulg+1;
                    }
                    j = k;
                    tabel[k][j] = s.substring(count,count+1);
                    count++;
                }
//                kui maatriksisse kirjutatud tähtede arv on võrdne stringi pikkusega, siis lõpetab.
                if (count==pikkus){
                    break;
                }
            }
            return tabel;
        }

//        ette antud stringi dekodeerimine
        public static String decode(String s) {
//            lähtestame väärtused
            String message = "";
            String returnMessage = "";

//          leian stringi pikkuse ja kui selle ruutjuur ei ole täisarv, siis lisan juurde tühikuid
            double pikkus = s.length();
            if (Math.sqrt(pikkus) % 1 != 0) {
                while (Math.sqrt(pikkus) % 1 !=0){
                    pikkus++;
                }
                double vahe = pikkus - s.length();
                for (int i = 0; i<vahe; i++){
                    s+=" ";
                }
            }
//            leian maatriksi küljepikkuse ja täidan maatriksi.
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
//                lähtestan näitajad, määran sisetsüklite arvu
                int count = 0;
                int siseTsuklid = 0;
                if (sqrtS % 2 == 0) {
                    siseTsuklid = (sqrtS + 1) / 2;
                } else {
                    siseTsuklid = sqrtS / 2;
                }

//                dešifreerin maatriksi
                for ( int i = 0; i< siseTsuklid; i++){
                    returnMessage+=deCipher(coded, i, sqrtS, count, pikkus, message);
                    count += (sqrtS)*4-4;
                    sqrtS-=2;
                }
            }
            return returnMessage;
        }

//        etteantud stringi dekodeerimise masin - spiraalselt sissepoole kirjutamine, int t - sisetsükli nr.
        public static String deCipher( String[][] tabel, int t, int kuljePikkus, int count, double pikkus, String message){
            String returnMessage = message;
            for ( int i = 0; i< kuljePikkus-1; i++){
                int kulg = kuljePikkus-1;
                int k = 0;
                int j = i;
//                kirjutan maatriksist ühe välja kaupa - asukoht on funktsioon küljepikkusest, sisetsükli nr ja tsükli nr.
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
//                kui 9-sest maatriksist on ära täidetud kõik muu, siis sisemine ruut täidetakse siin.
                if(count+1 ==pikkus){
                    if (count ==8){
                        k = 1;
                    } else {
                        k = kulg;
                    }
                    j = k;
                    returnMessage+=tabel[k][j];
                    count++;
                }
//                lõpetan tsükli kui välja kirjutatud tähtede arv = stringis antud arvule
                if (count==pikkus){
                    break;
                }
            }
            return returnMessage;
        }


    }


