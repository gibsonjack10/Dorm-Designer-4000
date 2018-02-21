import java.io.File;
//import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class LoadButton {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;

	private PApplet processing;
	private float[] position;
	private String label;
	private File f;

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
				String[] finalLines = new String[4];
				while(scan.hasNextLine()) {
//					finalLines[i] = scan.nextLine().trim().split(":")[1].split(",")[i];
					//System.out.println(scan.nextLine());
					//finalLines = scan.nextLine();
				}
//				for(int i = 0; i < 6; i++) {
//					furniture[i] = new Furniture(label, i, i, i, processing);
//				}

			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("This file does not exist");
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
