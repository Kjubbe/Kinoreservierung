package model;

import model.enums.*;

/**
 * contains all information for a movie and the showtimes
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Movie { // TODO pictures for the movies

    // Data fields
    private String title;
    private Genres genre;
    private FSKs fsk;
    public final Showtime[] showtimes; // contains all available showtimes for this movie FIXME is this final correct? because the showtimes cant be changed if the movie was created and that may be an issue?

    /**
     * constructor, sets data fields
     * @param title title of the movie
     * @param genre genre of the movie
     * @param fsk fsk of which the movie is rated
     * @param showtimes the available showtimes for movie
     */
    public Movie(String title, Genres genre, FSKs fsk, Showtime[] showtimes) {
        this.title = title;
        this.genre = genre;
        this.fsk = fsk;
        this.showtimes = showtimes;
    }

    /**
     * toString
     * @return the name and price in parentheses
     */
    @Override
    public String toString() {
        return title;
    }

    /**
     * get the description of the movie, containing the genre and fsk
     * @return genre and fsk in one string
     */
    public String getDescription() {
        try {
            return genre.toString() + ", " + fsk.getFSK();
        } catch (Exception e) {
            if (genre == null && fsk != null) { // no genre set
                return fsk.getFSK();
            } else if (genre != null && fsk == null) { // no fsk set
                return genre.toString();
            } else { // both not set
                return "";
            }
        }
    }
}