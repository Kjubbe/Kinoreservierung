package model.enums;

/**
 * this enum defines the available prices
 * contains prices for seats and caterings
 * @author Kjell Treder
 * @author Marcel Sauer
 */

public enum Prices {
    NORMAL_CAR_SEAT,
    VIP_CAR_SEAT,
    NORMAL_BEACH_CHAIR_SEAT,
    VIP_BEACH_CHAIR_SEAT,
    SMALL_DRINK,
    MEDIUM_DRINK,
    LARGE_DRINK,
    SMALL_SNACK,
    MEDIUM_SNACK,
    LARGE_SNACK;

    /**
	 * converts the enum to a double
	 * @return price as a double for calculation
	 */
    public double getPrice() {
		switch (this) {
			case NORMAL_CAR_SEAT:
				return 13.99;
			case VIP_CAR_SEAT:
				return 16.49;
			case NORMAL_BEACH_CHAIR_SEAT:
				return 9.99;
			case VIP_BEACH_CHAIR_SEAT:
				return 12.49;
			case SMALL_DRINK:
				return 2.99;
			case MEDIUM_DRINK:
                return 4.49;
            case LARGE_DRINK:
                return 5.49;
            case SMALL_SNACK:
                return 3.49;
            case MEDIUM_SNACK:
                return 4.99;
            case LARGE_SNACK:
				return 6.49;
			default:
				return 15;
		}
	}
}