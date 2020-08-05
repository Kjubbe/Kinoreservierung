package model;

import javax.swing.Icon;

import model.enums.FSKs;
import model.enums.Genres;
import model.enums.Vocab;

/**
 * contains all information for a movie, contains the showtimes for the movie
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Movie {

    // Data fields
    private final String title;
    private final Genres genre;
    private final FSKs fsk;
    private final String picPath;
    private final Showtime[] showtimes; // contains all available showtimes for this movie

    private Icon image;

    /**
     * constructor, sets data fields
     * 
     * @param title     title of the movie
     * @param genre     genre of the movie
     * @param fsk       rated fsk
     * @param picPath   path of the picture
     * @param showtimes array with the available showtimes for the movie
     */
    public Movie(String title, Genres genre, FSKs fsk, String picPath, Showtime[] showtimes) {
        this.title = title;
        this.genre = genre;
        this.fsk = fsk;
        this.picPath = picPath;
        this.showtimes = showtimes;
    }

    /**
     * get the description of the movie, (ideally) containing the genre and fsk
     * 
     * @return genre and/or fsk (or empty) in one string
     */
    public String getDescription() {
        System.out.println("DEBUG: movie: getting description for " + this); // DEBUG
        StringBuilder builder = new StringBuilder();
        if (genre != null) {
            builder.append(Vocab.GENRE_LABEL.toString() + ": " + genre.getGenre());
        }
        if (genre != null && fsk != null) {
            builder.append(", ");
        }
        if (fsk != null) {
            builder.append(fsk.getFsk());
        }
        return builder.toString();
    }

    /**
     * get the image for this movie
     * if no image is set, load it with the file manager
     * 
     * @return the image as an icon
     */
    public Icon getImage() {
        if (image == null) {
            image = FileManager.loadImage(picPath);
        }
        return image;
    }

    /**
     * get the showtimes,
     * the array is passed by value, not reference!
     * 
     * @return a copy of the array with showtimes for this movie
     */
    public Showtime[] getShowtimes() {
        return showtimes == null ? null : showtimes.clone(); // return a copy
    }

    /**
     * toString
     * 
     * @return the title of this movie
     */
    @Override
    public String toString() {
        return title;
    }
}