/** 
@author Vincent A Masiello II
Project 01 - Bags1 part B 
FileHandler.java
*/
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
	/* Read data from a file and return it as a List<String> object to be
		parsed later.
		@params	path	String of the path to the desired input file
		@return			List<String> object holds each line of the file as a String
						to be parsed later. */
	public static List<String> readData(String path) throws IOException {
		Path wPath = Paths.get(path);
		
		if (Files.exists(wPath) && Files.isReadable(wPath)) {
			return Files.readAllLines(wPath);
		} else {
			System.out.println("Error: " + wPath +" file does not exist or is not readable!");
			return null;
		}
	}
	
	/* write a List<String> to a file path 
		@params	path	String contains the desired output path
				data	the List<String> data to be written
	*/
	public static void writeData(String path, List<String> data) throws IOException {
		Path wPath = Paths.get(path);
		Files.write(wPath, data);
	}
}