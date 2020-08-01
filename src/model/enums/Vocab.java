package model.enums;

/**
 * this enum defines and contains all important strings for the software
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public enum Vocab {
    
    FRAME_NAME("Kinoreservierung"), // String for the JFrame
    FINISH_DIALOG_NAME("Vielen Dank!"), // String for the JDialog at the end
    ERROR_DIALOG_NAME("Hoppla..."), // String for error JDialogs

    // instruction text for tabs
    INSTRUCTION_TEXTS( new String[] // Strings for instructions on every tab in order
    {
        "Willkommen zum Autokinoreservierungssystem der TH Lübeck!",
        "Bitte wählen Sie einen Film aus:",
        "Bitte wählen Sie eine Vorstellungszeit für ihren Film aus:",
        "Bitte wählen Sie die Plätze aus, die Sie reservieren möchten:",
        "Möchten Sie noch etwas dazubestellen?",
        "Bitte überprüfen Sie ihre Bestellung:"
    }),

    // tab names
    DEFAULT_TAB_NAME("Tab"), // String for default tab name, used, when no name is set
    TAB_NAMES( new String[] // Strings for names of the tab in order
    {
        "Start",
        "Filme",
        "Zeiten",
        "Plätze",
        "Essen",
        "Bestellen"
    }),

    // misc
    NONE_LABEL("keins"), // String for showing nothing is chosen
    SCREEN_LABEL("Leinwand"), // String for screen JLabel

    START_MSG("Bitte fahren Sie fort, um mit der Reservierung zu beginnen."),
    
    FINISH_MSGS( new String[]
    {
        "Ihre Reservierung war erfolgreich.",
        "Wir freuen uns auf ihren Besuch!"
    }), // String for the finishing msg JLabels
    
    ORDER_MSGS( new String[] // String for the order file
    {
        "Vielen Dank, dass Sie unseren Service nutzen!",
        "Im Folgenden finden Sie die Informationen zu ihrer Reservierung; Bestellnummer"
    }),

    TOTAL_PRICE_LABEL("Gesamtpreis"), // String for the price JTextField
    CURRENCY("€"), // String for the currency
    SPLITTER_STRING("@"), // String to split

    // errors
    NO_TIMES_ERROR("Dieser Film läuft zurzeit leider nicht."),
    NO_SEATS_ERROR("Für diese Uhrzeit sind leider keine Plätze verfügbar"),

    // tooltips
    SOLD_OUT_TOOLTIP("Ausverkauft"), // sold out
    RESERVED_TOOLTIP("Reserviert"), // reserved
    
    VIP_TOOLTIP("VIP"), // vip
    PKW_TOOLTIP("PKW-Stellplatz"), // pkw
    SUV_TOOLTIP("SUV-Stellplatz"), // suv
    BEACH_CHAIR_TOOLTIP("Strandkorb"), // beach chair

    // hints
    VIP_HINT("beste Sicht"), // vip
    BEACH_CHAIR_HINT("für max. 3 Personen"), // beach chair
    PKW_HINT("für normalgroße Autos"), // pkw
    SUV_HINT("nur für SUV o.ä."), // suv
    

    // button labels
    BACK_BUTTON("Zurück"), // back
    QUIT_BUTTON("Abbrechen"), // quit
    PROCEED_BUTTON("Fortfahren"), // proceed
    FINISH_BUTTON("Reservieren"), // reserve/order
    EXIT_BUTTON("Beenden"), // exit

    // labels for summary
    MOVIE_LABEL("Film"), // movie
    GENRE_LABEL("Genre"), // genre
    TIME_LABEL("Zeit"), // time
    SEATS_LABEL("Plätze"), // seats
    
    LICENSE_PLATE_LABEL( new String[]
    {
        "Kennz.",
        "Bitte Kennz. eingeben"
    }),

    TICKET_LABEL("Ihr Ticket"), // tickets
    CATERING_LABEL("Essen"); // catering

    // data fields, contains the strings
    private final String[] strings;
    private final String string;

    /**
     * constructor, assigns data field
     * @param strings array of strings
     */
    Vocab(String[] strings) {
        this.strings = strings;
        this.string = null;
    }

    /**
     * constructor, assigns data field
     * @param string data as a string
     */
    Vocab(String string) {
        this.strings = null;
        this.string = string;
    }

    /**
     * get the array of strings for this enum
     * @return the strings in a cloned array
     */
    public String[] getStrings() {
        return strings.clone();
    }

    /**
     * toString
     * @return the string for this enum
     */
    @Override
    public String toString() {
        return string;
    }
}