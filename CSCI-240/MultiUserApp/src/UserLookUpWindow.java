import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

// UserLookUpWindow.java
// allows users to search for other users by username
// Naeem Tai
// 4/21/2013

public class UserLookUpWindow extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declare components
	/*private JTextField searchInput = new JTextField(10);
	private JButton search = new JButton("Search");*/
	private JComboBox usernameList = new JComboBox();
	private JTextArea info = new JTextArea(10, 20);
	
	// constructor
	public UserLookUpWindow(){
		super("Look Up Users");
		
		// make combobo
		makeComboBox();
		
		// set up display
		Container pnlDisplay = this.getContentPane();
		Panel options = new Panel();
		pnlDisplay.add(options, BorderLayout.CENTER);
		Panel pnlInfo = new Panel();
		pnlDisplay.add(pnlInfo, BorderLayout.SOUTH);
	    
	    // set up options panel
		options.add(new JLabel("Choose a username: "));
		options.add(usernameList);
	    
	    //set up information panel
    	pnlInfo.add(info);
    	// make the text area non-editable
    	info.setEditable(false);
		
		// set up events
		/*search.addActionListener(this);*/
    	usernameList.addActionListener(this);
		
		// handle window
		this.setDefaultCloseOperation(UserLookUpWindow.HIDE_ON_CLOSE);
	    this.pack();
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    this.setVisible(true);
	}// end constructor

	@Override
	// set actionPerformed method for search button
	public void actionPerformed(ActionEvent e) {
		// handle change in combobox
		if (e.getSource() == usernameList){
			// eliminate looking for user when no username is selected (first option is selected)
			if (usernameList.getSelectedIndex() == 0){
				info.setText("Please choose a username for info.");
			}
			else{
				info.setText(Data.users.get(usernameList.getSelectedIndex() - 1).printUser());
			}
		}
		// handle unexpected click
		else {
			System.out.println("Unexpected Click!");
		}	
	}// end actionPerformed
	
	// add usernames to the combobox
	private void makeComboBox(){
		usernameList.addItem("Choose One...");
		for (int i = 0; i < Data.usernames.size(); i++){
			usernameList.addItem(Data.usernames.get(i));
		}
	}

}