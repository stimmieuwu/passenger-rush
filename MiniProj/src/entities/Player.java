package entities;

import java.util.HashSet;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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

	/** The name of the jeepney */
	public String name;

	// Variables for movement
	private double startingX;
	private double startingY;
	/** The change in x-coordinate (horizontal movement). */
	private double dx;
	/** The change in y-coordinate (vertical movement). */
	private double dy;
	/** The change in x-coordinate (horizontal movement). */
	private double predictDX;
	/** The change in y-coordinate (vertical movement). */
	private double predictDY;
	/** The speed multiplier of the player's movement. */
	private double speedMultiplier;
	/** The amount of pixels that a jeepney moves per game tick */
	public static final double MOVE_AMOUNT = 1.0;

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
	public Rectangle2D hitbox;
	/** Hitbox for colliding with walls */
	public Rectangle2D collisionBox;
	/** Mechanism for detecting colission with walls */
	public CollisionDetector collision;
	public boolean isCollidingUp = false;
	public boolean isCollidingDown = false;
	public boolean isCollidingLeft = false;
	public boolean isCollidingRight = false;
	public boolean flippedControls = false;
	public boolean isInvincible = false;
	// Buffs
	/** Indicates whether the player has the speed buff */
	public boolean hasSpeedBuff = false;
	/** Indicates whether the player has the insurance buff */
	public boolean hasInsurance = false;
	/** Indicates whether the player has the invincibility buff */
	private boolean hasInvincibility = false;
	private boolean hasMissileBuff = false;
	// Debuffs
	/** Indicates whether the player has the oil spill debuff */
	public boolean hasOilSpillDebuff = false;
	/** Indicates whether the player has the oil spill debuff */
	public boolean hasCrack = false;

	// Game attributes
	/** Amount of passengers the player has transported */
	public int score;
	public int passengers;
	private HashSet<KeyCode> currentKeysPressed = new HashSet<>();

	/**
	 * Constructs a Player object. Initializes the player's position, speed, and
	 * texture.
	 *
	 * @param xPos  The initial x-coordinate of the player.
	 * @param yPos  The initial y-coordinate of the player.
	 * @param image The image to use for the player (currently not used).
	 * @param up,   down, left, right The buttons for moving the player
	 */
	public Player(String name, int xPos, int yPos, Image image, KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
		super(xPos, yPos, image); // From Sprite class
		// Player-related
		this.speedMultiplier = 1.0;
		this.playerImage = image;
		this.name = name;
		this.startingX = xPos;
		this.startingY = yPos;

		// Controls-related
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;

		// Colission-related
		this.collision = new CollisionDetector();
		this.collisionBox = generateCollisionBox();
		this.hitbox = generateHitBox();

	}

	public void reset() {
		this.dx = 0;
		this.dy = 0;
		this.predictDX = 0;
		this.predictDY = 0;
		this.speedMultiplier = 1.0;
		this.isLeft = false;

		this.isCollidingUp = false;
		this.isCollidingDown = false;
		this.isCollidingLeft = false;
		this.isCollidingRight = false;
		this.isColliding = false;
		this.flippedControls = false;

		this.hasInsurance = false;
		this.hasInvincibility = false;
		this.hasOilSpillDebuff = false;
		this.hasSpeedBuff = false;
		this.hasCrack = false;

		this.score = 0;
		this.passengers = 0;
	}

	public Rectangle2D generateHitBox() {
		return new Rectangle2D(this.getXPos() + 8, this.getYPos() + 8, playerImage.getWidth() - 12,
				playerImage.getHeight() - 12);
	}

	public Rectangle2D generateCollisionBox() {
		return new Rectangle2D(this.getXPos() + 30, this.getYPos() + 20, playerImage.getWidth() - 60,
				playerImage.getHeight() - 25);
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
		// gc.rotate(rotationAngle);
		gc.drawImage(playerImage, -playerImage.getWidth() / 2, -playerImage.getHeight() / 2);
		gc.restore();
		gc.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 12));
		gc.setFill(Color.BLACK);
		gc.fillText(name, this.getXPos(), this.getYPos());
		gc.fillText(Integer.toString(passengers), this.getXPos() + playerImage.getWidth() / 2,
				this.getYPos() + playerImage.getHeight());
		renderBox(gc, this.hitbox);
		renderBox(gc, this.collisionBox);
		// slowDown();
	}

	/**
	 * Debugging class that visualizes the hitboxes
	 * 
	 * @param gc  The GraphicsContext used for drawing.
	 * @param box The hitbox to visualize
	 */
	public void renderBox(GraphicsContext gc, Rectangle2D box) {
		gc.setFill(Color.TRANSPARENT);
		gc.setStroke(Color.AQUA);
		gc.setLineWidth(2);
		gc.strokeRect(box.getMinX(), box.getMinY(), box.getWidth(), box.getHeight());
	}

	/**
	 * Sets the dx or dy based on the given KeyCode. This method is used to initiate
	 * movement in a specific direction.
	 * 
	 * @param key The KeyCode representing the direction of movement.
	 */
	private void move(KeyCode key) {
		if (key == this.up && !isCollidingUp) {
			this.setDY(-MOVE_AMOUNT);
		} else if (key == this.down && !isCollidingDown) {
			this.setDY(MOVE_AMOUNT);
		} else if (key == this.right && !isCollidingRight) {
			this.setDX(MOVE_AMOUNT);
			this.isLeft = false;
		} else if (key == this.left && !isCollidingLeft) {
			this.setDX(-MOVE_AMOUNT);
			this.isLeft = true;
		}
	}

	public void move() {
		if (!flippedControls) {
			this.setXPos(this.getXPos() + dx * speedMultiplier);
			this.setYPos(this.getYPos() + dy * speedMultiplier);
		} else {
			this.setXPos(this.getXPos() - dx * speedMultiplier);
			this.setYPos(this.getYPos() - dy * speedMultiplier);
		}

	}

	public void pushBack(double overlapX, double overlapY) {
		this.setXPos(this.getXPos() - overlapX); // Adjust the player's x position
		this.setYPos(this.getYPos() - overlapY); // Adjust the player's y position
	}

	/**
	 * Sets the dx or dy based on the given KeyCode. This method is used to initiate
	 * movement in a specific direction.
	 * 
	 * @param key The KeyCode representing the direction of movement.
	 */
	public void simulateMove(KeyCode key) {
		/**
		 * value of setRotationAngle is not yet final, will change depending on the
		 * orientation of the jeepney icons; current value based on testing_car's
		 * orientation
		 */

		if (key == this.up) {
			this.setPredictDY(-MOVE_AMOUNT);
		} else if (key == this.down) {
			this.setPredictDY(MOVE_AMOUNT);
		} else if (key == this.right) {
			this.setPredictDX(MOVE_AMOUNT);
		} else if (key == this.left) {
			this.setPredictDX(-MOVE_AMOUNT);

		}
	}

	/**
	 * Stops the player's movement in the direction specified by the KeyCode. This
	 * method sets the corresponding dx or dy to 0.
	 * 
	 * @param key The KeyCode representing the direction to stop.
	 */
	public void stop(KeyCode key) {
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
		this.currentKeysPressed.add(key);
	}

	/**
	 * Stops the player's movement based on the provided KeyCode.
	 * 
	 * @param key The KeyCode that triggers the stop.
	 */
	public void stopPlayerMovement(KeyCode key) {
		stop(key);
		this.currentKeysPressed.remove(key);
	}

	public HashSet<KeyCode> getCurrentKeyPressed() {
		return this.currentKeysPressed;
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
	 * Sets the player's vertical movement (change in y).
	 * 
	 * @param dy The new vertical movement.
	 */
	public void setPredictDY(double dy) {
		this.predictDY = dy;
	}

	/**
	 * Sets the player's horizontal movement (change in x).
	 * 
	 * @param dx The new horizontal movement.
	 */
	public void setPredictDX(double dx) {
		this.predictDX = dx;
	}

	/**
	 * Sets the player's vertical movement (change in y).
	 * 
	 * @param dy The new vertical movement.
	 */
	public double getPredictDY() {
		return this.predictDY;
	}

	/**
	 * Sets the player's horizontal movement (change in x).
	 * 
	 * @param dx The new horizontal movement.
	 */
	public double getPredictDX() {
		return this.predictDX;
	}

	public void setOnKeyPressed(EventHandler<KeyEvent> eventHandler) {
		// TODO Auto-generated method stub

	}

	public void setOnKeyReleased(EventHandler<KeyEvent> eventHandler) {
		// TODO Auto-generated method stub

	}

	public void activateBuff(String buffType) {
		switch (buffType.toLowerCase()) {
		case "speed":
			this.hasSpeedBuff = true;
			this.speedMultiplier = 2.0;
			break;
		}
	}

	public void deactivateBuff(String buffType) {
		switch (buffType.toLowerCase()) {
		case "speed":
			this.hasSpeedBuff = false;
			this.speedMultiplier = 1.0;
			break;
		}
	}

	public boolean isFlippedControls() {
		return flippedControls;
	}

	public void setFlippedControlsToggle() {
		if (this.flippedControls == false)
			this.flippedControls = true;
		else
			this.flippedControls = false;
	}

	/** for missile launch */
	public boolean hasMissileBuff() {
		return hasMissileBuff;
	}

	public void setMissileBuff(boolean value) {
		hasMissileBuff = value;
	}

	public Obstacle launchMissile() {
		if (hasMissileBuff) {
			hasMissileBuff = false;
			return new Obstacle((int) this.getXPos(), (int) this.getYPos(), Obstacle.MISSILE_OBSTACLE,
					"missile_obstacle");
		}
		return null;
	}

	public boolean hasOilSpillDebuff() {
		return hasOilSpillDebuff;
	}

	public void setOilSpillDebuff(boolean value) {
		hasOilSpillDebuff = value;
	}

	public Obstacle placeOilSpill() {
		if (hasOilSpillDebuff) {
			hasOilSpillDebuff = false;
			if (isLeft)
				return new Obstacle((int) this.getXPos() - 90, (int) this.getYPos() + 20, Obstacle.OILSPILL_OBSTACLE,
						"oilspill_obstacle");
			if (!isLeft)
				return new Obstacle((int) this.getXPos() + 90, (int) this.getYPos() + 20, Obstacle.OILSPILL_OBSTACLE,
						"oilspill_obstacle");
		}
		return null;
	}

	public boolean hasInvincibility() {
		return hasInvincibility;
	}

	public void hasInvincibilityToggle() {
		if (this.hasInvincibility == false)
			this.hasInvincibility = true;
		else
			this.hasInvincibility = false;
	}

	public void teleportToStart() {
		if (!this.hasInsurance) {
			passengers = 0;
		}
		this.setXPos(startingX);
		this.setYPos(startingY);
		this.hasInsurance = false;
		System.out.println("Player teleported to starting point.");
	}
}
