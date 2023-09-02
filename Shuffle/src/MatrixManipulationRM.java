import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Guy Kogan
 * @class 421 Algorithms
 * @date 7/26/2023
 * @teacher Jyh Haw Yeh
 * @description This class handles the logic behind dynamic programming top-down recursion with memorization approach of
 * Shuffle-ness: The problem description is as follows:
 * Suppose we are given three strings of characters: X = x1x2 . . . xm, Y = y1y2 . . . yn, and
 * Z = z1z2 . . . zn+m, where each zi may or may not be one of the characters in X and Y .
 * Z is said to be a shuffle of X and Y if Z can be formed by interspersing the characters from
 * X and Y in a way that maintains the left-to-right ordering of the characters from each string.
 */
public class MatrixManipulationRM {
    private int count = 1;
    private int xLen;
    private int yLen;
    private String X;
    private String Y;
    private String Z;
    int[][] matrix;


    /**
     * This function is a constructor for ShuffleRM
     * @param X - The first input String
     * @param Y - The second input String
     * @param Z - The string to be tested which is a shuffle of X and Y
     * @param debug - 0 for print summary 1 for print summary with matrix and write output to file
     * @throws IOException - Throws exception if the writer can't write to a file
     */
    public void ShuffleRM(String X, String Y, String Z, String debug) throws IOException {
        xLen = X.length();
        yLen = Y.length();
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        matrix = new int[xLen + 1][yLen + 1];
        for (int i = 0; i <= xLen; i++) {
            for (int j = 0; j <= yLen; j++) {
                matrix[i][j] = -1;
            }
        }
        ShuffleRMSet(debug);
    }

    /**
     * This function starts the recursive with memorization top-down approach
     * @param debug - 0 for print summary 1 for print summary with matrix and write output to file
     * @throws IOException - Throws exception if the writer can't write to a file
     */
    private void ShuffleRMSet(String debug) throws IOException {
        boolean result = ShuffleRMSolve(xLen, yLen);
        if (debug.equals("1")) {
            System.out.println(displayMatrix());
            FileWriter f = new FileWriter("ShuffleRM-Table.txt");
            try {
                f.write(displayMatrix());
                f.write("\n");
                if (result) {
                    f.write("yes\n");
                    f.write("Number of table references: " + count + "\n");
                } else {
                    f.write("no\n");
                    f.write("Number of table references: " + count + "\n");
                }
                f.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        if (result) {
            System.out.println("yes");
            System.out.println("Number of table references: " + count + "\n");
        } else {
            System.out.println("no");
            System.out.println("Number of table references: " + count + "\n");
        }
    }

    /**
     * This function recursively solves the Z string to check if Z is a shuffle of X and Y
     * @param a - the i-th index
     * @param b - the j-th index
     * @return - returns boolean 1 for true and 0 for false
     */
    private boolean ShuffleRMSolve(int a, int b) {
        if (matrix[a][b] == 0) {
            return false;
        }
        if (a == 0 && b == 0) {
            matrix[a][b] = 1;
            return true;
        } else if (a > 0 && b == 0) {
            if (X.charAt(a - 1) == Z.charAt(a + b - 1)) {
                count++;
                if (ShuffleRMSolve(a - 1, b)) {
                    matrix[a][b] = 1;
                    return true;
                }
            }
        } else if (a == 0 && b > 0) {
            if (Y.charAt(b - 1) == Z.charAt(a + b - 1)) {
                count++;
                if (ShuffleRMSolve(a, b - 1)) {
                    matrix[a][b] = 1;
                    return true;
                }
            }
        } else if (a > 0 && b > 0) {
            if (X.charAt(a - 1) == Z.charAt(a + b - 1)) {
                count++;
                if (ShuffleRMSolve(a - 1, b)) {
                    matrix[a][b] = 1;
                    return true;
                } else if (Y.charAt(b - 1) == Z.charAt(a + b - 1)) {
                    count++;
                    if (ShuffleRMSolve(a, b - 1)) {
                        matrix[a][b] = 1;
                        return true;
                    }
                }
            } else if (Y.charAt(b - 1) == Z.charAt(a + b - 1)) {
                count++;
                if (ShuffleRMSolve(a, b - 1)) {
                    matrix[a][b] = 1;
                    return true;
                } else if (X.charAt(a - 1) == Z.charAt(a + b - 1)) {
                    count++;
                    if (ShuffleRMSolve(a - 1, b)) {
                        matrix[a][b] = 1;
                        return true;
                    }
                }
            }
        }
        matrix[a][b] = 0;
        return false;
    }

    /**
     * This function displays the matrix
     */
    private String displayMatrix() {
        boolean needsPadded = false;
        StringBuilder matrixS = new StringBuilder();
        for (int i = 0; i < xLen; i++) {
            if (matrix[i][0] == -1) {
                needsPadded = true;
                break;
            }
        }
        for (int i = 0; i <= xLen; i++) {
            for (int j = 0; j <= yLen; j++) {
                if (j == 0 && !needsPadded) {
                    matrixS.append(String.format("%s  ", matrix[i][j]));
                } else {
                    matrixS.append(String.format("%2s  ", matrix[i][j]));
                }
            }
            if (i != xLen) {
                matrixS.append("\n");
            }
        }
        return matrixS.toString();
    }
}