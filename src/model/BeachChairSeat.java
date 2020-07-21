package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.enums.*;

/**
 * special type of seat, inherites from the Seat class
 * contains a ticket number and list of all tickets
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class BeachChairSeat extends Seat {

    // Data fields
    private static final String name = Vocabulary.BEACH_CHAIR_TOOLTIP;
    private static List<Integer> tickets = new ArrayList<>(); // this list holds all tickets for every seat
    private String ticket; // holds the ticket "number" for this seat

    /**
     * constructor, calls super constructor
     * @param isVip if the seat is a vip seat
     */
    public BeachChairSeat(boolean isVip) {
        // price is determined by the isVip boolean
        super(isVip ? Prices.VIP_BEACH_CHAIR_SEAT : Prices.NORMAL_BEACH_CHAIR_SEAT, isVip, name);
    }

    /**
     * sets the ticket for this seat
     * gets a random number between the local min value (included) and the local max value (excluded)
     * checks if the ticket number is unique before assigning it
     */
    public void assignTicket() {
        int min = 10000000; // min value for the ticket number
        int max = 100000000; // max value for the ticket number
        
        Random rng = new Random();
        boolean isDuplicate;
        int ticketNr; // holds the ticket number
        do {
            ticketNr = min + (int)(rng.nextDouble() * (max - min)); // get a random number in the specified range
            isDuplicate = false; // assume, that it is not a duplicate
            for (Integer t : tickets) { // check every ticket
                if (t == ticketNr) { // check for uplication
                    System.out.println("Duplicate found: " + t + " " + ticketNr); // DEBUG
                    isDuplicate = true; // duplication found
                    break;
                }
            }
        } while (isDuplicate); // randomly generate ticket numbers until there is no duplication

        ticket = String.valueOf(ticketNr); // assign the ticket
        tickets.add(ticketNr); // add the ticket to the list of all tickets

        System.out.println("Ticket assigned: " + ticket); // DEBUG
        System.out.println("Alle tickets: " + tickets); // DEBUG
    }

    /**
     * get the ticket for this seat
     * @return ticket as String
     */
    public String getTicket() {
        return ticket;
    }
}