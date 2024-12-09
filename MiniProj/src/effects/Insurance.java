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

	public Insurance(long duration) {
		super("insured", duration);
	}

	public void apply(Player player) {
		// TODO Auto-generated method stub
		player.hasInsurance = true;
		System.out.println(player.hasInsurance);

		new Timer().schedule(new TimerTask() {
			public void run() {
				remove(player);
			}
		}, duration);
	}

	public void remove(Player player) {
		player.hasInsurance = false;
	}
}