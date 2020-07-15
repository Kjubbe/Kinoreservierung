package model;

import model.enums.*;

/**
 * information about a specific catering-option
 * holds price and name of the catering
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Catering {
    
    // Data fields
    public final double price; // TODO is this correct?
    private String name;

    /**
     * constructor, sets data fields
     * @param name name of the catering option
     * @param price price of the catering option
     */
    public Catering(String name, Prices price) {
        this.price = price.getPrice();
        this.name = name;
    }

    /**
     * toString
     * @return the name and price in parentheses
     */
    @Override
    public String toString() {
        return name + " (" + price + Vocabulary.currency + ")";
    }
}