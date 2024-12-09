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
public class CrackInTheRoad extends Debuff {
	private boolean tpToSpawn;

	public CrackInTheRoad() {
		super("crackintheroad", 0);
	}

	public void apply(Player player) {
		player.teleportToStart();
	}

	public void remove(Player player) {
	}
}
