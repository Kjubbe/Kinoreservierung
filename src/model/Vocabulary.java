package model;

/**
 * contains all strings for the software
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public final class Vocabulary {

    // titles
    public static final String FRAME_NAME = "Kinoreservierung"; // String for the JFrame
    public static final String FINISH_DIALOG_NAME = "Vielen Dank!"; // String for the JDialog at the end
    public static final String ERROR_DIALOG_NAME = "Hoppla..."; // String for error JDialogs

    // instruction text for tabs
    private static final String[] INSTRUCTION_TEXTS = // Strings for instructions on every tab in order
    {
        "Willkommen zum Autokinoreservierungssystem der TH Lübeck!",
        "Bitte wählen Sie einen Film aus:",
        "Bitte wählen Sie eine Vorstellungszeit für ihren Film aus:",
        "Bitte wählen Sie die Plätze aus, die Sie reservieren möchten:",
        "Möchten Sie noch etwas dazubestellen?",
        "Bitte überprüfen Sie ihre Bestellung:"
    };

    // tab names
    public static final String DEFAULT_TAB_NAME = "Tab"; // String for default tab name, used, when no name is set
    private static final String[] TAB_NAMES = // Strings for names of the tab in order
    {
        "Start",
        "Filme",
        "Zeiten",
        "Plätze",
        "Essen",
        "Bestellen"
    };

    // misc
    public static final String NONE_LABEL = "keins"; // String for showing nothing is chosen
    public static final String SCREEN_LABEL = "Leinwand"; // String for screen JLabel

    public static final String START_MSG = "Bitte fahren Sie fort, um mit der Reservierung zu beginnen.";
    
    private static final String[] FINISH_MSGS = {
        "Ihre Reservierung war erfolgreich.",
        "Wir freuen uns auf ihren Besuch!"
    }; // String for the finishing msg JLabels
    
    private static final String[] ORDER_MSGS = {
        "Im Folgenden finden Sie die Informationen zu ihrer Reservierung; Bestellnummer",
        "Vielen Dank, dass Sie unseren Service genutzt haben!"
    }; // String for the order file

    public static final String TOTAL_PRICE_LABEL = "Gesamtpreis"; // String for the price JTextField
    public static final String CURRENCY = "€"; // String for the currency
    public static final String SPLITTER_STRING = "@"; // String to split

    // errors
    public static final String NO_TIMES_ERROR = "Dieser Film läuft zurzeit leider nicht.";
    public static final String NO_SEATS_ERROR = "Für diese Uhrzeit sind leider keine Plätze verfügbar";

    // tooltips
    public static final String SOLD_OUT_TOOLTIP = "Ausverkauft"; // sold out
    public static final String RESERVED_TOOLTIP = "Reserviert"; // reserved
    public static final String VIP_TOOLTIP = "VIP"; // vip
    public static final String PKW_TOOLTIP = "PKW-Stellplatz"; // pkw
    public static final String SUV_TOOLTIP = "SUV-Stellplatz"; // suv
    public static final String BEACH_CHAIR_TOOLTIP = "Strandkorb"; // beach chair

    // hints
    public static final String VIP_HINT = "beste Sicht"; // vip
    public static final String BEACH_CHAIR_HINT = "für max. 3 Personen"; // beach chair
    public static final String PKW_HINT = "für normalgroße Autos"; // pkw
    public static final String SUV_HINT = "nur für SUV o.ä."; // suv
    

    // button labels
    public static final String BACK_BUTTON = "Zurück"; // back
    public static final String QUIT_BUTTON = "Abbrechen"; // quit
    public static final String PROCEED_BUTTON = "Fortfahren"; // proceed
    public static final String FINISH_BUTTON = "Reservieren"; // reserve/order
    public static final String EXIT_BUTTON = "Beenden"; // exit

    // labels for summary
    public static final String MOVIE_LABEL = "Film"; // movie
    public static final String GENRE_LABEL = "Genre"; // genre
    public static final String TIME_LABEL = "Zeit"; // time
    public static final String SEATS_LABEL = "Plätze"; // seats
    
    private static final String[] LICENSE_PLATE_LABEL = {
        "Kennz.",
        "Bitte Kennz. eingeben"
    };

    public static final String TICKET_LABEL = "Ihr Ticket"; // tickets
    public static final String CATERING_LABEL = "Essen"; // catering

    /**
     * private constructor, because this class is not meant to be instantiated
     */
    private Vocabulary () {
        throw new IllegalStateException("Utility class");
    }

    /**
     * get the instruction texts
     * @return the instruction text array
     */
    public static String[] getInstructionTexts() {
        return INSTRUCTION_TEXTS.clone(); // return a copy
    }

    /**
     * get the tab names
     * @return the tab names array
     */
    public static String[] getTabNames() {
        return TAB_NAMES.clone(); // return a copy
    }

    /**
     * get the finish messages
     * @return the finish message array
     */
    public static String[] getFinishMsgs() {
        return FINISH_MSGS.clone(); // return a copy
    }

    /**
     * get the order messages
     * @return the order message array
     */
    public static String[] getOrderMsgs() {
        return ORDER_MSGS.clone(); // return a copy
    }

    /**
     * get the license plate labels
     * @return the license plate label array
     */
    public static String[] getLicensePlateLabel() {
        return LICENSE_PLATE_LABEL.clone(); // return a copy
    }
}