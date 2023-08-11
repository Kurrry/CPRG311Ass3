package tracker;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Group 9 <br>
 *
 * Represents information about a word, including the word itself,
 * the line number where it appears, and the list of file names where it occurs.
 * The class implements Comparable to allow comparison based on the word.
 */
public class WordInfo implements Comparable<WordInfo>, Serializable {

    private final String word;
    private final int lineNumber;
    private final ArrayList<String> fileNames = new ArrayList<>();

    /**
     * Constructs a WordInfo object with the specified word, line number, and file name.
     *
     * @param word the word being represented
     * @param lineNumber the line number where the word appears
     * @param fileName the file name where the word appears
     */
    public WordInfo(String word, int lineNumber, String fileName) {
        this.word = word;
        this.lineNumber = lineNumber;
        addFileName(fileName);
    }

    /**
     * Gets the word represented by this WordInfo object.
     *
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * Gets the line number where the word appears.
     *
     * @return the line number
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Gets the list of file names where the word occurs.
     *
     * @return the list of file names
     */
    public ArrayList<String> getFileNames() {
        return fileNames;
    }

    /**
     * Adds a file name to the list of file names if it's not already present.
     * The file name is extracted from the given input using a specific format.
     *
     * @param fileName the full file path
     */
    public void addFileName(String fileName) {
        if(!(fileNames.contains(fileName.split("/", 3)[2]))) fileNames.add(fileName.split("/", 3)[2]);
    }

    /**
     * Compares this WordInfo object with another WordInfo object based on the word.
     *
     * @param o the other WordInfo object to compare
     * @return a negative integer, zero, or a positive integer if this object is less than,
     *         equal to, or greater than the other object
     */
    @Override
    public int compareTo(WordInfo o) {
        if (this.getWord().compareTo(o.getWord()) > 0) return 1;
        else if (this.getWord().compareTo(o.getWord()) < 0) return -1;
        return 0;
    }
}
