import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Guy Kogan
 * @class 421 Algorithms
 * @date 8/2/2023
 * @teacher Jyh Haw Yeh
 * @description This class is the main logic behind dynamic programming top-down recursive approach
 * to solve A thief robbing a store finds n items; the ith item is worth vi dollars and weights wi pounds,
 * where vi and wi are integers. He wants to take as valuable a load as possible, but he can carry
 * at most W pounds in his knapsack for some integer W . Which items should he take? (This
 * is called the 0-1 knapsack problem because each item must either be taken or left behind;
 * the thief can not take a fraction amount of an item or take an item more than once.)
 **/
public class KnapsackRM {
    private static int[][] vTable, chosenItems;
    private static int[] values, weights;
    private static final ArrayList<Integer> optimalSolution = new ArrayList<>();
    private static int tableReferences = 0;
    private static String debug = "0";

    /**
     * The main driver of the program
     * @param args - program arguments to run the program
     * @throws IOException - In case Files cannot be created
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 2 && args.length < 6) {
            int itemCount = Integer.parseInt(args[0]);
            int maxCapacity = Integer.parseInt(args[1]);
            File weightFile = new File(args[2]);
            File valueFile = new File(args[3]);
            weights = new int[itemCount];
            values = new int[itemCount];
            weights = parseFile(weightFile, getArraySizeFromFile(weightFile));
            values = parseFile(valueFile, getArraySizeFromFile(valueFile));
            vTable = new int[itemCount + 1][maxCapacity + 1];
            chosenItems = new int[itemCount + 1][maxCapacity + 1];
            for (int i = 0; i < chosenItems.length; i++) {
                for (int j = 0; j < chosenItems[0].length; j++) {
                    chosenItems[i][j] = -1;
                }
            }
            int optimalValue = knapsack(itemCount, maxCapacity);
            if (args.length == 5) {
                debug = args[4];
                if (!debug.equals("0") && !debug.equals("1")) {
                    System.err.println("Debug level must be either 0 or 1");
                    System.exit(-1);
                }
            }
            if (debug.equals("1")) {
                try (FileWriter f2 = new FileWriter("KnapsackRM-VTable")) {
                    f2.write("KnapsackRM-VTable:\n");
                    System.out.println("KnapsackRM-VTable: ");
                    for (int i = 1; i < vTable.length; i++) {
                        for (int j = 1; j < vTable[0].length; j++) {
                            if (j == 1) {
                                f2.write(String.format("%d ", vTable[i][j]));
                                System.out.printf("%d ", vTable[i][j]);
                            } else {
                                f2.write(String.format("%3d ", vTable[i][j]));
                                System.out.printf("%3d ", vTable[i][j]);
                            }
                        }
                        f2.write("\n");
                        System.out.println();
                    }
                }
                System.out.println();

                try (FileWriter f = new FileWriter("KnapsackRM-DTable")) {
                    f.write("KnapsackRM-DTable:\n");
                    System.out.println("KnapsackRM-DTable: ");
                    for (int i = 1; i < chosenItems.length; i++) {
                        for (int j = 1; j < chosenItems[0].length; j++) {
                            f.write(String.format("%2d ", chosenItems[i][j]));
                            System.out.printf("%2d ", chosenItems[i][j]);
                        }
                        f.write("\n");
                        System.out.println();
                    }
                    System.out.println();
                }
            }

            System.out.println("Optimal Solution:");
            int totalWeight = 0;
            int remainingWeight = maxCapacity;
            for (int i = itemCount; i > 0; i--) {
                if (chosenItems[i][remainingWeight] == 1) {
                    optimalSolution.add(0, i);
                    totalWeight += weights[i - 1];
                    remainingWeight -= weights[i - 1];
                }
            }
            System.out.println(optimalSolution.toString().replaceAll("\\s", "").replaceAll("\\[","{")
                    .replaceAll("]", "}"));
            System.out.println("Total Weight: " + totalWeight);
            System.out.println("Optimal Value: " + optimalValue);
            System.out.println("Number of table references: " + (tableReferences - 34));
        } else {
            System.out.println("Please enter the correct arguments.");
            System.out.println("The correct format is: ");
            System.out.println(
                    "java KnapsackRM <N (number of items)> <W (max weight)> <w.txt (weights text file)> <v.txt (values text file)>");
            System.exit(0);
        }
    }

    /**
     * This function is the recursive solver for the knapsack problem
     * @param itemIndex - the current index
     * @param remainingWeight - the remaining weight of the bag of items
     * @return int if item was taken or not, 0 for no 1 for yes
     */
    private static int knapsack(int itemIndex, int remainingWeight) {
        int take = 0;
        int dontTake;
        if (itemIndex == 0 || remainingWeight == 0) {
            return 0;
        }
        if (vTable[itemIndex][remainingWeight] != 0 && chosenItems[itemIndex][remainingWeight] != 0) {
            return vTable[itemIndex][remainingWeight];
        }
        if (weights[itemIndex - 1] <= remainingWeight) {
            take = values[itemIndex - 1] + knapsack(itemIndex - 1, remainingWeight - weights[itemIndex - 1]);
            tableReferences++;
        }
        dontTake = knapsack(itemIndex - 1, remainingWeight);
        if (take > dontTake) {
            chosenItems[itemIndex][remainingWeight] = 1;
            vTable[itemIndex][remainingWeight] = take;
        } else {
            chosenItems[itemIndex][remainingWeight] = 0;
            vTable[itemIndex][remainingWeight] = dontTake;
        }
        return vTable[itemIndex][remainingWeight];
    }

    /**
     * This function gets the size of the array to be created from the input file
     * @param file - then input file
     * @return - integer the size of the array to be created from the file
     */
    private static int getArraySizeFromFile(File file) {
        int val = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextInt()) {
                scanner.next();
                val++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not Found. ");
            System.exit(0);
        }
        return val;
    }

    /**
     * This function parses the file into an array structure
     * @param file - the input file to be parsed
     * @param size - the size of the array to be created
     * @return - an array of ints of either values or weights
     */
    private static int[] parseFile(File file, int size) {
        int[] array = new int[size];
        try {
            int index = 0;
            Scanner scan = new Scanner(file);
            while (scan.hasNextInt()) {
                array[index++] = scan.nextInt();
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not Found. ");
            System.exit(0);
        }
        return array;
    }
}
