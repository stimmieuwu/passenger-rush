package entities;
import gameObjects.Sprite;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */

public class Sprite{
	private Image spriteImage;
	private double xPos, yPos;
	private boolean isColliding;

	public Sprite(int xPos, int yPos, Image image) {
		this.setXPos(xPos);
		this.setYPos(yPos);
		this.spriteImage = image;
	}

	public double getXPos() {
		return xPos;
	}
	
	public double getYPos() {
		return yPos;
	}
	
	public void setXPos(double xPos){
		this.xPos = xPos;
	}

	public void setYPos(double yPos){
		this.yPos = yPos;
	}
	
	public void render(GraphicsContext gc) {
		gc.drawImage(spriteImage, xPos, yPos);
	}
	
	// acts as the boundaries of each sprite which determines collision
	public Rectangle2D hitBox() {
		return new Rectangle2D(xPos, yPos, spriteImage.getWidth()-10, spriteImage.getHeight()-10);
	}
	

	
	
}

