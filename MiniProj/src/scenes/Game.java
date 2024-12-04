package scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Text;

/**
 * This class represents the main game scene where the gameplay takes place. It
 * sets up the basic structure of the game window, including the scene, root
 * node, canvas, where all elements will be placed.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Game {
	/** The Scene object representing the game scene. */
	private Scene gameScene;
	/** The root node of the scene graph. */
	protected Group root;
	/** The Canvas object where game elements are drawn. */
	protected Canvas canvas;

    public Text fpsCounter;
    public Text timeElapsed;
    
    public Text player1Score;
    public Text player2Score;

	/**
	 * Constructs a Game object. Initializes the scene, root node, and canvas with
	 * appropriate dimensions. Adds the canvas to the scene graph.
	 *
	 * @param sceneManager The SceneManager used to manage scene transitions
	 */
	public Game(SceneManager sceneManager) {
		this.root = new Group();
		this.gameScene = new Scene(root);
		this.canvas = new Canvas(SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		
		this.fpsCounter = new Text();
		this.fpsCounter.setX(20);
		this.fpsCounter.setY(20);
		this.fpsCounter.setScaleX(1.5);
		this.fpsCounter.setScaleY(1.5);
		
		this.timeElapsed = new Text();
		this.timeElapsed.setX(750);
		this.timeElapsed.setY(20);
		this.timeElapsed.setScaleX(1.5);
		this.timeElapsed.setScaleY(1.5);
		
		this.player1Score = new Text();
		this.player1Score.setX(20);
		this.player1Score.setY(750);
		this.player1Score.setScaleX(1.5);
		this.player1Score.setScaleY(1.5);
		
		this.player2Score = new Text();
		this.player2Score.setX(750);
		this.player2Score.setY(750);
		this.player2Score.setScaleX(1.5);
		this.player2Score.setScaleY(1.5);
		
		this.root.getChildren().addAll(this.canvas, fpsCounter, timeElapsed, player1Score, player2Score);
		
		
	}

	/**
	 * Getter for the game scene
	 *
	 * @return The game scene.
	 */
	public Scene getScene() {
		return gameScene;
	}
}