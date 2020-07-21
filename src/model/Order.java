package model;

import java.util.List;
import java.util.Map;

/**
 * contains all information of an order
 * can display all information in a string
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Order {

    // data fields
    public final Movie movie;
    public final Showtime time;
    public final List<Seat> seats;
    public final Map<Catering, Integer> caterings;
    public final double totalPrice;

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
     * @return all data fields as strings
     */
    @Override
    public String toString() {
        
        // movie
        String moviePrint = Vocabulary.MOVIE_LABEL + ": " + movie;
        
        // time
        String timePrint = Vocabulary.TIME_LABEL + ": " + time;
        
        // seats
        String seatPrint = Vocabulary.SEATS_LABEL + ": ";
        for (Seat s : seats) { // go through every seat
            seatPrint += s + ", "; // add all seats to the print
        }

        // caterings
        String cateringPrint = Vocabulary.CATERING_LABEL + ": ";
        if (caterings != null) { // check if caterings are selected
            for (Map.Entry<Catering, Integer> entry : caterings.entrySet()) {
                Catering c = entry.getKey();
                Integer i = entry.getValue();
                if (i == 0) // check if the catering is chosen, if not skip
                    continue; // skip this entry
                cateringPrint += (i + "x " + c + ", "); // add the catering name and amount to the print
            }
            if (cateringPrint.equals("")) // no caterings chosen
                cateringPrint = Vocabulary.NONE_LABELS[0] + "  ";
        } else // no caterings set
            cateringPrint += Vocabulary.NONE_LABELS[0] + "  ";

        // price
        String pricePrint = Vocabulary.TOTAL_PRICE_LABEL + ": " + totalPrice + Vocabulary.CURRENCY;

        return "\n" + moviePrint +
            "\n" + timePrint +
            "\n" + seatPrint.substring(0, seatPrint.length() - 2) +
            "\n" + cateringPrint.substring(0, cateringPrint.length() - 2) +
            "\n" + pricePrint + 
            "\n";
    }
}