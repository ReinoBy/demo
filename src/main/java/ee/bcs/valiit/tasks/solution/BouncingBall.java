package ee.bcs.valiit.tasks.solution;

public class BouncingBall {

    public static void main(String[] args) {


    }

    public static int bouncingBall(double h, double bounce, double window) {

        if (h<=0 || 0>=bounce || 1<=bounce || window>h){
            return -1;
        }
        int count = -1;
        while (h-window>0){
            h*=bounce;
            count +=2;
        }

        return count;
    }

//    codewars https://www.codewars.com/kata/525f50e3b73515a6db000b83/train/java

    public static String createPhoneNumber(int[] numbers) {

        return ("\"" + "(" + numbers[0]+numbers[1] + numbers[2] + ") " + numbers[3]+numbers[4] + numbers[5] +"-"+numbers[6]+numbers[7]+numbers[8]+numbers[9]+"\"");
    }


}
