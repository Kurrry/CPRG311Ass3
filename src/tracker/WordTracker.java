package tracker;

import tree.BSTree;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.StringTokenizer;

public class WordTracker {

    private String outputFileName; // optional
    private BSTree<WordInfo> tree;
    public WordTracker(String[] args) {
        tree = new BSTree<>();
        File f = new File("res/repository.ser");
        if(f.isFile()) deserializeBSTFromFile();
        parseFileToTree(args[0]);

        for (String s : args) {
            switch (s) {
                case "-pf":
                    printWordFile(); //TODO
                    break;

                case "-pl":
                    printWordFileLine(); //TODO
                    break;

                case "-po":
                    printWordFileLineFreq(); //TODO
                    break;
            }
        }
    }

    private void parseFileToTree(String fileName) {
        try (LineNumberReader reader = new LineNumberReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                //int size = st.countTokens();

                while (st.hasMoreTokens()) {
                    String word = st.nextToken();
                    int lineNumber = reader.getLineNumber();
                    WordInfo info = new WordInfo(word, lineNumber, fileName);
                    tree.add(info);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // maybe done
    }

    private void printWordFileLineFreq() {
        //TODO
    }

    private void printWordFileLine() {
        //TODO
    }

    private void printWordFile() {
        //TODO
    }

    private void deserializeBSTFromFile() {
        //TODO
    }

    public void serializeBSTToFile() {
        //TODO
    }
}
