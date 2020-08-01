package model;

import model.enums.Days;
import model.enums.Times;

/**
 * contains data for a showtime and seating
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Showtime { // TODO maybe add not only weekdays but dates aswell? (overkill!)
    
    // data fields
    private final Days date;
    private final Times time;
    private boolean isSoldOut; // showtime is not sold out by default

    protected final AbstractSeat[][] seats; // contains all available seats for this showtime
    
    /**
     * constructor, assigns data fields,
     * builds rows and columns of seats
     * @param date date for the showtime
     * @param time time for the showtime
     * @param seatRowAmount amount of rows of seats for the showtime
     * @param seatColumnAmount amount of columns of seats for the showtime
     */
    public Showtime(Days date, Times time, int seatRowAmount, int seatColumnAmount) {
        this.date = date;
        this.time = time;
        this.seats = new AbstractSeat[seatRowAmount][seatColumnAmount]; // create seat array with row- and column count
        createSeats(seatRowAmount, seatColumnAmount);
    }

    /**
     * create a seat for every position in the seat array
     * @param rowAmount the amount of rows of seats
     * @param columnAmount the amount of columns of seats
     */
    private void createSeats(int rowAmount, int columnAmount) { // TODO maybe make this more variable with a SeatCreator class
        for (int row = 0; row < rowAmount; row++) { // every row
            for (int column = 0; column < columnAmount; column++) { // checks every column of every row
                boolean vip = row == 0; // first row is vip
                boolean suv = row == rowAmount - 1; // last row is for suv
                AbstractSeat seat; // this variable will hold the seat

                // beach chair seats and car seats make up a checkerboard like pattern
                // beach chair seats get placed on on
                // -> an even row in an odd column
                // -> an odd row in an even column
                // -> not the last row
                boolean condition1 = column % 2 == 1 && row % 2 == 0;
                boolean condition2 = column % 2 == 0 && row % 2 == 1;
                if ((condition1 || condition2) && !suv) {
                    seat = new BeachChairSeat(vip);
                } else {
                    seat = new CarSeat(vip, suv);
                }
                seats[row][column] = seat; // insert seat at the position
            }
        }
    }

    /**
     * get the date and time for this showtime
     * @return date and time in one string
     */
    public String getDateAndTime() {
        if (date == null || time == null) {
            return null;
        }
        return date.getDay() + ", " + time.getTime();
    }

    /**
     * updates the availibility of this showtime,
     * if no seat is available the show is sold out
     */
    protected void updateAvailability() {
        System.out.println("DEBUG: " + "Showtime: updating availibility..."); // DEBUG
        if (isSoldOut) { // only check is the show is not sold out already
            return;
        }
        for (AbstractSeat[] column : seats) {
            for (AbstractSeat seat : column) { // check every seat
                if (!seat.isReserved) {
                    return; // unreserved seat found > return, because show is not sold out
                }
            }
        }
        isSoldOut = true; // no unreserved seat was found > show sold out
        System.out.println("DEBUG: " + "Showtime: the show is sold out"); // DEBUG
    }

    /**
     * get if the showtime is sold out
     * @return if sold out
     */
    public boolean isSoldOut() {
        return isSoldOut;
    }
}