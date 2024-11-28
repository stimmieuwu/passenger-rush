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
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Instructions {

	private Scene aboutScene; // Create a scene, which we can switch to and from.
	private StackPane layout = new StackPane();
	private Text about = new Text("Menu"); // Store the info about the developer in a Text object
	
	public Instructions (SceneManager sceneManager){
		// Go back to the splash screen upon the click of this button
		Button back = new Button("Back");
		back.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        sceneManager.switchToMainMenu();
		    }
		});
		
		VBox vbox = new VBox(10); // 10 is the spacing between elements
        vbox.getChildren().addAll(about, back);
        vbox.setAlignment(Pos.CENTER); // Center the elements in the VBox

		layout.getChildren().addAll(vbox);
        aboutScene = new Scene(layout, 568, 568); // Create the Scene with the layout as the root node.
   
	}
	
	public Scene getScene() { // Getter for the scene
		return aboutScene;
	}
}
