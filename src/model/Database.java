package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.enums.Days;
import model.enums.FSKs;
import model.enums.Genres;
import model.enums.Prices;
import model.enums.Times;

/**
 * holds the data for movies and caterings
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public final class Database {

    // these list contain all orders, numbers and license plates
    private static final List<Order> ALL_ORDERS = new ArrayList<>(); // contains all orders
    private static final List<Integer> ALL_TICKET_NUMBERS = new ArrayList<>(); // this list holds all tickets for every seat
    private static final List<String> ALL_LICENSE_PLATES = new ArrayList<>(); // this list holds all license plates
    private static final List<Integer> ALL_ORDER_NUMBERS = new ArrayList<>(); // this list holds all order numbers

    // this list contains all movies
    private static final List<Movie> ALL_MOVIES = new ArrayList<>(Arrays.asList( // create array with all movies
            new Movie("The Fermentor", Genres.ACTION, FSKs.FSK_18, "images/the_fermentor.jpg", new Showtime[] {
                    new Showtime(Days.MONDAY, Times.PM_08_00, 5, 7),
                    new Showtime(Days.TUESDAY, Times.PM_10_00, 4, 7),
                    new Showtime(Days.THURSDAY, Times.PM_09_30, 5, 8),
                    new Showtime(Days.FRIDAY, Times.PM_10_00, 4, 9),
                    new Showtime(Days.SATURDAY, Times.PM_07_00, 5, 6),
                    new Showtime(Days.SATURDAY, Times.PM_10_00, 4, 9),
                    new Showtime(Days.SUNDAY, Times.PM_03_00, 5, 7),
                    new Showtime(Days.SUNDAY, Times.PM_05_00, 5, 8) }),
            new Movie("Suchet Nerno", Genres.FAMILY, FSKs.FSK_0, "images/suchet_nerno.jpg", new Showtime[] {
                    new Showtime(Days.WEDNESDAY, Times.PM_05_00, 4, 8),
                    new Showtime(Days.FRIDAY, Times.PM_04_00, 5, 7),
                    new Showtime(Days.FRIDAY, Times.PM_06_30, 4, 9),
                    new Showtime(Days.SATURDAY, Times.PM_03_00, 6, 8),
                    new Showtime(Days.SATURDAY, Times.PM_04_30, 5, 7),
                    new Showtime(Days.SUNDAY, Times.PM_03_00, 4, 9), }),
            new Movie("Abduction 2", Genres.THRILLER, FSKs.FSK_12, "images/abduction_2.jpg", new Showtime[] {
                    new Showtime(Days.WEDNESDAY, Times.PM_07_00, 5, 7),
                    new Showtime(Days.FRIDAY, Times.PM_08_30, 6, 7),
                    new Showtime(Days.SATURDAY, Times.PM_05_00, 5, 8),
                    new Showtime(Days.SATURDAY, Times.PM_07_30, 4, 6),
                    new Showtime(Days.SUNDAY, Times.PM_07_00, 5, 8) }),
            new Movie("Wintertime", Genres.FAMILY, FSKs.FSK_6, "images/wintertime.jpg", new Showtime[] {
                    new Showtime(Days.MONDAY, Times.PM_05_00, 4, 7),
                    new Showtime(Days.FRIDAY, Times.PM_06_30, 5, 8),
                    new Showtime(Days.SUNDAY, Times.PM_05_00, 6, 7),
                    new Showtime(Days.SUNDAY, Times.PM_07_00, 4, 6),
                    new Showtime(Days.SUNDAY, Times.PM_07_30, 5, 6) }),
            new Movie("Dueness", Genres.HORROR, FSKs.FSK_18, "images/dueness.jpg", new Showtime[] {
                    new Showtime(Days.MONDAY, Times.PM_10_00, 5, 6),
                    new Showtime(Days.SATURDAY, Times.PM_09_30, 4, 9),
                    new Showtime(Days.SATURDAY, Times.PM_10_00, 5, 7) }),
            new Movie("Missing Throne", Genres.WESTERN, FSKs.FSK_12, "images/missing_throne.jpg", new Showtime[] {
                    new Showtime(Days.WEDNESDAY, Times.PM_06_30, 4, 9),
                    new Showtime(Days.WEDNESDAY, Times.PM_08_00, 5, 7),
                    new Showtime(Days.THURSDAY, Times.PM_05_30, 5, 9),
                    new Showtime(Days.THURSDAY, Times.PM_07_00, 5, 8),
                    new Showtime(Days.SATURDAY, Times.PM_08_00, 4, 9) }),
            null, // test movie
            new Movie("Corrupted Movie 1", null, FSKs.FSK_12, "images/yellow.jpg", new Showtime[] { // test movie
                    new Showtime(Days.WEDNESDAY, Times.PM_06_30, 15, 19),
                    new Showtime(Days.WEDNESDAY, Times.PM_08_00, 6, 7),
                    new Showtime(Days.THURSDAY, Times.PM_05_30, 4, 9),
                    new Showtime(Days.THURSDAY, Times.PM_07_00, 5, 8),
                    new Showtime(Days.SATURDAY, Times.PM_08_00, 5, 9) }),
            new Movie("Corrupted Movie 2", Genres.WESTERN, null, "images/does-not-exist-test.jpg", null // test movie
            ),
            new Movie(null, Genres.WESTERN, FSKs.FSK_12, "images/yellow.jpg", null // test movie
            ),
            new Movie("Corrupted Movie 4", Genres.WESTERN, FSKs.FSK_12, null, new Showtime[] { // test movie
                    new Showtime(null, Times.PM_06_30, 4, 9), new Showtime(Days.WEDNESDAY, null, 5, 7), null,
                    new Showtime(null, null, 5, 8), new Showtime(Days.SATURDAY, Times.PM_08_00, 0, 9),
                    new Showtime(Days.SATURDAY, Times.PM_08_00, 0, 0),
                    new Showtime(null, null, 0, 0), }),
            new Movie("Corrupted Movie 5", null, null, "images/green.jpg", new Showtime[] { // test movie
                    new Showtime(Days.FRIDAY, Times.PM_06_30, 8, 9), }),
            null, // test movie
            new Movie("The Operator", Genres.ACTION, FSKs.FSK_16, "images/the_operator.jpg", new Showtime[] {
                    new Showtime(Days.TUESDAY, Times.PM_07_30, 5, 6),
                    new Showtime(Days.THURSDAY, Times.PM_08_30, 5, 6),
                    new Showtime(Days.FRIDAY, Times.PM_05_00, 4, 6),
                    new Showtime(Days.SATURDAY, Times.PM_08_30, 5, 9),
                    new Showtime(Days.SATURDAY, Times.PM_10_00, 5, 6),
                    new Showtime(Days.SUNDAY, Times.PM_07_30, 5, 5) })));

    // this list contains all caterings
    private static final List<Catering> ALL_CATERINGS = new ArrayList<>(Arrays.asList( // createlist with all caterings                                                                      
            new Catering("Cola", Prices.MEDIUM_DRINK),
            new Catering("Popcorn", Prices.LARGE_SNACK),
            new Catering("1l Wasser", Prices.MEDIUM_DRINK), null, // test catering
            new Catering("Eis", Prices.SMALL_SNACK),
            new Catering(null, null), // test catering
            new Catering("Corrupted honey", null), // test catering
            new Catering(null, Prices.VIP_BEACH_CHAIR_SEAT), // test catering
            new Catering("Nachos", Prices.MEDIUM_SNACK)));

    /**
     * private constructor to restrict access throws exception because this class is
     * not meant to be instantiated
     * 
     * @throws IllegalStateException when instantiating this class
     */
    private Database() throws IllegalStateException {
        throw new IllegalStateException("This Utility class can not be instantiated");
    }

    /**
     * get all movies
     * 
     * @return a list with all movies
     */
    public static List<Movie> getAllMovies() {
        return new ArrayList<>(ALL_MOVIES); // return a copy
    }

    /**
     * add a movie to the list
     * 
     * @param movie the movie which should be added
     */
    protected static void addMovie(Movie movie) {
        ALL_MOVIES.add(movie);
        System.out.println("DEBUG: database: added movie"); // DEBUG
    }

    /**
     * remove a movie from the list
     * 
     * @param movie the movie to be removed
     */
    protected static void removeMovie(Movie movie) {
        ALL_MOVIES.remove(movie);
        System.out.println("DEBUG: database: removed movie"); // DEBUG
    }

    /**
     * get all caterings
     * 
     * @return a list with all movies
     */
    public static List<Catering> getAllCaterings() {
        return new ArrayList<>(ALL_CATERINGS); // return a copy
    }

    /**
     * add a catering to the list
     * 
     * @param catering the catering which should be added
     */
    protected static void addCatering(Catering catering) {
        ALL_CATERINGS.add(catering);
        System.out.println("DEBUG: database: added catering"); // DEBUG
    }

    /**
     * remove a catering from the list
     * 
     * @param movie the movie to be removed
     */
    protected static void removeCatering(Catering catering) {
        ALL_CATERINGS.remove(catering);
        System.out.println("DEBUG: database: removed catering"); // DEBUG
    }

    /**
     * get all orders
     * 
     * @return a list with all orders
     */
    protected static List<Order> getAllOrders() {
        return new ArrayList<>(ALL_ORDERS); // return a copy
    }

    /**
     * add an order to the list
     * 
     * @param order the order to be added
     */
    protected static void addOrder(Order order) {
        ALL_ORDERS.add(order);
        System.out.println("DEBUG: database: added order to the list: " + ALL_ORDERS); // DEBUG
    }

    /**
     * get all ticket numbers
     * 
     * @return a list with all ticket numbers
     */
    protected static List<Integer> getAllTicketNumbers() {
        return new ArrayList<>(ALL_TICKET_NUMBERS); // return a copy
    }

    /**
     * add a ticket number to the list
     * 
     * @param ticketNumber the number to be added
     */
    protected static void addTicketNumber(int ticketNumber) {
        ALL_TICKET_NUMBERS.add(ticketNumber);
        System.out.println("DEBUG: database: added ticket number to the list: " + ALL_TICKET_NUMBERS); // DEBUG
    }

    /**
     * get all license plates
     * 
     * @return a list with all license plates
     */
    protected static List<String> getAllLicensePlates() {
        return new ArrayList<>(ALL_LICENSE_PLATES); // return a copy
    }

    /**
     * add a license plate to the list
     * 
     * @param orderNumber the license plate to be added
     */
    protected static void addLicensePlate(String licensePlate) {
        ALL_LICENSE_PLATES.add(licensePlate);
        System.out.println("DEBUG: database: added license plate to the list: " + ALL_LICENSE_PLATES); // DEBUG
    }

    /**
     * get all order numbers
     * 
     * @return a list with all order numbers
     */
    protected static List<Integer> getAllOrderNumbers() {
        return new ArrayList<>(ALL_ORDER_NUMBERS); // return a copy
    }

    /**
     * add a ticket number to the list
     * 
     * @param orderNumber the number to be added
     */
    protected static void addOrderNumber(int orderNumber) {
        ALL_ORDER_NUMBERS.add(orderNumber);
        System.out.println("DEBUG: database: added order number to the list: " + ALL_ORDER_NUMBERS); // DEBUG
    }
}