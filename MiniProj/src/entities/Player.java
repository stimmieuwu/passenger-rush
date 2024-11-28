package entities;

/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {
	private double xPos, yPos;
	private double dx, dy;
	private double playerSpeed;
	private Image playerImage;
	
	public final static Image PLAYER_IMAGE = new Image("../assets/sprites/testing_car.png", 100, 100, true , true);
	
	public Player(int xPos, int yPos, Image image) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.playerSpeed = 2.0;
		this.playerImage = PLAYER_IMAGE;
	}
	
	public void move() {
		this.xPos += this.dx;
		this.yPos += this.dy;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(playerImage, xPos, yPos);
	}
	
	public double getXPos() {
		return xPos;
	}
	
	public double getYPos() {
		return yPos;
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
}