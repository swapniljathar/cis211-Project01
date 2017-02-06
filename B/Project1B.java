/** 
@author Vincent A Masiello II
Project 01 - Bags1 part B */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.Iterator;

/* TODO:
	test artist name append and file output.
	then clean it up, you nasty. 
	change error messages to Exceptions
	combine file reading->returning List portion and abstract out Arts/Artist code
*/
public class Project1B {
	public static void main (String[] args) throws IOException {
		
		String PATH_TO_ARTIST_INPUT_FILE = "p1artists.txt";
		String PATH_TO_ARTS_INPUT_FILE = "p1arts.txt";
		String PATH_TO_ARTIST_OUTPUT_FILE = "p1artists_out.txt";
		String PATH_TO_ARTS_OUTPUT_FILE = "p1arts_out.txt";
		
		//read in both artists and arts from file
		BagInterface<Artist> artistRoster = readArtistsFromFile(PATH_TO_ARTIST_INPUT_FILE);
		BagInterface<Art> listOfArts = readArtsFromFile(PATH_TO_ARTS_INPUT_FILE);
		
		List<String> artsByArtist = new List<String>();
		
		//for each art
		for (int i=0; i<listOfArts.getCurrentSize(); i++) {
			Object[] artBagArray = listOfArts.toArray();
			
			//build next line
			//init with capacity 36, doubles +2 on append if needed
			Stringbuilder builder = new StringBuilder(36); 
			builder.append(artBagArray[i].toString());
			int thisArtArtistID = artBagArray.getArtistID();
			
			//for each artist,
			for (int j=0; j<artistRoster.getCurrentSize(); j++) {
				Object[] artistBagArray = artistRoster.toArray();
				int thisArtistID = artistBagArray[j].getArtistID();
				
				// add to next line string if IDs match.
				if (thisArtistID == thisArtArtistID) {
					builder.append(artistBagArray[j].getName());
				}
			}
			//add to List<String>
			artsByArtist.add(builder.toString());
		}
		
		//write arts w/ artist name to output file
		Files.write(PATH_TO_ARTS_OUTPUT_FILE, artsByArtist);
		
	} //end main
	
	private static BagInterface<Artist> readArtistsFromFile(String path) throws IOException {
		Path p = Paths.get(path);
		
		BagInterface<Artist> result = new ArrayBag<Artist>();
		
		if (Files.exists(p) && Files.isReadable(p)) {
			
			List<String> lines = Files.readAllLines(p);
			for (Iterator<String> it = lines.iterator(); it.hasNext(); ) {
				//ignore empty lines
				if (!it.next().equals("")) {
					String line = it.next();
					String[] props = line.split("\\t");
					Artist anArtist = new Artist(Integer.parseInt(props[0]), props[1]);
					result.add(anArtist);
				} else {
					System.out.println("detected empty line in file");
				}
			}
		} else {
			System.out.println("Error: " + p +" file does not exist or is not readable!");
		}
		return result;
	}
	
	private static BagInterface<Art> readArtsFromFile(String path) throws IOException {
		Path p = Paths.get(path);
		
		BagInterface<Art> result = new ArrayBag<Art>();
		
		if (Files.exists(p) && Files.isReadable(p)) {
			
			List<String> lines = Files.readAllLines(p);
			//ignore empty lines
			for (Iterator<String> it = lines.iterator(); it.hasNext(); ) {
				if (!it.next().equals("")) {
					String line = it.next();
					String[] props = line.split("\\t");
					Art anArt = new Art(Integer.parseInt(props[0]), props[1], Integer.parseInt(props[2]), Integer.parseInt(props[3]));
					System.out.println(anArt);
					result.add(anArt);
				} else {
					System.out.println("detected empty line");
				}
			}
		} else {
			System.out.println("Error: " + p + " file does not exist or is not readable!");
		}
		return result;
	}
	/*
	private static boolean writeDataToFile(String path, BagInterface<Arts> listOfArts) {
		boolean result = true; //store success or failure of write op
		
		Path p = Paths.get(path);
		if (Files.isWritable(p)) {
			
		} else  {
			System.out.println("Error: " + p + " file is not writable. Check permissions.");
		}
		return result;
	}*/
} //end class Project1B



