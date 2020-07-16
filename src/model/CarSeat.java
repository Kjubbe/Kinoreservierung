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
    private static final String pkwTooltip = Vocabulary.PKW_TOOLTIP;
    private static final String suvTooltip = Vocabulary.SUV_TOOLTIP;
    public final boolean isForSUV;
    private String licensePlateNr = null; // holds license plate number TODO add logic to add this

    /**
     * constructor, calls super constructor and sets suv data field
     * @param isVip if the seat is a vip seat
     * @param isForSUV if the seat is meant for suvs
     */
    public CarSeat(boolean isVip, boolean isForSUV) {
        // price is determined by the isVip boolean
        super(isVip ? Prices.VIP_CAR_SEAT : Prices.NORMAL_CAR_SEAT, isVip, isForSUV ? suvTooltip : pkwTooltip);
        this.isForSUV = isForSUV;
    }
    
    /**
     * toString
     * @return the tooltip of the seat
     */
    @Override
    public String toString() {
        // name is determined by the forSUV boolean
        return isForSUV ? suvTooltip : pkwTooltip;
    }
}