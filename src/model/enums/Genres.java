package model.enums;

/**
 * This enum defines the genres for movies
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public enum Genres {
    ACTION("Action"),
    FAMILY("Familie"),
    WESTERN("Western"),
    THRILLER("Thriller"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    SCIFI("SciFi"),
    DRAMA("Drama"),
    DOCU("Doku");

    // data field, contains readable genre as a String
    private final String genre;

    /**
     * constructor, assigns data field
     * @param genre String with the genre
     */
    private Genres(String genre) {
        this.genre = genre;
    }

    /**
     * get the String for the enum
     * @return a String with the genre
     */
    public String getGenre() {
        return genre;
    }
}