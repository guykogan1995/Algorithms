import java.util.ArrayList;

/**
 * @author Guy Kogan
 * @class CS421 - Algorithms
 * @teacher Jyh Haw Yeh
 * @date July 4, 2023
 * @description This class is responsible for generating the chess board as well as contains
 * the algorithms for movement of the knight across the board. Contains helper methods
 * for achieving this goal and manipulation of the chess board. As well as is reponsible
 * for printing the 2d position matrix to the user.
 */
public class KnightBoard {
    private final Position[][] board; // The 2-d array of positions representing the chessboard
    private int moves; // The amount of moves made to solve the knight's tour problem
    private int order; // The order of visited Positions as the knight traverses the board
    private final int size; // The size of the chess board [size][size]
    Position current; // The current position the knight is currently on
    Position startPosition; // The starting position of the knight on the chessboard
    int maxVal; // The length of the biggest number on the board to pad the results in the 2d matrix
    ArrayList<Position> lastPositions; // An array of previous positions with the one in the rear as the last position in correlation to current

    /**
     * The Constructor for the KnightBoard class, Initializes all the variables declared in this class
     * and creates the chess board of (x,y) positions depending on the size
     * @param size - the size of the chess board by rows and columns, Example: size of 3 would create KnightBoard[3][3]
     */
    public KnightBoard(int size) {
        this.board = new Position[size][size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Position(i, j);
            }
        }
        calculateMovesForBoard();
        lastPositions = new ArrayList<>();
        moves = 0;
        String s = String.valueOf(size * size);
        maxVal = s.length();
        order = 0;
    }

    /**
     * Helper function to calculate all possible moves for the knight for each position in the chessboard
     */
    public void calculateMovesForBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j].calculatePossibleMoves(size);
            }
        }
    }

    /**
     * This function recalculates possible moves after a back track has occurred to allow
     * that position to freely pick moves again as long as they have not been visited
     */
    public void calculateMovesForPosition() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].getValue() == 0) {
                    board[i][j].calculatePossibleMoves(size);
                }
            }
        }
    }

    /**
     * This function is used to print the matrix of results for the Knight's Tour Problem to the user
     * after the traversal of each position exactly once
     */
    public void showBoard() {
        String boardS = "\n";
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                boardS = boardS.concat(String.format("%" + maxVal + "s", this.board[i][j].getValue()) + " ");
            }
            boardS = boardS.concat("\n");
        }
        System.out.println("The total number of moves is " + moves + boardS);
    }

    /**
     * This function checks if the board traversal is complete upon every position being visited exactly once
     * @return - boolean value if the board is complete
     */
    public boolean isBoardDone() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].isVisited()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * This function represents the beginning of the exhaustive search with backtracking algorithm.
     * This portion simply adds the starting position to the board and begins the exhaustive search function
     * @param p - The starting position of the Knight on the chess board
     * @param option - The option of algorithm to choose from 0-2 -> options: 0. Exhaustive Search with Backtracking - Which is the brute force trial
     *  * and error option, 1. Heuristic Approach One - Where the algorithm picks moves that are closer to edges
     *  * first, and lastly 2. Heuristic Approach 2 (Warnsdorff's Heuristic) - Where the algorithm picks
     *  * the moves with the fewest possible next moves.
     */
    public void moveBoard(Position p, int option) {
        int posX = p.getX();
        int posY = p.getY();
        current = board[posX][posY];
        board[posX][posY].setValue(++order);
        board[posX][posY].setVisited(true);
        startPosition = current;
        lastPositions.add(current);
        exhaustiveSearch(option);
        moves++;
    }

    /**
     * This function is responsible for the algorithm portion and has 3 algorithm options to try and traverse
     * a chessboard using a knight.
     * @param option - The option of algorithm to choose from 0-2 -> options: 0. Exhaustive Search with Backtracking - Which is the brute force trial
     *  and error option, 1. Heuristic Approach One - Where the algorithm picks moves that are closer to edges
     *  first, and lastly 2. Heuristic Approach 2 (Warnsdorff's Heuristic) - Where the algorithm picks
     *  moves with the fewest possible next moves.
     */
    public void exhaustiveSearch(int option) {
        while (!isBoardDone()) {
            Position oldMove = current;
            Position moveToAdd = null;
            int min = 10000000;
            switch (option) {
                // Exhaustive search
                case 0 :
                    for (int i = 0; i < current.getPossibleMoves().size(); i++) {
                        if (board[current.getPossibleMoves().get(i).getX()][current.getPossibleMoves().get(i).getY()].isVisited()) {
                            moveToAdd = current.getPossibleMoves().get(i);
                            break;
                        }
                    }
                    break;
                // Heuristic 1
                case 1 :
                    int distance1;
                    int distance2;
                    int distance;
                    for (int i = 0; i < current.getPossibleMoves().size(); i++) {
                        int a = Math.abs(current.getPossibleMoves().get(i).getY() + 1 - size);
                        int b = Math.abs(current.getPossibleMoves().get(i).getY());
                        distance1 = Math.min(a, b);
                        int c = Math.abs(current.getPossibleMoves().get(i).getX() + 1 - size);
                        int d = Math.abs(current.getPossibleMoves().get(i).getX());
                        distance2 = Math.min(c, d);
                        distance = distance1 + distance2;
                        if (distance < min && board[current.getPossibleMoves().get(i).getX()][current.getPossibleMoves().get(i).getY()].isVisited()) {
                            moveToAdd = current.getPossibleMoves().get(i);
                            min = distance;
                        }
                    }
                break;
                // Heuristic 2 / Warnsdorff
                case 2:
                    for (int i = 0; i < current.getPossibleMoves().size(); i++) {
                        int minC = 0;
                        for (int j = 0; j < board[current.getPossibleMoves().get(i).getX()][current.getPossibleMoves().get(i).getY()].getPossibleMoves().size(); j++) {
                            Position x = board[current.getPossibleMoves().get(i).getX()][current.getPossibleMoves().get(i).getY()].getPossibleMoves().get(j);
                            if (board[x.getX()][x.getY()].isVisited()) {
                                minC++;
                            }
                        }
                        if (minC < min && board[current.getPossibleMoves().get(i).getX()][current.getPossibleMoves().get(i).getY()].isVisited()) {
                            min = minC;
                            moveToAdd = current.getPossibleMoves().get(i);
                        }
                    }
                    break;
                }
                    // This portion is used for backtracking
                    if (moveToAdd == null && current != startPosition) {
                        board[current.getX()][current.getY()].setValue(0);
                        board[current.getX()][current.getY()].setVisited(false);
                        current = board[lastPositions.get(lastPositions.size() - 1).getX()][lastPositions.get(lastPositions.size() - 1).getY()];
                        current.removePossibleMove(oldMove);
                        board[lastPositions.get(lastPositions.size() - 1).getX()][lastPositions.get(lastPositions.size() - 1).getY()].removePossibleMove(oldMove);
                        lastPositions.remove(lastPositions.size() - 1);
                        order--;
                        calculateMovesForPosition();
                        // This portion is used if no solution is found after every possible attempt
                    }else if(moveToAdd == null && current.getX() == startPosition.getX() && current.getY() == startPosition.getY()) {
                          moves++;
                          System.out.println("The total number of moves is " + moves);
                          System.err.println("No solution found!");
                          System.exit(0);
                    } else {
                        // This portion is used when a next move is available
                        if (moveToAdd == null) {
                            System.exit(-1);
                        }
                        current = board[moveToAdd.getX()][moveToAdd.getY()];
                        board[oldMove.getX()][oldMove.getY()].removePossibleMove(moveToAdd);
                        lastPositions.add(board[oldMove.getX()][oldMove.getY()]);
                        board[current.getX()][current.getY()].setValue(++order);
                        board[current.getX()][current.getY()].setVisited(true);
                        moves++;
                        calculateMovesForPosition();
                    }
            }
        }
    }