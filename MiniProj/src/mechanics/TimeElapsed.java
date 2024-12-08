package mechanics;

/**
 * Class for tracking how much time has elapsed in seconds by tracking when the
 * game started, tracking the current time, and subtracting.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class TimeElapsed {

	private static long startTime;
	private static int elapsedSeconds;
	private static final long NANO = 1_000_000_000L;
	
	/** Start the timer */
	public static void start() {
		startTime = System.nanoTime();
	}

	/** Update the elapsed time by calling this in the AnimationTimer. */
	public static void update() {
		long now = System.nanoTime();
		elapsedSeconds = (int) ((now - startTime) / NANO);
	}

	/** Get the elapsed time in seconds */
	public static int getElapsedSeconds() {
		return elapsedSeconds;
	}
}