package model.enums;

/**
 * this enum defines the available weekdays for the Showtimes of movies,
 * contains all seven days of the week
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public enum Dates {
    MONDAY("Mo"),
    TUESDAY("Di"),
    WEDNESDAY("Mi"),
    THURSDAY("Do"),
    FRIDAY("Fr"),
    SATURDAY("Sa"),
    SUNDAY("So");

    // data field, contains readable date as a String
    private final String date;

    /**
     * constructor, assigns data field
     * @param date String with the date
     */
    private Dates(String date) {
        this.date = date;
    }

    /**
     * get the String for the enum
     * @return a String with the date
     */
    public String getDate() {
        return date;
    }
}