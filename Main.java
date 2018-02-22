///////////////////////////////////////////////////////////////////////////////
//                  
// Title:            (Dorm Designer 4000)
// Files:            (CreateSofaButton.java, Furniture.java, SaveButton.java,
//                    LoadButton.java, CreateBedButton.java, 
//                    CreateSofaButton.java, background.png, bed.png, sofa.png)
// Semester:         (CS 300) Spring 2018
//
// Author:           (John Gibson)
// Email:            (jdgibson@wisc.edu)
// Lecturer's Name:  (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     (Bennett Majerowski)
// Email:            (majerowski@wisc.edu)
// Lecturer's Name:  (Gary Dahl)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//
// Persons:          no other people
//
// Online sources:   "https://stackoverflow.com/questions/11437442/restarting-
//                     the-while-loop" courtesy of Eng.Fouad. This page helped
//                     us learn how to go back to the beginning of a while loop
//                     without finishing the code in the loop
//
//////////////////////////// 80 columns wide ////////////////////////////////// 

import java.io.FileNotFoundException;

/**
 * (This class opens a window in which the user can design
 *  a dorm room layout. They can create, rotate and delete
 *  beds and sofas.)
 *
 * <p>Bugs: (no known bugs)
 *
 * @author (Bennett Majerowski and John Gibson)
 */
public class Main {
	
	/**
	 * (Fields of Main)
	 */
	private PApplet processing;
	private PImage backgroundImage;
	private Furniture[] furniture;
	private CreateBedButton bedButton;
	private CreateSofaButton sofaButton;
	private SaveButton saveButton;
	private LoadButton loadButton;
	
	/**
	 * Constructor that creates the Load Button
	 * @param processing uses information from the jar file
	 */
	public Main(PApplet processing) {
		this.processing = processing;
		backgroundImage = processing.loadImage("images/background.png");
		bedButton = new CreateBedButton(50, 24, processing);
		sofaButton = new CreateSofaButton(150, 24, processing);
		saveButton = new SaveButton(650, 24, processing);
		loadButton = new LoadButton(750, 24, processing);
		furniture = new Furniture[6];
		for (int i=0; i<6; i++) {
			furniture[i] = null;
		}
	}

	/**
	 * (Main starts the application and runs it from the .jar file)
	 */
	public static void main(String[] args) {
		Utility.startApplication();
	}

	/**
	 * (Update runs repeatedly and checks for user input. It calls the other classes to
	 * save those changes.)
	 */
	public void update() {
		processing.background(100,150,250);
		processing.image(backgroundImage, processing.width/2, processing.height/2);
		bedButton.update();
		sofaButton.update();
		saveButton.update();
		loadButton.update();
		for (int i=0; i<6; i++) {
			if (furniture[i] != null) {
				furniture[i].update();
			}
		}
	}		

	/**
	 * (Called when the mouse is pressed down and checks to see if the mouse 
	 * has clicked on an object. If it does it moves the center of the 
	 * bed or sofa to the mouses position or creates a bed or sofa if the mouse
	 * has clicked on a create button.
	 * @throws FileNotFoundException if the file required is not found in directory)
	 */	
	public void mouseDown() throws FileNotFoundException {
		for (int i=0; i<6; i++) {
			if (furniture[i] == null) {
				break;
			}
			else if(furniture[i].isMouseOver()) {
				furniture[i].mouseDown();
				break;
			}

		}
		for (int i=0; i<6; i++) {
			if (furniture[i] == null && bedButton.isMouseOver()) {
				bedButton.mouseDown();
				furniture[i] = new Furniture("bed", processing);
				break;
			}
		}
		for (int i=0; i<6; i++) {
			if (furniture[i] == null && sofaButton.isMouseOver()) {
				sofaButton.mouseDown();
				furniture[i] = new Furniture("sofa", processing);
				break;
			}
		}
		if(saveButton.isMouseOver()) {
			saveButton.mouseDown(this.furniture);
		}
		if(loadButton.isMouseOver()) {
			loadButton.mouseDown(this.furniture);
		}
	}
	
	/**
	 * (Called after the mouse button is released and it further calls
	 * the the mouseUp method for the desired furniture object to make
	 * the bed stay where it is release.)
	 */
	public void mouseUp() {
		for (int i=0; i<6; i++) {
			if (furniture[i] == null) {
				break;
			}
			else {
				furniture[i].mouseUp();
			}
		}
	}

	/**
	 * (Checks any keys pressed by the user and, if it is one of 'd' or 'r',
	 * calls the furniture class to delete or rotate that furniture object)
	 */
	public void keyPressed() {
		if(processing.key == 'D' || processing.key == 'd') {
			for (int i=0; i<6; i++) {
				if(furniture[i] != null) {
					if(furniture[i].isMouseOver()) {
						furniture[i] = null;
						break;
					}
				}
			}
		}
		else if(processing.key == 'R' || processing.key == 'r') {
			for (int i=0; i<6; i++) {
				if (furniture[i] != null) {
					if (furniture[i].isMouseOver()) {
						furniture[i].rotate();
						break;
					}
				}
			}
		}
	}
}
