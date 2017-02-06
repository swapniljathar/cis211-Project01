/** 
@author Vincent A Masiello II
Project 01 - Bags1 part B 
Art.java
*/

public class Art {
	private int artID;
	private String title;
	private int artistID;
	private int value;
	
	Art(int pArtID, String pTitle, int pArtistID, int pValue) {
		artID = pArtID;
		title = pTitle;
		artistID = pArtistID;
		value = pValue;
	}
	
	private int getArtID() {
		return artID;
	}
	
	private String getTitle() {
		return title;
	}
	
	private int getArtistID() {
		return artistID;
	}
	
	private int getValue() {
		return value;
	}
	
	private void setArtID(int pArtID) {
		artID = pArtID;
	}
	
	private void setTitle(String pTitle) {
		title = pTitle;
	}
	
	private void setArtistID(int pArtistID) {
		artistID = pArtistID;
	}
	
	private void setValue(int pValue) {
		value = pValue;
	} 
	
	public String toString() {
		return artID + "	" + title + "	" + artistID + "	" + value;
	}
}