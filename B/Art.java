/** 
@author Vincent A Masiello II
Project 01 - Bags1 part B 
Art.java
*/

public class Art {
	private int artID;
	private String title;
	private int artistID;
	private String artistName;
	private int value;
	
	private boolean artistNameFlag = false;
	
	//@constructors
	public Art(int pArtID, String pTitle, int pArtistID, int pValue) {
		artID = pArtID;
		title = pTitle;
		artistID = pArtistID;
		value = pValue;
	}
	
	/* If Art is constructed with this signiture, set the artistName flag */
	public Art(int pArtID, String pTitle, int pArtistID, String pArtistName, int pValue) {
		artID = pArtID;
		title = pTitle;
		artistID = pArtistID;
		artistName = pArtistName;
		value = pValue;
		
		artistNameFlag = true;
	}
	
	//@getters
	public int getArtID() {
		return artID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getArtistID() {
		return artistID;
	}
	
	public String getArtistName() {
		return artistName;
	}
	
	public int getValue() {
		return value;
	}
	
	//@setters
	public void setArtID(int pArtID) {
		artID = pArtID;
	}
	
	public void setTitle(String pTitle) {
		title = pTitle;
	}
	
	public void setArtistID(int pArtistID) {
		artistID = pArtistID;
	}
	
	/* When artistName is set, also set the artistNameFlag to true */
	public void setArtistName(String pArtistName) {
		artistName = pArtistName;
		artistNameFlag = true;
	}
	
	public void setValue(int pValue) {
		value = pValue;
	} 
	
	/* return the Art object as a formatted string 
		return		formatted string based on the existance of
					artistName. */
	public String toString() {
		String result = artID + "	" + title + "	" + artistID;
		if (artistNameFlag && !artistName.equals("")) {
			result = result + "	" + artistName; 
		}
		return result + "	" + value;
	}
}