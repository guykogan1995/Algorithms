package tm;
import java.util.*;

/**
 * This class represents a Turing Machine Object and handles all the manipulation
 * of the TM required
 * @author Guy Kogan, Yelena Irwin
 * 04/16/2023
 */
public class TM  {
    /**
     * A set of TMStates used to store all the states
     */
    private final Set<TMState> qStates;
    /**
     * A set of characters for storing a TM's alphabet
     */
    private final Set<Character> sigma;
    /**
     * The initial state of a TM
     */
    private TMState startState;
    /**
     *  A set of TMState objects storing all possible final states of a DFA
     */
    private final Set<TMState> finalState;

    /**
     * delta is a HashMap for storing transitions
     */
    private final Map<String, TransitionObject> delta;

    /**
     * Constructor for TM instantiates all private variables
     * required.
     */
    public TM() {
        qStates = new HashSet<>();
        sigma = new HashSet<>();
        startState = null;
        finalState = new HashSet<>();
        delta = new HashMap<>();
    }

    /**
     * This function add states to the TM's states Set
     * @param name - The name of the set that will be added
     * @return boolean - if the state was successfully added
     */
    public boolean addState(String name) {
        if (this.getState(name) != null) {
            return false;
        }
        TMState q = new TMState(name);
        return qStates.add(q);
    }

    /**
     * This function sets the final states for the TM
     * @param name - The name of the TMState to be set to a final state
     * @return boolean - if the state was successfully added
     */
    public boolean setFinal(String name) {
        for (TMState q : qStates) {
            if (q.getName().equals(name)) {
                finalState.add(q);
                return true;
            }
        }
        return false;
    }

    /**
     * Function to set the start of TM
     * @param name - the name of the state to be the start state
     * @return boolean if setting the start of TM was successful or not
     */
    public boolean setStart(String name) {
        for (TMState q : qStates) {
            if (q.getName().equals(name)) {
                startState = q;
                return true;
            }
        }
        return false;
    }

    /**
     * This function adds sigma to the set and represents the language for the TM
     * @param symbol - symbol to be added to the language
     */
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    /**
     * This function returns delta (The transitions of the TM)
     * @return String of transitions converted to strings
     */
    public String getDelta() {
        return delta.toString();
    }

    /**
     * This function takes the tape and sees if the final state can be reached
     * as well as outputs the tape after processing
     * @param tape - the tape to be checked
     * @return boolean if the TM reaches a final state
     */
    public boolean accepts(TMTape tape) {
        char read;
        char write;
        char direction;
        TMState current = startState;
        Set<Integer> cellsVisited = new HashSet<>();
        while (!isFinal(current.getName())) {
            read = tape.read();
            String key = current.getName() + read;
            TransitionObject transition = getNextTransition(key);
            if (transition == null) {
                return false;
            }
            current = transition.getNext_state();
            write = transition.getWrite();
            direction = transition.getMove();
            cellsVisited.add(tape.write(write));
            if (direction == 'R') {
                tape.moveRight();
            } else {
                cellsVisited = tape.moveLeft(cellsVisited);
            }
        }
        int sum = 0;
        ArrayList<Integer> array = new ArrayList<>(cellsVisited);
        Collections.sort(array);
        System.out.print("Output: ");
        for (Integer i : array) {
            System.out.print(tape.getCharFromPos(i));
            sum += tape.getCharFromPos(i) - '0';
        }
        System.out.println();
        System.out.println("Output length: " + cellsVisited.size());
        System.out.println("Sum of symbols: " + sum);
        return true;
    }

    /**
     * This function returns sigma also known as the language of the TM
     * @return Set of all the characters of Sigma
     */
    public Set<Character> getSigma() {
        return Set.copyOf(sigma);
    }

    /**
     * This function gets the state of a given state name
     * @param name - the name of the state to look for
     * @return TMState that matches the name input
     */
    public TMState getState(String name) {
        for (TMState q : qStates) {
            if (q.getName().equals(name)) {
                return q;
            }
        }
        return null;
    }

    /**
     * This function is a getter to grab all the states
     * @return Set of TMStates of all the states of the TM
     */
    public Set<TMState> getStates() {
      return qStates;
    }

    /**
     * This function checks if a given state is a final state
     * @param name - the name of the state to check if final
     * @return boolean if the state is a final state
     */
    public boolean isFinal(String name) {
        for (TMState q : finalState) {
            if (q.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This function checks if a TMState is a startState
     * @param name - the name of a state to be checked
     * @return boolean if the name of the state matches the name of the start state
     */
    public boolean isStart(String name) {
        return startState.getName().equals(name);
    }


    /**
     * This function adds transition to the transition table delta and uses the key
     * (StartState + CharacterRead) and stores all other values in the transition object
     * @param startState - the start state of the transition
     * @param next_state - the next state of the transition
     * @param read - the character read from the tape
     * @param write - the character that will be written to the tape
     * @param move - the move right or left on the tape
     */
    public void addTransition(TMState startState, TMState next_state, char read, char write, char move){
        TransitionObject newTransition = new TransitionObject(startState, next_state, read, write, move);
        delta.put((startState.getName()  + read), newTransition);
    }

    /**
     * This functions gets the next transition in the delta map (TM's transitions)
     * @param key - the key that will be used to find the transition object
     * @return TransitionObject that is the value of the key
     */
    public TransitionObject getNextTransition(String key){
        return delta.get(key);
    }

}
