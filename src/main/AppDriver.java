package main;

import exceptions.TreeException;
import tracker.WordTracker;

public class AppDriver {
    public static void main(String[] args) {
        String[] test = new String[2];
        test[0] = "src/res/test2.txt"; // change number for testing
        test[1] = "-pf";
        new WordTracker(test);
    }
}
