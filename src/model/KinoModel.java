package model;

/**
 * Model class, manages calculations
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoModel {
    
    public String softwareName = "Kinoreservierung";
    public String[] tabNames = { "Start", "Filme", "Zeiten", "Plätze", "Essen", "Zusammenfassung"};
    public String proceedButtonLabel = "Fortfahren";
    public String finishButtonLabel = "Reservieren";

    /**
     * Constructor
     */
    public KinoModel() {
        // TODO
    }

    public double getPrice() {
        return 9.0; // TODO
    }
}