package tm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class TMTape represents a single tape Object
 * and handles the necessary manipulation of the tape
 * @author Guy Kogan, Yelena Irwin
 * 4/17/2023
 */
public class TMTape {
    /**
     * The tape represented by an ArrayList
     */
    ArrayList<Character> tape;
    /**
     * The head which will keep track of the character at the current position
     */
    Character head;

    /**
     * The beginning position of the tapes when a tape is initialized
     */
    int pos = 46;

    /**
     * The constructor for the TMTape that is a blank tape in the case of no input on the last line of the
     * input file
     */
    public TMTape() {
        tape = new ArrayList<>(Arrays.asList('0','0','0','0','0','0','0','0','0','0','0','0','0','0','0', '0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0', '0','0','0','0','0','0','0','0','0'));
    }

    /**
     * The second constructor for the TMTape that is a non-blank tape in the case of input given on the last
     * line of the input file
     * @param tapeConfig - The input scanned from tester files to start the tape with
     */
    public TMTape(String tapeConfig) {
        char[] startString = tapeConfig.toCharArray();
        tape = new ArrayList<>(Arrays.asList('0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'));
        for (Character c : startString) {
            tape.add(c);
        }
        tape.add('0');
        tape.add('0');
        head = tape.get(pos);
    }

    /**
     * Function to move the head of the tape one position to the right, if the end is reached
     * adds 3 more blanks and then moves right to always have two blanks ahead of last position
     */
    public void moveRight() {
        if (pos == tape.size() - 1) {
            tape.add(tape.size(), '0');
            tape.add(tape.size(), '0');
            tape.add(tape.size(), '0');
        }
        pos++;
    }

    /**
     * Function to move the head of the tape one position to the left, if the beginning is reached
     * adds 3 more blanks and then moves left to always have two blanks behind of beginning position
     */
    public Set<Integer> moveLeft(Set<Integer> incrementValues) {
        Set<Integer> incrementValuesMod = new HashSet<>();
        if (pos == 0) {
            tape.add(0, '0');
            tape.add(0, '0');
            tape.add(0, '0');
            pos = 3;
            for (int i : incrementValues) {
                incrementValuesMod.add(i + 3);
            }
        } else {
            incrementValuesMod = incrementValues;
        }
        pos--;
        return incrementValuesMod;
    }

    /**
     * This function writes a character to the tape of where the head is located
     * @param c - The character to be written to the tape
     * @return Integer of position where a write operation occurred to keep track of visited cells
     */
    public Integer write(Character c) {
        tape.set(pos, c);
        return pos;
    }

    /**
     * This function reads the current character of where the head is located
     * @return Character that is stored in the head
     */
    public Character read() {
        head = tape.get(pos);
        return head;
    }

    /**
     * This function was created to keep track of visited cells
     * @param pos - position of cell
     * @return Character of the tape inside that visited cell
     */
    public Character getCharFromPos(int pos) {
        return tape.get(pos);
    }

    /**
     * Custom toString method
     * @return String of the tape printed
     */
    public String toString() {
        return tape.toString();
    }
}
