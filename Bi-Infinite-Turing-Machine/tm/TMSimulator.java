package tm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This is the main driver of the tm package
 * @author Guy Kogan, Yelena Irwin
 * 4/17/2023
 */
public class TMSimulator {

    public static void main(String[] args) {
        if (args.length != 1 || args[0] == null) {
            System.err.println("Please add file name to program in command line -> USAGE: java tm.TMSimulator file0.txt");
            System.exit(-1);
        }
        System.out.println(args[0]);
        File file = new File("test/"+args[0]);
        TM tm = new TM();
        try {
            Scanner scanner = new Scanner(file);
            if (!scanner.hasNextLine()) {
                System.err.println("Fatal Error: Nothing to read in file!");
                System.exit(-1);
            }
            int stateCount = Integer.parseInt(scanner.nextLine());
            if (!scanner.hasNextLine()) {
                System.err.println("Fatal Error: Missing sigma count!");
                System.exit(-1);
            }
            int symbolCount = Integer.parseInt(scanner.nextLine());
            if (symbolCount > 9) {
                symbolCount = 9;
            }
            for (int i = 0; i < symbolCount; i++) {
                tm.addSigma((char)(i + 1 + '0'));
            }
            ArrayList<Character> possibleMovements = new ArrayList<>(tm.getSigma());
            Collections.sort(possibleMovements);
            possibleMovements.add(0, '0');
            int countPossibleMovements = -1;
            int countStartState = 0;
            for (int i = 0; i < stateCount; i++) {
                boolean addOperation = tm.addState("" + i);
                if(!addOperation) {
                    System.err.println("Fatal error could not add state into TM, State: " + i);
                    System.exit(-1);
                }
            }
            for (int i = 0; i < (stateCount - 1) * possibleMovements.size(); i++) {
                    String transition = scanner.nextLine().replaceAll(",", "");
                    tm.addTransition(tm.getState("" + countStartState) ,tm.getState("" + transition.charAt(0)), possibleMovements.get(++countPossibleMovements), transition.charAt(1), transition.charAt(2));
                    if (countPossibleMovements == possibleMovements.size() - 1) {
                        countPossibleMovements = -1;
                        countStartState++;
                    }
            }
            boolean startCheck = tm.setStart("" + 0);
            boolean finalCheck = tm.setFinal(stateCount - 1 + "");
            if (!startCheck) {
                System.err.println("Fatal error could not add start state into TM, State: " + 0);
                System.exit(-1);
            }
            if (!finalCheck) {
                System.err.println("Fatal error could not add final state into TM, State: " + (stateCount - 1));
                System.exit(-1);
            }
            String tapeInput;
            TMTape tape;
            boolean acceptOrReject;
            if (scanner.hasNextLine()) {
                tapeInput = scanner.nextLine();
                tape = new TMTape(tapeInput);
                acceptOrReject = tm.accepts(tape);
            } else {
                tape = new TMTape();
                acceptOrReject = tm.accepts(tape);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The File could not be read");
        }
    }
}
