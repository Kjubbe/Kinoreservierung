package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.enums.Dates;
import model.enums.FSKs;
import model.enums.Genres;
import model.enums.Prices;
import model.enums.Times;
import model.enums.Vocab;

/**
 * model class, manages calculations and contains saves the information from the user input
 * creates all movies and caterings and places orders
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoModel {

    // constants
    private static final int MIN_LICENSE_PLATE_LENGTH = 5;
    private static final int MAX_LICENSE_PLATE_LEGNTH = 10;

    private static final int RESET_CATERING_AND_ABOVE = 1;
    private static final int RESET_SEATS_AND_ABOVE = 2;
    private static final int RESET_TIMES_AND_ABOVE = 3;
    public static final int RESET_MOVIES_AND_ABOVE = 4;

    // data
    private static final List<Movie> ALL_MOVIES = new ArrayList<>(); // contains all existing movies
    private static final List<Catering> ALL_CATERINGS = new ArrayList<>(); // contains all existing caterings

    // datafields, which change during runtime
    private Movie chosenMovie; // set based on user input in the movie tab
    private Showtime[] availableTimes; // set based on the chosen movie

    private Showtime chosenTime; // set based on user input in the times tab
    private Seat[][] availableSeats; // set based on the chosen time

    private List<Seat> chosenSeats; // set based on user input in the seat tab
    private int carSeatCount; // contains number of CarSeats in chosenSeats
    private List<String> licensePlates; // contains inputted license plates for CarSeats

    private Map<Catering, Integer> chosenCatering; // set based on user input in the catering tab

    /**
     * constructor, creates movies and caterings
     */
    public KinoModel() { 
        createMovies(); // create movies
        createCaterings(); // create caterings
    }

    /**
     * called from constructor
     * creates movie examples and puts them in the list
     */
    private static void createMovies() {
        Movie[] f = new Movie[] { // create array with all movies
            new Movie("The Fermentor", Genres.ACTION, FSKs.FSK_18, "images/the_fermentor.jpg", new Showtime[] {
                new Showtime(Dates.MONDAY, Times.PM_08_00, 5, 7),
                new Showtime(Dates.TUESDAY, Times.PM_10_00, 4, 7),
                new Showtime(Dates.THURSDAY, Times.PM_09_30, 5, 8),
                new Showtime(Dates.FRIDAY, Times.PM_10_00, 4, 9),
                new Showtime(Dates.SATURDAY, Times.PM_07_00, 5, 6),
                new Showtime(Dates.SATURDAY, Times.PM_10_00, 4, 9),
                new Showtime(Dates.SUNDAY, Times.PM_03_00, 5, 7),
                new Showtime(Dates.SUNDAY, Times.PM_05_00, 5, 8)
            }),
            new Movie("Suchet Nerno", Genres.FAMILY, FSKs.FSK_0, "images/suchet_nerno.jpg", new Showtime[] {
                new Showtime(Dates.WEDNESDAY, Times.PM_05_00, 4, 8),
                new Showtime(Dates.FRIDAY, Times.PM_04_00, 5, 7),
                new Showtime(Dates.FRIDAY, Times.PM_06_30, 4, 9),
                new Showtime(Dates.SATURDAY, Times.PM_03_00, 6, 8),
                new Showtime(Dates.SATURDAY, Times.PM_04_30, 5, 7),
                new Showtime(Dates.SUNDAY, Times.PM_03_00, 4, 9),
            }),
            new Movie("Abduction 2", Genres.THRILLER, FSKs.FSK_12, "images/abduction_2.jpg", new Showtime[] {
                new Showtime(Dates.WEDNESDAY, Times.PM_07_00, 5, 7),
                new Showtime(Dates.FRIDAY, Times.PM_08_30, 6, 7),
                new Showtime(Dates.SATURDAY, Times.PM_05_00, 5, 8),
                new Showtime(Dates.SATURDAY, Times.PM_07_30, 4, 6),
                new Showtime(Dates.SUNDAY, Times.PM_07_00, 5, 8)
            }),
            new Movie("Wintertime", Genres.FAMILY, FSKs.FSK_6, "images/wintertime.jpg", new Showtime[] {
                new Showtime(Dates.MONDAY, Times.PM_05_00, 4, 7),
                new Showtime(Dates.FRIDAY, Times.PM_06_30, 5, 8),
                new Showtime(Dates.SUNDAY, Times.PM_05_00, 6, 7),
                new Showtime(Dates.SUNDAY, Times.PM_07_00, 4, 6),
                new Showtime(Dates.SUNDAY, Times.PM_07_30, 5, 6)
            }),
            new Movie("Dueness", Genres.HORROR, FSKs.FSK_18, "images/dueness.jpg", new Showtime[] {
                new Showtime(Dates.MONDAY, Times.PM_10_00, 5, 6),
                new Showtime(Dates.SATURDAY, Times.PM_09_30, 4, 9),
                new Showtime(Dates.SATURDAY, Times.PM_10_00, 5, 7)
            }),
            new Movie("Missing Throne", Genres.WESTERN, FSKs.FSK_12, "images/missing_throne.jpg", new Showtime[] {
                new Showtime(Dates.WEDNESDAY, Times.PM_06_30, 4, 9),
                new Showtime(Dates.WEDNESDAY, Times.PM_08_00, 5, 7),
                new Showtime(Dates.THURSDAY, Times.PM_05_30, 5, 9),
                new Showtime(Dates.THURSDAY, Times.PM_07_00, 5, 8),
                new Showtime(Dates.SATURDAY, Times.PM_08_00, 4, 9)
            }),
            null, // test movie
            new Movie("Corrupted Movie 1", null, FSKs.FSK_12, "images/yellow.jpg", new Showtime[] { // test movie
                new Showtime(Dates.WEDNESDAY, Times.PM_06_30, 15, 19),
                new Showtime(Dates.WEDNESDAY, Times.PM_08_00, 6, 7),
                new Showtime(Dates.THURSDAY, Times.PM_05_30, 4, 9),
                new Showtime(Dates.THURSDAY, Times.PM_07_00, 5, 8),
                new Showtime(Dates.SATURDAY, Times.PM_08_00, 5, 9)
            }),
            new Movie("Corrupted Movie 2", Genres.WESTERN, null, "images/does-not-exist-test.jpg", null // test movie
            ),
            new Movie(null, Genres.WESTERN, FSKs.FSK_12, "images/yellow.jpg", null // test movie
            ),
            new Movie("Corrupted Movie 4", Genres.WESTERN, FSKs.FSK_12, null, new Showtime[] { // test movie
                new Showtime(null, Times.PM_06_30, 4, 9),
                new Showtime(Dates.WEDNESDAY, null, 5, 7),
                null,
                new Showtime(null, null, 5, 8),
                new Showtime(Dates.SATURDAY, Times.PM_08_00, 0, 9),
                new Showtime(Dates.SATURDAY, Times.PM_08_00, 0, 0),
                new Showtime(null, null, 0, 0),
            }),
            new Movie("Corrupted Movie 5", null, null, "images/green.jpg", new Showtime[] { // test movie
                new Showtime(Dates.FRIDAY, Times.PM_06_30, 8, 9),
            }),
            null, // test movie
            new Movie("The Operator", Genres.ACTION, FSKs.FSK_16, "images/the_operator.jpg", new Showtime[] {
                new Showtime(Dates.TUESDAY, Times.PM_07_30, 5, 6),
                new Showtime(Dates.THURSDAY, Times.PM_08_30, 5, 6),
                new Showtime(Dates.FRIDAY, Times.PM_05_00, 4, 6),
                new Showtime(Dates.SATURDAY, Times.PM_08_30, 5, 9),
                new Showtime(Dates.SATURDAY, Times.PM_10_00, 5, 6),
                new Showtime(Dates.SUNDAY, Times.PM_07_30, 5, 5)
            }),
        };
        ALL_MOVIES.addAll(Arrays.asList(f)); // add array in the list
    }

    /**
     * called from constructor
     * creates catering examples and puts them in the list
     */
    private static void createCaterings() {
        Catering[] c = new Catering[] { // create array with all caterings
            new Catering("Cola", Prices.MEDIUM_DRINK),
            new Catering("Popcorn", Prices.LARGE_SNACK),
            new Catering("1l Wasser", Prices.MEDIUM_DRINK),
            null, // test catering
            new Catering("Eis", Prices.SMALL_SNACK),
            new Catering(null, null), // test catering
            new Catering("Corrupted honey", null), // test catering
            new Catering(null, Prices.VIP_BEACH_CHAIR_SEAT), // test catering
            new Catering("Nachos", Prices.MEDIUM_SNACK)
        };
        ALL_CATERINGS.addAll(Arrays.asList(c)); // add array in the list
    }

    /**
     * invoked from controller when movie got chosen
     * assigns the chosen movie
     * assigns available showtimes from this movie
     * @param m movie which was chosen
     */
    public void setMovie(Movie m) {
        System.out.println("DEBUG: model: Movie set, Movie: " + m); // DEBUG
        
        chosenMovie = m; // set chosen movie
        availableTimes = m.showtimes; // set available times to the times contained in the movie
        reset(RESET_TIMES_AND_ABOVE);
    }

    /**
     * invoked from controller when time got chosen
     * assigns the chosen time from the available times
     * assigns the available seats from this time
     * @param cmd action command from the JRadioButton, contains the index for the time
     */
    public void setTime(String cmd) {
        int index = Integer.parseInt(cmd); // get the index from the action command
        System.out.println("DEBUG: model: Time set, Time: " + availableTimes[index]); // DEBUG
        
        // get the time at the index from the action command
        // this time is equivalent to the time displayed on the JRadioButton at the index
        chosenTime = availableTimes[index];
        availableSeats = chosenTime.seats; // set available seats to the seats contained in the showtime
        reset(RESET_SEATS_AND_ABOVE);
    }

    /**
     * invoked from controller when seats got chosen
     * adds or removes seats based on the remove boolean
     * increments or decrements the amount of CarSeats
     * @param cmd action command from the JCheckBox, contains the position of the seat
     * @param remove determines if an seat should be removed, if not, one gets added
     */
    public void changeSeats(String cmd, boolean remove) {
        String[] pos = cmd.split(Vocab.SPLITTER_STRING.toString()); // get the position from the action command
        
        // get the seat at the position from the action command
        // this seat is equivalent to the seat displayed on the JCheckBox at the position
        Seat s = availableSeats[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])]; 
        
        if (remove) {
            System.out.println("DEBUG: model: Seat removed " + s); // DEBUG
            chosenSeats.remove(s); // remove the seat from the list
        } else {
            System.out.println("DEBUG: model: Seat added " + s); // DEBUG
            chosenSeats.add(s); // add the seat to the list
        }
        if (s instanceof CarSeat) { // check if seat is an instance of CarSeat
            if (remove) { // increase when adding a CarSeat, decrease when removing
                carSeatCount--;
            } else {
                carSeatCount++;
            }
            System.out.println("DEBUG: model: Car seat count changed to " + carSeatCount); // DEBUG
        }
        System.out.println("DEBUG: model: List of seats " + chosenSeats); // DEBUG
        reset(RESET_CATERING_AND_ABOVE);
    }

    /**
     * invoked from controller when catering got chosen
     * assigns map with catering as key and amount as value
     * @param cateringAmounts list of amounts for each catering
     */
    public void setCatering(List<Integer> cateringAmounts) { // TODO update this logic
        int y = 0; // index counter

        // create a new map, which will contain every catering option with their specified amount
        chosenCatering = new HashMap<>();
        for (int i = 0; i < ALL_CATERINGS.size(); i++) { // check every Catering of the list

            // get the catering at the index, which is equivalent to the index of the amount for this catering
            Catering equivalentCatering = ALL_CATERINGS.get(i); 
            if (equivalentCatering == null || equivalentCatering.name == null || equivalentCatering.price == null) {
                continue; // catering corrupted, skip
            } 

            // put catering as key with the selected amount as a value in the map
            chosenCatering.put(equivalentCatering, cateringAmounts.get(y));
            y++;
        }
        System.out.println("DEBUG: model: Catering set, Caterings: " + chosenCatering); // DEBUG
    }

    /**
     * invoked from controller when text got typed in the textfield
     * assigns list with the text from the textfields
     * @param lps list of license plate Strings
     */
    public void setLicensePlates(List<String> lps) {
        System.out.println("DEBUG: model: License plate set, License plates: " + lps); // DEBUG
        
        licensePlates = new ArrayList<>(lps); // store a copy
    }

    /**
     * checks if the text has an acceptable length
     * @param tf the JTextField to be checked
     * @return if the input suffices
     */
    public boolean checkInput(String text) {
        System.out.println("DEBUG: model: checking input..."); // DEBUG
        
        text = text.replaceAll("\\s+", ""); // remove all whitespaces

        // input only suffices if the length of the text is greater than min and less than max
        return text.length() >= MIN_LICENSE_PLATE_LENGTH && text.length() <= MAX_LICENSE_PLATE_LEGNTH;
    }

    /**
     * resets all user input specified by a depth value,
     * a higher depth value = a deeper reset
     * @param depth the depth of the reset
     */
    public void reset(int depth) {
        System.out.println("DEBUG: model: resetting input..."); // DEBUG
        
        if (depth >= RESET_MOVIES_AND_ABOVE) { // this depth reaches to the movie tab
            chosenMovie = null;
            availableTimes = null;
        }
        if (depth >= RESET_TIMES_AND_ABOVE) { // this depth reaches to the times tab
            chosenTime = null;
            availableSeats = null;
        }
        if (depth >= RESET_SEATS_AND_ABOVE) { // this depth reaches to the seat tab
            chosenSeats = new ArrayList<>();
            licensePlates = null;
            carSeatCount = 0;
        }
        if (depth >= RESET_CATERING_AND_ABOVE) { // this depth reaches to the seat tab
            chosenCatering = null;
        }
    }

    /**
     * quit the application,
     * invoked from controller by pressing the JButton for exiting
     */
    public void quit() {
        System.out.println("\n" + "DEBUG: model: quitting..."); // DEBUG
        
        reset(RESET_MOVIES_AND_ABOVE); // reset the model
        System.exit(0); // terminate the program
    }

    /**
     * place an order,
     * assigns a ticket to every chosen beach chair,
     * assigns the license plate numbers to the chosen car seat,
     * creates and adds a new order object to the list
     */
    public void order() {
        int index = 0; // local index counter
        for (Seat s : chosenSeats) { // check every seat
            System.out.println("DEBUG: model: reserved seat " + s); // DEBUG
            s.isReserved = true; // reserve the seat
            if (s instanceof BeachChairSeat) {
                ((BeachChairSeat)s).assignTicket(); // assign a ticket to the beach chair seat
            } else {
                 // assign the license plate to the car seat and increment the index counter
                ((CarSeat)s).licensePlateNr = licensePlates.get(index);
                index++;
                System.out.println("DEBUG: model: set license plate " + ((CarSeat)s).licensePlateNr); // DEBUG
            } 
            chosenTime.updateAvailability(); // update the availability of the showtime, because seats got reserved
        }
        Order order = new Order(chosenMovie, chosenTime, chosenSeats, chosenCatering, getTotalPrice());
        Order.ALL_ORDERS.add(order); // create and add a new order with all information
        toFile(order); // TODO write the order to a file
        System.out.println("\n" + "DEBUG: model: All orders are: \n" + Order.ALL_ORDERS + "\n"); // DEBUGs
    }

    /**
     * write the order to a file
     * @param order the order which should be written to the file
     */
    public static void toFile(Order order) {
        String path = "orders/order" + order.getOrderNumber() + ".txt"; // string with path
        int fixer = 1; // TODO maybe change this
        try {
            File file = new File(path); // create a File Object with the desired path
            while (!file.createNewFile()) { // create a new file, if this failes, change the path and try again
                path = "orders/order" + (order.getOrderNumber() + fixer) + ".txt"; // fix the path and try again
                file = new File(path);
                fixer++;
            }
        } catch (IOException ex) { // error catched
            ex.printStackTrace();
            return; // skip following code
        }
        try (FileWriter writer = new FileWriter(path) // try-with-resources guarantees the writer is closed
            ) {
            writer.write(order.toString()); // try writing to the file
        } catch (IOException ex) { // error catched
            ex.printStackTrace();
        }
    }
    
    /**
     * get all the tickets as strings
     * @return array of strings with the ticket numbers
     */
    public String[] getTicketStrings() {
        StringBuilder builder = new StringBuilder();
        for (Seat s : chosenSeats) { // go through the chosen seats
            if (s instanceof BeachChairSeat) {
                // get the ticket from the seat and add it to the string
                builder.append(((BeachChairSeat)s).getTicket() + Vocab.SPLITTER_STRING.toString());
            }
        }
        return builder.toString().split(Vocab.SPLITTER_STRING.toString()); // split the string
    }

    /**
     * get the total price
     * adds all prices from chosen seats and caterings together to calculate the total price
     * @return total price
     */
    public double getTotalPrice() {
        System.out.println("DEBUG: " + "model: calculating price..."); // DEBUG
        double price = 0.0; // local variable, holds the price
        if (!chosenSeats.isEmpty()) { // check, if there are chosen seats
            for (Seat s : chosenSeats) { // check every seat
                price += s.price.getPrice(); // add price of the seat to the total amount
            }
        }
        if (chosenCatering != null) { // check, if there are chosen caterings
            for (Map.Entry<Catering, Integer> entry : chosenCatering.entrySet()) { // check every entry of the map
                Catering c = entry.getKey();
                Integer i = entry.getValue();
                price += c.price.getPrice() * i; // price is equal to the price multiplied by the amount
            }
        }
        return Math.round(price * 100.0) / 100.0; // round price to two decimal places
    }

    /**
     * get the chosen movie
     * @return chosen movie
     */
    public Movie getChosenMovie() {
        return chosenMovie; // TODO clone
    }

    /**
     * get the available times
     * @return array of showtimes
     */
    public Showtime[] getAvailableTimes() {
        if (availableTimes == null) {
            return null; // TODO maybe return an empty collection instead of null
        }
        return availableTimes.clone();
    }

    /**
     * get the chosen time
     * @return chosen time
     */
    public Showtime getChosenTime() {
        return chosenTime; // TODO clone
    }

    /**
     * get the available seats
     * @return array of seats
     */
    public Seat[][] getAvailableSeats() {
        if (availableSeats == null) {
            return null; // TODO maybe return an empty collection instead of null
        }
        return availableSeats.clone(); // return a copy
    }

    /**
     * get the chosen seats
     * @return list of seats
     */
    public List<Seat> getChosenSeats() {
        return new ArrayList<>(chosenSeats); // return a copy
    }

    /**
     * get the amount of car seats
     * @return number of car seats
     */
    public int getCarSeatCount() {
        return carSeatCount;
    }

    /**
     * get the license plates
     * @return list of license plates
     */
    public List<String> getLicensePlates() {
        if (licensePlates == null) {
            return null; // TODO maybe return an empty collection instead of null
        }
        return new ArrayList<>(licensePlates); // return a copy
    }

    /**
     * get the caterings
     * @return map with caterings and amounts
     */
    public Map<Catering, Integer> getChosenCatering() {
        return chosenCatering; // TODO return a copy
    }

    /**
     * get all movies
     * @return a list with all movies
     */
    public static List<Movie> getAllMovies() {
        return new ArrayList<>(ALL_MOVIES);
    }

    /**
     * get all caterings
     * @return a list with all movies
     */
    public static List<Catering> getAllCaterings() {
        return new ArrayList<>(ALL_CATERINGS);
    }
}