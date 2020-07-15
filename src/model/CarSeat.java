package model;

import model.enums.*;

/**
 * special type of seat, inherites from the Seat class
 * can be for suvs and holds a license plate
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class CarSeat extends Seat {

    // Data fields
    protected static final String pkwName = "PKW-Stellplatz";
    protected static final String suvName = "SUV-Stellplatz";
    protected String licensePlate = null;
    public boolean forSUV;

    /**
     * constructor, calls super constructor and sets suv data field
     * @param isVip if the seat is a vip seat
     * @param forSUV if the seat is meant for suvs
     */
    public CarSeat(boolean isVip, boolean forSUV) {
        super(isVip ? Prices.VIP_CAR_SEAT : Prices.NORMAL_CAR_SEAT, isVip, forSUV ? suvName : pkwName);
        this.forSUV = forSUV;
    }
    
    /**
     * toString
     * @return the name of the seat
     */
    @Override
    public String toString() {
        return forSUV ? suvName : pkwName;
    }
}