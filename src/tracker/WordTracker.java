package tracker;

import exceptions.TreeException;
import tree.BSTree;
import tree.BSTreeNode;
import tree.Iterator;

import java.io.*;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WordTracker {
    private BSTree<WordInfo> tree;
    public WordTracker(String[] args) {
        tree = new BSTree<>();
        File f = new File("src/res/repository.ser");
        if(f.isFile()) deserializeBSTFromFile();
        parseFileToTree(args[0]);


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

    private void parseFileToTree(String fileName) {
        try (LineNumberReader reader = new LineNumberReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, " ");

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
    }

    private void printWordFileLineFreq() {
        Iterator<WordInfo> iterator = tree.inorderIterator();
        WordInfo currentWord;

        System.out.printf("--------------------------------------------------------------%n");
        System.out.printf("     %-8s | %-13s  | %-13s  | %-13s%n", "WORD", "FILENAME", "LINE NUMBER", "FREQUENCY");
        System.out.printf("--------------------------------------------------------------%n");

        while (iterator.hasNext()) {
            currentWord = iterator.next();

            try {
                System.out.printf("%-14s| %-14s | %-14d | %-14d%n", currentWord.getWord(), currentWord.getFileName().split("/", 3)[2],
                        currentWord.getLineNumber(), tree.search(currentWord).getCount());
            } catch (TreeException tex) {
                tex.printStackTrace();
            }
        }
    }

    private void printWordFileLine() {
        Iterator<WordInfo> iterator = tree.inorderIterator();
        WordInfo currentWord;

        System.out.printf("----------------------------------------------%n");
        System.out.printf("     %-8s | %-13s  | %-13s%n", "WORD", "FILENAME", "LINE NUMBER");
        System.out.printf("----------------------------------------------%n");

        while (iterator.hasNext()) {
            currentWord = iterator.next();

            System.out.printf("%-14s| %-14s | %-14d%n", currentWord.getWord(), currentWord.getFileName().split("/", 3)[2],
                    currentWord.getLineNumber());
        }
    }

    private void printWordFile() {
        Iterator<WordInfo> iterator = tree.inorderIterator();
        WordInfo currentWord;

        System.out.printf("-----------------------------------%n");
        System.out.printf("     %-8s | %-13s%n", "WORD", "FILENAME");
        System.out.printf("-----------------------------------%n");

        while (iterator.hasNext()) {
            currentWord = iterator.next();

            System.out.printf("%-14s| %-14s%n", currentWord.getWord(), currentWord.getFileName().split("/", 3)[2]);
        }
    }

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
