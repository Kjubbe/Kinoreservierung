package model;

import model.enums.*;

public class BeachChairSeat extends Seat {

    protected static final String name = "Strandkorb";
    protected String ticketNr = null;

    public BeachChairSeat(boolean isVip) {
        super(isVip ? Prices.VIP_BEACH_CHAIR_SEAT : Prices.NORMAL_BEACH_CHAIR_SEAT, isVip, name);
    }
    
    @Override
    public String toString() {
        return name;
    }
}