package effects;

import entities.Player;
/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Speed extends Effect{
	private final double speedMultiplier;
	
	public Speed(long duration, double speedMultiplier) {
		super("speed", duration);
		this.speedMultiplier = speedMultiplier;
	}
	
	@Override
	public void apply(Player player) {
		player.setSpeedMultiplier(speedMultiplier);
	}
	
	@Override
	public void remove(Player player) {
		player.setSpeedMultiplier(1.0);
	}
}
