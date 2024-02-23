# WordTracking program (Data Structure)

## Program Language Used & Version
-Java / Version 1.8.0_202

## IDE Used
-Eclipse

## Introduction
-This is a word tracking program can store all the words in text files with file name and line numbers, it will display words in Alphabetic Order .

## Installation & Usage
1. Check your JDK version, using 'java -version', or 'javac -version' on the terminal. It should be higher than 1.8.0_202.
2. If you cannot check any on terminal. You should download it first to use this program.
3. Open the terminal, and compile the Java Jar file by following command.
 `java -jar WordTracker.jar <input.txt> -pf/-pl/-po [-f <output.txt>]`

4. Run the program using WordTracker.jar file.
 - ex) `java -jar WordTracker.jar C:\Address of the file\res\textfile.txt -pf -f output.txt`
	- '-pf' is for displaying all words in the text file
	- '-pl' is for displaying all words with it's occurring line numbers.
	- '-po' is for displaying all words with it's occurring line numbers and frequency of occuring.
 	- '-f' (optional) argument to redirect the report in the previous step to the path and filename specified in 'output.txt'


## Limitations
-This program is designed for tracking words with file name and line numbers.
-To run this program, it requires at least 2 options like fileName or path, and print option(pf, pl, or po). If you do not provide that information. It won't work.
-Provide an absolute path if the program cannot find the file name.
-An optional argument is (-f output.txt), including space between '-f' and the output file name.
-The repository.ser and the output files will be stored in the 'res' folder.
-The passed file names are stored in 'res/processedFiles.txt', which prevents duplicate data.


## File Path
WordTracker
├── src
│   ├── application
│   │   ├── Driver.java
│   │   ├── WordInfo.java
│   │   ├── WordTracker.java
│   │   └── WordTrackerManager.java
│   ├── exceptions
│   │   └── TreeException.java
│   ├── treepackage
│   │   └── BSTreeNode.java
│   ├── utilities
│   │   ├── BSTree.java
│   │   ├── BSTreeADT.java
│   │   └── Iterator.java
├── test
│   ├── utilities
│   │   └── BSTreeTest.java
└── res
    ├── processedFiles.txt	
    └── textfile.txt
