/** 
@author Vincent A Masiello II
Project 01 - Bags1 part B 
Project1B.java
*/

import java.io.*;
import java.util.*; //Hashtable

public class Project1B {
	public static void main (String[] args) throws IOException {
		
		/* The data file paths need only to be changed once, from here */
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
		
		/* Add the artist name to each piece of art with an artistID for an 
			existing artist, then write the new list to file. */
		List<String> writeList = new ArrayList<String>();
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
		
		FileHandler.writeData(PATH_TO_ARTS_OUTPUT_FILE, writeList);
		
		//test getTotalArtValue()
		String valOut = "Total Value of all Art Pieces: ";
		System.out.println(valOut + getTotalArtValue(listOfArts));
	} //end main
	
	/* Read Artist data from file and parse into an ArrayBag.
		@param	path	path string containg the input data path.
		@return			An ArrayBag containing Artist objects */
	public static BagInterface<Artist> readArtistsFromFile(String path) throws IOException {
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
	
	/* Read Arts data from file and parse into an ArrayBag.
		@param	path	String containing the input data path.
		@return			An ArrayBag containing Art objects */
	public static BagInterface<Art> readArtsFromFile(String path) throws IOException {
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
	
	/* Add together the value of each art
		@params	arts	ArrayBag containing art objects
		@returns		integer value of all art pieces
	*/
	public static int getTotalArtValue(BagInterface<Art> arts) {
		Object[] bag = arts.toArray();
		int result = 0;
		for (int i=0;i<arts.getCurrentSize();i++) {
			Art a = (Art)bag[i];
			result+=a.getValue();
		}
		return result;
	}
} //end class Project1B



