/*
 * Item.java
 * Hold general item information
 * Naeem Tai
 * 4/20/2014
 */

public class Item {
	
	// attributes
	private int id;
	private double price;

	// constructor
	public Item(int id, double price){
		this.id = id;
		this.price = price;
	}
	
	// getters
	public double getItemPrice(){
		return this.price;
	}
	public int getItemId(){
		return this.id;
	}

}
