package model.enums;

/**
 * this enum defines the available FSK ratings for movies,
 * contains FSKs from 0 up to 18 and unrated
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public enum FSKs {
	FSK_0,
	FSK_6,
	FSK_12,
	FSK_16,
	FSK_18,
	FSK_UNRATED;

	/**
	 * converts the enum to a string
	 * @return FSK as a readable String
	 */
	public String getFSK() {
		switch (this) {
			case FSK_0:
				return "FSK: ab 0";
			case FSK_6:
				return "FSK: ab 6";
			case FSK_12:
				return "FSK: ab 12";
			case FSK_16:
				return "FSK: ab 16";
			case FSK_18:
				return "FSK: ab 18";
			case FSK_UNRATED:
				return "FSK: /";
			default:
				return "FSK: /";
		}
	}
}