/**
 * Created on Dec 10, 2023
 * 
 * Project: Assignment03-Binary Search Trees and Serialization
 */
package applications;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WordInfo.java
 * 
 * @author Jiyeon Heo, Tze-chi Chan, and Parinthorn Songsana
 * @version 1.0
 *
 * Class Description: Represents information about a word, 
 * including the word itself, the files in which it occurs, and the line numbers in those files
 */
public class WordInfo implements Comparable<WordInfo>, Serializable{
	
	/**
	 * Serializable version ID to ensure compatibility during object serialization.
	 */
	private static final long serialVersionUID = 784939176759588959L;
	
	// attributes
	private String word;
    private Map<String, List<Integer>> fileLineNumbers;
	
    /**
     * Constructor to create a WordInfo instance with the specified word.
     * 
     * @param word The word for which information is stored.
     */
	public WordInfo(String word) {
		this.word = word;
		this.fileLineNumbers = new HashMap<>();
	}
	
	/**
	 * Gets the word associated with this WordInfo instance.
	 * 
	 * @return The word
	 */
	public String getWord() {
		return word;
	}
	
	/**
	 * Gets the mapping of file names to line numbers where the word occurs.
	 * 
	 * @return The map of file names to line numbers
	 */
	public Map<String, List<Integer>> getFileLineNumbers() {
        return fileLineNumbers;
    }

    /**
     * Adds line numbers for the specified file where the word occurs.
     * 
     * @param fileName The name of the file.
     * @param lineNumber The line number where the word occurs.
     */
    public void addLineNumbers(String fileName, int lineNumber) {
    	fileLineNumbers.computeIfAbsent(fileName, k -> new ArrayList<>()).add(lineNumber);
    }
    
    /**
     * Gets the frequency of the word's occurrence in the specified file.
     * 
     * @param fileName The name of the file.
     * @return The frequency of the word in the file, the size of lineNumbers
     */
    public int getFrequency(String fileName) {
    	List<Integer> lineNumbers = fileLineNumbers.get(fileName);
    	return lineNumbers != null ? lineNumbers.size() : 0;
    }

	@Override
	public int compareTo(WordInfo o) {
		return this.word.compareTo(o.word);
	}
	
}
