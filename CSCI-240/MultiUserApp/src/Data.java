import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// Data.java
// contains data for all users
// Naeem Tai
// 4/19/2013

public class Data{
	
	// declare an arraylist to store all users
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<String> usernames = new ArrayList<String>();
	public static ArrayList<String> passwords = new ArrayList<String>();
	
	// constructor
	public Data(){
		
	}// end constructor
	
	// load users from file and store them in arraylist
	public void loadUsers(){
		// clear arraylist and re-populate
		users.clear();
		usernames.clear();
		passwords.clear();
		
		try {
			// create file input mechanism
			File file = new File("usersData.txt");
			FileInputStream fIn = new FileInputStream(file);
			ObjectInputStream obIn = new ObjectInputStream(fIn);
			// read users from the file while they exist
			while (true){
				// read object as User
				User temp = (User)obIn.readObject();
				// add data of user to arraylists
				users.add(temp);
				usernames.add(temp.getUsername());
				passwords.add(temp.getPassword());
			}
		}
		// end of file
		catch (EOFException e){
		}
		// any other exception
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	// write users to the file
	public void writeUsers(){
		try {
			// create file output mechanism
			FileOutputStream fo = new FileOutputStream("usersData.txt", false);
			ObjectOutputStream obOut = new ObjectOutputStream(fo);
			// write all users to file
			for (int i = 0; i < users.size(); i++){
				obOut.writeObject(users.get(i));
			}
			// close file
			obOut.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}// end class
