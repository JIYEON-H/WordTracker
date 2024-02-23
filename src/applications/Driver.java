/**
 * Created on Dec 10, 2023
 * 
 * Project: Assignment03-Binary Search Trees and Serialization
 */
package applications;

/**
 * Driver.java
 * 
 * @author Jiyeon Heo, Tze-chi Chan, and Parinthorn Songsana
 * @version 1.0
 *
 * Class Description: This class launch the assignment03 application.
 */
public class Driver {
	public static void main(String[] args) {
		
		if(args.length < 2 || args.length > 4) {
			System.out.println("Usage: java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]");
			return;
		}
		
		WordTrackerManager manager = new WordTrackerManager(args);
		manager.runWordTracker();		
	}
}
