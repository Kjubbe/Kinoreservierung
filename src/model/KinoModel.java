package model;

import model.enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model class, manages calculations and contains saves the information from the user input
 * contains all movies, caterings and orders
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoModel {

    // These lists hold all data
    private static final List<Order> ALL_ORDERS = new ArrayList<>(); // contains all orders TODO maybe create a database class for these three?
    public static final List<Movie> ALL_MOVIES = new ArrayList<>(); // contains all existing movies
    public static final List<Catering> ALL_CATERINGS = new ArrayList<>(); // contains all existing caterings

    // Datafields, which change during runtime
    public Movie chosenMovie; // set based on user input in the movie tab
    public Showtime[] availableTimes; // set based on the chosen movie

    public Showtime chosenTime; // set based on user input in the times tab
    public Seat[][] availableSeats; // set based on the chosen time

    public List<Seat> chosenSeats; // set based on user input in the seat tab
    public int carSeatCount; // contains number of CarSeats in chosenSeats
    public List<String> licensePlates; // contains inputted license plates for CarSeats

    public Map<Catering, Integer> chosenCatering; // set based on user input in the catering tab

    /**
     * Constructor, creates movies and caterings
     */
    public KinoModel() { 
        createMovies(); // create movies
        createCaterings(); // create caterings
    }

    /**
     * called from constructor
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
            null, // test movie
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
     * called from constructor
     * creates catering examples and puts them in the list
     */
    private void createCaterings() {
        Catering[] c = new Catering[] { // create array with all caterings
            new Catering("Cola", Prices.MEDIUM_DRINK),
            new Catering("Popcorn", Prices.LARGE_SNACK),
            new Catering("1l Wasser", Prices.MEDIUM_DRINK),
            new Catering("Nachos", Prices.MEDIUM_SNACK),
            new Catering("Eis", Prices.SMALL_SNACK),
            new Catering(null, null), // test catering
            new Catering("Corrupted honey", null), // test catering
            new Catering(null, Prices.VIP_BEACH_CHAIR_SEAT), // test catering
            null // test catering
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
        System.out.println("DEBUG: " + "model: Movie set, Movie: " + m); // DEBUG
        chosenMovie = m; // set chosen movie
        availableTimes = m.showtimes; // set available times to the times contained in the movie
        reset(3);
    }

    /**
     * invoked from controller when time got chosen
     * assigns the chosen time from the available times
     * assigns the available seats from this time
     * @param cmd action command from the JRadioButton, contains the index for the time
     */
    public void setTime(String cmd) {
        int index = Integer.parseInt(cmd); // get the index from the action command
        System.out.println("DEBUG: " + "model: Time set, Time: " + availableTimes[index]); // DEBUG
        
        // get the time at the index from the action command
        // this time is equivalent to the time displayed on the JRadioButton at the index
        chosenTime = availableTimes[index]; 
        
        availableSeats = chosenTime.seats; // set available seats to the seats contained in the showtime
        reset(2);
    }

    /**
     * invoked from controller when seats got chosen
     * adds or removes seats based on the remove boolean
     * increments or decrements the amount of CarSeats
     * @param cmd action command from the JCheckBox, contains the position of the seat
     * @param remove determines if an seat should be removed, if not, one gets added
     */
    public void changeSeats(String cmd, boolean remove) {
        String[] pos = cmd.split(Vocabulary.SPLITTER_STRING); // get the position from the action command
        
        // get the seat at the position from the action command
        // this seat is equivalent to the seat displayed on the JCheckBox at the position
        Seat s = availableSeats[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])]; // get the seat at the specified position
        
        if (remove) {
            System.out.println("DEBUG: " + "model: Seat removed " + s); // DEBUG
            chosenSeats.remove(s); // remove the seat from the list
        } else {
            System.out.println("DEBUG: " + "model: Seat added " + s); // DEBUG
            chosenSeats.add(s); // add the seat to the list
        }
        if (s instanceof CarSeat) { // check if seat is an instance of CarSeat
            carSeatCount += remove ? -1 : 1;  // increase the counter when adding a CarSeat, decrease the counter when removing a CarSeat
            System.out.println("DEBUG: " + "model: Car seat count changed to " + carSeatCount); // DEBUG
        }
        System.out.println("DEBUG: " + "model: List of seats " + chosenSeats); // DEBUG
        reset(1);
    }

    /**
     * invoked from controller when catering got chosen
     * assigns map with catering as key and amount as value
     * @param cateringAmounts list of amounts for each catering
     */
    public void setCatering(List<Integer> cateringAmounts) { // TODO this will make problems with null pointers in the middle PLS check all these cases
        chosenCatering = new HashMap<>(); // create a new map, which will contain every catering option with their specified amount
        for (int i = 0; i < cateringAmounts.size(); i++) { // check every Integer of the list
            Catering equivalentCatering = ALL_CATERINGS.get(i); // get the catering at the index, which is equivalent to the index of the amount for this catering
            chosenCatering.put(equivalentCatering, cateringAmounts.get(i)); // put catering as key with the selected amount as a value in the map
        }
        System.out.println("DEBUG: " + "model: Catering set, Caterings: " + chosenCatering); // DEBUG
    }

    /**
     * invoked from controller when text got typed in the textfield
     * assigns list with the text from the textfields
     * @param lps list of license plate Strings
     */
    public void setLicensePlates(List<String> lps) {
        System.out.println("DEBUG: " + "model: License plate set, License plates: " + lps); // DEBUG
        licensePlates = lps;
    }

    /**
     * adds all prices from chosen seats and caterings together to calculate the total price
     * @return calculated total price
     */
    public double calculatePrice() {
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
     * resets all user input specified by a depth value
     * a higher depth value = a deeper reset
     * @param depth the depth of the reset
     */
    public void reset(int depth) {
        System.out.println("DEBUG: " + "model: resetting input..."); // DEBUG
        if (depth >= 4) { // this depth reaches to the movie tab
            chosenMovie = null;
            availableTimes = null;
        }
        if (depth >= 3) { // this depth reaches to the times tab
            chosenTime = null;
            availableSeats = null;
        }
        if (depth >= 2) { // this depth reaches to the seat tab
            chosenSeats = new ArrayList<>();
            licensePlates = null;
            carSeatCount = 0;
        }
        if (depth >= 1) // this depth reaches to the seat tab
            chosenCatering = null;
    }

    /**
     * quit the application
     * invoked from controller by pressing the JButton for exiting
     */
    public void quit() {
        System.out.println("\n" + "DEBUG: " + "quitting..."); // DEBUG
        reset(4); // reset the model
        System.exit(0); // terminate the program
    }

    /**
     * place an order
     * assigns a ticket to every chosen beach chair
     * assigns the license plate numbers to the chosen car seat
     * creates and adds a new order object to the list
     */
    public void order() {
        int i = 0; // local index counter
        for (Seat s : chosenSeats) { // check every seat
            System.out.println("DEBUG: model: reserved seat " + s); // DEBUG
            s.isReserved = true; // reserve the seat
            if (s instanceof BeachChairSeat)
                ((BeachChairSeat)s).assignTicket(); // assign a ticket to the beach chair seat
            else {
                ((CarSeat)s).licensePlateNr = licensePlates.get(i++); // assign the license plate to the car seat and increment the index counter
                System.out.println("DEBUG: model: set license plate " + ((CarSeat)s).licensePlateNr); // DEBUG
            } 
            chosenTime.updateAvailability(); // update the availability of the showtime, because seats got reserved
        }
        ALL_ORDERS.add(new Order(chosenMovie, chosenTime, chosenSeats, chosenCatering, calculatePrice())); // create and add a new order with all information
        System.out.println("\n" + "DEBUG: model: All orders are: \n" + ALL_ORDERS + "\n"); // DEBUGs
        // TODO write the order to a file
    }
    
    /**
     * get all the tickets as strings
     * @return array of strings with the ticket numbers
     */
    public String[] getTicketStrings() { // TODO is this stringbuilding not better in the view?
        String print = "";
        for (String s : Vocabulary.FINISH_MSGS) { // go through the finish msgs
            print += s + Vocabulary.SPLITTER_STRING; // add all msgs from the vocab
        }
        for (Seat s : chosenSeats) { // go through the chosen seats
            if (s instanceof BeachChairSeat) print += Vocabulary.TICKET_LABEL + ": " + ((BeachChairSeat)s).getTicket() + Vocabulary.SPLITTER_STRING; // get the ticket from the seat and add it to the string
        }
        return print.split(Vocabulary.SPLITTER_STRING); // split the string
    }
}