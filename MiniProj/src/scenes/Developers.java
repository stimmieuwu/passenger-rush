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


	/**
	 * Constructs a Developers object. Initializes the scene with a back button to
	 * return to the main menu.
	 *
	 * @param sceneManager The SceneManager object used for switching between
	 *                     scenes.
	 */
	Button back = new Button("Back");
	Button ref = new Button("ref");
	
	public Developers(SceneManager sceneManager) {
		// Go back to the splash screen upon the click of this button
		
		buttonEvents(sceneManager);
		
		Group layout = new Group();
		aboutScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight()); 																							// node.

		
		setUpButton();
		
		Canvas canvas = new Canvas(SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		GraphicsContext bg = canvas.getGraphicsContext2D();
		
		
		// drawing of background
		bg.drawImage(Graphics.DEVS, 0, 0);
		layout.getChildren().add(canvas);
		layout.getChildren().addAll(back, ref);
		setUpButton();
	}
	
	// sets up button layout and styling
	private void setUpButton() {
		this.back.getStyleClass().add("back");
		this.ref.getStyleClass().add("references");
		aboutScene.getStylesheets().add((getClass()).getResource("skin.css").toExternalForm());
		
		this.back.setLayoutX(25);
		this.back.setLayoutY(667);
		
		this.ref.setLayoutX(155);
		this.ref.setLayoutY(520);
		
	}
	
	private void buttonEvents(SceneManager sceneManager){
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneManager.switchToMainMenu();
			}
		});
		
		ref.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					openLink();
				} catch (URISyntaxException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private void openLink() throws URISyntaxException, IOException{
		Desktop.getDesktop().browse(new URI("https://github.com/stimmieuwu/passenger-rush"));

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
