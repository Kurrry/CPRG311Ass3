package tracker;

import exceptions.TreeException;
import tree.BSTree;
import tree.Iterator;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Group 9 <br>
 *
 * A utility class for tracking word occurrences in files and providing various printing options.
 */
public class WordTracker {
    private BSTree<WordInfo> tree;

    /**
     * Constructs a WordTracker object with the specified command-line arguments. Choices of output options are as follows: <br>
     *
     * "-pf" to print words with their filenames <br>
     * "-pl" to print words with their filenames and line numbers <br>
     * "-po" to print words with their filenames, line numbers, and frequencies of occurrence <br>
     *
     * optional: "-f output.txt" to print output to specified file instead of console
     *
     * @param args the command-line arguments, containing the path to the input file and choice of output options
     */
    public WordTracker(String[] args) {
        tree = new BSTree<>();
        File f = new File("src/res/repository.ser");
        if(f.isFile()) deserializeBSTFromFile();
        parseFileToTree(args[0]);

        if (args.length > 2 && args[2].equals("-f")) {
            try {
                //changes System.out to specified file rather than the console
                PrintStream outputStream = new PrintStream(new FileOutputStream(args[3]));
                System.setOut(outputStream);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (String s : args) {
            switch (s) {
                case "-pf":
                    printWordFile();
                    break;

                case "-pl":
                    printWordFileLine();
                    break;

                case "-po":
                    printWordFileLineFreq();
                    break;
            }
        }

        serializeBSTToFile();
    }

    /**
     * Parses the content of a text file, extracts words, and populates the word occurrences
     * in the Binary Search Tree (BST) of WordInfo objects.
     *
     * @param fileName the name of the input file to be parsed
     */
    private void parseFileToTree(String fileName) {
        try (LineNumberReader reader = new LineNumberReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");

                while (st.hasMoreTokens()) {
                    String word = st.nextToken();
                    int lineNumber = reader.getLineNumber();
                    WordInfo info = new WordInfo(word, lineNumber, fileName);
                    if (tree.isEmpty()) tree.add(info);
                    if (tree.contains(info)) {
                        tree.search(info).getElement().addFileName(fileName);
                    }
                    if (tree.getRoot().getElement() != info) tree.add(info);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Prints the tracked words along with their corresponding filenames, line numbers, and frequencies of occurrence.
     */
    private void printWordFileLineFreq() {
        Iterator<WordInfo> iterator = tree.inorderIterator();
        WordInfo currentWord;

        System.out.printf("--------------------------------------------------------------%n");
        System.out.printf("     %-8s | %-13s  | %-13s  | %-13s%n", "WORD", "FILENAME", "LINE NUMBER", "FREQUENCY");
        System.out.printf("--------------------------------------------------------------%n");

        while (iterator.hasNext()) {
            currentWord = iterator.next();

            try {
                System.out.printf("%-14s| %-14s | %-14d | %-14d%n", currentWord.getWord(), currentWord.getFileNames(),
                        currentWord.getLineNumber(), tree.search(currentWord).getCount());
            } catch (TreeException tex) {
                tex.printStackTrace();
            }
        }
    }

    /**
     * Prints the tracked words along with their corresponding filenames and line numbers.
     */
    private void printWordFileLine() {
        Iterator<WordInfo> iterator = tree.inorderIterator();
        WordInfo currentWord;

        System.out.printf("----------------------------------------------%n");
        System.out.printf("     %-8s | %-13s  | %-13s%n", "WORD", "FILENAME", "LINE NUMBER");
        System.out.printf("----------------------------------------------%n");

        while (iterator.hasNext()) {
            currentWord = iterator.next();

            System.out.printf("%-14s| %-14s | %-14d%n", currentWord.getWord(), currentWord.getFileNames(),
                    currentWord.getLineNumber());
        }
    }

    /**
     * Prints the tracked words along with their corresponding filenames.
     */
    private void printWordFile() {
        Iterator<WordInfo> iterator = tree.inorderIterator();
        WordInfo currentWord;

        System.out.printf("-----------------------------------%n");
        System.out.printf("     %-8s | %-13s%n", "WORD", "FILENAME");
        System.out.printf("-----------------------------------%n");

        while (iterator.hasNext()) {
            currentWord = iterator.next();

            System.out.printf("%-14s| %-14s%n", currentWord.getWord(), currentWord.getFileNames());
        }
    }

    /**
     * Deserializes the BST of WordInfo objects from a .ser file.
     */
    private void deserializeBSTFromFile() {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/res/repository.ser"));

            tree = (BSTree<WordInfo>) ois.readObject();
            ois.close();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Serializes the BST of WordInfo objects to a .ser file.
     */
    public void serializeBSTToFile() {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/res/repository.ser"));
            oos.writeObject(tree);
            oos.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
