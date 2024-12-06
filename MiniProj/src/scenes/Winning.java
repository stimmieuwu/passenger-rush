package scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This class represents the "Winning" scene in the Passenger rush game, which
 * is displayed after the game is over. It provides a simple interface showing
 * what happened during the game
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Winning {

	/** The Scene object representing the winning scene. */
	private Scene winningScene;
	/** The root node of the scene layout. */
	private StackPane layout = new StackPane();
	/** The Text object displaying a message on the winning scene. */
	private Text gameOver = new Text("Menu");

	/**
	 * Constructs a Winning object. Initializes the scene with a back button to
	 * return to the main menu.
	 *
	 * @param sceneManager The SceneManager object used for switching between
	 *                     scenes.
	 */
	public Winning(SceneManager sceneManager) {
		// Go back to the main menu upon the click of this button
		Button back = new Button("Back");
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneManager.switchToMainMenu();
			}
		});

		VBox vbox = new VBox(SceneManager.BUTTON_SPACING);
		vbox.getChildren().addAll(gameOver, back);
		vbox.setAlignment(Pos.CENTER); // Center the elements in the VBox

		layout.getChildren().addAll(vbox);
		winningScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
	}

	/**
	 * Returns the Scene object representing the winning scene.
	 *
	 * @return The winning scene.
	 */
	public Scene getScene() {
		return winningScene;
	}
}