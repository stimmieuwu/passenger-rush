package effects;

import entities.Player;

/**
 * Parent class for debuffs in Passenger Rush.
 * Whenever a jeepney picks a debuff up, it will inflict the debuff on the opponent.
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public abstract class Debuff {
	protected String name;
	protected long duration;
	
	public Debuff(String name, long duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public abstract void apply(Player player);
	
	public abstract void remove(Player player);
	
	public long getDuration() {
		return duration;
	}
	
	public String getName() {
		return name;
	}
}
