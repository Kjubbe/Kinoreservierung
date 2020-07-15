package model;

import model.enums.*;

/**
 * parent class for custom seats, contains basic data for a seat
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Seat {
    
    // Data fields
    public boolean isReserved = false;
    public double price;
    public boolean isVip;
    public String tooltip = ""; // tooltip for hovering over a seat

    /**
     * constructor, assigns data fields
     * @param price price for the seat
     * @param isVip if the seat is a vip seat
     * @param tooltip tooltip shows when hovering over a seat
     */
    public Seat(Prices price, boolean isVip, String tooltip) {
        this.price = price.getPrice();
        this.isVip = isVip;
        if (isVip) this.tooltip += "[VIP] "; // update tooltip to give more information when seat is vip
        this.tooltip += tooltip;
    }
}