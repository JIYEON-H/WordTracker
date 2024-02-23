/**
 * Created on Dec 10, 2023
 * 
 * Project: Assignment03-Binary Search Trees and Serialization
 */
package applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * WordTrackerManager.java
 * 
 * @author Jiyeon Heo, Tze-chi Chan, and Parinthorn Songsana
 * @version 1.0
 * 
 * Class Description: This class manages the execuion of the WordTracker program.
 * It parses command-line arguments, initializes the WordTracker instance, processes input files, generates word reports based on user options,
 * and serializes/deserializes the word tree to/from a binary file.
 *
 */
public class WordTrackerManager {
	
	//attributes
	private String inputFileName = null;
	private String option = null;
	private String outputFile = null;
	private Set<String> processedFiles;
	private String currentDir = System.getProperty("user.dir");
	
	/**
	 * Constructor to initialize a WordTrackerManager instance.
	 * 
	 * @param args Command-line arguments passed to the program.
	 */
	public WordTrackerManager(String[] args) {
		parseArguments(args);
		loadProcessedFiles();
	}
	
	/**
	 * Parses command-line arguments to extract input file name, user option, and output file name.
	 * Displays an error message and exits the program if the arguments are invalid.
	 * 
	 * @param args Command-line arguments passed to the program.
	 */
	public void parseArguments(String[] args) {
		try {

			if(args.length < 2) {throw new IllegalArgumentException("Invalid argument. Usage: java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]");}
			
			inputFileName = args[0];
			option = args[1];
			
			if(args.length > 2 && "-f".equals(args[2])) {
				if(args.length == 4) {
					outputFile = args[3];
				}else {
					throw new IllegalArgumentException("Invalid argument. Usage: java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]");
				}
			}
			
			processedFiles = new HashSet<>();
			
		}catch(IllegalArgumentException e) {
			System.out.println("Invalid arguments. Usage: java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]");
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * Loads processed files from the 'res/processedFiles.txt' file.
	 */
	private void loadProcessedFiles() {
        try (BufferedReader reader = new BufferedReader(new FileReader(currentDir + "/res/processedFiles.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processedFiles.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/**
	 * Saves processed files to the 'res/processedFiles.txt' file.
	 */
	 private void saveProcessedFiles() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(currentDir + "/res/processedFiles.txt"))) {
            for (String processedFile : processedFiles) {
                writer.println(processedFile);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	/**
	 * Executes the WordTracker program by initializing a WordTracker instance, processing the input file,
	 * generating word reports, and serializing the word tree to a binary file
	 */
	public void runWordTracker() {
		
		WordTracker wordTracker = new WordTracker();
		
		File checkSerFile = new File(System.getProperty("user.dir") + "/res/repository.ser");
		
		// Check this file already had passed.
		if(processedFiles.contains(inputFileName)) {
			System.out.println("This file has already been processed by the program.");
			return;
		}
		
		if(checkSerFile.exists()) {
			wordTracker.deserializeTreeFromFile();
		}
		
		wordTracker.processTextFile(inputFileName);
		
		processedFiles.add(inputFileName);
		saveProcessedFiles();
		
		StringBuilder wordReport = new StringBuilder();
		
		switch(option) {
		case "-pf":
			wordTracker.printWordReport(wordReport);
			break;
		case "-pl":
			wordTracker.printWordReportWithLineNumbers(wordReport);
			break;
		case "-po":
			wordTracker.printWordReportWithFrequency(wordReport);
			break;
		default :
			System.out.println("Invalid option. Use -pf, -pl, or -po");
			System.exit(1);
		}
		
		if(outputFile != null) {
			wordTracker.writeWordReportToFile(outputFile, wordReport);
		}
		
		wordTracker.serializeTreeToFile();
	}

}
