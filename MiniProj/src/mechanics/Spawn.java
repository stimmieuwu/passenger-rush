package mechanics;

import java.util.Random;

/**
 * Using the reference below, this class is passed to the AnimationTimer to
 * spawn things every n seconds. In this case, n is a value from 20-35.
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 * @reference https://gamedev.stackexchange.com/questions/64633/how-to-make-something-happen-every-n-seconds-in-game
 */

public class Spawn {

	private Random random = new Random();
	private long lastSpawnTime = 0;
	private int nextSpawnDelay;
	
	public Spawn() {
		 nextSpawnDelay = random.nextInt(1) + 1;
	}

	public boolean shouldSpawn(long now) {
		if (now - lastSpawnTime >= nextSpawnDelay * 1_000_000_000) {
			lastSpawnTime = now;
			nextSpawnDelay = random.nextInt(5) + 1;
			return true;
		}
		return false;
	}

}
