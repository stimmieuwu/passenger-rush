package effects;

import entities.Player;

/**
 * Parent class for effects in Passenger Rush. Whenever a jeepney picks an
 * effect up, it will gain advantageous attributes or powers.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public abstract class Effect {
	/** Name of the effect */
	protected String name;
	/** Duration of the effect in milliseconds */
	protected long duration;

	/**
	 * Constructor for the Effect class.
	 *
	 * @param name     Name of the effect.
	 * @param duration Duration of the effect in milliseconds.
	 */
	public Effect(String name, long duration) {
		this.name = name;
		this.duration = duration;
	}

	/**
	 * Applies the effect to the specified player
	 *
	 * @param player The player to apply the effect to.
	 */
	public abstract void apply(Player player);

	/**
	 * Removes the effect from the player.
	 *
	 * @param player The player to remove the effect from.
	 */
	public abstract void remove(Player player);

	/**
	 * Returns the duration of the effect.
	 *
	 * @return The duration of the effect in milliseconds.
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Returns the name of the effect.
	 *
	 * @return The name of the effect.
	 */
	public String getName() {
		return name;
	}
}
