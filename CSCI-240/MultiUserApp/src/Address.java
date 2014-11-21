import java.io.Serializable;

// Address.java
// contains information related to address
// Naeem Tai
// 4/20/2013

public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declare variables
	private String adrLineOne;
	private String adrLineTwo;
	private String city;
	private String state;
	private int zip;
	private boolean visible;
	
	// constructor with no parameters
	public Address(){
		this.adrLineOne = "";
		this.adrLineTwo = "";
		this.city = "";
		this.state = "";
		this.zip = 0;
		this.visible = false;
	}// end constructor
	
	// constructor with parameters
	public Address(String al1, String al2, String c, String s, int z, boolean v){
		this.adrLineOne = al1;
		this.adrLineTwo = al2;
		this.city = c;
		this.state = s;
		this.zip = z;
		this.visible = v;
	}// end constructor
	
	// return address to be printed
	public String printAddress(){
		String address = "";
		// check for empty spaces for display consistency
		if (this.visible){
			if (!this.adrLineOne.equals(""))
				address += "\n     " + this.adrLineOne + "\n";
			if (!this.adrLineTwo.equals(""))
				address += "     " + this.adrLineTwo + "\n";
			if (!this.city.equals(""))
				address += "     " + this.city + "\n";
			if (!this.state.equals(""))
				address += "     " + this.state + "\n";
			if (this.zip != 0)
				address += "     " + this.zip;
			return address;
		}
		else{
			return "Hidden";
		}
	}
	
	
	// getters
	
	// get line one of address
	public String getAdrLineOne(){
		return this.adrLineOne;
	}
	
	// get line two of address
	public String getAdrLineTwo(){
		return this.adrLineTwo;
	}
	
	// get city of address
	public String getCity(){
		return this.city;
	}
	
	// get state of address
	public String getState(){
		return this.state;
	}
	
	// get zip of address
	public int getZip(){
		return this.zip;
	}
	
	// get if address is visible or not
	public boolean isVisible(){
		return this.visible;
	}
	

	// setters
	
	// set line one of address
	public void setAdrLineOne(String al1){
		this.adrLineOne = al1;
	}
	
	// set line two of address
	public void setAdrLineTwo(String al2){
		this.adrLineTwo = al2;
	}
	
	// set city of address
	public void setCity(String c){
		this.city = c;
	}
	
	// set state of address
	public void setState(String s){
		this.state = s;
	}
	
	// set zip of address
	public void setZip(int z){
		this.zip = z;
	}
	
	// set if address is visible or not
	public void setVisible(boolean v){
		this.visible = v;
	}

}// end class
