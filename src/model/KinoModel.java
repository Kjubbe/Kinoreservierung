package model;

import model.enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Model class, manages calculations
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoModel {
    
    public static final String softwareName = "Kinoreservierung";
    public static final String[] instructions =
    {
        "Willkommen zum Autokinoreservierungssystem der TH Lübeck",
        "Bitte wählen Sie einen Film aus",
        "Bitte wählen Sie eine Vorstellungszeit für ihren Film aus",
        "Bitte wählen Sie die Plätze aus, die Sie reservieren möchten",
        "Möchten sie noch etwas dazubestellen?",
        "Bitte überprüfen sie ihre Bestellung"
    };
    public static final String[] tabNames =
    {
        "Start",
        "Filme",
        "Zeiten",
        "Plätze",
        "Essen",
        "Zusammenfassung",
        "Test"
    };

    public static final String defaultTabName = "Tab";

    public static final String backButtonLabel = "Zurück";
    public static final String abortButtonLabel = "Abbrechen";
    public static final String exitButtonLabel = "Beenden";
    public static final String proceedButtonLabel = "Fortfahren";
    public static final String finishButtonLabel = "Reservieren";

    private static final List<Film> availableFilms = new ArrayList<>();
    private static final List<Catering> availableCaterings = new ArrayList<>();

    /**
     * Constructor
     */
    public KinoModel() { 
        createFilms();
        createCaterings();
    }

    public void createFilms() {
        Film[] f = new Film[] {
            new Film("Terminator", Genres.Action, FSK.FSK_18, new Showtime[] {
                new Showtime(Date.Sa, Time.PM_7_30, 5, 5),
                new Showtime(Date.Mo, Time.PM_6_30, 5, 5)
            }),
            new Film("Findet Nemo", Genres.Familie, FSK.FSK_0, new Showtime[] {
                new Showtime(Date.Di, Time.PM_6_30, 5, 5),
                new Showtime(Date.Sa, Time.PM_5, 5, 5)
            }),
            new Film("Abduction 2", Genres.Thriller, FSK.FSK_12, new Showtime[] {
                new Showtime(Date.Sa, Time.PM_9_30, 5, 5),
                new Showtime(Date.Sa, Time.PM_7, 5, 5)
            }),
            new Film("Wintertime", Genres.Familie, FSK.FSK_16, new Showtime[] {
                new Showtime(Date.Mo, Time.PM_5, 5, 5),
                new Showtime(Date.Fr, Time.PM_9_30, 5, 5)
            }),
            new Film("Dueness", Genres.Horror, FSK.FSK_18, new Showtime[] {
                new Showtime(Date.Sa, Time.PM_7, 5, 5),
                new Showtime(Date.Mo, Time.PM_7_30, 5, 5)
            }),
            new Film("The Operator", Genres.Action, FSK.FSK_16, new Showtime[] {
                new Showtime(Date.Sa, Time.PM_5, 5, 5),
                new Showtime(Date.So, Time.PM_10, 5, 5)
            }),
            new Film("Missing Throne", Genres.Western, FSK.FSK_12, new Showtime[] {
                new Showtime(Date.Do, Time.PM_9_30, 5, 5),
                new Showtime(Date.Sa, Time.PM_5_30, 5, 5)
            })
        };
        availableFilms.addAll(Arrays.asList(f));
    }

    public void createCaterings() {
        Catering[] c = new Catering[] {
            new Catering("Cola", Prices.MEDIUM_DRINK),
            new Catering("Popcorn", Prices.LARGE_SNACK),
            new Catering("1l Wasser", Prices.MEDIUM_DRINK),
            new Catering("Nachos", Prices.MEDIUM_SNACK),
            new Catering("Eis", Prices.SMALL_SNACK),
        };
        availableCaterings.addAll(Arrays.asList(c));
    }

    public double getPrice() {
        return 9.0; // TODO
    }
}