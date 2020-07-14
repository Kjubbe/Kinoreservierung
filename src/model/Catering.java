package model;

import model.enums.*;

public class Catering {
    
    public double price;
    public String name;

    public Catering(String name, Prices price) {
        this.price = price.getPrice();
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " (" + price + "€)";
    }
}