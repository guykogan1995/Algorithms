package tm;

/**
 * Class TMState extends State abstract class
 * represents a single state of the TM
 * @author Guy Kogan, Yelena Irwin
 * 4/17/2023
 */
public class TMState extends State {

    /**
     * This is the constructor for the TMState class and utilizes super
     * to call the constructor in the State class
     * @param name - The name of the State to be created
     */
    public TMState(String name) {
        super(name);
    }

}
