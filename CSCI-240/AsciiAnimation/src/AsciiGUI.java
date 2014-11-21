// AsciiGUI.java
// creates the GUI for the AsciiAnimation
// provides an ability to edit, open, save, and auto-play/stop animation
// includes shortcut keys for menu options
// Naeem Tai


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.Timer;


public class AsciiGUI extends JFrame implements ActionListener{

	//create the components
	private AsciiCanvas screen = new AsciiCanvas();
	private JButton play = new JButton("Play |>");
	private JButton stop = new JButton("Stop X");
	private JButton next = new JButton("Next >>>");
	private JButton prev = new JButton("<<< Previous");
	private JLabel counter = new JLabel("   Frame: 1 of 10   ");
	private JMenuBar menubar = new JMenuBar();
	private JMenu filemenu = new JMenu("File");
	private JMenu navmenu = new JMenu("Animation");
	private JMenuItem newAnim = new JMenuItem("New Animation");
	private JMenuItem open = new JMenuItem("Open Animation");
	private JMenuItem save = new JMenuItem("Save Animation");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenuItem advance = new JMenuItem("Next Frame");
	private JMenuItem backup = new JMenuItem("Previous Frame");
	private JMenuItem playAnim = new JMenuItem("Play Animation");
	private JMenuItem stopAnim = new JMenuItem("Stop Animation");
	private Timer timer = new Timer(5000, this);
	
	// constructor
	public AsciiGUI(){
		// set title of the window
		super("ASCII ANIMATION");
		
		// create a menu system
		
		// file menu
		filemenu.add(newAnim);
		filemenu.add(open);
		filemenu.add(save);
		filemenu.add(exit);
		// navigate menu
		navmenu.add(advance);
		navmenu.add(backup);
		navmenu.add(playAnim);
		navmenu.add(stopAnim);
		
		menubar.add(filemenu);
		menubar.add(navmenu);
		this.setJMenuBar(menubar);
		
		// add menu shortcuts
		// file menu
		newAnim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		// navigation menu
		advance.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		backup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
		playAnim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		stopAnim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
	    
		// set up display panel
		Container pnlDisplay = this.getContentPane();
		Panel pnlControls = new Panel();
	    pnlDisplay.setLayout(new BorderLayout());
	    pnlDisplay.add(screen);
	    pnlDisplay.add(pnlControls, BorderLayout.SOUTH);
	    
	    //set up control panel
    	pnlControls.setLayout(new FlowLayout());
    	pnlControls.add(play);
    	pnlControls.add(stop);
    	pnlControls.add(counter);
    	pnlControls.add(prev);
    	pnlControls.add(next);
    	
    	//set up events
    	play.addActionListener(this);
    	stop.addActionListener(this);
    	next.addActionListener(this);
    	prev.addActionListener(this);
    	newAnim.addActionListener(this);
    	open.addActionListener(this);
    	save.addActionListener(this);
    	exit.addActionListener(this);
    	advance.addActionListener(this);
    	backup.addActionListener(this);
    	playAnim.addActionListener(this);
    	stopAnim.addActionListener(this);
    	timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (screen.getFrameNumber() - 1 < 9){
                	// advance to next screen
                	screen.goNext();
                	// update counter label
            		counter.setText("   Frame " + screen.getFrameNumber() + " of 10   ");
                }
                else{
                	timer.stop();
                }
            }
         });

	    // handle window
    	this.setDefaultCloseOperation(AsciiGUI.EXIT_ON_CLOSE);
	    this.pack();
	    this.setLocation(100, 100);
	    this.setVisible(true);
	}
	
	// method override
	public void actionPerformed(ActionEvent e) {
		// handle next
		if (e.getSource() == next || e.getSource() == advance){
			screen.goNext();
		}
		// handle previous
		else if (e.getSource() == prev || e.getSource() == backup){
			screen.goPrevious();
		}
		// handle File -> New Animation
		else if (e.getSource() == newAnim){
			screen.newAnimation();
		}
		// handle File -> Load Animation
		else if (e.getSource() == open){
			screen.loadAnimation();
		}
		// handle File -> Save Animation
		else if (e.getSource() == save){
			screen.saveAnimation();
		}
		// handle File -> Exit
		else if (e.getSource() == exit){
			dispose();
			System.exit(0);
		}
		// handle play
		else if (e.getSource() == play || e.getSource() == playAnim){
			// start timer
			timer.start();
		}
		// handle stop
		else if (e.getSource() == stop || e.getSource() == stopAnim){
			// stop timer
			timer.stop();
		}
		// handle unexpected click
		else {
			System.out.print("Unexpected click!!!");
		}
		// update counter label
		counter.setText("   Frame " + screen.getFrameNumber() + " of 10   ");
	}
	
	// execute
	public static void main(String[] args){
		new AsciiGUI();
	}

}
