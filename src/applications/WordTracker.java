/**
 * Created on Dec 10, 2023
 * 
 * Project: Assignment03-Binary Search Trees and Serialization
 */
package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import treepackage.BSTreeNode;
import utilities.BSTree;

/**
 * WordTracker.java
 * 
 * @author Jiyeon Heo, Tze-chi Chan, and Parinthorn Songsana
 * @version 1.0
 * 
 * Class Description: This class represents a word tracker that process text files, maintain a binary search tree of word information,
 * pringting word reports, and serializing/deserializing the tree.
 */
public class WordTracker implements Serializable {
	
	/**
	 * Serializable version ID to ensure compatibility during object serialization.
	 */
	private static final long serialVersionUID = -5340889282525262909L;
	
	//attributes
	private BSTree<WordInfo> wordTree;
	private String currentDir = System.getProperty("user.dir");
	private List<String> processedFiles = new ArrayList<>();
	
	/**
	 * Constructor for WordTracker class.
	 * Initializes a new instance of the WordTracker with an empty BSTree.
	 */
	public WordTracker() {
		this.wordTree = new BSTree<>();
	}
	
	/**
	 * Processes a text file, extracting words, and updating the wordTree.
	 * 
	 * @param filePath The path to the text file to be processed.
	 */
	public void processTextFile(String filePath) {
		
		if(processedFiles.contains(filePath)) {
			System.exit(1);
		}
		
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			String line;
			int lineNumber = 1;
			
			while((line = reader.readLine()) != null) {
				processLine(line, lineNumber, filePath);
				lineNumber ++;
			}
			
			processedFiles.add(filePath);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound: here? " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Processes a line of text, extracting words and updating the wordTree.
	 * 
	 * @param line The line of text to be passed.
	 * @param lineNumber The line number in the file.
	 * @param filePath The path to the file being processed.
	 */
	private void processLine(String line, int lineNumber, String filePath) {
		String[] words = line.split("\\s");
		
		File file = new File(filePath);
		String fileName = file.getName();
		
		for(int i = 0; i<words.length ; i++) {
			String word = words[i].replaceAll("[^a-zA-Z]", "").toLowerCase();
			
			if(!word.isEmpty()) {
				
				if(!wordTree.isEmpty()) {
					WordInfo wordInfo = new WordInfo(word);
					wordInfo.addLineNumbers(fileName, lineNumber);
					
					BSTreeNode<WordInfo> existingNode = wordTree.search(wordInfo);
					
					if(existingNode != null) {
						existingNode.getData().addLineNumbers(fileName,lineNumber);
					}else {
						wordTree.add(wordInfo);
					}
				}else {
					WordInfo wordInfo = new WordInfo(word);
					wordInfo.addLineNumbers(fileName, lineNumber);
					wordTree.add(wordInfo);
				}

			}
		}
	}
	
	/**
	 * Prints the word report in alphabetic order.
	 * 
	 * @param wordReport StringBuilder to store the word report.
	 */
	public void printWordReport(StringBuilder wordReport ) {
		System.out.println("Word Report (Alphabetic Order)");
		wordReport.append("Word Report (Alphabetic Order) \n");
		inorderTraversal(wordTree.getRoot(), wordReport);
	}
	
	/**
	 * Performs an inorder traversal of the BSTree, printing word information
	 * 
	 * @param node The current node in the traversal.
	 * @param wordReport StringBuilder to store the word report.
	 */
	private void inorderTraversal(BSTreeNode<WordInfo> node, StringBuilder wordReport) {
		if(node != null) {
			String word = node.getData().getWord();
			inorderTraversal(node.getLeftChild(), wordReport);	
			System.out.println(word);
			wordReport.append(word).append("\n");
			inorderTraversal(node.getRightChild(),wordReport);
		}
	}
	
	/**
	 * Prints the word report with line numbers in alphabetic order.
	 * 
	 * @param wordReport StringBuilder to store the word report.
	 */
	public void printWordReportWithLineNumbers(StringBuilder wordReport ) {
		System.out.println("Word Report with Line Numbers (Alphabetic Order)");
		wordReport.append("Word Report with Line Numbers (Alphabetic Order) \n");
		inorderTraversalWithLineNumber(wordTree.getRoot(), wordReport);
	}
	
	/**
	 * Performs an inorder traversal of the BSTree, printing word information with line numbers.
	 * 
	 * @param node The current node in the traversal.
	 * @param wordReport StringBuilder to store the word report.
	 */
	private void inorderTraversalWithLineNumber(BSTreeNode<WordInfo> node, StringBuilder wordReport) {
		if(node != null) {
			String word = node.getData().getWord();
			inorderTraversalWithLineNumber(node.getLeftChild(), wordReport);
			Map<String, List<Integer>> fileLineNumbers = node.getData().getFileLineNumbers();
			for(Entry<String, List<Integer>> entry: fileLineNumbers.entrySet()) {
				String fileName = entry.getKey();
				List<Integer> lineNumbers = entry.getValue();
				System.out.println(word + " in " +  fileName + " line numbers are " + lineNumbers);
				
				wordReport.append(word)
						.append(" in ").append(fileName)
						.append(" line numbers are  ").append(lineNumbers).append("\n");
			}
			inorderTraversalWithLineNumber(node.getRightChild(), wordReport);
		}
	}
	
	/**
	 * Prints the word report with line numbers and frequency in alphabetic order.
	 * 
	 * @param wordReport StringBuilder to store the word report.
	 */
	public void printWordReportWithFrequency(StringBuilder wordReport ) {
		System.out.println("Word Report with Frequency (Alphabetic Order)");
		wordReport.append("Word Report with Frequency (Alphabetic Order) \n");
		inorderTraversalWithFrequency(wordTree.getRoot(), wordReport);
	}
	
	/**
	 * Performs an inorder traversal of the BSTree, printing word information with line numbers and frequency.
	 * 
	 * @param node The current node in the traversal.
	 * @param wordReport StringBuilder to store the word report.
	 */
	private void inorderTraversalWithFrequency(BSTreeNode<WordInfo> node, StringBuilder wordReport) {
		if(node != null) {
			if(node != null) {
				String word = node.getData().getWord();
				
				inorderTraversalWithFrequency(node.getLeftChild(), wordReport);
				Map<String, List<Integer>> fileLineNumbers = node.getData().getFileLineNumbers();
				for(Entry<String, List<Integer>> entry: fileLineNumbers.entrySet()) {
					String fileName = entry.getKey();
					List<Integer> lineNumbers = entry.getValue();
					int frequency = node.getData().getFrequency(fileName);
					
					System.out.println(word + " in " +  fileName + " line numbers are " + lineNumbers + " Frequency is " + frequency);
					
					wordReport.append(word)
							.append(" in ").append(fileName)
							.append(" line numbers are ").append(lineNumbers)
							.append(" Frequency is ").append(frequency).append("\n");
				}
				inorderTraversalWithFrequency(node.getRightChild(), wordReport);
			}
		}
		
	}
	
	/**
	 * Serializes the wordTree to a binary file for later use.
	 * The file is store in the 'res' directory.
	 */
	public void serializeTreeToFile() {
		System.out.println("directory " + currentDir);
		File repositoryFile = new File(currentDir + "/res/repository.ser");
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(repositoryFile))){
			oos.writeObject(wordTree);
			System.out.println("WordTree is stored in the binary file (..res/repository.ser)");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deserializes the wordTree from a binary file.
	 * If the file does not exist, starts with an empty tree.
	 * The file is expected to be in the 'res' directory.
	 */
	@SuppressWarnings("unchecked")
	public void deserializeTreeFromFile() {
		File repositoryFile = new File(currentDir + "/res/repository.ser");
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repositoryFile))){
			wordTree = (BSTree<WordInfo>) ois.readObject();
			System.out.println("WordTree is loaded from ../res/repository.ser");
		} catch (FileNotFoundException e) {
			System.out.println("repository.ser does not exist. Start with an empty tree");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// generate output.txt
	/**
	 * Writes the word report to a text file.
	 * The file is stored in the 'res' directory with the given fileName.
	 * 
	 * @param fileName The name of the output text file.
	 * @param wordReport The content of the word report to be written.
	 */
	public void writeWordReportToFile(String fileName, StringBuilder wordReport) {
		File outputFile = new File(currentDir + "/res/" + fileName);
		try(PrintWriter writer = new PrintWriter(new FileWriter(outputFile))){
			writer.println(wordReport);
			System.out.println("Word report is stored in " + outputFile );
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
