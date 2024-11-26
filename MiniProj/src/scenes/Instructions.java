package scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/***********************************************************
 * This displays information about the developers of this version of the Passenger Rush game.
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Norman Marfa III
 * @author Jan Zuriel Camba
 * @created_date 2024-11-26 5:00 PM
 *
 ***********************************************************/
public class Instructions {

	private Scene aboutScene; // Create a scene, which we can switch to and from.
	private Canvas canvas; // Create a new canvas
	private Text about = new Text("Simonee Ezekiel M. Mariquit\nJan Zuriel Camba\nNorman Marfa III"); // Store the info about the developer in a Text object
	
	public Instructions (SceneManager sceneManager){
		// Go back to the splash screen upon the click of this button
		Button back = new Button("Back");
		back.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        sceneManager.switchToSplashScene();
		    }
		});
		
		VBox layout = new VBox(10); // Create a VBox, which arranges the things vertically
        canvas.getChildren().addAll(about, back); // Add content to the VBox

        aboutScene = new Scene(layout, 568, 568); // Create the Scene with the layout as the root node.
   
	}
	
	public Scene getScene() { // Getter for the scene
		return aboutScene;
	}
}
