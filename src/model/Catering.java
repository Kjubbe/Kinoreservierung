package model;

import model.enums.Prices;

/**
 * has information about a specific catering,
 * contains price and name of the catering
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Catering {

    // Data fields
    private Prices price;
    private String name;

    /**
     * constructor, sets data fields
     * 
     * @param name  name of the catering
     * @param price price of the catering
     */
    public Catering(String name, Prices price) {
        this.price = price;
        this.name = name;
    }

    /**
     * toString
     * 
     * @return the name of the catering
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * get the price of this catering
     * 
     * @return the price
     */
    public Prices getPrice() {
        return price;
    }

    /**
     * get the name of this catering
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
}