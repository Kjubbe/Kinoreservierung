package model;

import java.util.ArrayList;
import java.util.List;

import model.enums.*;

/**
 * contains all information for a movie,
 * contains the showtimes for the movie
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Movie { // TODO pictures for the movies

    // Data fields
    public static final List<Movie> ALL_MOVIES = new ArrayList<>(); // contains all existing movies

    private String title;
    private Genres genre;
    private FSKs fsk;
    public final Showtime[] showtimes; // contains all available showtimes for this movie
    
    /**
     * constructor, sets data fields
     * @param title title of the movie
     * @param genre genre of the movie
     * @param fsk rated fsk
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
     * @return the title of this movie
     */
    @Override
    public String toString() {
        return title;
    }

    /**
     * get the description of the movie, (ideally) containing the genre and fsk
     * @return (ideally) genre and fsk in one string
     */
    public String getDescription() {
        try {
            return genre.toString() + ", " + fsk.getFSK(); // try to return the genre and fsk as a string
        } catch (Exception e) { // some data field is not set
            if (genre == null && fsk != null)
                return fsk.getFSK(); // no genre set
            else if (genre != null && fsk == null) 
                return genre.toString(); // no fsk set
            else 
                return ""; // both not set
        }
    }
}