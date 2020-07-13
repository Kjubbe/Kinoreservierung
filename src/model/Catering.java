package model;

import model.enums.Prices;

public class Catering {
    
    public double price;
    public String name;

    public Catering(String name, Prices price) {
        this.price = price.getPrice();
        this.name = name;
    }
}