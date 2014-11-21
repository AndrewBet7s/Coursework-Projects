import java.util.Scanner;

/*
 * Register.java
 * Source of user interaction; all output happens from here.
 * Naeem Tai
 * 4/20/2014
 */

public class Register {
	
	// attributes
	private int regNum;
	private Employee operator;
	private Sale currentSale;
	private Scanner input = new Scanner(System.in);
	private double amountTendered;
	
	// constructor
	public Register(int registerNum){
		this.regNum = registerNum;
	}
	
	// methods
	public void startNewTransaction(){
		this.currentSale = new Sale();
		this.printStartInfo();
	}
	public void endTransaction(){
		this.currentSale.endSale();
		this.printEndInfo();
	}
	public void scanItem(Item i){
		this.currentSale.addItem(i);
		this.printItemDetails(i);
		this.printSubTotal();
	}
	public void makePayment(){
		//this.amountTendered = 0; // need this line for while(){}, not for do{}while()
		do{
			System.out.print("Enter Cash Amount Tendered: ");
			this.amountTendered = input.nextDouble();
		}while (this.amountTendered < this.currentSale.getTotal());
		// round to two decimal places
		this.amountTendered = ((int)(this.amountTendered * 100 + 0.5)) / 100.0;
		this.currentSale.makeCashPayment(amountTendered);
		this.printPaymentDetails();
	}
	
	// output
	private void printStartInfo(){
		System.out.println("TRANSACTION DETAILS");
		System.out.println(this.currentSale.getTimeofSale());
		this.printRegisterInfo();
		System.out.println("ItemID\tPrice");
	}
	private void printItemDetails(Item i){
		System.out.print(i.getItemId() + "\t" + i.getItemPrice());
		if (i instanceof RentalItem){
			System.out.print("\tRented for " + ((RentalItem) i).getRentalPeriodInDays() + " days");
		}
		System.out.println();
	}
	private void printSubTotal(){
		System.out.println("Subtotal: " + this.currentSale.getSubtotal());
	}
	private void printRegisterInfo(){
		System.out.println("Register #: " + this.regNum);
		System.out.println("Operator: " + this.operator.getEmployeeName());
	}
	private void printEndInfo(){
		System.out.println("End of Sale: ");
		System.out.print("\t");
		System.out.println("Total Items: " + this.currentSale.getTotalItems());
		System.out.print("\t");
		this.printSubTotal();
		System.out.print("\t");
		System.out.println("Tax: " + this.currentSale.getTax());
		System.out.print("\t");
		System.out.println("Total: " + this.currentSale.getTotal());
	}
	private void printPaymentDetails(){
		System.out.println("End of Sale: ");
		System.out.println("\tCash Tender: " + this.currentSale.getCashTendered());
		System.out.print("\tChange Due: " + this.currentSale.getChangeDue());
	}
	
	// getters
	public Employee getOperator(){
		return this.operator;
	}
	public int getRegisterNumber(){
		return this.regNum;
	}
	public Sale getCurrentSale(){
		return this.currentSale;
	}
	
	// setters
	public void assignOperator(Employee emp){
		this.operator = emp;
	}
	
}
