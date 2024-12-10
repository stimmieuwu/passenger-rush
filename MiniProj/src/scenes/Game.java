package scenes;

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
	public Text player1Name;
	public Text player2Name;
	
	// Buffs - replace with image if may time 
	public Text player1HasSpeed;
	public Text player1HasInsurance;
	public Text player1HasInvincibility;

	public Text player2HasSpeed;
	public Text player2HasInsurance;
	public Text player2HasInvincibility;

	// Debuffs - replace with image if may time
	public Text player1HasCrackInTheRoad;
	public Text player1HasOilSpill;

	public Text player2HasCrackInTheRoad;
	public Text player2HasOilSpill;

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
		this.timeElapsed = addText(760, 20, 1.5);
		this.player1Name = addText(25, 45, 1.5);
		this.player2Name = addText(680, 45, 1.5);
		this.player1Score = addText(20, 70, 1.5);
		this.player2Score = addText(750, 70, 1.5);
		

		// Buffs
		this.player1HasSpeed = addText(25, 95, 1.5);
		this.player1HasInsurance = addText(30, 120, 1.5);
		this.player1HasInvincibility = addText(32, 145, 1.5);

		this.player2HasSpeed = addText(680, 95, 1.5);
		this.player2HasInsurance = addText(680, 120, 1.5);
		this.player2HasInvincibility = addText(680, 145, 1.5);

		// Debuffs
		this.player1HasCrackInTheRoad = addText(25, 170, 1.5);
		this.player1HasOilSpill = addText(25, 195, 1.5);

		this.player2HasCrackInTheRoad = addText(675, 170, 1.5);
		this.player2HasOilSpill = addText(680, 195, 1.5);
		this.sceneManager = sceneManager;


		this.root.getChildren().addAll(this.bg, this.canvas, fpsCounter, timeElapsed, player1Score, player2Score, 
		                               player1Name, player2Name, 
		                               player1HasSpeed, player1HasInsurance, player1HasInvincibility,
		                               player2HasSpeed, player2HasInsurance, player2HasInvincibility,
		                               player1HasCrackInTheRoad, player1HasOilSpill,
		                               player2HasCrackInTheRoad, player2HasOilSpill);
		gameScene.getStylesheets().add((getClass()).getResource("skin.css").toExternalForm());
		assignStyleSheets();
	}
	
	
	private void assignStyleSheets() {
		this.fpsCounter.getStyleClass().add("details");
		this.timeElapsed.getStyleClass().add("details");
		this.player1Name.getStyleClass().add("details");
		this.player2Name.getStyleClass().add("details");
		this.player1Score.getStyleClass().add("details");
		this.player2Score.getStyleClass().add("details");
		

		// Buffs
		this.player1HasSpeed.getStyleClass().add("details");
		this.player1HasInsurance.getStyleClass().add("details");
		this.player1HasInvincibility.getStyleClass().add("details");

		this.player2HasSpeed.getStyleClass().add("details");
		this.player2HasInsurance.getStyleClass().add("details");
		this.player2HasInvincibility.getStyleClass().add("details");

		// Debuffs
		this.player1HasCrackInTheRoad.getStyleClass().add("details");
		this.player1HasOilSpill.getStyleClass().add("details");

		this.player2HasCrackInTheRoad.getStyleClass().add("details");
		this.player2HasOilSpill.getStyleClass().add("details");
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