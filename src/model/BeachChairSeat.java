package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.enums.*;

/**
 * special type of seat, inherites from the Seat class
 * holds a ticket number
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public class BeachChairSeat extends Seat {

    // Data fields
    private static final String name = Vocabulary.BEACH_CHAIR_TOOLTIP;
    private static List<Integer> tickets = new ArrayList<>();
    private String ticket; // holds ticket number

    /**
     * constructor, calls super constructor
     * @param isVip if the seat is a vip seat
     */
    public BeachChairSeat(boolean isVip) {
        // price is determined by the isVip boolean
        super(isVip ? Prices.VIP_BEACH_CHAIR_SEAT : Prices.NORMAL_BEACH_CHAIR_SEAT, isVip, name);
    }

    /**
     * sets the ticket
     */
    public void assignTicket() {
        int min = 10000000;
        int max = 100000000;
        
        Random rng = new Random();
        boolean duplicate;
        int ticketNr;
        do {
            ticketNr = min + (int)(rng.nextDouble() * (max - min));
            duplicate = false;
            for (Integer i : tickets) {
                if (i == ticketNr) {
                    System.out.println("Duplicate found: " + i + " " + ticketNr); // DEBUG
                    duplicate = true;
                    break;
                }
            }
        } while (duplicate);

        ticket = String.valueOf(ticketNr);
        tickets.add(ticketNr);

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