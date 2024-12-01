package scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * This class represents the main menu (splash screen) of the Passenger Rush game.
 * It provides buttons to navigate to different sections of the game, such as instructions, 
 * about the developer, starting the game, and viewing the game over screen.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-11-25 12:13 AM
 */
public class MainMenu {

    /** The Scene object representing the main menu. */
    private Scene splashScene; 
    /** The heading text displayed on the main menu. */
    Text heading = new Text("Passenger Rush"); 

    /**
     * Constructs a MainMenu object.
     * Initializes the main menu scene with buttons for navigation and sets up their actions.
     *
     * @param sceneManager The SceneManager object used for switching between scenes.
     */
    public MainMenu(SceneManager sceneManager) {
        // Create buttons for navigation
        Button instructions = new Button("Instructions");
        Button about = new Button("About the Developer");
        Button start = new Button("Start/Play");
        Button skinSwitching = new Button("Change Skins");

        // Instructions button action
        instructions.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneManager.switchToInstructionsScene();
            }
        });

        // About the developer button action
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneManager.switchToDevelopersScene();
            }
        });

        // Start game button action
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneManager.switchToGameScene();
            }
        });
        
        // Start game button action
        skinSwitching.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneManager.switchToSkinSwitchingScene();
            }
        });

        // Create the layout and add elements
        VBox layout = new VBox(SceneManager.BUTTON_SPACING);
        splashScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());

        layout.setAlignment(Pos.CENTER); 

        layout.getChildren().add(heading);
        layout.getChildren().addAll(instructions, about, start, skinSwitching);
    }

    /**
     * Getter for the main menu scene
     *
     * @return The main menu scene.
     */
    public Scene getScene() { 
        return splashScene;
    }
}