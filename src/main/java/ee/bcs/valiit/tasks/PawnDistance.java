package ee.bcs.valiit.tasks;

public class PawnDistance {
    private String color;
    private long distance;

    public static void main(String[] args) {
//        System.out.println(redKnight(0, 7));
    }

    public PawnDistance(String s, long d) {
        color = s;
        distance = d;
    }

    public static PawnDistance redKnight(int knight, long pawn) {
        String color0="";
        String color1 = "";
        String ret = "";
        if ( knight==0){
            color0="White";
            color1 = "Black";
        } else {
            color1 = "White";
            color0 = "Black";
        }
        long dist = pawn*2;
            if (pawn%2!=0){
                ret = color1;
            } else {
                ret=color0;
            }


        return new PawnDistance(ret, dist);
    }

}
