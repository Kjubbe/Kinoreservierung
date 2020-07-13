package model;

import model.enums.*;

public class Showtime {
    
    protected Date date;
    protected Time time;

    protected Seat[][] seats;
    
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

}