package scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import scenes.SceneManager;

/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Game {
	// Create a scene, group, and a canvas. These are essential elements for the GUI.
	private Scene gameScene;
	protected Group root;
	protected Canvas canvas;

	public Game(SceneManager sceneManager) {
		// Initialize the necessary elements of the game window
		this.root = new Group();
		this.gameScene = new Scene(root);
		this.canvas = new Canvas(SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		this.root.getChildren().add(this.canvas);
		
	}

	public Scene getScene() { // Getter for the scene
		return gameScene;
	}
}
