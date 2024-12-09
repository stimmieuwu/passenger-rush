package mechanics;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * The Audio class provides functionality for playing audio files in the
 * Passenger Rush game using the MediaPlayer of JavaFX.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Audio {
	/** The MediaPlayer object that plays the audio */
	private MediaPlayer mediaPlayer;

	/**
	 * Constructs an Audio object and starts playing the specified audio file.
	 *
	 * @param audioFile The path to the audio file.
	 * @param volume    The volume of the audio from 0 to 1
	 */
	public Audio(String audioFile, float volume) {
		File file = new File(audioFile);
		String filePath = file.toURI().toString(); // Convert the file path to a Universal Resource Indexer (URI)

		Media sound = new Media(filePath);
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the audio indefinitely
		mediaPlayer.setVolume(volume);
		mediaPlayer.play(); // Start the audio
	}

	/** Stops the audio playback. */
	public void stopMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}
}
