import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

/**
 * This class saves the room design that has been created in the java window
 * to a file RoomData.ddd. In the case that the array cannot be saved, 
 * it will print a message detailing the error that is occurring.
 * 
 * @author John Gibson and Bennett Majerowski
 *
 */
public class SaveButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	private PApplet processing;
	private float[] position;
	private String label;
	private File f;
	
	/**
	 * Constructor that creates the Save Button
	 * @param x is the horizontal location of the button
	 * @param y is the vertical location of the button
	 * @param processing uses information from the jar file
	 */
	public SaveButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = new String("Save Room");
	}
	
	/**
	 * Changes the color of the button based on whether or not the mouse is 
	 * hovering over it and labels the button.
	 */
	public void update() {
		if (isMouseOver() == true) {
			processing.fill(100);
		} else {
			processing.fill(200);
		}
		processing.rect(position[0] - WIDTH/2, position[1] + HEIGHT/2, position[0] + 
				WIDTH/2, position[1] - HEIGHT/2);
		
		processing.fill(0);
		processing.text(label, position[0], position[1]);
	}
	
	/**
	 * this method is called when the mouse clicks the save button. It saves the bed design
	 * on the screen to an external file. Values that it stores are x-position, y-position,
	 * furniture type, and number of rotations. It stores these values in the RoomData.ddd
	 * file in a way that can be parsed and read in by the Load Button.
	 * @param furniture array used to put in the data about individual pieces of
	 * furniture
	 * @throws FileNotFoundException if the file isn't found and can't be loaded
	 */
	public void mouseDown(Furniture furniture[]) throws FileNotFoundException {
		if (isMouseOver()) {
			try {
				f = new File("RoomData.ddd");
				PrintWriter pw = new PrintWriter(f);
				for (int i = 0; i < furniture.length; i++) {
					if (furniture[i] == null) {
						pw.println();
					} else {
						pw.println(furniture[i].getType() + ":" + furniture[i].getXPosition() + "," +
								 furniture[i].getYPosition() + "," + furniture[i].getRotation());
					}
				}	
				pw.close();
			} catch (Exception e) {
				System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");
			}
		}
	}
	
	/**
	 * checks if the mouse is over the load button
	 * @return true if its over the button, false otherwise
	 */
	public boolean isMouseOver() {
		if (processing.mouseX > position[0] - WIDTH/2 && 
				processing.mouseX < position[0] + WIDTH/2 &&
				processing.mouseY > position[1] - HEIGHT/2 &&
				processing.mouseY < position[1] + HEIGHT/2) {
			return true;
		}
		return false;	
	}
	
	
}
