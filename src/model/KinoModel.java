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

    // Datafields, which change during runtime // TODO check if public visibility is okay?
    public Movie chosenMovie; // set based on user input in the movie tab
    public Showtime[] availableTimes; // set based on the chosen movie

    public Showtime chosenTime; // set based on user input in the times tab
    public Seat[][] availableSeats; // set based on the chosen seat

    public List<Seat> chosenSeats; // set based on user input in the seat tab
    public int carSeatCount; // contains number of CarSeats in chosenSeats
    public List<String> licensePlates;

    public Map<Catering, Integer> chosenCatering; // set based on user input in the catering tab

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
            new Movie("Corrupted Movie 1", null, FSKs.FSK_12, new Showtime[] { // test movie
                new Showtime(Dates.Mi, Times.PM_6_30, 8, 9),
                new Showtime(Dates.Mi, Times.PM_8, 7, 7),
                new Showtime(Dates.Do, Times.PM_5_30, 8, 9),
                new Showtime(Dates.Do, Times.PM_7, 6, 8),
                new Showtime(Dates.Sa, Times.PM_8, 7, 9)
            }),
            new Movie("Corrupted Movie 2", Genres.Western, null, null // test movie
            ),
            new Movie(null, Genres.Western, FSKs.FSK_12, null // test movie
            ),
            new Movie("Corrupted Movie 4", Genres.Western, FSKs.FSK_12, new Showtime[] { // test movie
                new Showtime(null, Times.PM_6_30, 8, 9),
                new Showtime(Dates.Mi, null, 7, 7),
                null,
                new Showtime(null, null, 6, 8),
                new Showtime(Dates.Sa, Times.PM_8, 0, 9),
                new Showtime(Dates.Sa, Times.PM_8, 0, 0),
                new Showtime(null, null, 0, 0),
            }),
            null // test movie
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
            new Catering(null, null), // test catering
            new Catering("Corrupted honey", null), // test catering
            new Catering(null, Prices.VIP_BEACH_CHAIR_SEAT), // test catering
            null
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
        System.out.println("DEBUG: " + "model: Movie set, Movie: " + m); // DEBUG
        chosenMovie = m; // set chosen movie
        availableTimes = m.showtimes; // set available times to the times contained in the movie
        reset(3);
    }

    /**
     * invoked from controller when time got chosen
     * assigns the chosen time
     * assigns the available seats from this movie
     * @param index time which was chosen
     */
    public void setTime(int index) {
        System.out.println("DEBUG: " + "model: Time set, Time: " + availableTimes[index]); // DEBUG
        chosenTime = availableTimes[index]; // set chosen time
        availableSeats = chosenTime.seats; // set available seats to the seats contained in the showtime
        reset(2);
    }

    /**
     * invoked from controller when seats got chosen
     * assigns list of the chosen seats
     * counts amount of CarSeats
     * @param seats list of seats which were chosen
     */
    public void setSeats(List<Seat> seats) { // TODO split this to changeSeat and work with index and boolean remove/add
        System.out.println("DEBUG: " + "model: Seat set, Seats: " + seats); // DEBUG
        chosenSeats = seats; // set chosen seats
        carSeatCount = 0; // reset the counter
        for (Seat s : chosenSeats) { // check every seat
            if (s instanceof CarSeat)
                carSeatCount++; // if seat is an instance of CarSeat increase the counter
        }
        reset(1);
    }

    /**
     * invoked from controller when catering got chosen
     * assigns map with catering and amount
     * @param cateringCounts
     */
    public void setCatering(Map<Catering, Integer> cateringCounts) {
        System.out.println("DEBUG: " + "model: Catering set, Caterings: " + cateringCounts); // DEBUG
        chosenCatering = cateringCounts; // set chosen caterings
    }

    /**
     * set license plates
     * @param lps list of license plates
     */
    public void setLicensePlates(List<String> lps) {
        licensePlates = lps;
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
                price += c.price.getPrice() * i;
            }
        }
        return Math.round(price * 100.0) / 100.0; // round price to two decimal places
    }

    /**
     * resets all user input specified by a depth value
     * a higher depth value = deeper reset
     */
    public void reset(int depth) {
        System.out.println("DEBUG: " + "model: resetting input..."); // DEBUG
        if (depth >= 4) {
            chosenMovie = null;
            availableTimes = null;
        }
        if (depth >= 3) {
            chosenTime = null;
            availableSeats = null;
        }
        if (depth >= 2) {
            chosenSeats = null;
            licensePlates = null;
            carSeatCount = 0;
        }
        if (depth >= 1)
            chosenCatering = null;
    }

    /**
     * Quits the application
     * invoked from controller by pressing the JButton for exiting
     */
    public void quit() {
        System.out.println("\n" + "DEBUG: " + "quitting..."); // DEBUG
        reset(4);
        System.exit(0); // terminate the program
    }

    /**
     * finish an order
     */
    public void order() {
        int i = 0;
        for (Seat s : chosenSeats) {
            System.out.println("DEBUG: model: reserved seat " + s); // DEBUG
            s.isReserved = true;
            if (s instanceof BeachChairSeat)
                ((BeachChairSeat)s).assignTicket();
            else {
                ((CarSeat)s).licensePlateNr = licensePlates.get(i++); // TODO
                System.out.println(((CarSeat)s).licensePlateNr); // DEBUG
            }
                
            chosenTime.updateAvailability();
        }
        orders.add(new Order(chosenMovie, chosenTime, chosenSeats, chosenCatering, calculatePrice()));
        System.out.println("\n" + "DEBUG: model: All orders are: \n" + orders + "\n"); // DEBUGs
        // TODO write the order to a file
    }
    
    /**
     * TODO
     * @return
     */
    public String[] getTicketStrings() {
        String splitter = "@";
        String print = "";
        for (String s : Vocabulary.FINISH_MSGS) {
            print += s + splitter;
        }
        for (Seat s : chosenSeats) {
            if (s instanceof BeachChairSeat) print += Vocabulary.TICKET_LABEL + ": " + ((BeachChairSeat)s).getTicket() + splitter;
        }
        return print.split(splitter);
    }
}