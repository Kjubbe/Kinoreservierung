package model;

import java.util.ArrayList;
import java.util.List;

import model.enums.*;

/**
 * has information about a specific catering,
 * contains price and name of the catering
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Catering {
    
    // Data fields
    public static final List<Catering> ALL_CATERINGS = new ArrayList<>(); // contains all existing caterings

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