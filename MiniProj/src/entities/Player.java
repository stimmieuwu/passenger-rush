package entities;

import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

/**
 * This class represents a player (jeepney) in the game. 
 * It handles the player's position, movement, and rendering.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */

public class Player extends Sprite{
  /** The change in x-coordinate and y-coordinate (horizontal and vertical movement). */
	private double dx, dy;
  /** The objects to listen for key presses for each player's movement */
	private KeyCode up, down, left, right
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
	
	public Player(int xPos, int yPos, Image image, KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
		super(xPos, yPos, PLAYER_IMAGE);
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
    /**
     * Updates the player's position based on the current movement (dx, dy).
     */
    public void move() {
        this.xPos += this.dx * speedMultiplier; // Apply speed multiplier
        this.yPos += this.dy * speedMultiplier; // Apply speed multiplier
    }

    /**
     * Renders the player's image on the canvas at the current position.
     *
     * @param gc The GraphicsContext used for drawing.
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(playerImage, xPos, yPos);
    }
  
	public void setDY(double dy){
		this.dy = dy;
	}
	
	public void move() {
		this.setXPos(this.getXPos() + dx);
		this.setYPos(this.getYPos() + dy);

  }
	
	// actual player movement methods
	private void move(KeyCode key) {
		if (key == this.up)
			this.setDY(-10);
		if (key == this.down)
			this.setDY(10);
		if (key == this.left)
			this.setDX(-10);
		if (key == this.right)
			this.setDX(10);
	}
	
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
	
	//sets player movement
	public void setPlayerMovement(KeyCode key) {
		move(key);
	}

	public void stopPlayerMovement(KeyCode key) {
		stop(key);
	}

}
