package model;

import model.enums.Vocab;
import model.enums.Prices;

/**
 * special type of seat, inherites from the Seat class, contains a ticket number
 * and list of all tickets
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class BeachChairSeat extends AbstractSeat {

    // data field
    private String ticket; // holds the ticket "number" for this seat

    /**
     * constructor, calls super constructor
     * 
     * @param isVip if the seat is a vip seat
     */
    public BeachChairSeat(boolean isVip) {
        // price is determined by the isVip boolean
        super(isVip ? Prices.VIP_BEACH_CHAIR_SEAT : Prices.NORMAL_BEACH_CHAIR_SEAT, isVip,
                Vocab.BEACH_CHAIR_TOOLTIP.toString());
    }

    /**
     * invoked from model when placing the order, assigns a ticket and reserves a
     * seat
     */
    @Override
    protected void reserve() {
        if (!isReserved) {
            assignTicket();
            isReserved = true;
            System.out.println("DEBUG: beach-chair-seat: reserved: " + this); // DEBUG
        }
    }

    /**
     * sets the ticket for this seat, gets a random number between the local min
     * value (included) and the local max value (excluded), checks if the ticket
     * number is unique before assigning it
     */
    private void assignTicket() {
        // generate and assign the number as a string
        int ticketNumber = NumberManager.generateTicketNumber();
        String ticketString = String.valueOf(ticketNumber);

        int length = ticketString.length(); // get the length of the string

        // assign the ticketString with a dash in the middle
        String part1 = ticketString.substring(0, length / 2);
        String part2 = ticketString.substring(length / 2, ticketString.length());
        ticket = part1 + "-" + part2;

        System.out.println("DEBUG: beach-chair-seat: ticket assigned: " + ticket); // DEBUG
    }

    /**
     * get the ticket for this seat
     * 
     * @return ticket as String
     */
    protected String getTicket() {
        return ticket;
    }
}