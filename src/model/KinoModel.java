package model;

import model.enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Model class, manages calculations and holds Strings for labels
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoModel {

    // This list holds all orders
    private List<Order> orders = new LinkedList<Order>();

    // data
    public static final List<Movie> ALL_MOVIES = new ArrayList<>(); // contains all existing movies
    public static final List<Catering> ALL_CATERINGS = new ArrayList<>(); // contains all existing catering options

    // Datafields, which change during runtime
    public Movie chosenMovie; // set based on user input in the movie tab

    public Showtime[] availableTimes; // set based on the chosen movie
    public Showtime chosenTime; // set based on user input in the times tab

    public Seat[][] availableSeats; // set based on the chosen seat
    public List<Seat> chosenSeats; // set based on user input in the seat tab
    public int carSeatCount; // contains number of CarSeats in chosenSeats

    public Map<Catering, Integer> chosenCatering; // set based on user input in the catering tab TODO this is null when not choosing any catering, when choosing its the map, even when its zeros everywhere

    /**
     * Constructor, creates movies and catering options
     */
    public KinoModel() { 
        createMovies(); // create movies
        createCaterings(); // create catering options
    }

    /**
     * invoked when creating this
     * creates movie examples and puts them in the list
     */
    private void createMovies() {
        Movie[] f = new Movie[] { // create array with all movies
            new Movie("Terminator", Genres.Action, FSKs.FSK_18, new Showtime[] {
                new Showtime(Dates.Mo, Times.PM_8, 5, 7),
                new Showtime(Dates.Di, Times.PM_10, 6, 7),
                new Showtime(Dates.Do, Times.PM_9_30, 7, 8),
                new Showtime(Dates.Fr, Times.PM_7, 6, 8),
                new Showtime(Dates.Fr, Times.PM_8_30, 6, 7),
                new Showtime(Dates.Fr, Times.PM_10, 6, 9),
                new Showtime(Dates.Sa, Times.PM_7, 5, 6),
                new Showtime(Dates.Sa, Times.PM_8, 7, 8),
                new Showtime(Dates.Sa, Times.PM_9, 6, 7),
                new Showtime(Dates.Sa, Times.PM_10, 7, 9),
                new Showtime(Dates.So, Times.PM_3, 9, 7),
                new Showtime(Dates.So, Times.PM_5, 7, 8)
            }),
            new Movie("Findet Nemo", Genres.Familie, FSKs.FSK_0, new Showtime[] {
                new Showtime(Dates.Mi, Times.PM_5, 7, 8),
                new Showtime(Dates.Mi, Times.PM_5_30, 8, 9),
                new Showtime(Dates.Fr, Times.PM_4, 6, 7),
                new Showtime(Dates.Fr, Times.PM_6_30, 6, 9),
                new Showtime(Dates.Sa, Times.PM_3, 8, 8),
                new Showtime(Dates.Sa, Times.PM_4_30, 7, 7),
                new Showtime(Dates.Sa, Times.PM_5, 8, 9),
                new Showtime(Dates.So, Times.PM_3, 7, 9),
                new Showtime(Dates.So, Times.PM_5_30, 7, 8)
            }),
            new Movie("Abduction 2", Genres.Thriller, FSKs.FSK_12, new Showtime[] {
                new Showtime(Dates.Mi, Times.PM_7, 6, 7),
                new Showtime(Dates.Fr, Times.PM_8_30, 6, 7),
                new Showtime(Dates.Sa, Times.PM_5, 8, 8),
                new Showtime(Dates.Sa, Times.PM_7_30, 6, 6),
                new Showtime(Dates.So, Times.PM_7, 6, 8)
            }),
            new Movie("Wintertime", Genres.Familie, FSKs.FSK_6, new Showtime[] {
                new Showtime(Dates.Mo, Times.PM_5, 9, 7),
                new Showtime(Dates.Fr, Times.PM_6_30, 7, 8),
                new Showtime(Dates.So, Times.PM_5, 6, 7),
                new Showtime(Dates.So, Times.PM_7, 7, 6),
                new Showtime(Dates.So, Times.PM_7_30, 7, 6)
            }),
            new Movie("Dueness", Genres.Horror, FSKs.FSK_18, new Showtime[] {
                new Showtime(Dates.Mo, Times.PM_10, 9, 6),
                new Showtime(Dates.Sa, Times.PM_9_30, 7, 9),
                new Showtime(Dates.Sa, Times.PM_10, 8, 7)
            }),
            new Movie("The Operator", Genres.Action, FSKs.FSK_16, new Showtime[] {
                new Showtime(Dates.Di, Times.PM_7_30, 7, 6),
                new Showtime(Dates.Do, Times.PM_8_30, 8, 6),
                new Showtime(Dates.Fr, Times.PM_5, 5, 6),
                new Showtime(Dates.Sa, Times.PM_8_30, 9, 9),
                new Showtime(Dates.Sa, Times.PM_10, 7, 6),
                new Showtime(Dates.So, Times.PM_7_30, 7, 5)
            }),
            new Movie("Missing Throne", Genres.Western, FSKs.FSK_12, new Showtime[] {
                new Showtime(Dates.Mi, Times.PM_6_30, 8, 9),
                new Showtime(Dates.Mi, Times.PM_8, 7, 7),
                new Showtime(Dates.Do, Times.PM_5_30, 8, 9),
                new Showtime(Dates.Do, Times.PM_7, 6, 8),
                new Showtime(Dates.Sa, Times.PM_8, 7, 9)
            }),
            new Movie("Broken Movie 1", null, FSKs.FSK_12, new Showtime[] { // TODO remove
                new Showtime(Dates.Mi, Times.PM_6_30, 8, 9),
                new Showtime(Dates.Mi, Times.PM_8, 7, 7),
                new Showtime(Dates.Do, Times.PM_5_30, 8, 9),
                new Showtime(Dates.Do, Times.PM_7, 6, 8),
                new Showtime(Dates.Sa, Times.PM_8, 7, 9)
            }),
            new Movie("Broken Movie 2", Genres.Western, null, null // TODO remove
            ),
            new Movie(null, Genres.Western, FSKs.FSK_12, null // TODO remove
            ),
            new Movie("Broken Movie 3", Genres.Western, FSKs.FSK_12, new Showtime[] { // TODO remove
                new Showtime(null, Times.PM_6_30, 8, 9),
                new Showtime(Dates.Mi, null, 7, 7),
                null,
                new Showtime(null, null, 6, 8),
                new Showtime(Dates.Sa, Times.PM_8, 0, 9),
                new Showtime(Dates.Sa, Times.PM_8, 0, 0),
                new Showtime(null, null, 0, 0),
            }),
            null // TODO remove
        };
        ALL_MOVIES.addAll(Arrays.asList(f)); // add array in the list
    }

    /**
     * invoked when creating this
     * creates catering examples and puts them in the list
     */
    private void createCaterings() {
        Catering[] c = new Catering[] { // create array with all catering options
            new Catering("Cola", Prices.MEDIUM_DRINK),
            new Catering("Popcorn", Prices.LARGE_SNACK),
            new Catering("1l Wasser", Prices.MEDIUM_DRINK),
            new Catering("Nachos", Prices.MEDIUM_SNACK),
            new Catering("Eis", Prices.SMALL_SNACK),
            new Catering(null, null) // TODO remove
        };
        ALL_CATERINGS.addAll(Arrays.asList(c)); // add array in the list
    }

    /**
     * invoked from controller when movie got chosen
     * assigns the chosen movie
     * assigns showtimes from this movie
     * @param m movie which was chosen
     */
    public void setMovie(Movie m) {
        System.out.println("DEBUG: " + "model: Movie set, Movie: " + m); // DEBUG TODO remove
        chosenMovie = m; // set chosen movie
        availableTimes = m.showtimes; // set available times to the times contained in the movie
        chosenTime = null; // reset chosen time, because new movie got chosen // TODO is this way of resetting the data fields good?
        chosenSeats = null; // reset chosen seats, because new movie got chosen
        chosenCatering = null; // reset chosen catering, because new movie got chosen
    }

    /**
     * invoked from controller when time got chosen
     * assigns the chosen time
     * assigns the available seats from this movie
     * @param t time which was chosen
     */
    public void setTime(Showtime t) {
        System.out.println("DEBUG: " + "model: Time set, Time: " + t); // DEBUG TODO remove
        chosenTime = t; // set chosen time
        availableSeats = t.seats; // set available seats to the seats contained in the showtime
        chosenSeats = null; // reset chosen seats, because new time got chosen // TODO is this way of resetting the data fields good?
        chosenCatering = null; // reset chosen catering, because new time got chosen
    }

    /**
     * invoked from controller when seats got chosen
     * assigns list of the chosen seats
     * counts amount of CarSeats
     * @param seats list of seats which were chosen
     */
    public void setSeats(List<Seat> seats) {
        System.out.println("DEBUG: " + "model: Seat set, Seats: " + seats); // DEBUG TODO remove
        chosenSeats = seats; // set chosen seats
        carSeatCount = 0; // reset the counter
        for (Seat s : chosenSeats) { // check every seat
            if (s instanceof CarSeat) carSeatCount++; // if seat is an instance of CarSeat increase the counter
        }
        chosenCatering = null; // reset chosen catering, because new seats got chosen // TODO is this way of resetting the data fields good?
    }

    /**
     * invoked from controller when catering got chosen
     * assigns map with catering and amount
     * @param cateringCounts
     */
    public void setCatering(Map<Catering, Integer> cateringCounts) {
        System.out.println("DEBUG: " + "model: Catering set, Caterings: " + cateringCounts); // DEBUG TODO remove
        chosenCatering = cateringCounts; // set chosen caterings
    }

    /**
     * adds all prices from chosen seats and catering options
     * @return calculated total price
     */
    public double calculatePrice() {
        double price = 0.0; // local variable, holds the price
        if (chosenSeats != null) { // check, if there are chosen seats
            for (Seat s : chosenSeats) { // check every seat
                price += s.price.getPrice(); // add price of the seat to the total amount
            }
        }
        if (chosenCatering != null) { // check, if there are chosen caterings
            for (Map.Entry<Catering, Integer> entry : chosenCatering.entrySet()) { // check every entry of the map
                Catering c = entry.getKey();
                Integer i = entry.getValue();
                price += c.price.getPrice() * i; // add price of the catering options multiplied by its amount to the total amount
            }
        }
        return Math.round(price * 100.0) / 100.0; // round price to two decimal places
    }

    /**
     * resets all user input
     */
    public void reset() {
        System.out.println("DEBUG: " + "model: resetting input..."); // DEBUG TODO remove this
        chosenMovie = null;
        availableTimes = null;
        chosenTime = null;
        availableSeats = null;
        chosenSeats = null;
        carSeatCount = 0;
        chosenCatering = null;
    }

    /**
     * Quits the application
     * invoked from controller by pressing the JButton for exiting
     */
    public void quit() {
        System.out.println("\n" + "DEBUG: " + "quitting..."); // DEBUG TODO remove this
        reset();
        System.exit(0); // terminate the program TODO is this
    }

    /**
     * finish an order
     */
    public void order() {
        for (Seat s : chosenSeats) {
            System.out.println("DEBUG: model: reserved seat " + s); // DEBUG TODO remove this
            s.isReserved = true;
            chosenTime.checkAvailability();
        }
        orders.add(new Order(chosenMovie, chosenTime, chosenSeats, chosenCatering));
        System.out.println("\n" + "DEBUG: model: All orders are: \n" + orders + "\n"); // DEBUG TODO remove this
        reset();
    }
}