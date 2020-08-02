package model.enums;

/**
 * this enum defines the available weekdays for the Showtimes of movies,
 * contains all seven days of the week
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public enum Days {

    MONDAY("Mo"), TUESDAY("Di"), WEDNESDAY("Mi"), THURSDAY("Do"), FRIDAY("Fr"), SATURDAY("Sa"), SUNDAY("So");

    // data field, contains readable day as a String
    private final String day;

    /**
     * constructor, assigns data field
     * 
     * @param day String with the day
     */
    Days(String day) {
        this.day = day;
    }

    /**
     * get the String for the enum
     * 
     * @return a String with the day
     */
    public String getDay() {
        return day;
    }
}