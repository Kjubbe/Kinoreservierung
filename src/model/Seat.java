package model;

import model.enums.*;

/**
 * parent class for custom seats, contains basic data for a seat
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Seat {
    
    // Data fields
    public boolean isReserved = false; // TODO check if public visibility is okay?
    private String name = ""; // tooltip for hovering over a seat
    public final Prices price;
    public final boolean isVip;

    /**
     * constructor, assigns data fields
     * @param price price for the seat
     * @param isVip if the seat is a vip seat
     * @param name tooltip shows when hovering over a seat
     */
    public Seat(Prices price, boolean isVip, String name) {
        this.price = price;
        this.isVip = isVip;
        if (isVip) this.name += Vocabulary.VIP_TOOLTIP + " "; // update tooltip to give more information when seat is vip
        this.name += name;
    }

    /**
     * toString
     * @return String with the tooltip
     */
    @Override
    public String toString() {
        return name;
    }
}