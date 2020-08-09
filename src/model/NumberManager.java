package model;

import java.util.List;
import java.util.Random;

/**
 * creates random numbers for ticket and order numbers
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public final class NumberManager {

    // data fields
    private static final int MIN = 10_000_000; // inclusive min value for the ticket number
    private static final int MAX = 100_000_000; // exclusive max value for the ticket number

    private static final Random RNG = new Random(); // create a random number generator

    /**
     * private constructor to restrict access, throws exception because this class is
     * not meant to be instantiated
     * 
     * @throws IllegalStateException when instantiating this class
     */
    private NumberManager() throws IllegalStateException {
        throw new IllegalStateException("This Utility class can not be instantiated");
    }

    /**
     * create and return a ticket number with the default range
     * 
     * @return the generated ticket number
     */
    protected static int generateTicketNumber() {
        return generateTicketNumber(MIN, MAX);
    }

    /**
     * generate and return a ticket number
     * 
     * @param min the minimum of the number range, included
     * @param max the maximum of the number range, excluded
     * @return the generated ticket number
     */
    protected static int generateTicketNumber(int min, int max) {
        System.out.println("DEBUG: number manager: generating unique ticket number"); // DEBUG
        int ticketNumber = nextFor(Database.getAllTicketNumbers(), min, max);
        Database.addTicketNumber(ticketNumber);
        return ticketNumber;
    }

    /**
     * generate and return a order number with default range
     * 
     * @return the generated order number
     */
    protected static int generateOrderNumber() {
        return generateOrderNumber(MIN, MAX);
    }

    /**
     * generate and return an order number
     * 
     * @param min the minimum of the number range, included
     * @param max the maximum of the number range, excluded
     * @return the generated order number
     */
    protected static int generateOrderNumber(int min, int max) {
        System.out.println("DEBUG: number manager: generating unique order number"); // DEBUG
        int orderNumber = nextFor(Database.getAllOrderNumbers(), min, max);
        Database.addOrderNumber(orderNumber);
        return orderNumber;
    }

    /**
     * generate a unique, random number in the range for the specified list
     * 
     * @param list the list for the number
     * @param min  the minimum of the number range, included
     * @param max  the maximum of the number range, excluded
     * @return the randomly generated number
     */
    protected static int nextFor(List<Integer> list, int min, int max) {
        System.out.println("DEBUG: number manager: generating number in range " + min + " - " + max); // DEBUG
        boolean duplicate;
        int number; // holds the ticket number
        int iterations = 0;
        int bound = max - min;
        do {
            // get a random number in the specified range
            if (iterations >= 1000) { // too many iterations
                throw new StackOverflowError("No unique, random number can be generated");
            }
            // if bound is not positive use the default bound
            number = min + (bound > 0 ? RNG.nextInt(bound) : RNG.nextInt(MAX - MIN));
            duplicate = false; // assume, that it is not a duplicate
            for (Integer num : list) { // check every ticket
                if (num == number) { // check for duplication
                    System.out.println("DEBUG: number manager: duplicate found: " + num); // DEBUG
                    System.out.println("DEBUG: number manager: iteration count: " + iterations); // DEBUG
                    duplicate = true; // duplication found
                    iterations++;
                    break;
                }
            }
        } while (duplicate); // repeat until there is no duplication
        return number;
    }
}