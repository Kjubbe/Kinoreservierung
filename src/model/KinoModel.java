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
    public static final String softwareName = "Kinoreservierung"; // String for the frame
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

    public static final String defaultTabName = "Tab"; // String for default Tab name, used, when no name is set
    public static final String licensePlateLabel = "Kennz.:"; // String for the license plate JTextField in the seating tab

    // Buttons labels
    public static final String backButtonLabel = "Zurück"; // back
    public static final String abortButtonLabel = "Abbrechen"; // abort
    public static final String exitButtonLabel = "Beenden"; // exit
    public static final String proceedButtonLabel = "Fortfahren"; // proceed
    public static final String finishButtonLabel = "Reservieren"; // reserve/order

    // data
    public static final List<Movie> availableMovies = new ArrayList<>(); // contains all existing movies
    public static final List<Catering> availableCaterings = new ArrayList<>(); // contains all existing catering options

    // Datafields, which change during runtime
    public Movie chosenMovie; // set based on user input in the movie tab

    public Showtime[] availableTimes; // set based on the chosen movie
    public Showtime chosenTime; // set based on user input in the times tab

    public Seat[][] availableSeats; // set based on the chosen seat
    public List<Seat> chosenSeats; // set based on user input in the seat tab
    public int carSeatCount; // contains number of CarSeats in chosenSeats

    public Map<Catering, Integer> chosenCatering; // set based on user input in the catering tab

    /**
     * Constructor, creates movies and catering options
     */
    public KinoModel() { 
        createMovies(); // create movies
        createCaterings(); // create catering options
    }

    /**
     * invoked when creating this
     * creates movie examples and puts them in the list
     */
    public void createMovies() {
        Movie[] f = new Movie[] { // create array with all movies
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
        availableMovies.addAll(Arrays.asList(f)); // add array in the list
    }

    /**
     * invoked when creating this
     * creates catering examples and puts them in the list
     */
    public void createCaterings() {
        Catering[] c = new Catering[] { // create array with all catering options
            new Catering("Cola", Prices.MEDIUM_DRINK),
            new Catering("Popcorn", Prices.LARGE_SNACK),
            new Catering("1l Wasser", Prices.MEDIUM_DRINK),
            new Catering("Nachos", Prices.MEDIUM_SNACK),
            new Catering("Eis", Prices.SMALL_SNACK),
        };
        availableCaterings.addAll(Arrays.asList(c)); // add array in the list
    }

    /**
     * invoked from controller when movie got chosen
     * assigns the chosen movie
     * assigns showtimes from this movie
     * @param m movie which was chosen
     */
    public void setMovie(Movie m) {
        System.out.println("Movie set, Movie: " + m); // DEBUG TODO
        chosenMovie = m; // set chosen movie
        availableTimes = m.showtimes; // set available times to the times contained in the movie
    }

    /**
     * invoked from controller when time got chosen
     * assigns the chosen time
     * assigns the available seats from this movie
     * @param t time which was chosen
     */
    public void setTime(Showtime t) {
        System.out.println("Time set, Time: " + t); // DEBUG TODO
        chosenTime = t; // set chosen time
        availableSeats = t.seats; // set available seats to the seats contained in the showtime
    }

    /**
     * invoked from controller when seats got chosen
     * assigns list of the chosen seats
     * counts amount of CarSeats
     * @param seats list of seats which were chosen
     */
    public void setSeats(List<Seat> seats) {
        System.out.println("Seat set, Seats: " + seats); // DEBUG TODO
        chosenSeats = seats; // set chosen seats
        carSeatCount = 0; // reset the counter
        for (Seat s : chosenSeats) { // check every seat
            if (s instanceof CarSeat) carSeatCount++; // if seat is an instance of CarSeat increase the counter
        }
    }

    /**
     * invoked from controller when catering got chosen
     * assigns map with catering and amount
     * @param cateringCounts
     */
    public void setCatering(Map<Catering, Integer> cateringCounts) {
        System.out.println("Catering set, Caterings: " + cateringCounts); // DEBUG TODO
        chosenCatering = cateringCounts; // set chosen caterings
    }

    /**
     * adds all prices from chosen seats and catering options
     * @return calculated total price
     */
    public double calculatePrice() {
        double price = 0.0; // local variable, holds the price
        if (chosenSeats != null) { // check, if there are chosen seats
            for (Seat s : chosenSeats) { // check every seat
                price += s.price; // add price of the seat to the total amount
            }
        }
        if (chosenCatering != null) { // check, if there are chosen caterings
            for (Map.Entry<Catering, Integer> entry : chosenCatering.entrySet()) { // check every entry of the map
                price += entry.getKey().price * entry.getValue(); // add price of the catering options multiplied by its amount to the total amount
            }
        }
        return Math.round(price * 100.0) / 100.0; // round price to two decimal places
    }
}