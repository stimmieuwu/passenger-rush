package mechanics;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Audio {
	private MediaPlayer mediaPlayer;

	public Audio(String audioFile, float volume) {

		File file = new File(audioFile);
		String filePath = file.toURI().toString();

		Media sound = new Media(filePath);
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
		mediaPlayer.setVolume(volume);
		mediaPlayer.play();
	}

	public void stopMusic() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
	}
}
