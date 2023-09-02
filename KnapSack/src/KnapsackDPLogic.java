import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Guy Kogan
 * @class 421 Algorithms
 * @date 8/2/2023
 * @teacher Jyh Haw Yeh
 * @description This class is the main logic behind dynamic programming bottom-up approach to solve:
 * A thief robbing a store finds n items; the ith item is worth vi dollars and weights wi pounds,
 * where vi and wi are integers. He wants to take as valuable a load as possible, but he can carry
 * at most W pounds in his knapsack for some integer W . Which items should he take? (This
 * is called the 0-1 knapsack problem because each item must either be taken or left behind;
 * the thief can not take a fraction amount of an item or take an item more than once.)
 **/
public class KnapsackDPLogic {
    private int count = 0;
    private final int n;
    private int w;
    private final String debugLevel;
    private final ArrayList<Integer> weightsA;
    private final ArrayList<Integer> valuesA;
    private final ArrayList<Integer> takeT = new ArrayList<>();

    /**
     * This function is the constructor for the KnapsackDPLogic class
     * @param n - initilized the number of items
     * @param w - Initilizes the maximum weight of the bag
     * @param weightsArray - The array to hold the weights of the items
     * @param valuesArray - The array to hold the values of the items
     * @param debugLevel - debug level either 0 or 1 to display outputs to the user
     * @throws IOException - In case file cannot be created
     */
    public KnapsackDPLogic(int n, int w, ArrayList<Integer> weightsArray, ArrayList<Integer> valuesArray, String debugLevel) throws IOException {
        this.n = n;
        this.w = w;
        this.debugLevel = debugLevel;
        weightsA = weightsArray;
        valuesA = valuesArray;
        knapsackSol();
    }

    /**
     * This function solves the knapsack problem using dynamic programming by
     * following the recursive definition of knapsack. using the description:
     * A thief robbing a store finds n items; the ith item is worth vi dollars and weights wi pounds,
     * where vi and wi are integers. He wants to take as valuable a load as possible, but he can carry
     * at most W pounds in his knapsack for some integer W . Which items should he take? (This
     * is called the 0-1 knapsack problem because each item must either be taken or left behind;
     * the thief can not take a fraction amount of an item or take an item more than once.)
     */
    public void knapsackSol() {
        int[][] table = new int[n + 1][w + 1];
        boolean[][] keepT = new boolean[n][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                count++;
                if (weightsA.get(i - 1) > j) {
                    table[i][j] = table[i - 1][j];
                } else {
                    int take = valuesA.get(i - 1) + table[i - 1][j - weightsA.get(i - 1)];
                    int dontTake = table[i - 1][j];
                    count++;
                    if (take > dontTake) {
                        keepT[i - 1][j] = true;
                        table[i][j] = take;
                    } else {
                        table[i][j] = dontTake;
                    }
                }
            }
        }
        if (debugLevel.equals("1")) {
            try {
                try (FileWriter f = new FileWriter("KnapsackDP-VTable")) {
                    try {
                        try (FileWriter f2 = new FileWriter("KnapsackDP-DTable")) {
                            System.out.println("KnapsackDP-VTable:");
                            f.write("KnapsackDP-VTable:\n");
                            for (int i = 1; i <= n; i++) {
                                for (int j = 1; j <= w; j++) {
                                    String v;
                                    if (j == 1) {
                                        v = String.format("%d ", table[i][j]);
                                    } else {
                                        v = String.format("%2d ", table[i][j]);
                                    }
                                    System.out.printf(v);
                                    f.write(v);
                                }
                                System.out.println();
                                f.write("\n");
                            }
                            System.out.println();
                            f2.write("KnapsackDP-DTable:\n");
                            System.out.println("KnapsackDP-DTable:");
                            for (int i = 1; i < n; i++) {
                                for (int j = 1; j <= w; j++) {
                                    int num = keepT[i][j] ? 1 : 0;
                                    if (j == 1) {
                                        System.out.printf("%d ", num);
                                        f2.write(String.format("%d", num));
                                    } else {
                                        System.out.printf("%2d ", num);
                                        f2.write(String.format("%2d", num));
                                    }
                                }
                                f2.write("\n");
                                System.out.println();
                            }
                            System.out.println();
                        } catch (RuntimeException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
            System.out.println("Optimal Solution: ");
            int totalWeight = 0;
            int optVal = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (keepT[i][w]) {
                    takeT.add(0, i + 1);
                    totalWeight += weightsA.get(i);
                    optVal += valuesA.get(i);
                    w = w - weightsA.get(i);
                }
            }
            System.out.println(takeT.toString().replaceAll("\\[", "{").replaceAll("]", "}")
                    .replaceAll("\\s", ""));
            System.out.println("Total Weight: " + totalWeight);
            System.out.println("Optimal Value: " + optVal);
            System.out.println("Number of table references: " + count);
        }
    }
