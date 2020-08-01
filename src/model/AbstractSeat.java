package model;

import model.enums.Prices;
import model.enums.Vocab;

/**
 * parent class for custom seats, contains basic data for a seat
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public abstract class AbstractSeat {
    
    // Data fields
    protected boolean isReserved;
    public final String name; // name for hovering over a seat
    public final Prices price;
    public final boolean isVip;

    /**
     * constructor, assigns data fields
     * @param price price for the seat
     * @param isVip if the seat is a vip seat
     * @param name name shows when hovering over a seat
     */
    public AbstractSeat(Prices price, boolean isVip, String name) {
        this.price = price;
        this.isVip = isVip;
        if (isVip) {
            // update name to give more information when seat is vip
            this.name = "[" + Vocab.VIP_TOOLTIP + "] " + name;
        } else {
            this.name = name;
        }
    }

    /**
     * get if the seat is reserved
     * @return if this seat is reserved
     */
    public final boolean isReserved() {
        return isReserved;
    }

    /**
     * abstract method reserve is invoked from model when placing the order,
     * this method should set all important data fields
     */
    protected abstract void reserve();
}