package model;

public class Vocabulary {
    
    // Data fields with Strings
    public static final String frameName = "Kinoreservierung"; // String for the frame
    public static final String dialogName = "Vielen Dank"; // String for the dialog at the end

    public static final String[] instructions = // Strings for instructions on every tab in order
    {
        "Willkommen zum Autokinoreservierungssystem der TH Lübeck",
        "Bitte wählen Sie einen Film aus",
        "Bitte wählen Sie eine Vorstellungszeit für ihren Film aus",
        "Bitte wählen Sie die Plätze aus, die Sie reservieren möchten",
        "Möchten sie noch etwas dazubestellen?",
        "Bitte überprüfen sie ihre Bestellung"
    };
    public static final String[] tabNames = // Strings for names of the tab in order
    {
        "Start",
        "Filme",
        "Zeiten",
        "Plätze",
        "Essen",
        "Bestellen"
    };

    public static final String[] noneLabels = {"keins", "keine", "keiner"}; // String for showing nothing is chosen
    public static final String defaultTabName = "Tab"; // String for default Tab name, used, when no name is set
    public static final String licensePlateLabel = "Kennz."; // String for the license plate JTextField in the seating tab
    public static final String priceLabel = "Gesamtpreis"; // String for the price label
    public static final String currency = "€"; // String for the currency

    // Tooltips
    public static final String reservedTooltip = "Reserviert"; // reserved
    public static final String vipTooltip = "[VIP]"; // vip
    public static final String pkwTooltip = "PKW-Stellplatz"; // pkw
    public static final String suvTooltip = "SUV-Stellplatz"; // suv
    public static final String beachChairTooltip = "Strandkorb"; // beach chair

    // Buttons labels
    public static final String backButtonLabel = "Zurück"; // back
    public static final String quitButtonLabel = "Abbrechen"; // quit
    public static final String exitButtonLabel = "Beenden"; // exit
    public static final String proceedButtonLabel = "Fortfahren"; // proceed
    public static final String finishButtonLabel = "Reservieren"; // reserve/order

    // labels for summary
    public static final String movieLabel = "Film"; // movie
    public static final String timeLabel = "Zeit"; // time
    public static final String seatsLabel = "Plätze"; // seats
    public static final String cateringLabel = "Essen"; // catering
}