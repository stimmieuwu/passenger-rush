package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Passenger extends Sprite{

	public static final Image PASSENGER = new Image("../assets/sprites/passenger1.png", 50, 50, true, true);
	
	public Passenger(int xPos, int yPos, Image image) {
		super(xPos, yPos, image);
	}
	
	/**
	 * Renders the passenger's image on the canvas at the current position.
	 *
	 * @param gc The GraphicsContext used for drawing.
	 */
	public void render(GraphicsContext gc) {
		gc.save();
		gc.translate(this.getXPos() + spriteImage.getWidth() / 2, this.getYPos() + spriteImage.getHeight() / 2);

		gc.drawImage(spriteImage, -spriteImage.getWidth() / 2, -spriteImage.getHeight() / 2);
		gc.restore();
	}

}
