package model;

import java.util.List;
import java.util.Map;

/**
 * holds all information of an order
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Order {

    // data fields
    public final Movie movie;
    public final Showtime time;
    public final List<Seat> seats;
    public final Map<Catering, Integer> caterings;

    /**
     * constructor, assigns data fields
     * @param movie the chosen movie
     * @param time the chosen time of the movie
     * @param seats the chosen seats for the time
     * @param caterings chosen catering options
     */
    public Order(Movie movie, Showtime time, List<Seat> seats, Map<Catering, Integer> caterings) {
        this.movie = movie;
        this.time = time;
        this.seats = seats;
        this.caterings = caterings;
    }

    /**
     * toString
     * @return all data fields as strings
     */
    @Override
    public String toString() {
        String moviePrint = Vocabulary.movieLabel + ": " + movie;
        String timePrint = Vocabulary.timeLabel + ": " + time;
        String seatPrint = Vocabulary.seatsLabel + ": ";
        for (Seat s : seats) { // go through every seat
            seatPrint += s.toString() + ", "; // add all seats to the print
        }
        String cateringPrint = Vocabulary.cateringLabel + ": " + caterings; // TODO this must be better
        return moviePrint + "\n" + timePrint + "\n" + seatPrint.substring(0, seatPrint.length() - 2) + "\n" + cateringPrint;
    }
}