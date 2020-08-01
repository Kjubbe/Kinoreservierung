package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class NumberManager {

    private static final List<Integer> ALL_TICKET_NUMBERS = new ArrayList<>(); // this list holds all tickets for every seat
    private static final List<Integer> ALL_ORDER_NUMBERS = new ArrayList<>(); // this list holds all tickets for every seat

    private static final int MIN = 10_000_000; // inclusive min value for the ticket number
    private static final int MAX = 100_000_000; // exclusive max value for the ticket number

    private static Random rng = new Random(); // create a random number generator

    private NumberManager() {
        throw new IllegalArgumentException("Utility class");
    }

    protected static int createTicketNumber() {
        System.out.println("DEBUG: Seat: all tickets: " + ALL_TICKET_NUMBERS); // DEBUG
        return nextFor(ALL_TICKET_NUMBERS);
    }

    protected static int createOrderNumber() {
        return nextFor(ALL_ORDER_NUMBERS);
    }
    
    private static int nextFor(List<Integer> list) {
        boolean isDuplicate;
        int number; // holds the ticket number
        do {
            // get a random number in the specified range
            number = MIN + rng.nextInt(MAX - MIN);
            isDuplicate = false; // assume, that it is not a duplicate
            for (Integer t : list) { // check every ticket
                if (t == number) { // check for uplication
                    System.out.println("DEBUG: Seat: duplicate found: " + t); // DEBUG
                    isDuplicate = true; // duplication found
                    break;
                }
            }
        } while (isDuplicate); // randomly generate ticket numbers until there is no duplication
        return number;
    }
}