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
    protected static final String name = "Strandkorb";
    protected String ticketNr = null;

    /**
     * constructor, calls super constructor
     * @param isVip if the seat is a vip seat
     */
    public BeachChairSeat(boolean isVip) {
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