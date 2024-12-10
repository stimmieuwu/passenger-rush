package effects;

import entities.Player;
import javafx.scene.image.Image;

/**
 * This directly hits the opponent, resulting in the loss of at least one
 * passenger for the affected jeepeny.
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class MissileObstacle extends Debuff {
	public MissileObstacle(long duration) {
		super("missile", duration);
	}
	
	public void apply(Player player) {
		player.teleportToStart();
	}
	
	public void remove(Player player) {}
	
//	public void home(double d, double e, int scale) {
//		double dy = e - this.getXPos();
//		double dx = d - this.getYPos();
//		double speed, sep;
//		boolean homing = true;
//
//		sep = Math.sqrt(dx * dx + dy * dy);
//		speed = scale / sep;
//
//		if (dy < 50 && dx < 50) {
//			homing = false;
//		}
//
//		if (homing) {
//			this.setXPos(this.getXPos() + dx * speed);
//			this.setYPos(this.getYPos() * speed);
//		} else {
//			this.setXPos(this.getXPos());
//			this.setYPos(this.getYPos() + scale);
//		}
//	}
}
