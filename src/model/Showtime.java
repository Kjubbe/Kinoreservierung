package model;

import model.enums.*;

/**
 * contains data for a showtime and seating
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Showtime { // TODO maybe add not only weekdays but dates aswell? (overkill!)
    
    // Data fields
    private Dates date;
    private Times time;
    private boolean isSoldOut = false; // showtime is not sold out by default

    public final Seat[][] seats; // contains all available seats for this showtime
    
    /**
     * constructor, assigns data fields
     * builds rows and columns of seats
     * @param date date for the showtime
     * @param time time for the showtime
     * @param seatRowAmount amount of rows of seats for the showtime
     * @param seatColumnAmount amount of columns of seats for the showtime
     */
    public Showtime(Dates date, Times time, int seatRowAmount, int seatColumnAmount) {
        this.date = date;
        this.time = time;
        seats = new Seat[seatRowAmount][seatColumnAmount]; // create seat array with row- and column count

        for (int row = 0; row < seatRowAmount; row++) { // every row
            for (int column = 0; column < seatColumnAmount; column++) { // checks every column of every row
                boolean vip = false; // seat is non-vip by default
                boolean suv = false; // seat is non-suv by default
                Seat seat; // variable holds seat

                if (row == 0)
                    vip = true; // first row is vip
                else if (row == seatRowAmount - 1)
                    suv = true; // last row is for suv

                // beach chair seats and car seats make up a checkerboard like pattern
                // beach chair seats get placed on on
                // -> an even row in an odd column
                // -> an odd row in an even column
                // -> not the last row
                if (((column % 2 == 1 && row % 2 == 0) || (column % 2 == 0 && row % 2 == 1)) && row != seatRowAmount - 1)
                    seat = new BeachChairSeat(vip);
                else
                    seat = new CarSeat(vip, suv);

                seats[row][column] = seat; // insert seat at the position
            }
        }
    }

    /**
     * toString
     * @return date and time in one string
     */
    @Override
    public String toString() {
        return date.toString() + ", " + time.getTime();
    }

    /**
     * updates the availibility of this showtime
     * if no seat is available the show is sold out
     */
    protected void updateAvailability() {
        if (!isSoldOut) { // only check is the show is not sold out already
            for (Seat[] column : seats) {
                for (Seat s : column) { // check every seat
                    if (!s.isReserved)
                        return; // unreserved seat found > return, because show is not sold out
                }
            }
            isSoldOut = true; // no unreserved seat was found > show sold out
        }
    }

    /**
     * get if the showtime is sold out
     * @return if sold out
     */
    public boolean isSoldOut() {
        return isSoldOut;
    }

}