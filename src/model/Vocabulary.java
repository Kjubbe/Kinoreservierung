package model;

public class Vocabulary {
    
    // Data fields with Strings
    public static final String FRAME_NAME = "Kinoreservierung"; // String for the frame
    public static final String FINISH_DIALOG_NAME = "Vielen Dank"; // String for the dialog at the end
    public static final String ERROR_DIALOG_NAME = "Hoppla..."; // String for error dialogs

    public static final String[] INSTRUCTION_TEXTS = // Strings for instructions on every tab in order
    {
        "Willkommen zum Autokinoreservierungssystem der TH Lübeck!",
        "Bitte wählen Sie einen Film aus:",
        "Bitte wählen Sie eine Vorstellungszeit für ihren Film aus:",
        "Bitte wählen Sie die Plätze aus, die Sie reservieren möchten:",
        "Möchten sie noch etwas dazubestellen?",
        "Bitte überprüfen sie ihre Bestellung:"
    };

    // tabs 
    public static final String[] TAB_NAMES = // Strings for names of the tab in order
    {
        "Start",
        "Filme",
        "Zeiten",
        "Plätze",
        "Essen",
        "Bestellen"
    };
    public static final String DEFAULT_TAB_NAME = "Tab"; // String for default Tab name, used, when no name is set

    public static final String[] NONE_LABELS = {"keins", "keine", "keiner"}; // String for showing nothing is chosen
    public static final String LICENSE_PLATE_LABEL = "Kennz."; // String for the license plate JTextField in the seating tab
    public static final String START_MSG = "Bitte fahren Sie fort, um mit der Reservierung zu beginnen."; // String for the starting msg
    public static final String FINISH_MSG = "Ihre Reservierung war erfolgreich!"; // String for the finishing msg
    public static final String PRICE_LABEL = "Gesamtpreis"; // String for the price label
    public static final String CURRENCY = "€"; // String for the currency

    // errors
    public static final String NO_TIMES_ERROR = "Dieser Film läuft zurzeit leider nicht."; // no times set
    public static final String NO_SEATS_ERROR = "Für diese Uhrzeit sind leider keine Plätze verfügbar"; // no seats

    // Tooltips
    public static final String SOLD_OUT_TOOLTIP = "Ausverkauft"; // sold out
    public static final String RESERVED_TOOLTIP = "Reserviert"; // reserved
    public static final String VIP_TOOLTIP = "[VIP]"; // vip
    public static final String PKW_TOOLTIP = "PKW-Stellplatz"; // pkw
    public static final String SUV_TOOLTIP = "SUV-Stellplatz"; // suv
    public static final String BEACH_CHAIR_TOOLTIP = "Strandkorb"; // beach chair

    // Buttons labels
    public static final String BACK_BUTTON_LABEL = "Zurück"; // back
    public static final String QUIT_BUTTON_LABEL = "Abbrechen"; // quit
    public static final String EXIT_BUTTON_LABEL = "Beenden"; // exit
    public static final String PROCEED_BUTTON_LABEL = "Fortfahren"; // proceed
    public static final String FINISH_BUTTON_LABEL = "Reservieren"; // reserve/order

    // labels for summary
    public static final String MOVIE_LABEL = "Film"; // movie
    public static final String TIME_LABEL = "Zeit"; // time
    public static final String SEATS_LABEL = "Plätze"; // seats
    public static final String CATERING_LABEL = "Essen"; // catering
}