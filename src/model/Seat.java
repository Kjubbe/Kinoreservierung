package model;

import model.enums.*;

/**
 * parent class for custom seats, contains basic data for a seat
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Seat {
    
    // Data fields
    public boolean isReserved = false; // TODO check if this kind of visibility is okay?
    private String tooltip = ""; // tooltip for hovering over a seat
    public final Prices price; // FIXME is this final correct? because the price cant be changed if the seat was created and that may be an issue?
    public final boolean isVip;

    /**
     * constructor, assigns data fields
     * @param price price for the seat
     * @param isVip if the seat is a vip seat
     * @param tooltip tooltip shows when hovering over a seat
     */
    public Seat(Prices price, boolean isVip, String tooltip) {
        this.price = price;
        this.isVip = isVip;
        if (isVip) this.tooltip += Vocabulary.VIP_TOOLTIP + " "; // update tooltip to give more information when seat is vip
        this.tooltip += tooltip;
    }

    /**
     * get this seats tooltip
     * @return String with the tooltip
     */
    public final String getTooltip() {
        return tooltip;
    }
}