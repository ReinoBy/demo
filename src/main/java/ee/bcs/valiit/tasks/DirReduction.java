package ee.bcs.valiit.tasks;

import java.util.ArrayList;
import java.util.List;


public class DirReduction {

    public static void main(String[] args) {
        dirReduc(new String[]{"NORTH", "SOUTH", "SOUTH", "EAST", "NORTH", "WEST", "SOUTH"});
    }
    public static String[] dirReduc(String[] arr) {
        // Your code here.
        int len = arr.length;
        List<Integer> fin = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            switch (arr[i]) {
                case "NORTH":
                    fin.add(0);
                    break;
                case "SOUTH":
                    fin.add(2);
                    break;
                case "WEST":
                    fin.add(1);
                    break;
                case "EAST":
                    fin.add(3);
                    break;
            }
        }
        for (int i = 0; i < len - 1; i++) {
            if (len > 1 && (fin.get(i) + fin.get(i + 1)) % 2 == 0 && fin.get(i) != fin.get(i+1)) {
                fin.remove(i);
                fin.remove(i);
                len -= 2;
                i = -1;
            }
        }
        int y= fin.size();
        String[] directions = new String[y];
        for (int i = 0; i<y; i++) {
            switch (fin.get(i)) {
                case 0:
                    directions[i] = "NORTH";
                    break;
                case 1:
                    directions[i] = "WEST";
                    break;
                case 2:
                    directions[i] = "SOUTH";
                    break;
                case 3:
                    directions[i] = "EAST";
                    break;

            }

        }
        return directions;

    }
}
