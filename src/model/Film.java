package model;

import model.enums.*;

public class Film {

    protected String title;
    protected Genres genre;
    protected FSK fsk;
    public Showtime[] showtimes;

    public Film(String title, Genres genre, FSK fsk, Showtime[] showtimes) {
        this.title = title;
        this.genre = genre;
        this.fsk = fsk;
        this.showtimes = showtimes;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getDescription() {
        return genre + ", " + fsk.getFSK();
    }
}