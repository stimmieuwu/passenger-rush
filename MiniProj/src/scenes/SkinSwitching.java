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
 * This class represents is responsible for switching the jeepney skin of each player.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class SkinSwitching {

	/** The Scene object representing the winning scene. */
	private Scene skinSwitchingScene;
	/** The root node of the scene layout. */
	private StackPane layout = new StackPane();
	/** The Text object displaying a message on the winning scene. */
	private Text skinSwitching = new Text("Select Skin");

	/**
	 * Constructs a SkinSwitching object. Initializes the scene with a back button to
	 * return to the main menu.
	 *
	 * @param sceneManager The SceneManager object used for switching between
	 *                     scenes.
	 */
	public SkinSwitching(SceneManager sceneManager) {
		// Go back to the main menu upon the click of this button
		Button start = new Button("Start");
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneManager.switchToGameScene();
			}
		});

		VBox vbox = new VBox(SceneManager.BUTTON_SPACING);
		vbox.getChildren().addAll(skinSwitching, start);
		vbox.setAlignment(Pos.CENTER); // Center the elements in the VBox

		layout.getChildren().addAll(vbox);
		skinSwitchingScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
	}

	/**
	 * Returns the Scene object representing the skin switching scene.
	 *
	 * @return The winning scene.
	 */
	public Scene getScene() {
		return skinSwitchingScene;
	}
}