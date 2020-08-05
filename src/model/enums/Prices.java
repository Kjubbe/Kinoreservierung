package model.enums;

/**
 * this enum defines the available prices, contains prices for seats and
 * caterings
 * 
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public enum Prices {

    NORMAL_CAR_SEAT(13.99), VIP_CAR_SEAT(16.49), NORMAL_BEACH_CHAIR_SEAT(9.99), VIP_BEACH_CHAIR_SEAT(12.49),
    SMALL_DRINK(2.99), MEDIUM_DRINK(4.49), LARGE_DRINK(5.49), SMALL_SNACK(3.49), MEDIUM_SNACK(4.99), LARGE_SNACK(6.49);

    // data field, contains price as double for calculation
    private final double price;

    /**
     * constructor, assigns data field
     * 
     * @param price price as a double
     */
    Prices(double price) {
        this.price = price;
    }

    /**
     * get the price for the enum
     * 
     * @return the price as a double for calculation
     */
    public double getPrice() {
        return price;
    }
}