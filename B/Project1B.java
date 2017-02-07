/** 
@author Vincent A Masiello II
Project 01 - Bags1 part B 
Project1B.java
*/

import java.io.*;
import java.util.*; //Hashtable

public class Project1B {
	public static void main (String[] args) throws IOException {
		
		String PATH_TO_ARTIST_INPUT_FILE = "p1artists.txt";
		String PATH_TO_ARTS_INPUT_FILE = "p1arts.txt";
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
		FileHandler.writeData(PATH_TO_ARTS_OUTPUT_FILE, writeList);
		
	} //end main
	
	private static BagInterface<Artist> readArtistsFromFile(String path) throws IOException {
		BagInterface<Artist> result = new ArrayBag<Artist>();
		List<String> lines = FileHandler.readData(path);
		
		for (Iterator<String> it = lines.iterator(); it.hasNext(); ) {
			//ignore empty lines
			if (!it.next().equals("")) {
				String line = it.next();
				String[] props = line.split("\\t");
				Artist anArtist = new Artist(Integer.parseInt(props[0]), props[1]);
				result.add(anArtist);
			}
		}
		return result;
	}
	
	private static BagInterface<Art> readArtsFromFile(String path) throws IOException {
		BagInterface<Art> result = new ArrayBag<Art>();
		List<String> lines = FileHandler.readData(path);
		//ignore empty lines
		for (Iterator<String> it = lines.iterator(); it.hasNext(); ) {
			if (!it.next().equals("")) {
				String line = it.next();
				String[] props = line.split("\\t");
				Art anArt = new Art(Integer.parseInt(props[0]), props[1], Integer.parseInt(props[2]), Integer.parseInt(props[3]));
				
				result.add(anArt);
			}
		}
		return result;
	}
} //end class Project1B



