import view.KinoView;

/**
 * main, creates the view
 * this program allows users to reserve seats for a drive in cinema
 * the user can choose a movie and a showtime and the desired seats
 * the user can also choose catering options
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class KinoMain {

    /**
     * main method, gets called when program runs, creates new view
     * 
     * @param args cmd parameters, unused
     */
    public static void main(String[] args) {
        System.out.println("DEBUG: main: created view..."); // DEBUG
        new KinoView();
    }
}