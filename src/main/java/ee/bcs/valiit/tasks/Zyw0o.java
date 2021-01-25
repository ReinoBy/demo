package ee.bcs.valiit.tasks;

public class Zyw0o {

    public static void main(String[] args) {
        sumOfDifferences(new int[] {1, 2, 10});
    }
        public static int sumOfDifferences(int[] arr) {
            int len = arr.length;
            if(len<2){
                return 0;
            }else {
                int vahe = 0;
                for (int i = 0; i < len - 1; i++) {
                    if (arr[i + 1] > arr[i]) {
                        vahe = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = vahe;
                        i = -1;
                    }
                }

                return arr[0] - arr[len - 1];
            }
        }
}
