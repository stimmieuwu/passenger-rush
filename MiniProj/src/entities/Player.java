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
public class Player extends Sprite {
    /** The change in x-coordinate (horizontal movement). */
    private double dx;
    /** The change in y-coordinate (vertical movement). */
    private double dy;
    /** The speed multiplier of the player's movement. */
    private double speedMultiplier; 
    /** The image representing the player. */
    private Image playerImage;
    
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
	
    /**
     * Constructs a Player object.
     * Initializes the player's position, speed, and texture.
     *
     * @param xPos The initial x-coordinate of the player.
     * @param yPos The initial y-coordinate of the player.
     * @param image The image to use for the player (currently not used).
     */
	public Player(int xPos, int yPos, Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.speedMultiplier = 1.0;
		this.playerImage = new Image("../assets/sprites/testing_car.png", 100, 100, true , true);
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
     * Returns the player's x-coordinate.
     * @return The x-coordinate.
     */
    public double getXPos() {
        return xPos;
    }

    /** 
     * Returns the player's y-coordinate.
     * @return The y-coordinate.
     */
    public double getYPos() {
        return yPos;
    }

    /** 
     * Returns the player's horizontal movement (change in x).
     * @return The horizontal movement (dx).
     */
    public double getDX() {
        return this.dx;
    }

    /** 
     * Returns the player's vertical movement (change in y).
     * @return The vertical movement (dy).
     */
    public double getDY() {
        return this.dy;
    }

    /** 
     * Sets the player's horizontal movement (change in x).
     * @param dx The new horizontal movement.
     */
    public void setDX(double dx){
        this.dx = dx;
    }

    /** 
     * Sets the player's vertical movement (change in y).
     * @param dy The new vertical movement.
     */
    public void setDY(double dy){
        this.dy = dy;
    }
}
