package tracker;

import java.io.Serializable;
import java.util.ArrayList;

public class WordInfo implements Comparable<WordInfo>, Serializable {

    private final String word;
    private final int lineNumber;
    private final ArrayList<String> fileNames = new ArrayList<>();

    public WordInfo(String word, int lineNumber, String fileName) {
        this.word = word;
        this.lineNumber = lineNumber;
        addFileName(fileName);
    }

    public String getWord() {
        return word;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public ArrayList<String> getFileNames() {
        return fileNames;
    }

    public void addFileName(String fileName) {
        if(!(fileNames.contains(fileName.split("/", 3)[2]))) fileNames.add(fileName.split("/", 3)[2]);
    }

    @Override
    public int compareTo(WordInfo o) {
        if (this.getWord().compareTo(o.getWord()) > 0) return 1;
        else if (this.getWord().compareTo(o.getWord()) < 0) return -1;
        return 0;
    }
}
