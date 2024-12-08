package entities;

import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PowerUp extends Sprite{
	private double xPos, yPos;
	private Image powerupImage;
	
	private Rectangle2D hitbox;
	private String type;
	
	public static final Image SPEED_BUFF = new Image("./../assets/sprites/speed.png", 40, 40, true, true);
	public static final Image OILSPILL_DEBUFF = new Image("./../assets/sprites/oil.png", 40, 40, true, true);
	public static final Image POWERUP_ICON = new Image("./../assets/sprites/mysteryItem.png", 40, 40, true, true);
	
	public static final String[] TYPES = {"speed", "oilbarrel"};
	
	public PowerUp(int xPos, int yPos, Image image, String type) {
		super(xPos, yPos, image);
		this.type = TYPES[new Random().nextInt(TYPES.length)];
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
