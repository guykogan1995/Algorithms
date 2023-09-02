import java.util.ArrayList;

/**
 * @author Guy Kogan
 * @class CS421 - Algorithms
 * @teacher Jyh Haw Yeh
 * @date July 4, 2023
 * @description This class is responsible for defining a Position Object, Which will be used to fill
 * each position within a chess board.
 */
public class Position {
    private int x; // The row of the chess board
    private int y; // The column of the chess board
    private boolean visited; // if a position on the chess board has been visited
    private int value; // The value of a position or order number of the knight moving
    private final ArrayList<Position> possibleMoves; // The possible positions a knight can move from a certain position

    /**
     * The constructor for the Position class initializes all variables in Position class
     * @param x - the row of the chessboard
     * @param y - the col of the chessboard
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
        visited = false;
        value = 0;
        possibleMoves = new ArrayList<>();
    }

    /**
     * This function returns the value of a position
     * @return - Integer of the value
     */
    public int getValue(){
        return value;
    }

    /**
     * This function returns the row of a position
     * @return - Integer of the row
     */
    public int getX() {
        return x;
    }

    /**
     * This function returns the column of a position
     * @return - Integer of the column
     */
    public int getY(){
        return y;
    }

    /**
     * This function sets the value of a position
     */
    public void setValue(int newVal){
        value = newVal;
    }

    /**
     * This function sets the column of a position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This function sets the row of a position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * This function is used to check if a position has been visited
     * @return boolean if the position is visited
     */
    public boolean isVisited() {
        return !visited;
    }

    /**
     * This function sets the visited boolean of a position
     */
    public void setVisited(boolean visit) {
        visited = visit;
    }

    /**
     * This function returns the possible moves for a knight on a position
     * @return array of possible positions
     */
    public ArrayList<Position> getPossibleMoves() {
        return possibleMoves;
    }

    /**
     * This function removes a possible move from a position
     * @param p - the position to be removed
     */
    public void removePossibleMove(Position p) {
        for (int i = 0; i < possibleMoves.size(); i++) {
            if (possibleMoves.get(i).getX() == p.getX() && possibleMoves.get(i).getY() == p.getY()) {
                possibleMoves.remove(i);
                break;
            }
        }
    }

    /**
     * This function calculates possible moves of a position and sets the array in the
     * Position class for each position
     * @param size - The size of the board to calculate bounds
     */
    public void calculatePossibleMoves(int size){
        int[] xMove = {-2, -1, 1, 2, 2, 1, -1 , -2};
        int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};
        possibleMoves.clear();
        for (int i = 0; i < xMove.length; i++) {
            Position newPos = new Position(x, y);
            newPos.setX(x+xMove[i]);
            newPos.setY(y+yMove[i]);
            if (newPos.getX() >= 0 && newPos.getY() >= 0 && newPos.getX() <= size - 1 &&
                    newPos.getY() <= size - 1) {
                possibleMoves.add(newPos);
            }
        }
    }
}
