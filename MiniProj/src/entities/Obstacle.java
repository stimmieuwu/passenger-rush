package entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Obstacle extends Sprite{
	private double xPos, yPos;
	private Image obstacleImage;
	
	private Rectangle2D hitbox;
	private String type;
	
	public static final Image OILSPILL_OBSTACLE = new Image("./../assets/sprites/spill.png", 40, 40, true, true);
	public static final Image HOLE_OBSTACLE = new Image("./../assets/sprites/hole.png", 40, 40, true, true);
	
	public Obstacle(int xPos, int yPos, Image image, String type) {
		super(xPos, yPos, image);
		this.type = type;
		this.hitbox = new Rectangle2D(xPos, yPos, spriteImage.getWidth(), spriteImage.getHeight());
	}
	
	public void render(GraphicsContext gc) {
		gc.save();
		gc.translate(this.getXPos() + spriteImage.getWidth() / 2, this.getYPos() + spriteImage.getHeight() / 2);

		gc.drawImage(spriteImage, -spriteImage.getWidth() / 2, -spriteImage.getHeight() / 2);
		gc.restore();	
	}
	
	public Rectangle2D getHitbox() {
		return hitbox;
	}
	
	public String getType() {
		return type;
	}
}

