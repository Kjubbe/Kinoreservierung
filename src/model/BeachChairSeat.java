package model;

import model.enums.*;

/**
 * special type of seat, inherites from the Seat class
 * holds a ticket number
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class BeachChairSeat extends Seat {

    // Data fields
    private static final String name = "Strandkorb";
    private String ticketNr = null; // holds ticket number

    /**
     * constructor, calls super constructor
     * @param isVip if the seat is a vip seat
     */
    public BeachChairSeat(boolean isVip) {
        // price is determined by the isVip boolean
        super(isVip ? Prices.VIP_BEACH_CHAIR_SEAT : Prices.NORMAL_BEACH_CHAIR_SEAT, isVip, name);
    }
    
    /**
     * toString
     * @return the name of the seat
     */
    @Override
    public String toString() {
        return name;
    }
}