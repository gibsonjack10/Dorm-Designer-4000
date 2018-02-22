import java.io.File;
import java.io.PrintWriter;
//import java.util.Scanner;
import java.io.FileNotFoundException;

public class SaveButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	 
	private PApplet processing;
	private float[] position;
	private String label;
	private File f;
	//private File myOutFile;
	
	public SaveButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = new String("Save Room");
	}
	
	
	
	
	
	
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
	
	public void mouseDown(Furniture furniture[]) throws FileNotFoundException{
		if (isMouseOver()) {
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
		}
	}
	
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
