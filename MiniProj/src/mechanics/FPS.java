package mechanics;

/**
 * This class maintains an FPS variable for the game. We followed the
 * instructions in the StackOverflow link below.
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 * @reference https://stackoverflow.com/questions/28287398/what-is-the-preferred-way-of-getting-the-frame-rate-of-a-javafx-application
 */

public class FPS {
	/** The time in nanoseconds when the last frame was rendered. */
	private static long lastFrameTime = 0;
	/** Pointer variable for frameRates array */
	private static int index = 0;
	/** Stores the last 100 frames */
	private static double[] frameRates = new double[100];

	/** Initialize the frameRates arra with an initial 60FPS */
	static {
		for (int i = 0; i < frameRates.length; i++) {
			frameRates[i] = 60.0;
		}
	}

	/** Calculation to update the FPS, which is run in the AnimationTimer. */
	public static void update(long now) {
		if (lastFrameTime > 0) {
			long nanosElapsed = now - lastFrameTime;
			double frameRate = 1_000_000_000.0 / nanosElapsed;
			index %= frameRates.length;
			frameRates[index++] = frameRate;
		}

		lastFrameTime = now;
	}

	/** Getter for the average FPS in the last 100 frames. */
	public static double getAverageFPS() {
		int total = 0;

		for (int i = 0; i < frameRates.length; i++) {
			total += frameRates[i];
		}

		return total / frameRates.length;
	}
}