package ee.bcs.valiit.tasks;

public class Lesson1MathUtil {
    private String test;


    // TODO tagasta a ja b väikseim väärtus
    public static int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    // TODO tagasta a ja b suurim väärtus
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    // TODO tagasta a absoluut arv
    public static int abs(int a) {
        if (a <0) {
            return a*-1;
        }
        return a;
    }

    // TODO tagasta true, kui a on paaris arv
    // tagasta false kui a on paaritu arv
    public static boolean isEven(int a) {
        return (a %2==0);
    }

    // TODO tagasta kolmest arvust kõige väiksem
    public static int min(int a, int b, int c) {
        return min(a, min(b,c));
    }

    // TODO tagasta kolmest arvust kõige suurem
    public static int max(int a, int b, int c) {
        return max(a, max(b,c));
    }
}
