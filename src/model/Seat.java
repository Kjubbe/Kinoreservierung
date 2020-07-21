package model;

import model.enums.*;

/**
 * parent class for custom seats, contains basic data for a seat
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Seat {
    
    // Data fields
    protected boolean isReserved = false;
    private String name = ""; // name for hovering over a seat
    public final Prices price;
    public final boolean isVip;

    /**
     * constructor, assigns data fields
     * @param price price for the seat
     * @param isVip if the seat is a vip seat
     * @param name name shows when hovering over a seat
     */
    public Seat(Prices price, boolean isVip, String name) {
        this.price = price;
        this.isVip = isVip;
        if (isVip)
            this.name += "[" + Vocabulary.VIP_TOOLTIP + "] "; // update name to give more information when seat is vip
        this.name += name;
    }

    /**
     * get the name of this seat
     * @return String with the name
     */
    public String getName() {
        return name;
    }

    /**
     * get if the seat is reserved
     * @return if this seat is reserved
     */
    public boolean isReserved() {
        return isReserved;
    }
}