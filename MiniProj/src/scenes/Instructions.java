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
 * This class represents the "Instructions" scene in the application. It
 * displays instructions for the game and provides a button to return to the
 * main menu.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Instructions {

	/** The Scene object representing the instructions scene. */
	private Scene instructionsScene;
	/** The root node of the scene layout. */
	private StackPane layout = new StackPane();
	/** The Text object displaying the game instructions. */
	private Text about = new Text("Menu"); // You might want to change this to actual instructions

	/**
	 * Constructs an Instructions object. Initializes the scene with a back button
	 * to return to the main menu.
	 *
	 * @param sceneManager The SceneManager object used for switching between
	 *                     scenes.
	 */
	public Instructions(SceneManager sceneManager) {
		// Go back to the main menu upon the click of this button
		Button back = new Button("Back");
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneManager.switchToMainMenu();
			}
		});

		VBox vbox = new VBox(SceneManager.BUTTON_SPACING);
		vbox.getChildren().addAll(about, back);
		vbox.setAlignment(Pos.CENTER); // Center the elements in the VBox

		layout.getChildren().addAll(vbox);
		instructionsScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
	}

	/**
	 * Returns the Scene object representing the instructions scene.
	 *
	 * @return The instructions scene.
	 */
	public Scene getScene() {
		return instructionsScene;
	}
}