import java.io.Serializable;

// User.java
// contains data related to a user
// Naeem Tai
// 4/19/2013

public class User extends Data implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// declare variables
	private String fName;
	private String lName;
	private String username;
	private String password;
	private Date dob;
	private Address adr;
	private String phone;
	private String email;
	
	// constructor
	public User(String fName, String lName, 
			String username, String password){
		// set user data as given
		this.fName = fName;
		this.lName = lName;
		this.username = username;
		this.password = password;
		this.dob = new Date();
		this.adr = new Address();
		this.phone = "";
		this.email = "";
		
		// add new user data to arraylists
		Data.users.add(this);
		Data.usernames.add(this.username);
		Data.passwords.add(this.password);
	}// end constructor
	
	// return user to be printed
	public String printUser(){
		return "NAME : " + this.fName + " " + this.lName + "\n" 
				+ "DOB : " + this.dob.printDate() + "\n"
				+ "ADDRESS : " + this.adr.printAddress() + "\n"
				+ "PHONE : " + this.phone + "\n" + "EMAIL : " + this.email + "\n";
	}
	
	
	// getters

	// get username of user
	public String getUsername(){
		return this.username;
	}
	
	// get password of user
	public String getPassword(){
		return this.password;
	}
	
	// get first name of user
	public String getFirstName(){
		return this.fName;
	}
	
	// get last name of user
	public String getLastName(){
		return this.lName;
	}
	
	// get date of birth of user
	public Date getDob(){
		return this.dob;
	}
	
	// get address of user
	public Address getAddress(){
		return this.adr;
	}
	
	// get phone of user
	public String getPhone(){
		return this.phone;
	}
		
	// get email of user
	public String getEmail(){
		return this.email;
	}
	
	
	// setters
	
	// set phone of the user
	public void setPhone(String p){
		this.phone = p;
	}
	
	// set email of the user
	public void setEmail(String e){
		this.email = e;
	}

}// end class
