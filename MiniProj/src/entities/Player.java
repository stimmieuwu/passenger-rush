package entities;

import javafx.event.EventHandler;
import javafx.scene.Scene;

/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player extends Sprite{
	private double dx, dy;

	private KeyCode up;
	private KeyCode down;
	private KeyCode left;
	private KeyCode right;
	public final static Image PLAYER_IMAGE = new Image("../assets/sprites/testing_car.png", 100, 100, true , true);
	
	public Player(int xPos, int yPos, Image image, KeyCode up, KeyCode down, KeyCode left, KeyCode right) {
		super(xPos, yPos, PLAYER_IMAGE);
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	public double getDX() {
		return this.dx;
	}
	public double getDY() {
		return this.dy;
	}

//setters
	public void setDX(double dx){
		this.dx = dx;
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
	

	
