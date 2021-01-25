package ee.bcs.valiit.tasks;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Padovan {

    public static BigInteger Get (long power) {

        power = (int)power+1;

        BigInteger[] padovan = new BigInteger[(int)power];

        String binary = Integer.toBinaryString((int)power);
        int binLength = binary.length();

        BigInteger[][] mainMatrix = {{new BigInteger("0"), new BigInteger("0"), new BigInteger("0")},
                {new BigInteger("0"), new BigInteger("0"), new BigInteger("0")},
                {new BigInteger("0"), new BigInteger("0"), new BigInteger("0")}};

        int count = 0;

        for (int x = binLength; x > 0; x--) {
            if (binary.substring(x - 1, x).equalsIgnoreCase("1")) {

                BigInteger[][] matrix = {{new BigInteger("0"), new BigInteger("1"), new BigInteger("0")},
                        {new BigInteger("0"), new BigInteger("0"), new BigInteger("1")},
                        {new BigInteger("1"), new BigInteger("1"), new BigInteger("0")}};

                BigInteger[][] c = {{new BigInteger("0"), new BigInteger("0"), new BigInteger("0")},
                        {new BigInteger("0"), new BigInteger("0"), new BigInteger("0")},
                        {new BigInteger("0"), new BigInteger("0"), new BigInteger("0")}};

                for (int x2 = 0; x2 < binLength-x; x2++) {
                    matrixCreator(c, matrix, matrix);
                    for (int i2 = 0; i2 < 3; i2++) {
                        for (int j2 = 0; j2 < 3; j2++) {
                            matrix[i2][j2] = c[i2][j2];
                        }
                    }
                }
                count++;

                if (count == 1) {

                    for (int i2 = 0; i2 < 3; i2++) {
                        for (int j2 = 0; j2 < 3; j2++) {
                            mainMatrix[i2][j2] = matrix[i2][j2];
                        }
                    }

                } else {

                    matrixCreator(c, matrix, mainMatrix);
                    for (int i2 = 0; i2 < 3; i2++) {
                        for (int j2 = 0; j2 < 3; j2++) {
                            mainMatrix[i2][j2] = c[i2][j2];
                        }
                    }
                }
            }

        }
        BigInteger answer = mainMatrix[2][1];
        return answer;
    }

    public static BigInteger[][] matrixCreator (BigInteger[][] c, BigInteger[][] a, BigInteger[][] b){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = new BigInteger("0");
                for (int k = 0; k < 3; k++) {
                    BigInteger num = (c[i][j]);
                    BigInteger num2 = a[i][k];
                    BigInteger num3 = b[k][j];
                    BigInteger num4 = (num2.multiply(num3));
                    c[i][j] = num.add(num4);
                }
            }
        }

        return c;
    }
}
