package main;

import tracker.WordTracker;

public class AppDriver {
    public static void main(String[] args) {
        String[] test = new String[2];
        test[0] = "src/res/test1.txt";
        test[1] = "-pf";
        new WordTracker(test);
    }
}
