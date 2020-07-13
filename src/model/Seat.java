package model;

import model.enums.*;

public class Seat {
    
    protected boolean isReserved = false;
    protected double price;
    protected boolean isVip;

    public Seat(Prices price, boolean isVip) {
        this.price = price.getPrice();
        this.isVip = isVip;
    }
}