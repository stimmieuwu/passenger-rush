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
public class Crack extends Effect {
	
	public Crack(int duration) {
		super("crack", duration);
	}

	public void apply(Player player) {
		player.hasCrack = true;
		player.setSpeedMultiplier(0.5);
	}

	public void remove(Player player) {
		player.hasCrack = false;
		player.setSpeedMultiplier(1.0);
	}
}

