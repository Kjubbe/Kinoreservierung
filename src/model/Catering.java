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
    public final Prices price; // FIXME is this final correct? because the price cant be changed if the catering was created and that may be an issue?
    private String name;

    /**
     * constructor, sets data fields
     * @param name name of the catering option
     * @param price price of the catering option
     */
    public Catering(String name, Prices price) {
        this.price = price;
        this.name = name;
    }

    /**
     * toString
     * @return the name
     */
    @Override
    public String toString() {
        return name;
    }
}