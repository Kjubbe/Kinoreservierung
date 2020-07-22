package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * contains all information of an order,
 * can display all information in a string
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Order {

    // data fields
    protected static final List<Order> ALL_ORDERS = new ArrayList<>(); // contains all orders TODO maybe create a database class for these three?

    private final Movie movie;
    private final Showtime time;
    private final List<Seat> seats;
    private final Map<Catering, Integer> caterings;
    private final double totalPrice;

    /**
     * constructor, assigns data fields
     * @param movie the chosen movie
     * @param time the chosen time of the movie
     * @param seats the chosen seats for the time
     * @param caterings chosen caterings
     */
    public Order(Movie movie, Showtime time, List<Seat> seats, Map<Catering, Integer> caterings, double totalPrice) {
        this.movie = movie;
        this.time = time;
        this.seats = seats;
        this.caterings = caterings;
        this.totalPrice = totalPrice;
    }

    /**
     * toString
     * @return string with all information
     */
    @Override
    public String toString() {
        return movie + ", " + time.getDateAndTime() + ", " + seats + ", " + caterings + ", " + totalPrice + Vocabulary.CURRENCY;
    }
}