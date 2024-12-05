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
	/** The change in x-coordinate (horizontal movement). */
	private double dx;
	/** The change in y-coordinate (vertical movement). */
	private double dy;
	/** The speed multiplier of the player's movement. */
	private double speedMultiplier;
	/** The image representing the player. */
	private Image playerImage;
	/** The key objects in order to listen for the player controls. */
	public KeyCode up, down, left, right;
	
	/** The image for skin 1 */
	public static final Image SKIN_1 = new Image("../assets/sprites/testing_car.png");
	/** The image for skin 2 */
	public static final Image SKIN_2 = new Image("../assets/sprites/testing_car.png");
	/** The image for skin 3 */
	public static final Image SKIN_3 = new Image("../assets/sprites/testing_car.png");
	/** The image for skin 4 */
	public static final Image SKIN_4 = new Image("../assets/sprites/testing_car.png");
	/** The image for skin 5 */
	public static final Image SKIN_5 = new Image("../assets/sprites/testing_car.png");
	public static final double MOVE_AMOUNT = 0.70;
	
	public CollisionDetector collision;
	private boolean hasSpeedBuff;
	private boolean hasInsurance;
	private boolean hasInvincibility;
	
	private boolean hasOilSpillDebuff;
	
	public int score;
	
	public Rectangle hitbox;
	
	/**
	 * Constructs a Player object. Initializes the player's position, speed, and
	 * texture.
	 *
	 * @param xPos  The initial x-coordinate of the player.
	 * @param yPos  The initial y-coordinate of the player.
	 * @param image The image to use for the player (currently not used).
	 */
	public Player(int xPos, int yPos, Image image, KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
		super(xPos, yPos, SKIN_1);
		this.speedMultiplier = 1.0;
		this.playerImage = new Image("../assets/sprites/testing_car.png", 30, 15, true, true);
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.collision = new CollisionDetector();
		this.hitbox  = new Rectangle();
		
	}
	
	
	// TODO improve upon hitbox generation
	public void generateHitBox() {
		this.hitbox.x = (int) this.getXPos() + 5;
		this.hitbox.y = (int) this.getYPos() + 4;
		this.hitbox.width = (int) (playerImage.getWidth() - 5);
		this.hitbox.height = (int) (playerImage.getHeight() - 5);
	}

	/**
	 * Renders the player's image on the canvas at the current position.
	 *
	 * @param gc The GraphicsContext used for drawing.
	 */
	public void render(GraphicsContext gc) {
		gc.drawImage(playerImage, this.getXPos(), this.getYPos(), 30, 15);
		renderBox(gc);
//		slowDown();
	}
	
	private void renderBox(GraphicsContext gc) {
		gc.setFill(Color.TRANSPARENT);
		gc.setStroke(Color.AQUA);
		gc.setLineWidth(2);
		gc.strokeRect(hitbox.x, hitbox.y,hitbox.width, hitbox.height);
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
	


	/**
	 * Sets the dx or dy based on the given KeyCode. This method is used to initiate
	 * movement in a specific direction.
	 * 
	 * @param key The KeyCode representing the direction of movement.
	 */
	private void move(KeyCode key) {
	
		
		
		if(!isColliding) {
			if (key == this.up)
				this.setDY(-MOVE_AMOUNT);
			if (key == this.down)
				this.setDY(MOVE_AMOUNT);
			if (key == this.right)
				this.setDX(MOVE_AMOUNT);
			if (key == this.left)
				this.setDX(-MOVE_AMOUNT);
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
}
