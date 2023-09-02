package tm;
/**
 * Class TransitionObject represents a single transition
 * @author Guy Kogan, Yelena Irwin
 * 4/17/2023
 */
public class TransitionObject {

    /**
     * Represents the character to be written
     */
    private final char write;
    /**
     * Represents the move (R or L) onto the tape to move right or left
     */
    private final char move;
    /**
     * The next state after the transition on the tape is complete
     */
    private final TMState nextState;
    /**
     * The start state before the transition on the tape is made
     */
    private final TMState startState;
    /**
     * The character read off of the tape before the transition occurs
     */
    private final char read;

    /**
     * This is the constructor for the TransitionObject Class and instantiates
     * all variables above
     * @param startState - The start state of the transition
     * @param next_state - The next state of the transition
     * @param read - The character read from the tape of the transition
     * @param write - The character that will be written to the tape before move
     * @param move - The move onto the tape before another transition occurs
     */
    public TransitionObject(TMState startState, TMState next_state, char read, char write, char move){
        this.write = write;
        this.move = move;
        this.read = read;
        this.nextState = next_state;
        this.startState = startState;
    }

    /**
     * Function is a getter to get write variable.
     * @return Character stored in write variable
     */
    public char getWrite(){
        return write;
    }

    /**
     * Function is a getter to get move variable.
     * @return Character stored in move variable
     */
    public char getMove(){
        return move;
    }

    /**
     * Function is a getter to get the next state of a transition.
     * @return TMState stored in nextState variable
     */
    public TMState getNext_state(){
        return nextState;
    }

    /**
     * Function is a getter to get the start state of a transition.
     * @return TMState stored in startState variable
     */
    public TMState getStartState() {
        return startState;
    }

    /**
     * Overridden toString method for testing and printing out purposes.
     * @return String of a customized string for printing
     */
    @Override
    public String toString() {
        return nextState.getName() + "--" + read + "" + write + move;
    }
}
