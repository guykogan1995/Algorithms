import java.io.IOException;

/**
 * @author Guy Kogan
 * @class 421 Algorithms
 * @date 7/26/2023
 * @teacher Jyh Haw Yeh
 * @description This class is the main driver behind dynamic programming top-down recursion with memorization approach of
 **/
public class ShuffleRM extends MatrixManipulationRM {

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            System.err.println("NOT ENOUGH ARGS! USAGE: java ShuffleRM <X> <Y> <Z> [<debug level>]");
            System.exit(-1);
        }
        String X = args[0];
        String Y = args[1];
        String Z = args[2];
        String debugLevel;
        if (args.length == 4) {
            debugLevel = args[3];
            if (!debugLevel.equals("0") && !debugLevel.equals("1")) {
                System.err.println("debug = 0 −→ Default level. Print summary of experiment on the console, including\n" +
                        "the “yes/no” answer, and the number of table references.\n" +
                        "debug = 1 −→ Print summary of experiment on the console and also print the table\n" +
                        "into a file ShuffleRM-Table. The table contains the solutions (1 for yes; 0 for no;\n" +
                        "and -1 for unknown).");
                System.exit(-1);
            }
        } else {
            debugLevel = "0";
        }
        if (X.length() + Y.length() != Z.length()) {
            System.err.println("X and Y are compatible Strings if their Length added together is the length of Z");
            System.exit(-1);
        }
        MatrixManipulationRM m = new MatrixManipulationRM();
        m.ShuffleRM(X,Y,Z, debugLevel);
    }
}