package model.enums;

/**
 * this enum defines the available FSK ratings for movies, contains FSKs from 0
 * up to 18 and unrated
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public enum FSKs {

	FSK_0("ab 0"), FSK_6("ab 6"), FSK_12("ab 12"), FSK_16("ab 16"), FSK_18("ab 18"), FSK_UNRATED("/");

	// data field, contains readable fsk as a String
	private final String fsk;

	/**
	 * constructor, assigns data field
	 * 
	 * @param fsk String with the fsk
	 */
	FSKs(String fsk) {
		this.fsk = fsk;
	}

	/**
	 * get the String for the enum
	 * 
	 * @return a String with the fsk
	 */
	public String getFsk() {
		return "FSK: " + fsk;
	}
}