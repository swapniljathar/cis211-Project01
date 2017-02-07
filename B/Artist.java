/** 
@author Vincent A Masiello II
Project 01 - Bags1 part B 
Artist.java
*/

public class Artist {
	private int artistID;
	private String artistName;
	
	//constructors
	Artist(int id, String name) {
		artistID = id;
		artistName = name;
	}
	
	// @getters
	/** gets the artist ID
		@return		the integer ID for this artist */
	public int getArtistID() {
		return artistID;
	}
	
	/**	gets the artists name
		@return		the artists's name as a String */
	public String getArtistName() {
		return artistName;
	}
	
	// @setters
	/** set the artist ID
		@param	ID	integer to set as the artist ID */
	public void setArtistID(int id) {
		artistID = id;
	}
	
	/** set the artist name
		@param	name	the string to set to artistName */
	public void setArtistName(String name) {
		artistName = name;
	}
	
	/** return a string from the artist object
		@return		the artist object as a string ready for output */
	public String toString() {
		return artistID + "		" + artistName;
	}
}