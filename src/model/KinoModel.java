package model;

import model.enums.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Model class, manages calculations and holds Strings for labels
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoModel {
    
    // Data fields with Strings
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
        "Bestellen"
    };

    public static final String defaultTabName = "Tab";
    public static final String licensePlateLabel = "Kennz.:";

    public static final String backButtonLabel = "Zurück";
    public static final String abortButtonLabel = "Abbrechen";
    public static final String exitButtonLabel = "Beenden";
    public static final String proceedButtonLabel = "Fortfahren";
    public static final String finishButtonLabel = "Reservieren";

    // data
    public static final List<Movie> availableMovies = new ArrayList<>();
    public static final List<Catering> availableCaterings = new ArrayList<>();

    // Datafields, which change during runtime
    public Movie chosenMovie;

    public Showtime[] availableTimes;
    public Showtime chosenTime;

    public Seat[][] availableSeats;
    public List<Seat> chosenSeats;
    public int carSeatCount;

    public Map<Catering, Integer> chosenCatering;

    /**
     * Constructor, creates movies and caterings
     */
    public KinoModel() { 
        createMovies();
        createCaterings();
    }

    /**
     * invoked when creating this
     * creates movie examples and puts them in the list
     */
    public void createMovies() {
        Movie[] f = new Movie[] {
            new Movie("Terminator", Genres.Action, FSK.FSK_18, new Showtime[] {
                new Showtime(Date.Sa, Time.PM_7_30, 6, 5),
                new Showtime(Date.Mo, Time.PM_6_30, 5, 8),
                new Showtime(Date.Mo, Time.PM_4_30, 7, 3),
                new Showtime(Date.Mo, Time.PM_9_30, 5, 2)
            }),
            new Movie("Findet Nemo", Genres.Familie, FSK.FSK_0, new Showtime[] {
                new Showtime(Date.Di, Time.PM_6_30, 5, 5),
                new Showtime(Date.Sa, Time.PM_5, 4, 5)
            }),
            new Movie("Abduction 2", Genres.Thriller, FSK.FSK_12, new Showtime[] {
                new Showtime(Date.Sa, Time.PM_9_30, 5, 8),
                new Showtime(Date.Sa, Time.PM_7, 7, 6),
                new Showtime(Date.Sa, Time.PM_5_30, 5, 3),
            }),
            new Movie("Wintertime", Genres.Familie, FSK.FSK_16, new Showtime[] {
                new Showtime(Date.Mo, Time.PM_5, 5, 5),
                new Showtime(Date.Fr, Time.PM_9_30, 7, 5),
                new Showtime(Date.Sa, Time.PM_7, 6, 3),
                new Showtime(Date.Sa, Time.PM_5, 3, 6),
                new Showtime(Date.Sa, Time.PM_6, 5, 3),
            }),
            new Movie("Dueness", Genres.Horror, FSK.FSK_18, new Showtime[] {
                new Showtime(Date.Sa, Time.PM_7, 5, 3),
                new Showtime(Date.Mo, Time.PM_7_30, 5, 6)
            }),
            new Movie("The Operator", Genres.Action, FSK.FSK_16, new Showtime[] {
                new Showtime(Date.Sa, Time.PM_5, 3, 5),
                new Showtime(Date.So, Time.PM_10, 9, 5),
                new Showtime(Date.Mo, Time.PM_8_30, 7, 6),
                new Showtime(Date.Mo, Time.PM_7_30, 5, 3)
            }),
            new Movie("Missing Throne", Genres.Western, FSK.FSK_12, new Showtime[] {
                new Showtime(Date.Do, Time.PM_9_30, 5, 5),
                new Showtime(Date.Sa, Time.PM_5_30, 4, 5)
            })
        };
        availableMovies.addAll(Arrays.asList(f));
    }

    /**
     * invoked when creating this
     * creates catering examples and puts them in the list
     */
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

    /**
     * invoked from controller when movie got chosen
     * assigns the chosen movie
     * assigns showtimes from this movie
     * @param m movie which was chosen
     */
    public void setMovie(Movie m) {
        chosenMovie = m;
        availableTimes = m.showtimes;
    }

    /**
     * invoked from controller when time got chosen
     * assigns the chosen time
     * assigns the available seats from this movie
     * @param t time which was chosen
     */
    public void setTime(Showtime t) {
        chosenTime = t;
        availableSeats = t.seats;
    }

    /**
     * invoked from controller when seats got chosen
     * assigns list of the chosen seats
     * counts amount of CarSeats
     * @param seats list of seats which were chosen
     */
    public void setSeats(List<Seat> seats) {
        System.out.println("Seat set");
        chosenSeats = seats;
        carSeatCount = 0;
        for (Seat s : chosenSeats) {
            if (s instanceof CarSeat) carSeatCount++;
        }
    }

    /**
     * invoked from controller when catering got chosen
     * assigns map with catering and amount
     * @param cateringCounts
     */
    public void setCatering(Map<Catering, Integer> cateringCounts) {
        System.out.println("Caterings: " + cateringCounts);
        chosenCatering = cateringCounts;
    }

    /**
     * adds all prices from chosen seats and catering options
     * @return calculated total price
     */
    public double calculatePrice() {
        double price = 0.0;
        if (chosenSeats != null) {
            for (Seat s : chosenSeats) {
                price += s.price;
            }
        }
        if (chosenCatering != null) {
            for (Map.Entry<Catering, Integer> entry : chosenCatering.entrySet()) {
                price += entry.getKey().price * entry.getValue();
            }
        }
        return Math.round(price * 100.0) / 100.0;
    }
}