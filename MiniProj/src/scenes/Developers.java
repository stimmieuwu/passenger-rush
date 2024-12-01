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
public class Developers {

	 /** The Scene object representing the developers scene. */
    private Scene aboutScene; 
    /** The root node of the scene layout using a StackPane. */
    private StackPane layout = new StackPane();
    /** The Text object displaying information about the developers. */
    private Text about = new Text("Menu"); 
	
    /**
     * Constructs a Developers object.
     * Initializes the scene with a back button to return to the main menu.
     *
     * @param sceneManager The SceneManager object used for switching between scenes.
     */
	public Developers (SceneManager sceneManager){
		// Go back to the splash screen upon the click of this button
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
		aboutScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight()); // Create the Scene with the layout as the root node.
   
	}
	
	/**
     * Returns the Scene object representing the developers scene.
     *
     * @return The developers scene.
     */
	public Scene getScene() { // Getter for the scene
		return aboutScene;
	}
}
