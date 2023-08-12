package main;


import tracker.WordTracker;

/**
 * @author Group 9 <br>
 *
 * The main driver class for running the word tracking application.
 */
public class AppDriver {

    /**
     * The entry point for the word tracking application. The command-line arguments specify the filename and output options. <br>
     *
     * User will run the program following this format: java -jar WordTracker.jar input.txt -pf/-pl/-po [-f output.txt]<br>
     *
     * Choices of output options are as follows: <br>
     *
     * "-pf" to print words with their filenames <br>
     * "-pl" to print words with their filenames and line numbers <br>
     * "-po" to print words with their filenames, line numbers, and frequencies of occurrence <br>
     *
     * optional: "-f output.txt" to print output to specified file instead of console
     *
     * @param args the command-line arguments, containing the path to the input file and choice of output options
     */
    public static void main(String[] args) {
        /*String[] test = new String[4];
        test[0] = "src/res/test2.txt"; // change number for testing
        test[1] = "-po";
        test[2] = "-f";
        test[3] = "src/res/output.txt";*/
        new WordTracker(args); // change param to test if not using cli args
    }
}
