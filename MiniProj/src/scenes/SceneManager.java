package scenes;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import mechanics.GameTimer;

/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class SceneManager {
	/** Main display passed from the Main class */
	private Stage stage;
	
	// Initialize the five different scenes
    private MainMenu mainMenu;
    private Instructions instructionsScene;
    private Game gameScene;
    private Developers developersScene;
    private Winning winningScene;
    
    // Create the window width and height, then create getters for each
 	private final static int WINDOW_WIDTH = 800;

 	public static int getWindowWidth() {
 		return WINDOW_WIDTH;
 	}

 	private final static int WINDOW_HEIGHT = 800;

 	public static int getWindowHeight() {
 		return WINDOW_HEIGHT;
 	}

    
    public SceneManager(Stage stage) {
    	// 
    	this.stage = stage;
    	mainMenu = new MainMenu(this);
    	instructionsScene = new Instructions(this);
    	gameScene = new Game(this);
    	developersScene = new Developers(this);
    	winningScene = new Winning(this);
    }
    
    public void switchToMainMenu() {
        stage.setScene(mainMenu.getScene());
    }
    
    public void switchToInstructionsScene() {
        stage.setScene(instructionsScene.getScene());
    }
    
    public void switchToDevelopersScene() {
        stage.setScene(developersScene.getScene());
    }

    public void switchToWinningScene() {
        stage.setScene(winningScene.getScene());
    }
    
    public void switchToGameScene() {
        stage.setScene(gameScene.getScene());

        GraphicsContext gc = gameScene.canvas.getGraphicsContext2D();
        
        // GameTimer is the class that extends Java's AnimationTimer. This is where the kart race takes place.
        // We pass the gc object so we can draw on the canvas
        GameTimer gameTimer = new GameTimer(gc, gameScene);
        gameTimer.start();		// This starts the gameTimer. Once started, handle() is called in every frame.
    }

}
