package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represents a generic sprite object in the game. It provides basic
 * functionality for position, image rendering, and collision detection (to-do).
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Sprite {
	/** The image representing the sprite. */
	protected Image spriteImage;
	/** The x-coordinate of the sprite's position. */
	private double xPos;
	/** The y-coordinate of the sprite's position. */
	protected double yPos;
	/**
	 * An indicator whether the sprite is currently colliding with another object.
	 */
	public boolean isColliding;

	/**
	 * Constructs a Sprite object with a passed x/y position and image.
	 *
	 * @param xPos  The initial x-coordinate of the sprite.
	 * @param yPos  The initial y-coordinate of the sprite.
	 * @param image The image to use for the sprite.
	 */
	public Sprite(int xPos, int yPos, Image image) {
		this.setXPos(xPos);
		this.setYPos(yPos);
		this.spriteImage = image;
	}

	/**
	 * Returns the x-coordinate of the sprite's position.
	 *
	 * @return xPos The x-coordinate.
	 */
	public double getXPos() {
		return xPos;
	}

	/**
	 * Returns the y-coordinate of the sprite's position.
	 *
	 * @return yPos The y-coordinate.
	 */
	public double getYPos() {
		return yPos;
	}

	/**
	 * Sets the x-coordinate of the sprite's position.
	 *
	 * @param xPos The new x-coordinate.
	 */
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	/**
	 * Sets the y-coordinate of the sprite's position.
	 *
	 * @param yPos The new y-coordinate.
	 */
	public void setYPos(double yPos) {
		this.yPos = yPos;
	}

	/**
	 * Renders the sprite's image on the canvas at its current position. This will
	 * be called in the AnimationTimer.
	 *
	 * @param gc The GraphicsContext used for drawing.
	 */
	public void render(GraphicsContext gc) {
		gc.drawImage(spriteImage, xPos, yPos, 20, 20);
	}

	/**
	 * Returns a Rectangle2D representing the sprite's hitbox. for colission
	 * detection. A certain number of pixels is subtracted to allow for a bit of
	 * overlap
	 *
	 * @return The hitbox of the sprite.
	 */
	public Rectangle2D hitBox() {
		return new Rectangle2D(xPos, yPos, spriteImage.getWidth() - 10, spriteImage.getHeight() - 10);
	}
}