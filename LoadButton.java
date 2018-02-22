import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * This class Loads a previously saved arrangement of furniture into the
 * dorm room.  It checks for any errors in what type of furniture is put
 * in, that the correct file is loaded and that the style is correct.
 * 
 * @author John Gibson and Bennett Majerowski
 *
 */
public class LoadButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	private PApplet processing;
	private float[] position;
	private String label;
	private File f;
	private File g;
	
/**
 * Constructor that creates the Load Button
 * @param x is the horizontal location of the button
 * @param y is the vertical location of the button
 * @param processing uses information from the jar file
 */
	public LoadButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = new String("Load Room");
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
 * this method is called when the mouse clicks on the load button and loads a
 * previous state of the room that was saved
 * @param furniture array used to put in the data about individual pieces of
 * furniture
 * @return returns the furniture arrays to the Furniture class so they can be
 * constructed into pieces of furniture in our room
 * @throws FileNotFoundException if the file isn't found and can't be loaded
 */
	public Furniture mouseDown(Furniture furniture[]) throws FileNotFoundException {
		try { // looks to make sure we are loading the correct file
			if (isMouseOver()) {
				f = new File("RoomData.ddd");
				Scanner scan = new Scanner(f);
				String finalLines = new String();
				int i = 0;
				int lineCounter = 0;
				for (int j=0; j<6; j++)
					furniture[j] = null;
				while(scan.hasNextLine()) {
					lineCounter++;
					if (i > 5) {
						System.out.println("WARNING: Unable to load more furniture.");
						break;
					}
					finalLines = scan.nextLine();
					while (finalLines.isEmpty()) {
						finalLines = scan.nextLine();
						lineCounter++;
					}
					
					try { // checks for correct formatting then puts data into Furniture class
						String trimLine = finalLines.trim();
						String furnType = trimLine.split(":")[0].trim();
						g = new File("images/" + furnType + ".png");
						String numbers = trimLine.split(":")[1];
						String xPos = numbers.split(",")[0];
						String yPos = numbers.split(",")[1];
						String rotations = numbers.split(",")[2];
						if (!g.isFile()) { 
							System.out.println("WARNING: Could not find an image for"
									+ "a furniture object of type: " + furnType.trim());
							continue;
						}
						
						furniture[i] = new Furniture(furnType.trim(), 
								Float.parseFloat(xPos.trim()), Float.parseFloat(yPos.trim()), 
								Integer.parseInt(rotations.trim()), processing);
						i++;
					} catch (Exception e) {
						System.out.println("WARNING: Found incorrectly formatted line in file: "
								+ lineCounter);
					}
				}
				scan.close();
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("WARNING: Could not load room contents from file RoomData.ddd.");
		}
		return null;
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
