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

    protected Seat[][] seats;
    
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
        seats = new Seat[seatRowCount][seatColumnCount];

        for (int row = 0; row < seatRowCount; row++) {
            for (int column = 0; column < seatColumnCount; column++) {
                boolean vip = false;
                boolean suv = false;
                Seat seat;
                if (row == 0) vip = true; // row == 0 -> first row
                else if (row == seatRowCount - 1) suv = true; // last row

                if ((column % 2 == 1 && row % 2 == 0) || (column % 2 == 0 && row % 2 == 1)) seat = new BeachChairSeat(vip);
                else seat = new CarSeat(vip, suv);
                seats[row][column] = seat; // Strandkorb an die Position einfügen
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