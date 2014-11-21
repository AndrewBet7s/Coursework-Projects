import java.util.ArrayList;
import java.util.Date;

/*
 * Sale.java
 * Hold information about a sale (when, what, how much)
 * Naeem Tai
 * 4/20/2014
 */

public class Sale {
	
	// attributes
	private static final double percentTax = 7; // one constant tax rate for all sales
	private ArrayList<Item> items = new ArrayList<Item>();
	private double subtotal, tax, total, cashTendered, changeDue;
	private boolean isComplete;
	private Date timeOfSale;
	private int totalItems;
	
	// constructor
	public Sale(){
		// set time of sale as current time
		this.timeOfSale = new Date();
		// initialize sale attributes
		this.subtotal = 0;
		this.tax = 0;
		this.total = 0;
		this.isComplete = false;
		this.totalItems = 0;
	}
	
	// methods
	// add item to sale
	public void addItem(Item i){
		this.items.add(i);
		this.totalItems++;
		this.subtotal += i.getItemPrice();
	}
	// calculate tax
	private void calculateTax(){
		this.tax = this.subtotal * percentTax / 100;
		// round to two decimal places
		this.tax = ((int)(this.tax * 100 + 0.5)) / 100.0;
	}
	// calculate total
	private void calculateTotal(){
		this.total = this.subtotal + this.tax;
	}
	public void makeCashPayment(double amountTendered){
		this.cashTendered = amountTendered;
		this.changeDue = this.cashTendered - this.total;
		// round to two decimal places
		this.changeDue = ((int)(this.changeDue * 100 + 0.5)) / 100.0;
	}
	public void endSale(){
		this.isComplete = true;
		this.calculateTax();
		this.calculateTotal();
	}
	// temporary method for testing
	public void printItems(){
		System.out.println("Sale:");
		for (int i = 0; i < this.items.size(); i++){
			System.out.println(this.items.get(i).getItemId());
		}
	}
	
	// getters
	public String getTimeofSale(){
		return this.timeOfSale.toString();
	}
	public int getTotalItems(){
		return this.totalItems;
	}
	public double getSubtotal(){
		return this.subtotal;
	}
	public double getTax(){
		return this.tax;
	}
	public double getTotal(){
		return this.total;
	}
	public double getCashTendered(){
		return this.cashTendered;
	}
	public double getChangeDue(){
		return this.changeDue;
	}
	public boolean getStatus(){
		return this.isComplete;
	}
	
}
