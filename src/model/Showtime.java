package model;

import model.enums.*;

/**
 * contains data for a showtime and seating
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class Showtime {
    
    // Data fields
    protected Date date;
    protected Time time;

    protected Seat[][] seats; // contains all available seats for this showtime
    
    /**
     * constructor, assigns data fields
     * builds rows and columns of seats
     * @param date date for the showtime
     * @param time time for the showtime
     * @param seatRowCount amount of rows of seats for the showtime
     * @param seatColumnCount amount of columns of seats for the showtime
     */
    public Showtime(Date date, Time time, int seatRowCount, int seatColumnCount) {
        this.date = date;
        this.time = time;
        seats = new Seat[seatRowCount][seatColumnCount]; // create seat array with row- and column count

        for (int row = 0; row < seatRowCount; row++) { // every row
            for (int column = 0; column < seatColumnCount; column++) { // checks every column of every row
                boolean vip = false; // seat is non-vip by default
                boolean suv = false; // seat is non-suv by default
                Seat seat; // variable holds seat

                if (row == 0) vip = true; // first row is vip
                else if (row == seatRowCount - 1) suv = true; // last row is for suv

                // beach chair seats and car seats make up a checkerboard like pattern
                // beach chair seats get placed on on
                // -> an even row in an odd column
                // -> an odd row in an even column
                if ((column % 2 == 1 && row % 2 == 0) || (column % 2 == 0 && row % 2 == 1)) seat = new BeachChairSeat(vip);
                else seat = new CarSeat(vip, suv);

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
        return date + ", " + time.getTime();
    }

}