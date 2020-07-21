package model.enums;

/**
 * this enum defines the available prices,
 * contains times from 3pm to 10pm in 30min steps
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public enum Times {
    PM_3,
    PM_3_30,
    PM_4,
    PM_4_30,
    PM_5,
    PM_5_30,
    PM_6,
    PM_6_30,
    PM_7,
    PM_7_30,
    PM_8,
    PM_8_30,
    PM_9,
    PM_9_30,
    PM_10;

    /**
	 * converts the enum to a string
	 * @return time as a readable String in "HH:MM Uhr" format
	 */
    public String getTime() {
		switch (this) {
            case PM_3:
                return "15:00 Uhr";
            case PM_3_30:
                return "15:30 Uhr";
            case PM_4:
                return "16:00 Uhr";
            case PM_4_30:
                return "16:30 Uhr";
            case PM_5:
                return "17:00 Uhr";
            case PM_5_30:
                return "17:30 Uhr";
            case PM_6:
                return "18:00 Uhr";
            case PM_6_30:
                return "18:30 Uhr";
            case PM_7:
                return "19:00 Uhr";
            case PM_7_30:
                return "19:30 Uhr";
            case PM_8:
                return "20:00 Uhr";
            case PM_8_30:
                return "20:30 Uhr";
            case PM_9:
                return "21:00 Uhr";
            case PM_9_30:
                return "21:30 Uhr";
            case PM_10:
                return "22:00 Uhr";
            default:
                return "/ Uhr";
        }
    }
}