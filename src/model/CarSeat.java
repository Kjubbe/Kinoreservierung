package model;

import java.util.ArrayList;
import java.util.List;

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

    private static List<String> openLicensePlates;
    private String licensePlateNr; // holds license plate number

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

    /**
     * invoked from model when placing the order,
     * assigns a license plate and reserves a seat
     */
    @Override
    protected void reserve() {
        licensePlateNr = openLicensePlates.remove(0);
        isReserved = true;
    }

    /**
     * assign the list of license plates
     * @param openLicensePlates the list with license plates
     */
    public static void setOpenLicensePlates(List<String> openLicensePlates) {
        CarSeat.openLicensePlates = openLicensePlates == null ? null : new ArrayList<>(openLicensePlates);
    }

    /**
     * get the license plate for this seat
     * @return the license plate nr
     */
    public String getLicensePlateNr() {
        return licensePlateNr;
    }
}