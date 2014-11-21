import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

// PersonalInfoWindow.java
// creates a gui to display and modify personal information related to individual user
// Naeem Tai
// 4/20/2013

public class PersonalInfoWindow extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// declare components
	// general
	private Container pnlDisplay;
	private JTabbedPane tabs = new JTabbedPane();
	private JPanel welcomePanel = new JPanel(new GridLayout(4, 3));
	private JPanel dobPanel = new JPanel(new GridLayout(6, 4));
	private JPanel adrPanel = new JPanel(new GridLayout(9, 4));
	private JPanel contactPanel = new JPanel(new GridLayout(5, 4));
	// for menu
	private JMenuBar menubar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenu gotoMenu = new JMenu("Go To");
	private JMenuItem saveAllChanges = new JMenuItem("Save All Changes");
	private JMenuItem cancelAllChanges = new JMenuItem("Cancel All Changes");
	private JMenuItem logMeOff = new JMenuItem("Log Out");
	private JMenuItem lookUpUsers = new JMenuItem("Search for Users...");
	private JMenuItem removeMyAccount = new JMenuItem("Remove Account");
	private JMenuItem goWelcome = new JMenuItem("Home");
	private JMenuItem goDob = new JMenuItem("DOB");
	private JMenuItem goAdr = new JMenuItem("Address");
	private JMenuItem goContact = new JMenuItem("Contact Info");
	// for welcome
	private JButton toDobFromWelcome = new JButton("Next");
	// for dob
	private JComboBox monthSelect = new JComboBox();
	private JComboBox daySelect = new JComboBox();
	private JComboBox yearSelect = new JComboBox();
	private JRadioButton dobVisibleYes = new JRadioButton("Yes");
	private JRadioButton dobVisibleNo = new JRadioButton("No");
	private ButtonGroup dobVisibleGroup = new ButtonGroup();
	private JButton saveDob = new JButton("SAVE");
	private JButton cancelDob = new JButton("CANCEL");
	private JButton toWelcomeFromDob = new JButton("Home");
	private JButton toAdrFromDob = new JButton("Next");
	// for address
	private JTextField adrLine1Input = new JTextField(15);
	private JTextField adrLine2Input = new JTextField(15);
	private JTextField cityInput = new JTextField(10);
	private JTextField stateInput = new JTextField(2);
	private JTextField zipInput = new JTextField(5);
	private JRadioButton adrVisibleYes = new JRadioButton("Yes");
	private JRadioButton adrVisibleNo = new JRadioButton("No");
	private ButtonGroup adrVisibleGroup = new ButtonGroup();	
	private JButton saveAdr = new JButton("SAVE");
	private JButton cancelAdr = new JButton("CANCEL");
	private JButton toDobFromAdr = new JButton("Previous");
	private JButton toContactFromAdr = new JButton("Next");
	// for contact info
	private JTextField phoneInput = new JTextField(10);
	private JTextField emailInput = new JTextField(15);
	private JButton saveContact = new JButton("SAVE");
	private JButton cancelContact = new JButton("CANCEL");
	private JButton toAdrFromContact = new JButton("Previous");
	private JButton toWelcomeFromContact = new JButton("Home");
	// additional
	private JButton saveAll = new JButton("SAVE ALL");
	private JButton cancelAll = new JButton("CANCEL ALL");
	private JButton logoff = new JButton("LOG OUT");
	private JButton lookup = new JButton("SEARCH USERS");
	private JButton remove = new JButton("REMOVE ACCOUNT");
	// image icons
	private ImageIcon saveIcon = new ImageIcon("saveIcon.png");
	private ImageIcon logoffIcon = new ImageIcon("logoffIcon.png");
	private ImageIcon cancelIcon = new ImageIcon("cancelIcon.png");
	private ImageIcon lookupIcon = new ImageIcon("lookupIcon.png");
	private ImageIcon removeIcon = new ImageIcon("removeIcon.png");
	private ImageIcon homeIcon = new ImageIcon("homeIcon.png");
	private ImageIcon nextIcon = new ImageIcon("nextIcon.png");
	private ImageIcon previousIcon = new ImageIcon("previousIcon.png");
	
	// declare variables
	private User tmp;
	int[] months = new int[12];
	int[] dates = new int[31];
	int[] years = new int[100];
	String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", 
							"August", "September", "October", "November", "December"};
	
	// get local time, date, day of week
    Calendar localCalendar = Calendar.getInstance(TimeZone.getDefault());
    int currentMonth = localCalendar.get(Calendar.MONTH) + 1;
    int currentDayOfMonth = localCalendar.get(Calendar.DAY_OF_MONTH);
    int currentYear = localCalendar.get(Calendar.YEAR);
	
	// constructor
	public PersonalInfoWindow(User u){		
		super(u.getUsername());
		
		// make a copy of the user using index of the user using username
		int index = Data.usernames.indexOf(u.getUsername());
		tmp = Data.users.get(index);
		
		// set up menu
		setUpMenu();
		
		// make comboboxes
		makeComboBoxes();
		
		// group radio buttons
		// dob
		dobVisibleGroup.add(dobVisibleYes);
		dobVisibleGroup.add(dobVisibleNo);
		// address
		adrVisibleGroup.add(adrVisibleYes);
		adrVisibleGroup.add(adrVisibleNo);
		
		// set up display
		pnlDisplay = this.getContentPane();
		setUpPanels();
		tabs.add("Home", welcomePanel);
		tabs.add("Date of Birth", dobPanel);
		tabs.add("Address", adrPanel);
		tabs.add("Contact Info", contactPanel);
		
		// set button icons
		setButtonIcons();
		
		// set up events
		setUpEvents();
		
		// load user info
		loadUserInfo();		
		
		// set custom close operation
		// ask user's permission to close window without saving info
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent e) {
		    	confirmExit();
		    }
		});
		
		// handle window
		this.setDefaultCloseOperation(PersonalInfoWindow.DO_NOTHING_ON_CLOSE);
		/*this.add(welcomePanel);*/pnlDisplay.add(tabs, BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);		
	}// end constructor
	
	// confirm with user before exiting
	private void confirmExit(){
		int option = JOptionPane.showConfirmDialog(this, 
				"Are you sure you want to exit this window? \n " +
				"You Will be logged off and unsaved changes will be lost.",
			    "confirm exit", JOptionPane.YES_NO_OPTION);
		if (option == 0){
			new LoginWindow();
			this.setVisible(false);
		}
	}
	
	@Override
	// set actionPerformed methods for all buttons
	public void actionPerformed(ActionEvent e) {
		// handle save
		if (e.getSource() == saveAll || e.getSource() == saveAllChanges){
			// save new info of the user if user says yes
			int option = JOptionPane.showConfirmDialog(this, "Save all changes?",
					"confirm save", JOptionPane.YES_NO_OPTION);
			if (option == 0){
				// save user info
				saveUserInfo();
			}
		}
		// handle cancel all
		else if (e.getSource() == cancelAll || e.getSource() == cancelAllChanges){
			// restore original values for each input if user says yes
			int option = JOptionPane.showConfirmDialog(this, "Cancel all unsaved changes?",
					"confirm cancel", JOptionPane.YES_NO_OPTION);
			if (option == 0){
				// restore user info
				loadUserInfo();
			}
		}
		// handle logoff
		else if (e.getSource() == logoff || e.getSource() == logMeOff){
			// logoff without saving if user says yes
			int option = JOptionPane.showConfirmDialog(this, 
					"Are you sure you want to logoff? \n Unsaved changes will be lost.",
					"confirm logoff", JOptionPane.YES_NO_OPTION);
			if (option == 0){
				// switch to login window
				new LoginWindow();
				this.setVisible(false);
			}
		}
		// handle remove account
		else if (e.getSource() == remove || e.getSource() == removeMyAccount){
			// confirm users action to remove account and remove user from arraylists
			// remove user's account if user says yes
			int option = JOptionPane.showConfirmDialog(this, 
					"Are you sure you want to remove your account?" +
					" \n This action cannot be undone.",
					"confirm logoff", JOptionPane.YES_NO_OPTION);
			if (option == 0){
				// remove user from arraylists
				Data.usernames.remove(tmp.getUsername());
				Data.passwords.remove(tmp.getPassword());
				Data.users.remove(tmp);
				JOptionPane.showMessageDialog(this, "Your account has been removed.", "account removed", JOptionPane.PLAIN_MESSAGE);
				// switch to login window
				new LoginWindow();
				this.setVisible(false);
			}
		}
		// handle lookup user
		else if (e.getSource() == lookup || e.getSource() == lookUpUsers){
			// open new user look up window
			new UserLookUpWindow();
		}
		// handle save dob
		else if (e.getSource() == saveDob){
			// save dob
			saveDate();
		}
		// handle cancel dob
		else if (e.getSource() == cancelDob){
			// restore dob info
			loadDate();
		}
		// handle save address
		else if (e.getSource() == saveAdr){
			// save address
			saveAddress();
		}
		// handle cancel address
		else if (e.getSource() == cancelAdr){
			// restore address info
			loadAddress();
		}
		// handle save contact info
		else if (e.getSource() == saveContact){
			// save contact info
			saveContactInfo();
		}
		// handle cancel contact info
		else if (e.getSource() == cancelContact){
			// restore contact info
			loadContactInfo();
		}
		// handle tab switching
		// handle switching to welcome tab
		else if (e.getSource() == toWelcomeFromDob || e.getSource() == toWelcomeFromContact
				 || e.getSource() == goWelcome){
			// switch to welcome tab
			tabs.setSelectedIndex(0);
		}
		// handle switching to dob tab
		else if (e.getSource() == toDobFromWelcome || e.getSource() == toDobFromAdr ||
				 e.getSource() == goDob){
			// switch to dob tab
			tabs.setSelectedIndex(1);
		}
		// handle switching to address tab
		else if (e.getSource() == toAdrFromDob || e.getSource() == toAdrFromContact ||
				 e.getSource() == goAdr){
			// switch to adr tab
			tabs.setSelectedIndex(2);
		}
		// handle switching to contact info tab
		else if (e.getSource() == toContactFromAdr || e.getSource() == goContact){
			// switch to contact info tab
			tabs.setSelectedIndex(3);
		}
		// handle unexpected click
		else {
			System.out.println("Unexpected Click!");
		}
	}// end actionPerformaed
	
	// load saved information for the user
	private void loadUserInfo(){
		// load date
		loadDate();
		// load address
		loadAddress();
		// load contact info
		loadContactInfo();
	}
	
	// save changed information for the user
	private void saveUserInfo(){
		// save dob
		saveDate();
		// save adress
		saveAddress();
		// save contact info
		saveContactInfo();
	}
	
	// fill arrays for day, date, and month and add them to comboboxes
	private void makeComboBoxes(){
		// month
		for (int x = 0; x < months.length; x++){
			months[x] = x + 1;
			monthSelect.addItem(months[x]);
		}
		// day
		for (int x = 0; x < dates.length; x++){
			dates[x] = x + 1;
			daySelect.addItem(dates[x]);
		}
		// year
		for (int x = 0; x < years.length; x++){
			years[x] = currentYear - x;
			yearSelect.addItem(years[x]);
		}
	}
	
	// set up panels
	private void setUpPanels(){
		// welcome panel
		JPanel[][] welcomeHolder = new JPanel[4][3];
		for(int m = 0; m < 4; m++) {
			for(int n = 0; n < 3; n++) {
			  welcomeHolder[m][n] = new JPanel();
			  welcomePanel.add(welcomeHolder[m][n]);
			}
		}
		// current date
		welcomeHolder[0][0].add(new JLabel(currentMonth + "/" + currentDayOfMonth + "/" + currentYear));
		// username
		welcomeHolder[1][0].add(new JLabel("UserName: "));
		welcomeHolder[1][1].add(new JLabel(tmp.getUsername()));
		// name
		welcomeHolder[2][0].add(new JLabel("Name: "));
		welcomeHolder[2][1].add(new JLabel(tmp.getFirstName() + " " + tmp.getLastName()));
		// buttons
		welcomeHolder[3][0].add(saveAll);
		welcomeHolder[3][1].add(cancelAll);
		welcomeHolder[1][2].add(remove);
		welcomeHolder[0][2].add(logoff);
		welcomeHolder[2][2].add(lookup);
		welcomeHolder[3][2].add(toDobFromWelcome);
		
		// dob panel
		JPanel[][] dobHolder = new JPanel[5][3];
		for(int m = 0; m < 5; m++) {
			for(int n = 0; n < 3; n++) {
			  dobHolder[m][n] = new JPanel();
			  dobPanel.add(dobHolder[m][n]);
			}
		}
		dobHolder[0][0].add(new JLabel("DOB:"));
		dobHolder[1][0].add(new JLabel("Month"));
		dobHolder[1][1].add(new JLabel("Day"));
		dobHolder[1][2].add(new JLabel("Year"));
		dobHolder[2][0].add(monthSelect);
		dobHolder[2][1].add(daySelect);
		dobHolder[2][2].add(yearSelect);
		dobHolder[3][0].add(new JLabel("Visible?"));
		dobHolder[3][1].add(dobVisibleYes);
		dobHolder[3][2].add(dobVisibleNo);
		dobHolder[0][1].add(saveDob);
		dobHolder[0][2].add(cancelDob);
		dobHolder[4][0].add(toWelcomeFromDob);
		dobHolder[4][2].add(toAdrFromDob);
		
		// adr panel
		JPanel[][] adrHolder = new JPanel[8][3];
		for(int m = 0; m < 8; m++) {
			for(int n = 0; n < 3; n++) {
			  adrHolder[m][n] = new JPanel();
			  adrPanel.add(adrHolder[m][n]);
			}
		}
		adrHolder[0][0].add(new JLabel("ADDRESS:"));
		adrHolder[1][0].add(new JLabel("Line 1"));
		adrHolder[1][1].add(adrLine1Input);
		adrHolder[2][0].add(new JLabel("Line 2"));
		adrHolder[2][1].add(adrLine2Input);
		adrHolder[3][0].add(new JLabel("City"));
		adrHolder[3][1].add(cityInput);
		adrHolder[4][0].add(new JLabel("State"));
		adrHolder[4][1].add(stateInput);
		adrHolder[5][0].add(new JLabel("Zip"));
		adrHolder[5][1].add(zipInput);
		adrHolder[6][0].add(new JLabel("Visible?"));
		adrHolder[6][1].add(adrVisibleYes);
		adrHolder[6][2].add(adrVisibleNo);
		adrHolder[0][1].add(saveAdr);
		adrHolder[0][2].add(cancelAdr);
		adrHolder[7][0].add(toDobFromAdr);
		adrHolder[7][2].add(toContactFromAdr);
		
		// contact info panel
		JPanel[][] contactHolder = new JPanel[4][3];
		for(int m = 0; m < 4; m++) {
			for(int n = 0; n < 3; n++) {
			  contactHolder[m][n] = new JPanel();
			  contactPanel.add(contactHolder[m][n]);
			}
		}
		contactHolder[0][0].add(new JLabel("CONTACT INFO:"));
		contactHolder[1][0].add(new JLabel("Phone"));
		contactHolder[1][1].add(phoneInput);
		contactHolder[2][0].add(new JLabel("Email"));
		contactHolder[2][1].add(emailInput);
		contactHolder[0][1].add(saveContact);
		contactHolder[0][2].add(cancelContact);
		contactHolder[3][0].add(toAdrFromContact);
		contactHolder[3][2].add(toWelcomeFromContact);
	}// end set up panels
	
	// set up events
	private void setUpEvents(){
		// menu options
		saveAllChanges.addActionListener(this);
		cancelAllChanges.addActionListener(this);
		logMeOff.addActionListener(this);
		lookUpUsers.addActionListener(this);
		removeMyAccount.addActionListener(this);
		goWelcome.addActionListener(this);
		goDob.addActionListener(this);
		goAdr.addActionListener(this);
		goContact.addActionListener(this);
		// buttons
		toDobFromWelcome.addActionListener(this);
		saveDob.addActionListener(this);
		cancelDob.addActionListener(this);
		toWelcomeFromDob.addActionListener(this);
		toAdrFromDob.addActionListener(this);
		saveAdr.addActionListener(this);
		cancelAdr.addActionListener(this);
		toDobFromAdr.addActionListener(this);
		toContactFromAdr.addActionListener(this);
		saveContact.addActionListener(this);
		cancelContact.addActionListener(this);
		toAdrFromContact.addActionListener(this);
		toWelcomeFromContact.addActionListener(this);
		saveAll.addActionListener(this);
		cancelAll.addActionListener(this);
		logoff.addActionListener(this);
		lookup.addActionListener(this);
		remove.addActionListener(this);
	}// end set up events
	
	// save address
	private void saveAddress(){
		// get values from each input and store them for the user
		
		// make sure zip is valid
		boolean validZip = true;
		int z = 0;
		// if zip is not empty then check if it is integer
		if (!zipInput.getText().equals("")){
			try{
				// convert to integer
				z = Integer.parseInt(zipInput.getText());			
				// check 5 digit length of entered zip
				if (zipInput.getText().length() == 5){
					validZip = true;
				}
				else {
					JOptionPane.showMessageDialog(this, "Please enter five digit Zip.", "zip error", JOptionPane.ERROR_MESSAGE);
					validZip = false;
				}
			}
			catch (NumberFormatException nfe){
				JOptionPane.showMessageDialog(this, "Please enter five digit Zip.", "zip error", JOptionPane.ERROR_MESSAGE);
				validZip = false;
			}
			catch (Exception e){
				e.printStackTrace();
				validZip = false;
			}	
		}
		else{
			z = 0;
		}
		
		// if zip is valid then save address and other info
		if (validZip){
			// save address
			tmp.getAddress().setAdrLineOne(adrLine1Input.getText());
			tmp.getAddress().setAdrLineTwo(adrLine2Input.getText());
			tmp.getAddress().setCity(cityInput.getText());
			tmp.getAddress().setState(stateInput.getText());
			//tmp.getAddress().setZip(Integer.parseInt(zipInput.getText()));
			tmp.getAddress().setZip(z);
			tmp.getAddress().setVisible(adrVisibleYes.isSelected());
			
			JOptionPane.showMessageDialog(this, "Address successfully saved.", "success", JOptionPane.PLAIN_MESSAGE);
		}
	}

	// check for valid date and save
	private void saveDate(){
		// check for valid date
		boolean validDate = true;
		int i, month, day, year;
		i = monthSelect.getSelectedIndex();
		month = months[i];
		i = daySelect.getSelectedIndex();
		day = dates[i];
		i = yearSelect.getSelectedIndex();
		year = years[i];
		// check for invalid 31st
		if ((month == 4 || month == 6 || month == 9 || month == 11) && day == 31){
			JOptionPane.showMessageDialog(this, monthNames[month-1] + " does on have " + day + " days.\n" +
					"Please Choose a valid date.", "date error", JOptionPane.ERROR_MESSAGE);
			validDate = false;
		}
		// february and > 29th
		else if(month == 2 && day > 29){
			JOptionPane.showMessageDialog(this, monthNames[month-1] + " does on have a date " + day + " .\n" +
					"Please Choose a valid date.", "date error", JOptionPane.ERROR_MESSAGE);
			validDate = false;
		}
		// check for leap year (divisible by 4)
		else if (month == 2 && day == 29 && year % 4 != 0){
			JOptionPane.showMessageDialog(this, monthNames[month-1] + " did not have a date " + day +
					" in " + year + ".\n" +
					"Please Choose a valid date.", "date error", JOptionPane.ERROR_MESSAGE);
			validDate = false;
		}
		// check for future date
		else if ((year == currentYear && month > currentMonth) || (month == currentMonth && day > currentDayOfMonth)){
			JOptionPane.showMessageDialog(this, "It has not been " + month + "/" + day + "/" + year + 
					" yet.\n" +
					"Please Choose a valid date.", "date error", JOptionPane.ERROR_MESSAGE);
			validDate = false;
		}
		// save if date is valid
		if (validDate){
			// save date of birth
			int index;
			index = monthSelect.getSelectedIndex();
			tmp.getDob().setMonth(months[index]);
			index = daySelect.getSelectedIndex();
			tmp.getDob().setDay(dates[index]);
			index = yearSelect.getSelectedIndex();
			tmp.getDob().setYear(years[index]);
			tmp.getDob().setVisible(dobVisibleYes.isSelected());
			
			JOptionPane.showMessageDialog(this, "Date successfully saved.", "success", JOptionPane.PLAIN_MESSAGE);
		}
	}

	// save contact info
	private void saveContactInfo(){
		// save phone
		tmp.setPhone(phoneInput.getText());
		// save email
		tmp.setEmail(emailInput.getText());
		
		JOptionPane.showMessageDialog(this, "Contact Information successfully saved.", "success", JOptionPane.PLAIN_MESSAGE);
	}
	
	// load dob
	private void loadDate(){
		monthSelect.setSelectedIndex((tmp.getDob().getMonth()) - 1);
		daySelect.setSelectedIndex(tmp.getDob().getDay() - 1);
		yearSelect.setSelectedIndex(currentYear - ((tmp.getDob()).getYear()));
		dobVisibleYes.setSelected((tmp.getDob()).isVisible());
		dobVisibleNo.setSelected(!(tmp.getDob()).isVisible());
	}
	
	// load address
	private void loadAddress(){
		adrLine1Input.setText(tmp.getAddress().getAdrLineOne());
		adrLine2Input.setText(tmp.getAddress().getAdrLineTwo());
		cityInput.setText(tmp.getAddress().getCity());
		stateInput.setText(tmp.getAddress().getState());
		// check if zip is 0 (empty field)
		if (tmp.getAddress().getZip() == 0){
			zipInput.setText("");
		}
		else{
			zipInput.setText("" + tmp.getAddress().getZip());
		}
		adrVisibleYes.setSelected((tmp.getAddress()).isVisible());
		adrVisibleNo.setSelected(!(tmp.getAddress()).isVisible());
	}
	
	// load contact info
	private void loadContactInfo(){
		phoneInput.setText(tmp.getPhone());
		emailInput.setText(tmp.getEmail());
	}

	// set up menu system with key board shortcuts
	private void setUpMenu(){
		// file menu
		fileMenu.add(saveAllChanges);
		fileMenu.add(cancelAllChanges);
		fileMenu.add(logMeOff);
		fileMenu.add(lookUpUsers);
		fileMenu.add(removeMyAccount);
		// go to menu
		gotoMenu.add(goWelcome);
		gotoMenu.add(goDob);
		gotoMenu.add(goAdr);
		gotoMenu.add(goContact);
		
		menubar.add(fileMenu);
		menubar.add(gotoMenu);
		this.setJMenuBar(menubar);
		
		// add menu shortcuts
		// file menu
		saveAllChanges.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		cancelAllChanges.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		logMeOff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		lookUpUsers.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		removeMyAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		// go to menu
		goWelcome.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
		goDob.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		goAdr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		goContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
	}
	
	// set up button icons
	private void setButtonIcons(){
		saveAll.setIcon(saveIcon);
		cancelAll.setIcon(cancelIcon);
		logoff.setIcon(logoffIcon);
		lookup.setIcon(lookupIcon);
		remove.setIcon(removeIcon);
		saveDob.setIcon(saveIcon);
		saveAdr.setIcon(saveIcon);
		saveContact.setIcon(saveIcon);
		cancelDob.setIcon(cancelIcon);
		cancelAdr.setIcon(cancelIcon);
		cancelContact.setIcon(cancelIcon);
		toDobFromWelcome.setIcon(nextIcon);
		toAdrFromDob.setIcon(nextIcon);
		toContactFromAdr.setIcon(nextIcon);
		toWelcomeFromContact.setIcon(homeIcon);
		toWelcomeFromDob.setIcon(homeIcon);
		toDobFromAdr.setIcon(previousIcon);
		toAdrFromContact.setIcon(previousIcon);
	}
}// end class
