/** 
@author Vincent A Masiello II
Project 01 - Bags1 part B 
FileHandler.java
*/
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
	public static List<String> readData(String path) throws IOException {
		Path wPath = Paths.get(path);
		
		if (Files.exists(wPath) && Files.isReadable(wPath)) {
			return Files.readAllLines(wPath);
		} else {
			System.out.println("Error: " + wPath +" file does not exist or is not readable!");
			return null;
		}
	}
	
	public static void writeData(String path, List<String> data) throws IOException {
		Path wPath = Paths.get(path);
		Files.write(wPath, data);
	}
}