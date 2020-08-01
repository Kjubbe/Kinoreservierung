package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class NumberManager {

    private static final List<Integer> ALL_TICKET_NUMBERS = new ArrayList<>(); // this list holds all tickets for every seat
    private static final List<Integer> ALL_ORDER_NUMBERS = new ArrayList<>(); // this list holds all tickets for every seat

    protected static final int MIN = 10_000_000; // inclusive min value for the ticket number
    protected static final int MAX = 100_000_000; // exclusive max value for the ticket number

    private static final Random RNG = new Random(); // create a random number generator

    /**
     * private constructor to restrict access
     * throws exception because this class is not meant to be instantiated
     * @throws IllegalStateException when instantiating this class
     */
    private NumberManager() throws IllegalStateException {
        throw new IllegalStateException("Utility class");
    }

    /**
     * create and return a ticket number with default range
     * @return the generated ticket number
     */
    protected static int createTicketNumber() {
        return createTicketNumber(MIN, MAX);
    }

    /**
     * create and return a ticket number
     * @param min the minimum of the number range
     * @param max the maximum of the number range
     * @return the generated ticket number
     */
    protected static int createTicketNumber(int min, int max) {
        System.out.println("DEBUG: Seat: all tickets: " + ALL_TICKET_NUMBERS); // DEBUG
        return nextFor(ALL_TICKET_NUMBERS, min, max);
    }

    /**
     * create and return a ticket number with default range
     * @return the generated ticket number
     */
    protected static int createOrderNumber() {
        return createOrderNumber(MIN, MAX);
    }

    /**
     * create and return an order number
     * @param min the minimum of the number range
     * @param max the maximum of the number range
     * @return the generated order number
     */
    protected static int createOrderNumber(int min, int max) {
        return nextFor(ALL_ORDER_NUMBERS, min, max);
    }
    
    /**
     * generate a random number in the range for the specified list
     * @param list the list for the number
     * @param min the minimum of the number range
     * @param max the maximum of the number range
     * @return the randomly generated number
     */
    private static int nextFor(List<Integer> list, int min, int max) {
        boolean duplicate;
        int number; // holds the ticket number
        do {
            // get a random number in the specified range
            number = min + RNG.nextInt(max - min);
            duplicate = false; // assume, that it is not a duplicate
            for (Integer t : list) { // check every ticket
                if (t == number) { // check for duplication
                    System.out.println("DEBUG: rng: duplicate found: " + t); // DEBUG
                    duplicate = true; // duplication found
                    break;
                }
            }
        } while (duplicate); // randomly generate ticket numbers until there is no duplication
        return number;
    }
}