/**
 * @author Guy Kogan
 * @class CS421 - Algorithms
 * @teacher Jyh Haw Yeh
 * @date July 4, 2023
 * @description This class is the main driver of the application to the Knight's Tour Problem
 * Where a Knight must traverse every cell of a chess board exactly once. This program allows
 * A user to use 3 options: 1. Exhaustive Search with Backtracking - Which is the brute force trial
 * and error option, 2. Heuristic Approach One - Where the algorithm picks moves that are closer to edges
 * first, and lastly 3. Heuristic Approach 2 (Warnsdorff's Heuristic) - Where the algorithm picks
 * the moves with the fewest possible next moves.
 * @Program java KnightTour <0/1/2 (Exhaustive Search, Heuristic 1, Heuristic 2 / Warnsdorff's Heuristic)> n x y
 */
public class KnightTour {
    public static void main(String[] args) {
        String usage = "Program USAGE: java KnightTour <0/1/2 (Exhaustive Search, Heuristic 1, Heuristic 2 / Warnsdorff's Heuristic)> <n> <x> <y>";
        if (args.length != 4) {
            System.err.println("Not Enough Arguments, 4 needed and You entered: " + args.length);
            System.out.println(usage);
            System.exit(-1);
        } else {
            if (!args[0].equals("0") && !args[0].equals("1") && !args[0].equals("2")) {
                System.err.println("First argument must be 0, 1, or 2. You Entered: " + args[0]);
                System.out.println(usage);
                System.exit(-1);
            }
            try{
                Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("N must be an integer that is greater then 2 (n > 2). You Entered: " + args[1]);
                System.out.println(usage);
                System.exit(-1);
            }
            int searchType = Integer.parseInt(args[0]);
            int chessBoardSize = Integer.parseInt(args[1]);
            try{
                Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                System.err.println("x must be integer ranging from 0 to the chessboard size: " + chessBoardSize + ". You Entered: " + args[2]);
                System.out.println(usage);
                System.exit(-1);
            }
            if (Integer.parseInt(args[2]) < 0 || Integer.parseInt(args[2]) > chessBoardSize) {
                System.err.println("x coordinate cannot be negative(x >= 0) and must be smaller then the chessboard row size which is: " + chessBoardSize +
                ". You Entered: " + args[2]);
                System.out.println(usage);
                System.exit(-1);
            }
            try{
                Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                System.err.println("y coordinate must be integer ranging from 0 to the chessboard size: " + chessBoardSize + ". You Entered: " + args[2]);
                System.out.println(usage);
                System.exit(-1);
            }
            if (Integer.parseInt(String.valueOf(Integer.parseInt(args[2]))) < 0 || Integer.parseInt(String.valueOf(Integer.parseInt(args[3]))) < 0 || Integer.parseInt(args[2]) > chessBoardSize) {
                System.err.println("y or x cannot be negative(x >= 0 || y >= 0) and must be smaller then the chessboard row size which is: " + chessBoardSize +
                        ". You Entered: (" + args[2] + "," + args[3] +")");
                System.out.println(usage);
                System.exit(-1);
            }
            // Starting Position
            Position start = new Position(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
            // Initialize the Board
            KnightBoard board = new KnightBoard(chessBoardSize);
            // Search Algorithm
            board.moveBoard(start, searchType);
            // Show the board to the user if a result has been found
            board.showBoard();
        }
    }
}
