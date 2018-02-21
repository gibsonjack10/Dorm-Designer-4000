import java.io.FileNotFoundException;

public class Main {

	private PApplet processing;
	private PImage backgroundImage;
	private Furniture[] furniture;
	private CreateBedButton bedButton;
	private CreateSofaButton sofaButton;
	private SaveButton saveButton;
	private LoadButton loadButton;

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

	public static void main(String[] args) {
		Utility.startApplication();

	}


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
		if (saveButton.isMouseOver()) {
			saveButton.mouseDown(this.furniture);
		}
		if (loadButton.isMouseOver()) {
			loadButton.mouseDown(this.furniture);
		}
		
	}
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