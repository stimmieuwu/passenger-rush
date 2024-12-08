package effects;

import java.util.Timer;
import java.util.TimerTask;

import entities.Player;

/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class OilBarrel extends Effect{
	private boolean flippedControls;
	
	public OilBarrel (long duration, boolean flippedControls) {
		super("oilbarrel", duration);
		this.flippedControls = flippedControls;
	}

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

	@Override
	public void remove(Player player) {
		// TODO Auto-generated method stub
		player.setFlippedControlsToggle();
	}
}
