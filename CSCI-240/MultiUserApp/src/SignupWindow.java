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

// LoginWindow.java
// create login window GUI
// Naeem Tai
// 4/19/2013

public class SignupWindow extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declare components	
	private JTextField fNameInput = new JTextField(10);
	private JTextField lNameInput = new JTextField(10);
	private JTextField usernameInput = new JTextField(10);
	private JPasswordField passwordInput = new JPasswordField(10);
	private JPasswordField rePasswordInput = new JPasswordField(10);
	private JButton signup = new JButton("Sign Up");
	private JButton cancel = new JButton("Cancel");
	// image icons
	private ImageIcon signupIcon = new ImageIcon("signupIcon.png");
	private ImageIcon cancelIcon = new ImageIcon("cancelIcon.png");
	
	// declare variables
	private String fName;
	private String lName;
	private String username;
	private String password;
	private String rePassword;

	// constructor
	public SignupWindow(){
		super("New User Signup");
		
		// set up display
		int i = 6;
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
		panelHolder[0][0].add(new JLabel("First Name: "));
		panelHolder[0][1].add(fNameInput);
		panelHolder[1][0].add(new JLabel("Last Name: "));
		panelHolder[1][1].add(lNameInput);
		panelHolder[2][0].add(new JLabel("Username: "));
		panelHolder[2][1].add(usernameInput);
		panelHolder[3][0].add(new JLabel("Password: "));
		panelHolder[3][1].add(passwordInput);
		panelHolder[4][0].add(new JLabel("Confirm Password: "));
		panelHolder[4][1].add(rePasswordInput);
		panelHolder[5][0].add(signup);
		panelHolder[5][1].add(cancel);
		
		// set button icons
		signup.setIcon(signupIcon);
		cancel.setIcon(cancelIcon);
		
		// set up events
		signup.addActionListener(this);
		cancel.addActionListener(this);
		
		// set custom close operation
		// ask user's permission to cancel sign up
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	cancelSignUp();
		    }
		});
		
		// handle window
		this.setDefaultCloseOperation(SignupWindow.DO_NOTHING_ON_CLOSE);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setVisible(true);
	}// end constructor
	
	// get user to cancel sign up and switch to login window
	private void cancelSignUp(){
		// confirm with user to cancel
		int option = JOptionPane.showConfirmDialog(this, "Cancel signing up for a new account?",
				"confirm cancel", JOptionPane.YES_NO_OPTION);
		if (option == 0){
			// switch to login window
			new LoginWindow();
			this.setVisible(false);
		}
	}

	@Override
	// action performed method for buttons
	public void actionPerformed(ActionEvent e) {
		// handle sign up
		if (e.getSource() == signup){
			// check entries and sign up new user
			signupUser();
		}
		// handle cancel
		else if (e.getSource() == cancel){
			cancelSignUp();
		}
		// handle unexpected click
		else {
			System.out.println("Unexpected Click!");
		}
	}// end actionPerformed
	
	// check for valid information entries and sign up new user
	private void signupUser(){
		// get text from input fields
		fName = fNameInput.getText();
		lName = lNameInput.getText();
		username = usernameInput.getText();
		password = new String(passwordInput.getPassword());
		rePassword = new String(rePasswordInput.getPassword());
		
		// check for empty fields
		if (fName.equals("")){
			JOptionPane.showMessageDialog(this, "Please Input First Name.", "first name error", JOptionPane.ERROR_MESSAGE);
		}
		else if (lName.equals("")){
			JOptionPane.showMessageDialog(this, "Please Input Last Name.", "last name error", JOptionPane.ERROR_MESSAGE);
		}
		else if (username.equals("")){
			JOptionPane.showMessageDialog(this, "Please Input Username.", "username error", JOptionPane.ERROR_MESSAGE);
		}
		else if (password.equals("")){
			JOptionPane.showMessageDialog(this, "Please Input Password.", "password error", JOptionPane.ERROR_MESSAGE);
		}
		else if (rePassword.equals("")){
			JOptionPane.showMessageDialog(this, "Please Confirm Password.", "password confirmation error", JOptionPane.ERROR_MESSAGE);
		}
		// if all fields contain something check for valid username and match passwords
		else {
			// check if username already exists
			if (Data.usernames.indexOf(username) != -1){
				JOptionPane.showMessageDialog(this, "Username already exists.", "username error", JOptionPane.ERROR_MESSAGE);
			}
			// check if two passwords match
			else if (password.equals(rePassword)){
				JOptionPane.showMessageDialog(this, "Sign Up Successful.", "success", JOptionPane.PLAIN_MESSAGE);
				// create new user
				new User(fName, lName, username, password);
				// switch to login window
				new LoginWindow();
				this.setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(this, "Passwords do not match.", "password match error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}// end class
