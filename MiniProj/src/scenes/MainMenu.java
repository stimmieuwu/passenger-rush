package scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * 
 * This is the splash screen or the "main menu" of the Passenger Rush game.
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @created_date 2024-11-25 12:13 AM
 *
 */
public class MainMenu {
	private Scene splashScene; // Initialize a scene object
	
	// Create the objects to be displayed
	//private Image gameLogo = new Image("path");
	Text heading = new Text("Passenger Rush");
	
	public MainMenu(SceneManager sceneManager) {
		// Create buttons to navigate around the game.
		Button instructions = new Button("Instructions");
		Button about = new Button("About the Developer");
		Button start = new Button("Start/Play");
		Button gameOver = new Button("Game Over");
		
		// Instructions button
		instructions.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        sceneManager.switchToInstructionsScene();
		    }
		});

		// About the developer button
		about.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        sceneManager.switchToDevelopersScene();
		    }
		});

		// Start game button
		start.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        sceneManager.switchToGameScene();
		    }
		});
		
		// Button to see the game over screen
		gameOver.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        sceneManager.switchToWinningScene();
		    }
		});

		// We then create a VBox and a scene with the VBox as the root
		VBox layout = new VBox(30);
		splashScene = new Scene(layout, 568, 568); 

		layout.setAlignment(Pos.CENTER); // Center the content of the VBox
		
		// Add the heading text and all the buttons.
		layout.getChildren().add(heading);
		layout.getChildren().addAll(instructions, about, start, gameOver); 
		//layout.getChildren().add(new ImageView(gameLogo));
	}
	
	public Scene getScene() { // Getter for the scene
		return splashScene;
	}
}
