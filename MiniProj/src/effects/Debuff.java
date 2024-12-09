package effects;

import entities.Player;

/**
 * Parent class for debuffs in Passenger Rush. Whenever a jeepney picks a debuff
 * up, it will gain disadvantageous attributes or powers.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public abstract class Debuff {
	/** Name of the debuff */
	protected String name;
	/** Duration of the debuff in milliseconds */
	protected long duration;

	/**
	 * Constructor for the Debuff class.
	 *
	 * @param name     Name of the debuff.
	 * @param duration Duration of the debuff in milliseconds.
	 */
	public Debuff(String name, long duration) {
		this.name = name;
		this.duration = duration;
	}

	/**
	 * Applies the debuff to the specified player
	 *
	 * @param player The player to apply the debuff to.
	 */
	public abstract void apply(Player player);

	/**
	 * Removes the debuff from the player.
	 *
	 * @param player The player to remove the debuff from.
	 */
	public abstract void remove(Player player);

	/**
	 * Returns the duration of the debuff.
	 *
	 * @return The duration of the debuff in milliseconds.
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
