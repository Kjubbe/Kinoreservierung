package model;

import model.enums.Prices;
import model.enums.Vocab;

/**
 * special type of seat, inherites from the Seat class, this seat can define if
 * it is for suvs, contains a license plate
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class CarSeat extends AbstractSeat {

    // Data fields
    public final boolean isForSUV; // determines if the seat is meant for suvs
    protected String licensePlateNr; // holds license plate number // TODO change visibility to protected and provide accessors

    /**
     * constructor, calls super constructor and sets suv data field
     * @param isVip if the seat is a vip seat
     * @param isForSUV if the seat is meant for suvs
     */
    public CarSeat(boolean isVip, boolean isForSUV) {
        // price is determined by the isVip boolean
        // name is determined by the isForSUV boolean
        super(isVip ? Prices.VIP_CAR_SEAT : Prices.NORMAL_CAR_SEAT,
            isVip,
            isForSUV ? Vocab.SUV_TOOLTIP.toString() : Vocab.PKW_TOOLTIP.toString());
        this.isForSUV = isForSUV;
    }

    @Override
    protected void reserve() {
        isReserved = true;
        // TODO assign license plate
    }
}