package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class SceneManager {
	private Stage stage;
    private Instructions instructionsScene;
    private Game gameScene;
    private Developers gameScene;
    private GameOver gameOverScene;
    
    public SceneManager(Stage stage) {
    	this.stage = stage;
    	splashScreen = new SplashScreen(this);
    	instructionsScene = new InstructionsScene(this);
    	aboutScene = new AboutScene(this);
    	gameScene = new Game(this);
    	gameOverScene = new GameOver(this);
        stage.setScene(splashScreen.getScene());
    }
    
    public void switchToSplashScene() {
        stage.setScene(splashScreen.getScene());
    }
    
    public void switchToInstructionsScene() {
        stage.setScene(instructionsScene.getScene());
    }
    
    public void switchToAboutScene() {
        stage.setScene(aboutScene.getScene());
    }

    public void switchToGameOverScene() {
        stage.setScene(gameOverScene.getScene());
    }
    
    public void switchToGameScene() {
        stage.setScene(gameScene.getScene());

        GraphicsContext gc = gameScene.canvas.getGraphicsContext2D();
        
        // GameTimer is the class that extends Java's AnimationTimer. This is where the kart race takes place.
        // We pass the gc object so we can draw on the canvas
        
        GameTimer gameTimer = new GameTimer(gc, this);
        gameTimer.start();		// This starts the gameTimer. Once started, handle() is called in every frame.
        
        gameTimer.startRace();	// We start our kart threads to start racing. We do this after showing the stage.
    }

}
