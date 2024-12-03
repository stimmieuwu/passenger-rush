package mechanics;

import javafx.animation.AnimationTimer;

public class TimeElapsed {

    private static long startTime;
    private static int elapsedSeconds;

    public static void start() {
        startTime = System.nanoTime();
    }

    public static void update() {
        long now = System.nanoTime();
        elapsedSeconds = (int) ((now - startTime) / 1_000_000_000.0);
    }

    public static int getElapsedSeconds() {
        return elapsedSeconds;
    }
}