package model;

import java.awt.image.BufferedImage;

import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import model.enums.FSKs;
import model.enums.Genres;
import model.enums.Vocab;

/**
 * contains all information for a movie,
 * contains the showtimes for the movie
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Movie {

    // Data fields
    private final String title;
    private final Genres genre;
    private final FSKs fsk;
    private final String picPath;
    protected final Showtime[] showtimes; // contains all available showtimes for this movie
    
    /**
     * constructor, sets data fields
     * @param title title of the movie
     * @param genre genre of the movie
     * @param fsk rated fsk
     * @param picPath name of the picture
     * @param showtimes the available showtimes for movie
     */
    public Movie(String title, Genres genre, FSKs fsk, String picPath, Showtime[] showtimes) {
        this.title = title;
        this.genre = genre;
        this.fsk = fsk;
        this.picPath = picPath;
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
     * read the picture from the files
     * @return icon with the picture
     */
    public Icon getImage() {
		BufferedImage pic = null;
		try {
			pic = ImageIO.read(new File(picPath));
		} catch (Exception ex) {
			return null;
        }
        return new ImageIcon(pic);
    }
}