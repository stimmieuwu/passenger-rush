package entities;

import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Parent class for power ups in Passenger Rush. Whenever a jeepney picks an
 * effect up, it will gain advantageous attributes or powers.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class PowerUp extends Sprite {

	/** The horizontal and vertical position of the powerup */
	private double xPos, yPos;
	/** The image that represents the powerup */
	private Image powerUpImage;
	/** The rectangular boundary of the powerup for collision detection */
	private Rectangle2D hitbox;
	/** The type of powerup */
	private String type;

	/** Image for the speed buff */
	public static final Image SPEED_BUFF = new Image("./../assets/sprites/speed.png", 40, 40, true, true);
	/** Image for the oil spill debuff */
	public static final Image OILSPILL_DEBUFF = new Image("./../assets/sprites/oil.png", 40, 40, true, true);
	/** Image for Insurance powerup */
	public static final Image INSURANCE_BUFF = new Image("./../assets/sprites/insurance_placeholder.png", 40, 40, true,
			true);
	/** Image for Missile Powerup*/
	public static final Image MISSILE_BUFF = new Image("./../assets/sprites/oil.png", 40, 40, true, true);
	/** Image for a generic powerup */
	public static final Image POWERUP_ICON = new Image("./../assets/sprites/mysteryItem.png", 40, 40, true, true);

	/** Array of possible powerup types */
	public static final String[] TYPES = { "speed", "oilbarrel", "insured", "missile"};

	/**
	 * Constructs a PowerUp object.
	 *
	 * @param xPos  The initial x-coordinate of the power-up.
	 * @param yPos  The initial y-coordinate of the power-up.
	 * @param image The image to be used for rendering the power-up.
	 * @param type  The type of the power-up (this parameter is currently not used).
	 */
	public PowerUp(int xPos, int yPos, Image image, String type) {
		super(xPos, yPos, image);
		this.type = TYPES[new Random().nextInt(TYPES.length)];
		this.hitbox = new Rectangle2D(xPos, yPos, spriteImage.getWidth(), spriteImage.getHeight());
	}

	/**
	 * Renders the power-up on the game canvas.
	 *
	 * @param gc The GraphicsContext used for drawing on the canvas.
	 */
	public void render(GraphicsContext gc) {
		gc.save();
		gc.translate(this.getXPos() + spriteImage.getWidth() / 2, this.getYPos() + spriteImage.getHeight() / 2);

		gc.drawImage(spriteImage, -spriteImage.getWidth() / 2, -spriteImage.getHeight() / 2);
		gc.restore();
	}

	/**
	 * Returns the hitbox of the power-up.
	 *
	 * @return The Rectangle2D representing the power-up's hitbox.
	 */
	public Rectangle2D getHitbox() {
		return hitbox;
	}

	/**
	 * Returns the type of the power-up.
	 *
	 * @return The type of the power-up as a String.
	 */
	public String getType() {
		return type;
	}
}
