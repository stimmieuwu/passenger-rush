package entities;

import java.awt.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import mechanics.CollisionDetector;

/**
 * This class represents a player (jeepney) in the game. It handles the player's
 * position, movement, and rendering.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Player extends Sprite {

	// Variables for movement
	/** The change in x-coordinate (horizontal movement). */
	private double dx;
	/** The change in y-coordinate (vertical movement). */
	private double dy;
	/** The speed multiplier of the player's movement. */
	private double speedMultiplier;
	/** The amount of pixels that a jeepney moves per game tick */
	public static final double MOVE_AMOUNT = 0.70;

	// Objects related to images
	/** The image representing the player. */
	private Image playerImage;
	/** The image for skin 1 */
	public static final Image SKIN_1 = new Image("./../assets/sprites/blueJeep.png", 80, 80, true, true);
	/** The image for skin 2 */
	public static final Image SKIN_2 = new Image("./../assets/sprites/redJeep.png", 80, 80, true, true);
	/** The image for skin 3 */
	public static final Image SKIN_3 = new Image("./../assets/sprites/testing_car.png");
	/** The image for skin 4 */
	public static final Image SKIN_4 = new Image("./../assets/sprites/testing_car.png");
	/** The image for skin 5 */
	public static final Image SKIN_5 = new Image("./../assets/sprites/testing_car.png");
	/** Check whether or not the player is facing left */
	private boolean isLeft = false;

	// Movement and colission related
	/** The key objects in order to listen for the player controls. */
	public KeyCode up, down, left, right;
	/** Hitbox for picking up passengers and effects */
	public Rectangle hitbox;
	/** Hitbox for colliding with walls */
	public Rectangle collisionBox;
	/** Mechanism for detecting colission with walls */
	public CollisionDetector collision;

	// Buffs
	/** Indicates whether the player has the speed buff */
	private boolean hasSpeedBuff;
	/** Indicates whether the player has the insurance buff */
	private boolean hasInsurance;
	/** Indicates whether the player has the invincibility buff */
	private boolean hasInvincibility;

	// Debuffs
	private boolean hasOilSpillDebuff;

	// Game attributes
	public int score;

	/**
	 * Constructs a Player object. Initializes the player's position, speed, and
	 * texture.
	 *
	 * @param xPos  The initial x-coordinate of the player.
	 * @param yPos  The initial y-coordinate of the player.
	 * @param image The image to use for the player (currently not used).
	 * @param up,   down, left, right The buttons for moving the player
	 */
	public Player(int xPos, int yPos, Image image, KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
		super(xPos, yPos, image); // From Sprite class
		// Player-related
		this.speedMultiplier = 1.0;
		this.playerImage = image;

		// Controls-related
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;

		// Colission-related
		this.collision = new CollisionDetector();
		this.collisionBox = new Rectangle();
		this.hitbox = new Rectangle();

	}

	// TODO improve upon hitbox generation
	public void generateHitBox() {
		this.hitbox.x = (int) this.getXPos() + 30;
		this.hitbox.y = (int) this.getYPos() + 20;
		this.hitbox.width = (int) (playerImage.getWidth() - 60);
		this.hitbox.height = (int) (playerImage.getHeight() - 25);
	}

	public void generateCollisionBox() {
		this.collisionBox.x = (int) this.getXPos();
		this.collisionBox.y = (int) this.getYPos();
		this.collisionBox.width = (int) (playerImage.getWidth());
		this.collisionBox.height = (int) (playerImage.getHeight());
	}

	/**
	 * Renders the player's image on the canvas at the current position.
	 *
	 * @param gc The GraphicsContext used for drawing.
	 */
	public void render(GraphicsContext gc) {
		gc.save();
		gc.translate(this.getXPos() + playerImage.getWidth() / 2, this.getYPos() + playerImage.getHeight() / 2);

		if (this.isLeft)
			gc.scale(-1, 1);
//		gc.rotate(rotationAngle);
		gc.drawImage(playerImage, -playerImage.getWidth() / 2, -playerImage.getHeight() / 2);
		gc.restore();
		renderBox(gc, this.hitbox);
		renderBox(gc, this.collisionBox);
//		slowDown();
	}

	private void renderBox(GraphicsContext gc, Rectangle box) {
		gc.setFill(Color.TRANSPARENT);
		gc.setStroke(Color.AQUA);
		gc.setLineWidth(2);
		gc.strokeRect(box.x, box.y, box.width, box.height);
	}

	/**
	 * Sets the dx or dy based on the given KeyCode. This method is used to initiate
	 * movement in a specific direction.
	 * 
	 * @param key The KeyCode representing the direction of movement.
	 */
	private void move(KeyCode key) {
		/**
		 * value of setRotationAngle is not yet final, will change depending on the
		 * orientation of the jeepney icons; current value based on testing_car's
		 * orientation
		 */

		if (!isColliding) {
			if (key == this.up) {
				this.setDY(-MOVE_AMOUNT);
			} else if (key == this.down) {
				this.setDY(MOVE_AMOUNT);
			} else if (key == this.right) {
				this.setDX(MOVE_AMOUNT);
				this.isLeft = false;
			} else if (key == this.left) {
				this.setDX(-MOVE_AMOUNT);
				this.isLeft = true;

			}
		}
	}

	/**
	 * Stops the player's movement in the direction specified by the KeyCode. This
	 * method sets the corresponding dx or dy to 0.
	 * 
	 * @param key The KeyCode representing the direction to stop.
	 */
	private void stop(KeyCode key) {
		if (key == this.up)
			this.setDY(0);
		if (key == this.down)
			this.setDY(0);
		if (key == this.left)
			this.setDX(0);
		if (key == this.right)
			this.setDX(0);
	}

	/**
	 * Sets the player's movement based on the provided KeyCode.
	 * 
	 * @param key The KeyCode that triggers the movement.
	 */
	public void setPlayerMovement(KeyCode key) {
		move(key);
	}

	/**
	 * Stops the player's movement based on the provided KeyCode.
	 * 
	 * @param key The KeyCode that triggers the stop.
	 */
	public void stopPlayerMovement(KeyCode key) {
		stop(key);
	}

	/**
	 * Returns the player's image.
	 *
	 * @return The player's image.
	 */
	public Image getPlayerImage() {
		return playerImage;
	}

	/**
	 * Sets the player's image.
	 *
	 * @param playerImage The new image for the player.
	 */
	public void setPlayerImage(Image playerImage) {
		this.playerImage = playerImage;
	}

	/**
	 * Returns the player's speed multiplier.
	 *
	 * @return The speed multiplier.
	 */
	public double getSpeedMultiplier() {
		return speedMultiplier;
	}

	/**
	 * Sets the player's speed multiplier.
	 *
	 * @param speedMultiplier The new speed multiplier.
	 */
	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = speedMultiplier;
	}

	/**
	 * Returns the player's horizontal movement (change in x).
	 * 
	 * @return The horizontal movement (dx).
	 */
	public double getDX() {
		return this.dx;
	}

	/**
	 * Returns the player's vertical movement (change in y).
	 * 
	 * @return The vertical movement (dy).
	 */
	public double getDY() {
		return this.dy;
	}

	/**
	 * Sets the player's horizontal movement (change in x).
	 * 
	 * @param dx The new horizontal movement.
	 */
	public void setDX(double dx) {
		this.dx = dx;
	}

	/**
	 * Sets the player's vertical movement (change in y).
	 * 
	 * @param dy The new vertical movement.
	 */
	public void setDY(double dy) {
		this.dy = dy;
	}

	/**
	 * Move the player based on the current dx and dy values.
	 */
	public void move() {
		this.setXPos(this.getXPos() + dx);
		this.setYPos(this.getYPos() + dy);
	}

}
