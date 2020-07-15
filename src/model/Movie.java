package model;

import model.enums.*;

/**
 * contains all information for a movie and the showtimes
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Movie {

    // Data fields
    protected String title;
    protected Genres genre;
    protected FSK fsk;
    public Showtime[] showtimes; // contains all available showtimes for this movie

    /**
     * constructor, sets data fields
     * @param title title of the movie
     * @param genre genre of the movie
     * @param fsk fsk of which the movie is rated
     * @param showtimes the available showtimes for movie
     */
    public Movie(String title, Genres genre, FSK fsk, Showtime[] showtimes) {
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
        return genre + ", " + fsk.getFSK();
    }
}