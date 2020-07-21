package model;

import java.util.ArrayList;
import java.util.List;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

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
    private String picPath;
    public final Showtime[] showtimes; // contains all available showtimes for this movie
    
    /**
     * constructor, sets data fields
     * @param title title of the movie
     * @param genre genre of the movie
     * @param fsk rated fsk
     * @param picName name of the picture
     * @param showtimes the available showtimes for movie
     */
    public Movie(String title, Genres genre, FSKs fsk, String picName, Showtime[] showtimes) {
        this.title = title;
        this.genre = genre;
        this.fsk = fsk;
        this.picPath = "images/" + picName + ".jpg";
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
            return Vocabulary.GENRE_LABEL + ": " + genre.toString() + ", " + fsk.getFSK(); // try to return the genre and fsk as a string
        } catch (Exception e) { // some data field is not set
            if (genre == null && fsk != null)
                return fsk.getFSK(); // no genre set
            else if (genre != null && fsk == null) 
                return Vocabulary.GENRE_LABEL + ": " + genre.toString(); // no fsk set
            else 
                return ""; // both not set
        }
    }

    /**
     * read the picture from the files
     * @return icon with the picture
     */
    public Icon getImage() {
		BufferedImage pic = null;
		try {
			pic = ImageIO.read(new File(picPath));
		} catch (IOException e) {
			return null;
        }
        return new ImageIcon(pic);
    }
}