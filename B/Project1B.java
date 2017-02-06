/** 
@author Vincent A Masiello II
Project 01 - Bags1 part B */

import java.io.*;
import java.nio.file.*;
import java.util.*; //Hashtable


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
		
		//build a hash table of <artistID, ArtistName>
		Hashtable<Integer, String> idHash = new Hashtable<Integer, String>();
		for (int i=0; i<artistRoster.getCurrentSize(); i++) {
			Object[] roster = artistRoster.toArray();
			
			//cast obj back into Artist
			Artist artist = (Artist)roster[i];
			
			//System.out.println(thisArtist.getArtistName());
			idHash.put(artist.getArtistID(), artist.getArtistName());
		}
		
		List<String> writeList = new ArrayList<String>();
		
		//for each art
		for (int i=0; i<listOfArts.getCurrentSize(); i++) {
			Object[] artBagArray = listOfArts.toArray();
			
			//cast back into Art
			Art art = (Art)artBagArray[i];
			int id = art.getArtistID();
			
			String name = idHash.get(art.getArtistID());
			
			//if no artistName exists
			if (name == null) {
				art.setArtistName("N/A"); //assign placeholder
			} else {
				art.setArtistName(name); //else set name
			}
			
			writeList.add(art.toString());
		}
		
		//write arts w/ artist name to output file
		Path path = Paths.get(PATH_TO_ARTS_OUTPUT_FILE);
		Files.write(path, writeList);
		
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
} //end class Project1B



