package model.enums;

public enum FSK {
    FSK_0, FSK_6, FSK_12, FSK_16, FSK_18, FSK_UNRATED;

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