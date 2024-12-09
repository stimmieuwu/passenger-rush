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

	/** Random number generator for creating random spawn delays */
	private Random random = new Random();
	/** The time in nanoseconds when the last spawn occurred */
	private long lastSpawnTime = 0;
	/** The delay in seconds before the next spawn should occur */
	private int nextSpawnDelay;
	/** The amount of nanoseconds in one second for conversion */
	private static final long NANO = 1_000_000_000L;

	/** Construct a spawn object and initialize the first spawn delay */
	public Spawn() {
		nextSpawnDelay = random.nextInt(1) + 1;
	}

	/**
	 * Checks if a spawn event should occur based on the current time. This is
	 * passed to the AnimationTimer.
	 *
	 * @param now The current time in nanoseconds.
	 * @return true if a spawn should occur, false otherwise.
	 */
	public boolean shouldSpawn(long now) {
		if (now - lastSpawnTime >= nextSpawnDelay * NANO) {
			lastSpawnTime = now;
			nextSpawnDelay = random.nextInt(5) + 1;
			return true;
		}
		return false;
	}

}
