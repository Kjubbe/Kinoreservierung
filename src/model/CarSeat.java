package model;

import model.enums.Prices;

/**
 * special type of seat, inherites from the Seat class, this seat can define if
 * it is for suvs, contains a license plate
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class CarSeat extends Seat {

    // Data fields
    private static final String PKW_NAME = Vocabulary.PKW_TOOLTIP;
    private static final String SUV_NAME = Vocabulary.SUV_TOOLTIP;
    public final boolean isForSUV; // determines if the seat is meant for suvs
    protected String licensePlateNr; // holds license plate number

    /**
     * constructor, calls super constructor and sets suv data field
     * @param isVip if the seat is a vip seat
     * @param isForSUV if the seat is meant for suvs
     */
    public CarSeat(boolean isVip, boolean isForSUV) {
        // price is determined by the isVip boolean
        // name is determined by the isForSUV boolean
        super(isVip ? Prices.VIP_CAR_SEAT : Prices.NORMAL_CAR_SEAT, isVip, isForSUV ? SUV_NAME : PKW_NAME);
        this.isForSUV = isForSUV;
    }
}