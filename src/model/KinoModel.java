package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.Icon;

import model.enums.Vocab;

/**
 * model class, manages calculations and 
 * contains saves the information from the user input,
 * calculates price,
 * manages quitting the application
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoModel {

    // license plate constants
    private static final int MIN_LICENSE_PLATE_LENGTH = 5;
    private static final int MAX_LICENSE_PLATE_LENGTH = 9;  // 8 + separator
    private static final String LICENSE_PLATE_REGEX_PATTERN = "^[a-zA-Z]{1,3}-?[a-zA-Z]{1,2}\\d{1,4}$";

    // resetter constants
    private static final int RESET_ABOVE_SEATS = 1;
    private static final int RESET_ABOVE_TIME = 2;
    private static final int RESET_ABOVE_MOVIE = 3;
    private static final int RESET_ABOVE_START = 4;

    // datafields, which change during runtime
    private Movie chosenMovie; // set based on user input in the movie tab
    private Showtime[] availableTimes; // set based on the chosen movie

    private Showtime chosenTime; // set based on user input in the times tab
    private AbstractSeat[][] availableSeats; // set based on the chosen time

    private List<AbstractSeat> chosenSeats = new LinkedList<>(); // set based on user input in the seat tab
    private int carSeatAmount; // contains number of CarSeats in chosenSeats
    private List<String> licensePlates; // contains inputted license plates for CarSeats

    private Map<Catering, Integer> chosenCatering; // set based on user input in the catering tab

    /**
     * invoked from controller when movie got chosen,
     * assigns the chosen movie,
     * assigns available showtimes from this movie
     * 
     * @param movie movie which was chosen
     */
    public void setMovie(Movie movie) {
        System.out.println("DEBUG: model: Movie set, Movie: " + movie); // DEBUG
        if (movie != null) {
            chosenMovie = movie; // set chosen movie
            availableTimes = movie.getShowtimes(); // set available times to the times contained in the movie
            reset(RESET_ABOVE_MOVIE);
        }
    }

    /**
     * invoked from controller when time got chosen,
     * assigns the chosen time from the index in the action command
     * assigns available seats from this time
     * 
     * @param cmd action command from the JRadioButton, contains the index for the time
     */
    public void setTime(String cmd) {
        int index = Integer.parseInt(cmd); // get the index from the action command
        System.out.println("DEBUG: model: Time set, Time: " + availableTimes[index]); // DEBUG

        // get the time at the index from the action command
        // this time is equivalent to the time displayed on the JRadioButton at the index
        chosenTime = availableTimes[index];
        availableSeats = chosenTime.seats; // set available seats to the seats contained in the showtime
        reset(RESET_ABOVE_TIME);
    }

    /**
     * invoked from controller when seats got chosen,
     * adds or removes seats from the list of chosen seats based on the boolean,
     * changes the amount of CarSeats
     * 
     * @param cmd           action command from the JCheckBox, contains the position of the seat                   
     * @param removeElseAdd determines if an seat should be removed, if not, one gets added                
     */
    public void setSeat(String cmd, boolean removeElseAdd) {
        System.out.println("DEBUG: model: changing seats..."); // DEBUG
        String[] xyPos = cmd.split(Vocab.SPLITTER_STRING.toString()); // get the position from the action command

        // get the seat at the position from the action command
        // this seat is equivalent to the seat displayed on the JCheckBox at the position
        AbstractSeat seat = availableSeats[Integer.parseInt(xyPos[0])][Integer.parseInt(xyPos[1])];

        if (removeElseAdd) {
            System.out.println("DEBUG: model: Seat removed: " + seat); // DEBUG
            chosenSeats.remove(seat); // remove the seat from the list
        } else {
            System.out.println("DEBUG: model: Seat added: " + seat); // DEBUG
            chosenSeats.add(seat); // add the seat to the list
        }
        if (seat instanceof CarSeat) { // check if seat is an instance of CarSeat
            if (removeElseAdd) { // increase when adding a CarSeat, decrease when removing
                carSeatAmount--;
            } else {
                carSeatAmount++;
            }
            System.out.println("DEBUG: model: Car seat count is " + carSeatAmount); // DEBUG
        }
        System.out.println("DEBUG: model: List of seats " + chosenSeats); // DEBUG
        reset(RESET_ABOVE_SEATS);
    }

    /**
     * invoked from controller when catering got chosen,
     * assigns map with catering (key) and amount (value)
     * 
     * @param cateringAmounts list of amounts for each catering
     */
    public void setCatering(List<Integer> cateringAmounts) {
        System.out.println("DEBUG: model: changing caterings..."); // DEBUG
        int index = 0; // local index counter
        // create a new map, which will contain every catering option with their
        // specified amount
        chosenCatering = new HashMap<>();
        for (int i = 0; i < Database.getAllCaterings().size(); i++) { // check every Catering of the list

            // get the catering at the index, which is equivalent to the index of the amount
            // for this catering
            Catering equivalentCatering = Database.getAllCaterings().get(i);
            if (equivalentCatering == null || equivalentCatering.getName()== null || equivalentCatering.getPrice() == null) {
                System.out.println("DEBUG: model: corrupt catering skipped"); // DEBUG
                continue; // catering corrupted, skip
            }

            // put catering as key with the selected amount as a value in the map
            chosenCatering.put(equivalentCatering, cateringAmounts.get(index));
            index++;
        }
        System.out.println("DEBUG: model: Catering set, Caterings: " + chosenCatering); // DEBUG
    }

    /**
     * invoked from controller when text got typed in the textfield,
     * assigns list with the text from the textfields
     * 
     * @param lps list of license plate Strings
     */
    public void setLicensePlates(List<String> lps) {
        System.out.println("DEBUG: model: License plate set, License plates: " + lps); // DEBUG

        licensePlates = new ArrayList<>(lps); // store a copy
    }

    /**
     * checks if the input has an acceptable length
     * 
     * @param input String to be checked
     * @return if the input suffices
     */
    public boolean checkInput(String input) {
        System.out.println("DEBUG: model: checking input..."); // DEBUG

        input = input.replaceAll("\\s+", ""); // remove all whitespaces

        if(!Pattern.matches(LICENSE_PLATE_REGEX_PATTERN, input)){
            return false;
        }

        // input only suffices if the length of the text is greater than min and less than max
        return input.length() >= MIN_LICENSE_PLATE_LENGTH && input.length() <= MAX_LICENSE_PLATE_LENGTH;
    }

    /**
     * resets all user input specified by a depth value,
     * a higher depth value = a deeper reset
     * 
     * @param depth the depth of the reset
     */
    public void reset(int depth) {
        System.out.println("DEBUG: model: resetting input..."); // DEBUG

        if (depth >= RESET_ABOVE_START) { // this depth reaches to the movie tab
            chosenMovie = null;
            availableTimes = null;
            System.out.println("DEBUG: model: resetted above start"); // DEBUG
        }
        if (depth >= RESET_ABOVE_MOVIE) { // this depth reaches to the times tab
            chosenTime = null;
            availableSeats = null;
            System.out.println("DEBUG: model: resetted above movie"); // DEBUG
        }
        if (depth >= RESET_ABOVE_TIME) { // this depth reaches to the seat tab
            chosenSeats = new LinkedList<>();
            licensePlates = null;
            carSeatAmount = 0;
            System.out.println("DEBUG: model: resetted above time"); // DEBUG
        }
        if (depth >= RESET_ABOVE_SEATS) { // this depth reaches to the catering tab
            chosenCatering = null;
            System.out.println("DEBUG: model: resetted above seats"); // DEBUG
        }
    }

    /**
     * resets all user input
     */
    public void reset() {
        System.out.println("DEBUG: model: resetting everything"); // DEBUG
        reset(Integer.MAX_VALUE);
    }

    /**
     * invoked from controller by pressing the JButton for exiting,
     * resets input,
     * quit the application
     */
    public void quit() {
        System.out.println("\n" + "DEBUG: model: quitting..."); // DEBUG

        reset(); // reset the model
        System.exit(0); // terminate the program
    }

    /**
     * place an order,
     * reserves every chosen seat,
     * creates and adds a new order object to the database
     * creates a file with the order
     */
    public void placeOrder() {
        System.out.println("DEBUG: model: placing order..."); // DEBUG
        CarSeat.setOpenLicensePlates(licensePlates);
        for (AbstractSeat seat : chosenSeats) { // check every seat
            System.out.println("DEBUG: model: reserving seat...: " + seat); // DEBUG
            seat.reserve();
        }
        chosenTime.updateAvailability(); // update the availability of the showtime, because seats got reserved
        System.out.println("DEBUG: model: creating order object"); // DEBUG
        Order order = new Order(chosenMovie, chosenTime, chosenSeats, chosenCatering, getTotalPrice());
        Database.addOrder(order); // create and add a new order with all information

        String path = "orders/Bestellung-" + order.orderNumber;
        System.out.println("DEBUG: model: creating file"); // DEBUG
        while (!FileManager.createTXTFile(path, order.orderDescription)) {
            path += "_";
        }
    }

    /**
     * get all the tickets as strings
     * 
     * @return array of strings with the ticket numbers
     */
    public String[] getTicketStrings() {
        String[] ticketStrings = new String[chosenSeats.size()];
        for (int i = 0; i < ticketStrings.length; i++) {
            AbstractSeat seat = chosenSeats.get(i);
            if (seat instanceof BeachChairSeat) {
                // get the ticket from the seat and add it to the array
                ticketStrings[i] = ((BeachChairSeat) seat).getTicket();
            }
        }
        return ticketStrings;
    }

    /**
     * get the total price,
     * adds all prices from chosen seats and caterings
     * together to calculate the total price
     * 
     * @return total price
     */
    public double getTotalPrice() {
        System.out.println("DEBUG: " + "model: calculating price..."); // DEBUG
        double price = 0.0; // local variable, holds the price
        if (!chosenSeats.isEmpty()) { // check, if there are chosen seats
            for (AbstractSeat seat : chosenSeats) { // check every seat
                price += seat.getPrice().getPrice(); // add price of the seat to the total amount
            }
        }
        if (chosenCatering != null) { // check, if there are chosen caterings
            for (Map.Entry<Catering, Integer> entry : chosenCatering.entrySet()) { // check every entry of the map
                Catering catering = entry.getKey();
                Integer amount = entry.getValue();
                price += catering.getPrice().getPrice() * amount; // price is equal to the price multiplied by the amount
            }
        }
        return Math.round(price * 100.0) / 100.0; // round price to two decimal places
    }

    /**
     * get the description of the chosen movie
     * 
     * @return description of chosen movie
     */
    public String getChosenMovieDescription() {
        return chosenMovie.getDescription();
    }

    /**
     * get the image of the chosen movie
     * 
     * @return icon image of chosen movie
     */
    public Icon getChosenMovieImage() {
        return chosenMovie.getImage();
    }

    /**
     * get the available times,
     * the array is passed by value, not reference!
     * 
     * @return copy of array of showtimes
     */
    public Showtime[] getAvailableTimes() {
        return availableTimes == null ? null : availableTimes.clone(); // return a copy
    }

    /**
     * get the chosen day and time
     * 
     * @return chosen day and time
     */
    public String getChosenDayAndTime() {
        return chosenTime.getDayAndTime();
    }

    /**
     * get the available seats,
     * the array is passed by value, not reference!
     * 
     * @return copy of array of seats
     */
    public AbstractSeat[][] getAvailableSeats() {
        return availableSeats == null ? null : availableSeats.clone(); // return a copy
    }

    /**
     * get the chosen seats,
     * the list is passed by value, not reference!
     * 
     * @return copy of list of seats
     */
    public List<AbstractSeat> getChosenSeats() {
        return chosenSeats == null ? null : new ArrayList<>(chosenSeats); // return a copy
    }

    /**
     * get the amount of car seats
     * 
     * @return number of car seats
     */
    public int getCarSeatAmount() {
        return carSeatAmount;
    }

    /**
     * get the license plates,
     * the list is passed by value, not reference!
     * 
     * @return copy of list of license plates
     */
    public List<String> getLicensePlates() {
        return licensePlates == null ? null : new ArrayList<>(licensePlates); // return a copy
    }

    /**
     * get the caterings,
     * the map is passed by value, not reference!
     * 
     * @return copy of map with caterings and amounts
     */
    public Map<Catering, Integer> getChosenCatering() {
        if (chosenCatering != null) {
            Map<Catering, Integer> copy = new HashMap<>(); // create a new map
            for (Map.Entry<Catering, Integer> entry : chosenCatering.entrySet()) {
                copy.put(entry.getKey(), entry.getValue()); // put every entry in the new map
            }
            return copy; // return the copy
        }
        return null; // if null return null
    }
}