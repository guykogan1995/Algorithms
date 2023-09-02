import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Guy Kogan
 * @class 421 Algorithms
 * @date 7/27/2023
 * @teacher Jyh Haw Yeh
 * @description This class handles the logic behind dynamic programming bottom-up approach of
 * Shuffle-ness: The problem description is as follows:
 * Suppose we are given three strings of characters: X = x1x2 . . . xm, Y = y1y2 . . . yn, and
 * Z = z1z2 . . . zn+m, where each zi may or may not be one of the characters in X and Y .
 * Z is said to be a shuffle of X and Y if Z can be formed by interspersing the characters from
 * X and Y in a way that maintains the left-to-right ordering of the characters from each string.
 */
public class MatrixManipulation {

    private int count = 0;

    /**
     * This function checks if two chars are the same
     * @param c1 - The first character to check in a string
     * @param c2 - The second character to check in another string
     * @return boolean if they match
     */
    boolean b(char c1, char c2) {
        return c1 == c2;
    }

    /**
     * This function is used to check if Z is a shuffle of X and Y using the bottom-up method
     * @param X - The first input string
     * @param Y - The second input string
     * @param Z - The string to be checked if it is a shuffle of X and Y
     * @param debug - 0 for print summary 1 for print summary with matrix and write output to file
     */
    void isShuffle(String X, String Y, String Z, String debug) {
        int m = X.length();
        int n = Y.length();
        boolean[][] table = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    table[i][j] = true;
                } else if (i > 0 && j == 0) {
                    boolean firstPart = b(X.charAt(i - 1), Z.charAt(i - 1));
                    if (!firstPart) {
                        table[i][0] = false;
                    } else {
                        count++;
                        table[i][0] = table[i-1][0];
                    }
                }
                else if (i == 0 && j > 0) {
                    boolean firstPart = b(Y.charAt(j - 1), Z.charAt(j - 1));
                    if (!firstPart) {
                        table[0][j] = false;
                    } else {
                        table[0][j] = table[0][j - 1];
                        count++;
                    }
                }
                else if (i > 0 && j > 0) {
                    boolean firstPart = b(X.charAt(i - 1), Z.charAt(i + j - 1));
                    if (!firstPart) {
                        ConditionalAlgo(Y, Z, table, i, j);
                    } else if (table[i - 1][j]) {
                        table[i][j] = table[i-1][j];
                        count++;
                    } else {
                        count++;
                        ConditionalAlgo(Y, Z, table, i, j);
                    }
                }
            }
        }
        boolean isShuffled = table[X.length()][Y.length()];
        if (debug.equals("1")) {
            printTable(X, Y, table);
            try {
                FileWriter f = new FileWriter("ShuffleDP-Table.txt");
                for (int i = 0; i <= X.length(); i++) {
                    for (int j = 0; j <= Y.length(); j++) {
                        f.write(table[i][j] ? "1  " : "0  ");
                    }
                    f.write("\n");
                }
                if (isShuffled) {
                    f.write("yes\n");
                    f.write("Number of table references: " + count);
                } else {
                    f.write("no\n");
                    f.write("Number of table references: " + count);
                }
                f.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        if (isShuffled) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
        System.out.println("Number of table references: " + count);
    }

    /**
     * This function creates the matrix
     * @param X - The first input string
     * @param Y - The second input string
     * @param table - The 2-d integer matrix to be printed to the user
     */
    private void printTable(String X, String Y, boolean[][] table) {
        for (int i = 0; i <= X.length(); i++) {
            for (int j = 0; j <= Y.length(); j++) {
                System.out.print(table[i][j] ? "1  " : "0  ");
            }
            System.out.println();
        }
    }

    /**
     * This function is used as a helper function to eliminate code redundancies in isShuffle()
     * @param Y - The second input string
     * @param Z - The string to be checked if it is a Shuffle of X and Y
     * @param table - The matrix to be printed to the user
     * @param i - The index(row) of matrix
     * @param j - The index(col) of matrix
     */
    private void ConditionalAlgo(String Y, String Z, boolean[][] table, int i, int j) {
        boolean secondPart = b(Y.charAt(j - 1), Z.charAt(i + j - 1));
        if (!secondPart) {
            table[i][j] = false;
        } else {
            table[i][j] = table[i][j-1];
            count++;
        }
    }
}
