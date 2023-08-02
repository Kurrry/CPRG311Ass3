package tracker;

import java.io.Serializable;

public class WordInfo implements Comparable<WordInfo>, Serializable {

    private String word;
    private int lineNumber;
    private String fileName;

    public WordInfo(String word, int lineNumber, String fileName) {
        this.word = word;
        this.lineNumber = lineNumber;
        this.fileName = fileName;
    }

    public String getWord() {
        return word;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public int compareTo(WordInfo o) {
        if (this.getWord().compareTo(o.getWord()) > 0) return 1;
        else if (this.getWord().compareTo(o.getWord()) < 0) return -1;
        return 0;
    }
}
