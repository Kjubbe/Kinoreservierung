package model;

import java.util.LinkedList;
import java.util.List;

import model.enums.Prices;
import model.enums.Vocab;

/**
 * special type of seat, inherits from the Seat class, this seat can define if
 * it is for suvs, contains a license plate
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class CarSeat extends AbstractSeat {

    // Data fields
    public final boolean isForSUV; // determines if the seat is meant for suvs

    private static List<String> openLicensePlates;
    private String licensePlate; // holds license plate number

    /**
     * constructor, calls super constructor and sets suv data field
     * 
     * @param isVip    if the seat is a vip seat
     * @param isForSUV if the seat is meant for suvs
     */
    public CarSeat(boolean isVip, boolean isForSUV) {
        // price is determined by the isVip boolean
        // name is determined by the isForSUV boolean
        super(isVip ? Prices.VIP_CAR_SEAT : Prices.NORMAL_CAR_SEAT, isVip,
                isForSUV ? Vocab.SUV_TOOLTIP.toString() : Vocab.PKW_TOOLTIP.toString());
        this.isForSUV = isForSUV;
    }

    /**
     * invoked from model when placing the order, assigns a license plate and
     * reserves a seat
     */
    @Override
    protected void reserve() {
        if (!isReserved) {
            licensePlate = openLicensePlates.remove(0);
            System.out.println("DEBUG: car-seat: license plate set: " + licensePlate); // DEBUG
            Database.addLicensePlate(licensePlate);
            isReserved = true;
            System.out.println("DEBUG: car-seat: reserved: " + this); // DEBUG
        }
    }

    /**
     * assign the list of license plates
     * 
     * @param openLicensePlates the list with license plates
     */
    protected static void setOpenLicensePlates(List<String> openLicensePlates) {
        CarSeat.openLicensePlates = openLicensePlates == null ? null : new LinkedList<>(openLicensePlates);
        System.out.println("DEBUG: car-seat: list of open license plates set: " + openLicensePlates); // DEBUG
    }

    /**
     * get the license plate for this seat
     * 
     * @return the license plate nr
     */
    protected String getLicensePlate() {
        return licensePlate;
    }
}