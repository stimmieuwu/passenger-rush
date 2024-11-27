package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import map.GridMap;
import windows.Menu;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;

/***********************************************************
 * This program implements a game called "Passenger Rush" 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-11-25 12:13 AM
 *
 ***********************************************************/
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			// to test tiles
			// remember to remove import
			
			Canvas canvas = new Canvas(800, 800);
			GraphicsContext gc = canvas.getGraphicsContext2D();
			GridMap map = new GridMap(gc);
	        root.getChildren().add(canvas);
			map.drawMap(gc);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
