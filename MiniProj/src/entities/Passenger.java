package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The `Passenger` class represents a passenger in the Passenger Rush game. The
 * players need to transport the passengers to the Junction Unloading Area in
 * order to score.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Passenger extends Sprite {

	/** Default image used for passengers. */
	public static final Image PASSENGER = new Image("./../assets/sprites/passenger1.png", 50, 50, true, true);

	/**
	 * Constructs a `Passenger` object.
	 *
	 * @param xPos  The initial x-coordinate of the passenger.
	 * @param yPos  The initial y-coordinate of the passenger.
	 * @param image The image to be used for rendering the passenger.
	 */
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
