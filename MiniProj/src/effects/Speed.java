package effects;

import entities.Player;

/**
 * The Speed class represents an effect in Passenger Rush that modifies the
 * player's speed. When a jeepney picks up this effect, its speed will be
 * increased or decreased based on the speedMultiplier value.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Speed extends Effect {

	/**
	 * The multiplier applied to the player's base speed. A value greater than 1
	 * increases speed, while a value between 0 and 1 decreases speed.
	 */
	private final double speedMultiplier;

	/**
	 * Constructs a Speed effect.
	 *
	 * @param duration        The duration of the effect in milliseconds.
	 * @param speedMultiplier The factor by which the player's speed is multiplied.
	 */
	public Speed(long duration, double speedMultiplier) {
		super("speed", duration);
		this.speedMultiplier = speedMultiplier;
	}

	/**
	 * Applies the Speed effect to the player, modifying their speed by the
	 * speedMultiplier.
	 *
	 * @param player The player to apply the effect to.
	 */
	@Override
	public void apply(Player player) {
		player.setSpeedMultiplier(speedMultiplier);
	}

	/**
	 * Removes the Speed effect from the player, resetting their speed multiplier to
	 * 1.
	 *
	 * @param player The player to remove the effect from.
	 */
	@Override
	public void remove(Player player) {
		player.setSpeedMultiplier(1.0);
	}
}
