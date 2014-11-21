Multi User App
CSCI 24000 Final Project
Software Developer: Naeem Tai
Date Released: 4/23/2013


Technical Details:
	Programming Language: Java
	Concepts Used: GUI development using several types of widgets and layouts, 
			data storing with mostly non-readable text file, 
			reading and writing objects with files, 
			file input and output, 
			arraylist data structure for run time data management, 
			polymorphism, 
			public/private methods, 
			private/public static (global) variables, 
			multiple data types, 
			multiple classes, 
			inheritance, 
			encapsulation, 
			object serialization, 
			searching, 
			customized exits, 
			dialog pop up boxes,  
			etc.
	Number of classes: 8
	Number of GUIs: 4


Features:
	4 GUI windows with 1 GUI featuring multiple tabs, 
	button icons, 
	consistent design, 
	multiple user accounts, 
	individual information management, 
	sharing option, 
	login/logout system for information security, 
	no duplication of usernames, 
	new user sign up option, 
	remove an account option, 
	menu system with keyboard shortcuts, 
	multiple ways of performing each action, 
	one step data backup, 
	real time data management, 
	always ask for user confirmation to change data or to prevent data loss, 
	and more.


Main Idea:
	This application has information such as date of birth, address, and contact information; however it can be modified to store any other pieces of information by simply adding them to the project. Many of the project's classes can be kept untouched during this process. Thus this is a well-designed and organized project.


Program Details:
	When first starting the program, all the users are loaded from the text file and stored into an arraylist. All the data management during runtime is done through the arraylist. When the program terminates, the arraylist is written back to the file and file is overridden. Data is centralized for all users in one place for storing and for runtime data management.
	All the windows are centered on the screen for consistent location. All the windows are also not resizable to maintain design and look consistency. At any time there will only be one window shown on the screen, except when looking up other users' information because it is independent of the other window that is on the screen. To improve user-friendliness, the designs of all gui windows are consistent and each action can be performed in multiple ways.
	While using the program, users will often be asked to confirm their action in order to change their data or to prevent data loss. Users will also be notified when certain actions are performed, so that they would not be waiting to make sure that the action has been performed.
	The login window is the start and the end of using this application. It is the only way to start and terminate the program.


Program Flow:
	When the program starts, a login window is shown. If the user already has an account, the user can login using username and password. The program will check for the existence of the username and the right username and password match. In the event of any error, the user will be notified and asked to correct it.
	If the user does not already have an account, the user can sign up for an account by providing their first name and last name and choosing their username and password. The program will check if the username is already used. The program will also check if the password user provided and the password user confirmed match. In the event of any empty input field or mismatch, the user will be notified of an error and asked to correct it. The user can create an account or cancel the sign up process. When the user successfully creates a new account, the user will be notified of success and brought back to the login window. If the user cancels the sign up process, the user will be brought back to the login window. The user can login right away after creating a new account without restarting the program.
	After a successful login, a window titled with the user's username will be shown. This window will contain all the information related to the individual user. The information will be different for different users. Here, the user can navigate between different pieces of the information modify them. The user can modify the information and privacy, but the information required while signing up cannot be changed. After changing the information, the user can save each piece of information individually or all at once. Also after changing the information, the user can cancel all unsaved changes and and restore the original information. The user can also look up other users' information by opening another window and searching for other users by their username. After all, the user can log out. In the event of logging out or simply closing the window, the user will be asked to confirm the exit. The user can also choose to remove the account. In the event of removing an account, the user will be asked to confirm accout removal and brought back to the login window. The user can do all of this by using tabs, buttons, menu options, or key board shortcuts.
	If user selects to look up other users' information, a new window will be shown with a list of users from which the user can select a user to see the information of. The information other users the user can see depends on the privacy settings of the user whose information is being shown.
	After user exits the personal information window, a login window will be shown with the same functionality as the initial login window. The user can simply close the login window to terminate the program.