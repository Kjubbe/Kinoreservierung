package model;

import model.enums.*;

public class Film {

    protected String title;
    protected Genres genre;
    protected FSK fsk;
    protected Showtime[] showtimes;

    public Film(String title, Genres genre, FSK fsk, Showtime[] showtimes) {
        this.title = title;
        this.genre = genre;
        this.fsk = fsk;
        this.showtimes = showtimes;
    }
}