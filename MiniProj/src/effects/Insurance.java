package effects;

import java.util.Timer;
import java.util.TimerTask;

import entities.Player;

/**
 * When a jeepney obtains the Insurance effect, it can return to the starting
 * point without losing passengers.
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Insurance extends Effect {
	private boolean isInsured;

	public Insurance(long duration) {
		super("insured", duration);
		this.isInsured = false;
	}

	public void apply(Player player) {
		// TODO Auto-generated method stub
		if (player.isInsured() == true) {
			player.hasInvincibilityToggle();
		}
		;

		new Timer().schedule(new TimerTask() {
			public void run() {
				remove(player);
			}
		}, duration);
	}

	public void remove(Player player) {
		player.hasInvincibilityToggle();
	}
}
