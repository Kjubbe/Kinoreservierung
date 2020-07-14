package model;

import model.enums.*;

public class Seat {
    
    public boolean isReserved = false;
    public double price;
    public boolean isVip;
    public String tooltip = "";

    public Seat(Prices price, boolean isVip, String tooltip) {
        this.price = price.getPrice();
        this.isVip = isVip;
        if (isVip) this.tooltip += "[VIP] ";
        this.tooltip += tooltip;
    }
}