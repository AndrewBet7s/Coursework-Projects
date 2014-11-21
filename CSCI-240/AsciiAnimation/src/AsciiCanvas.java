// AsciiCanvas.java
// provides a component to create and display animation
// controls everything related to the component
// Naeem Tai


import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;


public class AsciiCanvas extends JTextArea{
	
	// variable declarations
	private String[] animation = new String[] {"","","","","","","","","",""};
	private int index = 0;
	private String separator = "This is a Frame Seprator.";
	private JFileChooser filechooser = new JFileChooser();
	
	// constructor
	public AsciiCanvas(){
		// create a JTextArea of 15 columns and 50 rows with mono-space font
		super(15, 50);
		super.setFont(new Font( "Monospaced", Font.PLAIN, 16));
	}
	
	// advance animation by one frame
	public void goNext(){
		this.setFrame();
		if (this.index < this.animation.length - 1){
			this.index++;
		}
		this.updateScreen();
	}
	
	// backup animation by one frame
	public void goPrevious(){
		this.setFrame();
		if (this.index > 0){
			this.index--;
		}
		this.updateScreen();
	}
	
	// register current frame to array
	private void setFrame(){
		this.animation[index] = this.getText();
	}
	
	// put correct frame on the screen
	private void updateScreen(){
		this.setText(this.animation[index]);
	}
	
	// return current frame number
	public int getFrameNumber(){
		return this.index + 1;
	}
	
	// save animation
	public void saveAnimation(){
		// register current frame to array
		this.setFrame();
		
		int returnVal = filechooser.showSaveDialog(this);
		
		// if user wants to save, get the file and save
		if (returnVal == JFileChooser.APPROVE_OPTION){
			// get the file to save to
			File file = filechooser.getSelectedFile();
			
			// save file as a text file
			// write each frame of animation to the file
			try{
				// get file path
				String path = file.getPath();
				// add .txt if it is not there
				if (!path.contains(".txt")){
					path = path + ".txt";
				}
				
				// create mechanism for writing to the file
				FileWriter fstream = new FileWriter(path);
				BufferedWriter output = new BufferedWriter(fstream);
				
				// write animation to the file
				for (int i = 0; i < this.animation.length; i++) {
                	output.write(this.animation[i]);
                	output.newLine();
                	output.write(separator);
                	output.newLine();
                }
                
                // close the file.
                output.close();
                
			} catch (Exception e){
            	e.printStackTrace();
            }
		}
	}
	
	// load animation
	public void loadAnimation(){
		int returnVal = filechooser.showOpenDialog(this);
		 
		// if the user wants to open a file, load animation from selected file
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	
        	// get selected the file.
        	File file = filechooser.getSelectedFile();
        	
        	// read file and load each frame of animation
			try {
				// create mechanism for file input
				Scanner scanner = new Scanner(file);
				
				// clear current animation
				this.clearAnimation();
	        	int frame = 0;
	        	String line;
	        	
	        	// read each line of the file
	        	// change frame when there is a separator
	        	while (scanner.hasNextLine() && frame < this.animation.length) {
	        	    line = scanner.nextLine();
	        	    if (line.contains(separator)) {
	        	    	frame++;
	        	    }
	        	    else {
	        	    	this.animation[frame] += line + "\n";
	        	    }
	        	}
	        	
	        	// close the scanner and update screen
	        	scanner.close();
	        	this.index = 0;
	        	this.updateScreen();
			} 
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
        }
	}
	
	// clear animation
	private void clearAnimation(){
		for (int i = 0; i < this.animation.length; i++){
			this.animation[i] = "";
		}
	}
	
	// set screen for new animation
	public void newAnimation(){
		this.clearAnimation();
		this.index = 0;
		this.updateScreen();
	}
	
}
