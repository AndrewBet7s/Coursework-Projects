/*
 * RentalItem.java 
 * Type of item, hold rental item information
 * Naeem Tai
 * 4/20/2014
 */

public class RentalItem extends Item {
	
	// additional attributes other than item
	private int rentalPeriodInDays;
	
	// constructor
	public RentalItem(int id, double price, int rentalPeriodInDays) {
		super(id, price);
		this.rentalPeriodInDays = rentalPeriodInDays;
	}
	
	// getters
	public int getRentalPeriodInDays(){
		return this.rentalPeriodInDays;
	}

}
