package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.enums.*;

/**
 * special type of seat, inherites from the Seat class,
 * contains a ticket number and list of all tickets
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class BeachChairSeat extends Seat {

    // Data fields
    private static final String NAME = Vocabulary.BEACH_CHAIR_TOOLTIP;
    private static List<Integer> tickets = new ArrayList<>(); // this list holds all tickets for every seat
    private String ticket; // holds the ticket "number" for this seat

    private Random rng = new Random();

    /**
     * constructor, calls super constructor
     * @param isVip if the seat is a vip seat
     */
    public BeachChairSeat(boolean isVip) {
        // price is determined by the isVip boolean
        super(isVip ? Prices.VIP_BEACH_CHAIR_SEAT : Prices.NORMAL_BEACH_CHAIR_SEAT, isVip, NAME);
    }

    /**
     * sets the ticket for this seat,
     * gets a random number between the local min value (included) and the local max value (excluded),
     * checks if the ticket number is unique before assigning it
     */
    protected void assignTicket() {
        int min = 10000000; // min value for the ticket number
        int max = 100000000; // max value for the ticket number
        
        boolean isDuplicate;
        int ticketNr; // holds the ticket number
        do {
            System.out.println("DEBUG: " + "Seat: generating ticket..."); // DEBUG
            ticketNr = min + rng.nextInt(max - min); // get a random number in the specified range
            isDuplicate = false; // assume, that it is not a duplicate
            for (Integer t : tickets) { // check every ticket
                if (t == ticketNr) { // check for uplication
                    System.out.println("DEBUG: " + "Seat: duplicate found: " + t); // DEBUG
                    isDuplicate = true; // duplication found
                    break;
                }
            }
        } while (isDuplicate); // randomly generate ticket numbers until there is no duplication

        // build the string
        String ticketString = String.valueOf(ticketNr); // get the number as a string
        int length = ticketString.length(); // get the length of the string
        ticket = ticketString.substring(0, length / 2) + "-" + ticketString.substring(length / 2, ticketString.length()); // assign the ticketString with a dash in the middle
        tickets.add(ticketNr); // add the ticket to the list of all tickets

        System.out.println("DEBUG: " + "Seat: ticket assigned: " + ticket); // DEBUG
        System.out.println("DEBUG: " + "Seat: all tickets: " + tickets); // DEBUG
    }

    /**
     * get the ticket for this seat
     * @return ticket as String
     */
    protected String getTicket() {
        return ticket;
    }
}