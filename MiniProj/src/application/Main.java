package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This is the main class for the JavaFX application. The program implements a
 * game called "Passenger Rush"
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-11-25 12:13 AM
 */
public class Main extends Application {

	/**
	 * Override the start method of the Application class to create the main
	 * application window, set the scene, and add a stylesheet.
	 * 
	 * @param primaryStage The primary stage for this application where scenes can
	 *                     be set.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
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
