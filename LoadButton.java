import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class LoadButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	private PApplet processing;
	private float[] position;
	private String label;
	private File f;
	private File g;

	public LoadButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = new String("Load Room");
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

	public Furniture mouseDown(Furniture furniture[]) throws FileNotFoundException {
		try {
			if (isMouseOver()) {
				f = new File("RoomData.ddd");
				Scanner scan = new Scanner(f);
				String finalLines = new String();
				int i = 0;
				for (int j=0; j<6; j++)
					furniture[j] = null;
				while(scan.hasNextLine()) {
					if (i == 6) {
						System.out.println("WARNING: Unable to load more furniture.");
						break;
					}
					finalLines = scan.nextLine();
					String trimLine = finalLines.trim();
					String furnType = trimLine.split(":")[0];
					g = new File(furnType + ".png");
					System.out.println(g);
					if (g.exists()) {
						System.out.println("WARNING: Could not find an image for a furniture object of type: " + furnType);
						finalLines = scan.nextLine();
					}
					String numbers = trimLine.split(":")[1];
					String xPos = numbers.split(",")[0];
					String yPos = numbers.split(",")[1];
					String rotations = numbers.split(",")[2];
					furniture[i] = new Furniture(furnType, Float.parseFloat(xPos), Float.parseFloat(yPos), Integer.parseInt(rotations), processing);
					i++;
				}
				scan.close();
			} else {
				return null;
			}
		} catch (Exception e) {
			
		}
		return null;
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
