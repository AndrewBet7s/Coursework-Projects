/*
 * Employee.java
 * Hold employee information.
 * Naeem Tai
 * 4/20/2014
 */

public class Employee {
	
	// attributes
	private String name;
	private int id;
	
	// constructor
	public Employee(String employeeName, int employeeId){
		this.name = employeeName;
		this.id = employeeId;
	}
	
	// getters
	public String getEmployeeName(){
		return this.name;
	}
	public int getEmployeeId(){
		return this.id;
	}
}
