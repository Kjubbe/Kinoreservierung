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
    protected static final List<Order> ALL_ORDERS = new ArrayList<>(); // contains all orders

    private final Movie movie;
    private final Showtime time;
    private final List<Seat> seats;
    private final Map<Catering, Integer> caterings;
    private final double totalPrice;
    private final int orderNumber; // TODO maybe change this

    /**
     * constructor, assigns data fields // TODO store clones
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
        this.orderNumber = ALL_ORDERS.size(); // TODO maybe change this
    }

    /**
     * toString
     * @return string with all information
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Vocabulary.getOrderMsgs()[0] + " " + orderNumber + "\n"); // msg with order number
        builder.append(Vocabulary.getOrderMsgs()[1] + "\n"); // second msg
        builder.append("\n" + Vocabulary.MOVIE_LABEL + ": " + movie + "\n"); // movie
        builder.append("\n" + Vocabulary.TIME_LABEL + ": " + time.getDateAndTime() + "\n"); // time
        
        // seats
        builder.append("\n" + Vocabulary.SEATS_LABEL + ": " + "\n");
        for (Seat s : seats) {
            builder.append("- " + s.name + " (" + s.price.getPrice() + Vocabulary.CURRENCY + "), "); // seat
            if (s instanceof BeachChairSeat) {
                // add ticket
                builder.append(Vocabulary.TICKET_LABEL + ": " + ((BeachChairSeat)s).getTicket() + "\n");
            } else {
                // add license plate
                builder.append(Vocabulary.getLicensePlateLabel()[0] + ": \"" + ((CarSeat)s).licensePlateNr + "\"" + "\n");
            }
        }

        // catering
        builder.append("\n" + Vocabulary.CATERING_LABEL + ": ");
        if (caterings != null) {
            boolean none = true; // TODO maybe change this
            for (Map.Entry<Catering, Integer> entry : caterings.entrySet()) { // go through every entry of the map
                Catering c = entry.getKey();
                Integer i = entry.getValue();
                if (i == 0) { // check if the catering is chosen, if not skip
                    continue; // skip this entry
                }
                double price = Math.round(c.price.getPrice() * i * 100.0) / 100.0; // calculate and round the price
                
                // add the catering name and price with their amount to the print
                builder.append("\n" + i + "x " + c.name + " (" + price + Vocabulary.CURRENCY + ")");
                none = false;
            }
            if (none) {
                builder.append(Vocabulary.NONE_LABEL);
            }
        } else {
            builder.append(Vocabulary.NONE_LABEL);
        }

        builder.append("\n" + "\n" + Vocabulary.TOTAL_PRICE_LABEL + ": " + totalPrice + Vocabulary.CURRENCY); // price
        
        return builder.toString();
    }

    /**
     * get the order number for this order
     * @return order number of this order
     */
    public int getOrderNumber() {
        return orderNumber;
    }
}