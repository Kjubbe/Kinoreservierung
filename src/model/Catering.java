package model;

import model.enums.*;

/**
 * information about a specific catering
 * holds price and name of the catering
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Catering {
    
    // Data fields
    public final Prices price;
    private String name;

    /**
     * constructor, sets data fields
     * @param name name of the catering
     * @param price price of the catering
     */
    public Catering(String name, Prices price) {
        this.price = price;
        this.name = name;
    }

    /**
     * toString
     * @return the name of this catering
     */
    @Override
    public String toString() {
        return name;
    }
}