package model;

/**
 * Model class, manages calculations
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoModel {
    
    public String softwareName = "Kinoreservierung";
    public String[] instructions =
    {
        "Willkommen zum Autokinoreservierungssystem der TH Lübeck",
        "Bitte wählen Sie einen Film aus",
        "Bitte wählen Sie eine Vorstellungszeit für ihren Film aus",
        "Bitte wählen Sie die Plätze aus, die Sie reservieren möchten",
        "Möchten sie noch etwas dazubestellen?",
        "Bitte überprüfen sie ihre Bestellung"
    };
    public String[] tabNames =
    {
        "Start",
        "Filme",
        "Zeiten",
        "Plätze",
        "Essen",
        "Zusammenfassung",
        "Test"
    };

    public String defaultTabName = "Tab";
    public String backButtonLabel = "Zurück";
    public String exitButtonLabel = "Abbrechen";
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