package model;

import java.util.List;
import java.util.Map;

import model.enums.Vocab;

/**
 * contains all information of an order,
 * can display all information in a string
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Order {

    // data fields
    private final Movie movie;
    private final Showtime time;
    private final List<AbstractSeat> seats;
    private final Map<Catering, Integer> caterings;
    private final double totalPrice;

    protected final int orderNumber;

    /**
     * constructor, assigns data fields // TODO store clones
     * @param movie the chosen movie
     * @param time the chosen time of the movie
     * @param seats the chosen seats for the time
     * @param caterings chosen caterings
     */
    public Order(Movie movie, Showtime time, List<AbstractSeat> seats, Map<Catering, Integer> caterings, double totalPrice) {
        this.movie = movie;
        this.time = time;
        this.seats = seats;
        this.caterings = caterings;
        this.totalPrice = totalPrice;
        this.orderNumber = NumberManager.createOrderNumber();
    }

    /**
     * toString
     * @return string with all information
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(Vocab.ORDER_MSGS.getStrings()[0] + "\n"); // first msg
        builder.append(Vocab.ORDER_MSGS.getStrings()[1] + ": " + orderNumber + "\n"); // second msg
        builder.append("\n" + Vocab.MOVIE_LABEL + ": " + movie + "\n"); // movie
        builder.append("\n" + Vocab.TIME_LABEL + ": " + time.getDayAndTime() + "\n"); // time
        
        // seats
        builder.append("\n" + Vocab.SEATS_LABEL + ": " + "\n");
        for (AbstractSeat seat : seats) {
            builder.append("- " + seat.name + " (" + seat.price.getPrice() + Vocab.CURRENCY + "), "); // seat
            if (seat instanceof BeachChairSeat) {
                // add ticket
                builder.append(Vocab.TICKET_LABEL + ": " + ((BeachChairSeat)seat).getTicket() + "\n");
            } else {
                // add license plate
                builder.append(Vocab.LICENSE_PLATE_LABEL.getStrings()[0] + ": \"" + ((CarSeat)seat).getLicensePlate() + "\"" + "\n");
            }
        }

        // catering
        builder.append("\n" + Vocab.CATERING_LABEL + ": ");
        if (caterings != null) {
            boolean none = true;
            for (Map.Entry<Catering, Integer> entry : caterings.entrySet()) { // go through every entry of the map
                Catering catering = entry.getKey();
                Integer amount = entry.getValue();
                if (amount == 0) { // check if the catering is chosen, if not skip
                    continue; // skip this entry
                }
                double price = Math.round(catering.price.getPrice() * amount * 100.0) / 100.0; // calculate and round the price
                
                // add the catering name and price with their amount to the print
                builder.append("\n" + amount + "x " + catering.name + " (" + price + Vocab.CURRENCY + ")");
                none = false;
            }
            if (none) {
                builder.append(Vocab.NONE_LABEL);
            }
        } else {
            builder.append(Vocab.NONE_LABEL);
        }

        builder.append("\n" + "\n" + Vocab.TOTAL_PRICE_LABEL + ": " + totalPrice + Vocab.CURRENCY); // price
        
        return builder.toString();
    }
}