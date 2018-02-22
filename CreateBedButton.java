/**
 * (This class creates a button to make beds.)
 *
 * <p>Bugs: (no known bugs)
 *
 * @author (Bennett Majerowski and John Gibson)
 */
public class CreateBedButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	private PApplet processing;
	private float[] position;
	private String label;
	
	/**
	 * Constructor that creates the bed button
	 * @param x is the horizontal location of the button
	 * @param y is the vertical location of the button
	 * @param processing uses information from the jar file
	 */
	public CreateBedButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = new String("Create Bed");
	}
	
	/**
	 * (update prints the button on the java window that can be clicked
	 * to create new bed objects.)
	 */
	public void update() {
		if (isMouseOver() == true) {
			processing.fill(100);
		} else {
			processing.fill(200);
		}
		processing.rect(position[0] - WIDTH/2, position[1] + HEIGHT/2, position[0] + WIDTH/2, position[1] - HEIGHT/2);
		
		processing.fill(0);
		processing.text(label, position[0], position[1]);
	}
	
	/**
	 * (mouseDown identifies the type "bed" if the mouse is clicked while above the 
	 * Create sofa button.)
	 */
	public Furniture mouseDown() { 
		if (isMouseOver()) {
			Furniture bed = new Furniture("bed", processing);
			return bed;
		}
		else {
			return null;
		}
	}
	
	/**
	 * (isMouseOver is a helper method that identifies when the mouse is 
	 * over the Create Bed button.)
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
