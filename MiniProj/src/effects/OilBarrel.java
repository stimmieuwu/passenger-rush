package effects;

import java.util.Timer;
import java.util.TimerTask;

import entities.Player;

/**
 * The OilBarrel class represents an effect in the Passenger Rush game that
 * temporarily flips the player's controls. When a jeepney picks up this effect,
 * its controls will be inverted for a specified duration.
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class OilBarrel extends Effect {
	/** Indicates whether the controls are flipped or not */
	private boolean flippedControls;

	/**
	 * Constructs an OilBarrel effect.
	 *
	 * @param duration        The duration of the effect in milliseconds.
	 * @param flippedControls The initial state of the controls (flipped or not).
	 */
	public OilBarrel(long duration, boolean flippedControls) {
		super("oilbarrel", duration);
		this.flippedControls = flippedControls;
	}

	/**
	 * Applies the OilBarrel effect to the player. This flips the player's controls
	 * and starts a timer to remove the effect after the specified duration.
	 *
	 * @param player The player to apply the effect to.
	 */
	@Override
	public void apply(Player player) {
		// TODO Auto-generated method stub
		player.setFlippedControlsToggle();

		new Timer().schedule(new TimerTask() {
			public void run() {
				remove(player);
			}
		}, duration);
	}

	/**
	 * Removes the OilBarrel effect from the player. This flips the player's
	 * controls back to normal
	 *
	 * @param player The player to remove the effect from.
	 */
	@Override
	public void remove(Player player) {
		// TODO Auto-generated method stub
		player.setFlippedControlsToggle();
	}
}
