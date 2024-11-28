package application;

import javafx.application.Application;
import javafx.stage.Stage;
import map.GridMap;
//import windows.Menu;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import mechanics.GameTimer; //temp
import entities.Player;

/**
 * This is the main class for the JavaFX application that implements a
 * game called Passenger Rush.
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
			Scene scene = new Scene(root, 800, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Passenger Rush Test");
			primaryStage.setScene(scene);
			primaryStage.show();

//			gameTimer.setScene(scene);

			// to test tiles
			// remember to remove import

			Canvas canvas = new Canvas(800, 800);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			root.getChildren().add(canvas);

			// Testing purposes of GameTImer
			GameTimer gameTimer = new GameTimer(gc, scene);
			gameTimer.start();
//			GridMap map = new GridMap(gc);
//
//			map.drawMap(gc);

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
