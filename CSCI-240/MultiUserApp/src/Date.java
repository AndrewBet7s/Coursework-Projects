import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

// Date.java
// contains information related to date
// Naeem Tai
// 4/20/2013

public class Date implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declare variables
	private int month;
	private int day;
	private int year;
	private boolean visible;
	
	// get current month, day, and year
	Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
    int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
    int currentDayOfMonth = localCalendar.get(Calendar.DAY_OF_MONTH);
    int currentYear = localCalendar.get(Calendar.YEAR);
	
	// constructor with no parameters
	public Date(){
		this.month = currentMonth;
		this.day = currentDayOfMonth;
		this.year = currentYear;
		this.visible = false;
	}// end constructor
		
	// constructor with parameters
	public Date(int m, int d, int y, boolean v){
		this.month = m;
		this.day = d;
		this.year = y;
		this.visible = v;
	}// end constructor
	
	// return date to be printed
	public String printDate(){
		if (this.visible){
			return this.month + "/" + this.day + "/" + this.year;
		}
		else{
			return "Hidden";
		}
	}
	
	// getters
	
	// get month of the date
	public int getMonth(){
		return this.month;
	}
	
	// get day of the date
	public int getDay(){
		return this.day;
	}
	
	// get year of the date
	public int getYear(){
		return this.year;
	}
	
	// get if date is visible or not
	public boolean isVisible(){
		return this.visible;
	}
	
	
	// setters
	
	// set month of the date
	public void setMonth(int m){
		this.month = m;
	}
	
	// set day of the date
	public void setDay(int d){
		this.day = d;
	}
	
	// set year of the date
	public void setYear(int y){
		this.year = y;
	}
	
	// set visibility of the date
	public void setVisible(boolean v){
		this.visible = v;
	}
	
}// end class
