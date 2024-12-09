package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Obstacles are stationary objects that the player's jeepney must avoid. This
 * class handles the obstacle's image, collision, and rendering.
 */
public class Obstacle extends Sprite {

	/** Image for the oil spill obstacle */
	public static final Image OILSPILL_OBSTACLE = new Image("./../assets/sprites/spill.png", 40, 40, true, true);
	/** Image for the hole obstacle */
	public static final Image HOLE_OBSTACLE = new Image("./../assets/sprites/hole.png", 40, 40, true, true);
	/** The horizontal and vertical position of the obstacle */
	private double xPos, yPos;
	/** The image that represents the obstacle */
	private Image obstacleImage;

	/** The rectangular boundary of the obstacle for collision detection */
	private Rectangle2D hitbox;
	/** The type of obstacle */
	private String type;

	/**
	 * Constructs an `Obstacle` object.
	 *
	 * @param xPos  The initial x-coordinate of the obstacle.
	 * @param yPos  The initial y-coordinate of the obstacle.
	 * @param image The image to be used for rendering the obstacle.
	 * @param type  The type of the obstacle.
	 */
	public Obstacle(int xPos, int yPos, Image image, String type) {
		super(xPos, yPos, image); // From the Sprite class
		this.type = type;
		this.hitbox = new Rectangle2D(xPos, yPos, spriteImage.getWidth(), spriteImage.getHeight());
	}

	/**
	 * Returns the hitbox of the obstacle.
	 *
	 * @return The Rectangle2D representing the obstacle's hitbox.
	 */
	public Rectangle2D getHitbox() {
		return hitbox;
	}

	/**
	 * Returns the type of the obstacle.
	 *
	 * @return The type of the obstacle as a String.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Renders the obstacle on the game canvas.
	 *
	 * @param gc The GraphicsContext used for drawing on the canvas.
	 */
	public void render(GraphicsContext gc) {
		gc.save();
		gc.translate(this.getXPos() + spriteImage.getWidth() / 2, this.getYPos() + spriteImage.getHeight() / 2);

		gc.drawImage(spriteImage, -spriteImage.getWidth() / 2, -spriteImage.getHeight() / 2);
		gc.restore();
	}
}
