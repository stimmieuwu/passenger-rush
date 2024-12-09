package scenes;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import mechanics.Graphics;

/**
 * This class represents the "Instructions" scene in the application. It
 * displays instructions for the game and provides a button to return to the
 * main menu.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Instructions {

	/** The Scene object representing the instructions scene. */
	private Scene instructionsScene;
	Button back = new Button("Back");

	/**
	 * Constructs an Instructions object. Initializes the scene with a back button
	 * to return to the main menu.
	 *
	 * @param sceneManager The SceneManager object used for switching between
	 *                     scenes.
	 */
	public Instructions(SceneManager sceneManager) {
		// Go back to the main menu upon the click of this button
		
		buttonEvents(sceneManager);
		
		Group layout = new Group();
		instructionsScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		
		
		setUpButton();
		
		Canvas canvas = new Canvas(SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		GraphicsContext bg = canvas.getGraphicsContext2D();
		
		
		// drawing of background
		bg.drawImage(Graphics.HOW2, 0, 0);
		layout.getChildren().add(canvas);
		layout.getChildren().add(back);

		
	}
	
	// sets up button layout and styling
	private void setUpButton() {
		this.back.getStyleClass().add("back");
		instructionsScene.getStylesheets().add((getClass()).getResource("skin.css").toExternalForm());
		this.back.setLayoutX(25);
		this.back.setLayoutY(667);
		
	}
	
	private void buttonEvents(SceneManager sceneManager){
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneManager.switchToMainMenu();
			}
		});
	}
	

	/**
	 * Returns the Scene object representing the instructions scene.
	 *
	 * @return The instructions scene.
	 */
	public Scene getScene() {
		return instructionsScene;
	}
}