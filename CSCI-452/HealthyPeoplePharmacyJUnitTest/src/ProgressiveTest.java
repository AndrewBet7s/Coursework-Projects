/*
 * ProgressiveTest.java
 * Progressively test the project.
 * Naeem Tai
 * 3/29/2014
 */

public class ProgressiveTest {

	public static void main(String[] args) {
		
		// declare variables
		Register r1;
		Employee e1;
		Item i1, i2;
		RentalItem ri1, ri2;
		
		
		// initialize variables
		r1 = new Register(1);
		e1 = new Employee("First Last", 101);
		i1 = new Item(1231, 1.00);
		i2 = new Item(1232, 3.49);
		ri1 = new RentalItem(2311, 5.99, 7);
		ri2 = new RentalItem(2312, 7.25, 10);
		
		// operate register
		r1.assignOperator(e1);
		r1.startNewTransaction();
		r1.scanItem(i1);
		r1.scanItem(ri1);
		r1.scanItem(i2);
		r1.scanItem(ri2);
		r1.endTransaction();
		r1.makePayment();
		
	}
}
