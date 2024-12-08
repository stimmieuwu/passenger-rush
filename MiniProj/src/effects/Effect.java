package effects;

import entities.Player;

/**
 * Parent class for effects in Passenger Rush.
 * Whenever a jeepney picks an effect up, it will gain certain attributes or powers.
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public abstract class Effect {
	protected String name;
	protected long duration;
	
	public Effect(String name, long duration) {
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
