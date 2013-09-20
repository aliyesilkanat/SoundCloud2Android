package com.example.androiddeneme2;

public class Track {
	private int ID;
	private String Link;
	private String Title;
	public Track(int iD, String link, String title) {
		ID = iD;
		Link = link;
		Title = title;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return Title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		Title = title;
	}
	public Track(int iD, String link) {
		ID = iD;
		Link = link;
	}
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return Link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		Link = link;
	}

}
