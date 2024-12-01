package application;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import scenes.SceneManager;


/**
 * This is the main class for the JavaFX application. The program implements a
 * game called "Passenger Rush"
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 * @reference Stage icon implementation inspired by
 *            https://www.youtube.com/watch?v=UZKKaI8OnjY
 */
public class Main extends Application {
	/** The main window of the game */
	Stage stage;

	/**
	 * Override the start method of the Application class to create the main
	 * application window, set the scene, and add a stylesheet.
	 * 
	 * @param primaryStage The primary stage for this application where scenes can
	 *                     be set.
	 */
	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		try {
			/** The mechanism responsible for switching scenes in the game */
			SceneManager sceneManager = new SceneManager(stage);

			sceneManager.switchToMainMenu();

			// Set some properties of the stage, then show it to the user
			Image icon = new Image("../assets/passenger_rush.png");
			stage.getIcons().add(icon);
			stage.setTitle("Passenger Rush");
			stage.setResizable(false);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Entry point to the JavaFX application
	 * 
	 * @param args Command line arguments, which for this application is not
	 *             necessary
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
