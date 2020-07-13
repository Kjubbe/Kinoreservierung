package model;

import model.enums.*;

public class CarSeat extends Seat {

    protected static final String name = "Autostellplatz";
    protected String licensePlate = null;
    protected boolean forSUV;

    public CarSeat(boolean isVip, boolean forSUV) {
        super(isVip ? Prices.VIP_CAR_SEAT : Prices.NORMAL_CAR_SEAT, isVip);
        this.forSUV = forSUV;
    }
    
}