package model;

import model.enums.*;

public class CarSeat extends Seat {

    protected static final String pkwName = "PKW-Stellplatz";
    protected static final String suvName = "SUV-Stellplatz";
    protected String licensePlate = null;
    public boolean forSUV;

    public CarSeat(boolean isVip, boolean forSUV) {
        super(isVip ? Prices.VIP_CAR_SEAT : Prices.NORMAL_CAR_SEAT, isVip, forSUV ? suvName : pkwName);
        this.forSUV = forSUV;
    }
    
}