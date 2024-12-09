package scenes;

import entities.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
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
	/** The Canvas object for the non-game assets */
	protected Canvas bg;

	// Text displays in the game scene
	public Text fpsCounter;
	public Text timeElapsed;
	public Text player1Score;
	public Text player2Score;

	public SceneManager sceneManager;

	public Image player1Icon = new Image("./../assets/sprites/jeep0.png");
	public Image player2Icon = new Image("./../assets/sprites/jeep3.png");

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
		this.bg = new Canvas(SceneManager.getWindowWidth(), SceneManager.getWindowHeight());

		this.fpsCounter = addText(20, 20, 1.5);
		this.timeElapsed = addText(750, 20, 1.5);
		this.player1Score = addText(20, 750, 1.5);
		this.player2Score = addText(750, 750, 1.5);

		this.sceneManager = sceneManager;

		this.root.getChildren().addAll(this.bg, this.canvas, fpsCounter, timeElapsed, player1Score, player2Score);

	}

	/**
	 * Utility function to add text to the scene.
	 * 
	 * @param x     The x-coordinate or horizontal position of the text.
	 * @param y     The y-coordinate or vertical position of the text.
	 * @param scale The scale of the text.
	 * @return The created Text object.
	 */
	private Text addText(double x, double y, double scale) {
		Text text = new Text();
		text.setX(x);
		text.setY(y);
		text.setScaleX(scale);
		text.setScaleY(scale);
		return text;
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