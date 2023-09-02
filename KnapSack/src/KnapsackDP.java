import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Guy Kogan
 * @class 421 Algorithms
 * @date 8/2/2023
 * @teacher Jyh Haw Yeh
 * @description This class is the main driver behind dynamic programming bottom-up approach
 **/
public class KnapsackDP {
    public static void main(String[] args) {
        int numItems = 0;
        int maxBagWeight = 0;
        String fileItemWeights = null;
        String fileItemValues = null;
        String debugLevel;
        if (args.length < 4 || args.length > 5) {
            System.err.println("USAGE: java KnapsackDP <n> <W> <w.txt> <v.txt> [<debug level>]");
            System.exit(-1);
        }
        try {
            numItems = Integer.parseInt(args[0]);
            maxBagWeight = Integer.parseInt(args[1]);
            fileItemWeights = args[2];
            fileItemValues = args[3];
        } catch (NumberFormatException e) {
            System.err.println("Couldn't parse number of items! MESSAGE: " + e.getMessage());
            System.exit(-1);
        }
        if (args.length == 5) {
            debugLevel = args[4];
            if (!debugLevel.equals("0") && !debugLevel.equals("1")) {
                System.err.println("debug = 0 −→ Default level. Print summary of experiment on the console, including\n" +
                        "the optimal solution, the optimal value, and the number of table references when\n" +
                        "constructing the optimal value table.\n" +
                        "debug = 1 −→ Print summary of experiment on the console and also print the\n" +
                        "optimal value table and the decision table to two files KnapsackDP-VTable and\n" +
                        "KnapsackDP-DTable.\n");
                System.exit(-1);
            }
        } else {
            debugLevel = "0";
        }
        File fileW = new File(fileItemWeights);
        File fileV = new File(fileItemValues);
        try {
            ArrayList<Integer> weightsA = new ArrayList<>();
            ArrayList<Integer> valuesA = new ArrayList<>();
            Scanner f = new Scanner(fileW);
            Scanner f2 = new Scanner(fileV);
            while (f.hasNextInt() || f2.hasNextInt()) {
                weightsA.add(f.nextInt());
                valuesA.add(f2.nextInt());
            }
            f.close();
            f2.close();
            new KnapsackDPLogic(numItems, maxBagWeight, weightsA, valuesA, debugLevel);
            if (weightsA.size() != valuesA.size() || valuesA.size() == 0) {
                System.err.println("Unequal amount of weights to values or no values in files at all!");
                System.exit(-1);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find file! MESSAGE: " + e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}