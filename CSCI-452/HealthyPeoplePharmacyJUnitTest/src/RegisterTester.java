import junit.framework.TestCase;

/*
 * RegisterTester.java
 * JUnit Test Case
 * Test the Register.java class with JUnit test framework.
 * Naeem Tai
 * 4/20/2014
 */

public class RegisterTester extends TestCase{

	// initialize variables
	Register r1 = new Register(1);
	Employee e1 = new Employee("First Last", 101);
	Item i1 = new Item(1231, 1.00);
	Item i2 = new Item(1232, 3.49);
	RentalItem ri1 = new RentalItem(2311, 5.99, 7);
	RentalItem ri2 = new RentalItem(2312, 7.25, 10);
	
	
	// test cases
	
	public void testOperator(){
		putOperatorOnRegister();
		assertEquals("Register r1's operator must be Employee e1.", e1, r1.getOperator());
	}
	
	public void testSubtotal(){
		putOperatorOnRegister();
		performSale();
		assertEquals("Subtotal of i1, i2, ri1, and ri2 should equal 17.73.", 17.73, r1.getCurrentSale().getSubtotal());
	}
	
	public void testTax(){
		putOperatorOnRegister();
		performSale();
		assertEquals("Tax for subtotal 17.73 should equal 1.24.", 1.24, r1.getCurrentSale().getTax());
	}

	public void testTotal(){
		putOperatorOnRegister();
		performSale();
		assertEquals("Total for subtotal 17.73 and tax 1.24 should equal 18.97.", 18.97, r1.getCurrentSale().getTotal());
	}
	
	public void testChangeDue(){
		putOperatorOnRegister();
		performSale();
		// make cash payment with 20
		r1.makePayment(); // enter 20 as cash tendered
		assertEquals("Change due for total of 18.97 and cash tendered of 20 should equal 1.03.", 1.03, r1.getCurrentSale().getChangeDue());
	}
	
	
	// private methods
	
	private void putOperatorOnRegister(){
		// assign operator to register
		r1.assignOperator(e1);
	}
	
	private void performSale(){
		r1.startNewTransaction();
		scanItemsOnRegister();
		r1.endTransaction();
	}
	
	private void scanItemsOnRegister(){
		r1.scanItem(i1);
		r1.scanItem(ri1);
		r1.scanItem(i2);
		r1.scanItem(ri2);
	}

}
