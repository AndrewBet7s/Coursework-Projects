// LoginWindow.java
// create login window GUI
// Naeem Tai
// 4/18/2013


import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginWindow extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declare components
	private JTextField usernameInput = new JTextField(10);
	private JPasswordField passwordInput = new JPasswordField(10);
	private JButton login = new JButton("Login");
	private JButton signup = new JButton("New User Sign Up");
	// image icons
	private ImageIcon loginIcon = new ImageIcon("loginIcon.png");
	private ImageIcon newUserIcon = new ImageIcon("newUserIcon.png");
	
	// declare variables
	private String username;
	private String password;

	// constructor
	public LoginWindow(){
		super("Login");
		
		// set up display
		int i = 3;
		int j = 2;
		Container pnlDisplay = this.getContentPane();
		pnlDisplay.setLayout(new GridLayout(i,j));
		JPanel[][] panelHolder = new JPanel[i][j];

		for(int m = 0; m < i; m++) {
		   for(int n = 0; n < j; n++) {
		      panelHolder[m][n] = new JPanel();
		      pnlDisplay.add(panelHolder[m][n]);
		   }
		}
		
		// add components to display
		panelHolder[0][0].add(new JLabel("Username: "));
		panelHolder[0][1].add(usernameInput);
		panelHolder[1][0].add(new JLabel("Password: "));
		panelHolder[1][1].add(passwordInput);
		panelHolder[2][0].add(login);
		panelHolder[2][1].add(signup);
		
		// set button icons
		login.setIcon(loginIcon);
		signup.setIcon(newUserIcon);
		
		// set up events
		login.addActionListener(this);
		signup.addActionListener(this);
		
		// set custom close operation
		// write users to file before closing the window and terminating program
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		        Data a = new Data();
		        a.writeUsers();
		        dispose();
		        System.exit(0);
		    }
		});
		
		// handle window
		this.setDefaultCloseOperation(LoginWindow.DO_NOTHING_ON_CLOSE);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setVisible(true);
	}// end constructor
	
	@Override
	// action performed method for buttons
	public void actionPerformed(ActionEvent e) {
		// handle login
		if (e.getSource() == login){
			// check for valid login
			checkLogin();
		}
		// handle sign up
		else if (e.getSource() == signup){
			// switch to sign up window
			new SignupWindow();
			this.setVisible(false);
		}
		// handle unexpected click
		else {
			System.out.println("Unexpected Click!");
		}		
	}// end actionPerformed
	
	// check for valid login
	private void checkLogin(){
		// get text from username and password input fields
		username = usernameInput.getText();
		password = new String(passwordInput.getPassword());
		
		// get indexes of username and password from arraylists
		int usernameIndex = Data.usernames.indexOf(username);
		int passwordIndex = Data.passwords.indexOf(password);
		
		// if username exists check for match
		if (usernameIndex != -1){
			// username and password are correct, grant access
			if (usernameIndex == passwordIndex){
				// open personal info window for the user
				new PersonalInfoWindow(Data.users.get(usernameIndex));
				this.setVisible(false);
			}
			// password does not match the username, deny access
			else {
				JOptionPane.showMessageDialog(this, "Invalid Password.", "password error", JOptionPane.ERROR_MESSAGE);
			}
		}
		// username does not exist, deny access
		else {
			JOptionPane.showMessageDialog(this, "Invalid Username.", "username error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// main method to execute
	public static void main (String[] args){
		Data a = new Data();
		a.loadUsers();
		new LoginWindow();
	}// end main

}// end class
