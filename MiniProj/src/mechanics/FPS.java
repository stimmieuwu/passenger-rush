package mechanics;

/**
 * This class maintains an FPS variable that
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 * @reference https://stackoverflow.com/questions/28287398/what-is-the-preferred-way-of-getting-the-frame-rate-of-a-javafx-application
 */

public class FPS {
	/** */
	private static long lastFrameTime = 0;
	private static int index = 0;
	private static double[] frameRates = new double[100];

	static {
		for (int i = 0; i < frameRates.length; i++) {
			frameRates[i] = 60.0;
		}
	}

	public static void update(long now) {

		if (lastFrameTime > 0) {
			long nanosElapsed = now - lastFrameTime;
			double frameRate = 1_000_000_000.0 / nanosElapsed;
			index %= frameRates.length;
			frameRates[index++] = frameRate;
		}

		lastFrameTime = now;
	}

	public static double getInstantFPS() {
		return frameRates[index % frameRates.length];
	}

	public static double getAverageFPS() {
		int total = 0;

		for (int i = 0; i < frameRates.length; i++) {
			total += frameRates[i];
		}

		return total / frameRates.length;
	}
}