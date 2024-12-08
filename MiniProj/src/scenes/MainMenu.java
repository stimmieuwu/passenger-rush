package scenes;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Graphics;

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
    private Button instructions = new Button("Instructions");
    private Button about = new Button("About the Developer");
    private Button start = new Button("Start/Play");
    private Button exit = new Button("Exit");
    private Canvas canvas;
    
    
    /**
     * Constructs a MainMenu object.
     * Initializes the main menu scene with buttons for navigation and sets up their actions.
     *
     * @param sceneManager The SceneManager object used for switching between scenes.
     */
    public MainMenu(SceneManager sceneManager, Stage stage) {
        // Create buttons for navigation
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
                sceneManager.switchToSkinSwitchingScene();
            }
        });
        // Exit game button action
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
    			stage.close();
    		}
        });
        
        
        // Create the layout and add elements
        Group layout = new Group();
        splashScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
        setButtonLayout();

        // drawing of images
        this.canvas = new Canvas( SceneManager.getWindowWidth(), SceneManager.getWindowHeight() );
        layout.getChildren().add(canvas);
        
        
        
        
        // for css
        splashScene.getStylesheets()
		.add((getClass())
		.getResource("application.css")
		.toExternalForm());
        
        Graphics.generateMenuGraphics();
        renderAnimation(layout);
        assignStyleSheets();
        layout.getChildren().addAll(start,instructions, about,exit);
        
    }
    
    
    private void assignStyleSheets() {
    	this.instructions.getStyleClass().add("instructions");
		this.about.getStyleClass().add("about");
		this.start.getStyleClass().add("start");
		this.exit.getStyleClass().add("exit");
		
    }
    
    // hard coded button layout
    private void setButtonLayout() {
    	
    	this.about.setLayoutX(56);
    	this.about.setLayoutY(322);
		
    	this.start.setLayoutX(280);
    	this.start.setLayoutY(298);
		
     	this.instructions.setLayoutX(526);
    	this.instructions.setLayoutY(308);
		
    	this.exit.setLayoutX(310);
    	this.exit.setLayoutY(447);
    }
    
    
	protected void renderAnimation(Group layout) {
		// inherits constant
		GraphicsContext gc = canvas.getGraphicsContext2D();
        final long startNanoTime = System.nanoTime();

		new AnimationTimer()
		{
			public void handle(long currentNanoTime)
	
			{
				
		        gc.clearRect(0, 0, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		        int time = (int) ((currentNanoTime - startNanoTime) / 100000000);
		        // menu animation
		        gc.drawImage(Graphics.background, 0, 0);
		        gc.drawImage(Graphics.menuAnimation[time%Graphics.FRAMES], 0, 0);
			}
		}.start();
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