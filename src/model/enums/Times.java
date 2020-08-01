package model.enums;

/**
 * this enum defines the available prices,
 * contains times from 3pm to 10pm in 30min steps
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public enum Times {
    
    PM_03_00("15:00"),
    PM_03_30("15:30"),
    PM_04_00("16:00"),
    PM_04_30("16:30"),
    PM_05_00("17:00"),
    PM_05_30("17:30"),
    PM_06_00("18:00"),
    PM_06_30("18:30"),
    PM_07_00("19:00"),
    PM_07_30("19:30"),
    PM_08_00("20:00"),
    PM_08_30("20:30"),
    PM_09_00("21:00"),
    PM_09_30("21:30"),
    PM_10_00("22:00");

    // data field, contains readable time as a String
    private final String time;

    /**
     * constructor, assigns data field
     * @param time String with the time
     */
    Times(String time) {
        this.time = time;
    }

    /**
     * get the String for the enum
     * @return a String with the time
     */
    public String getTime() {
        return time + " Uhr";
    }
}