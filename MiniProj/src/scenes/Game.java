package scenes;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
		this.player1Name = addText(20, 35, 1.5);
		this.player2Name = addText(700, 35, 1.5);
		this.player1Score = addText(20, 50, 1.5);
		this.player2Score = addText(770, 50, 1.5);
		

		// Buffs
		this.player1HasSpeed = addText(29, 65, 1.5);
		this.player1HasInsurance = addText(33, 80, 1.5);
		this.player1HasInvincibility = addText(40, 95, 1.5);

		this.player2HasSpeed = addText(700, 65, 1.5);
		this.player2HasInsurance = addText(670, 80, 1.5);
		this.player2HasInvincibility = addText(640, 95, 1.5);

		// Debuffs
		this.player1HasCrackInTheRoad = addText(25, 125, 1.5);
		this.player1HasOilSpill = addText(27, 140, 1.5);

		this.player2HasCrackInTheRoad = addText(710, 125, 1.5);
		this.player2HasOilSpill = addText(700, 140, 1.5);

		this.player1Name.setTextAlignment(TextAlignment.RIGHT); 
		this.fpsCounter.setTextAlignment(TextAlignment.RIGHT); 
		this.player1Score.setTextAlignment(TextAlignment.RIGHT); 
		this.player1HasSpeed.setTextAlignment(TextAlignment.RIGHT); 
		this.player1HasInsurance.setTextAlignment(TextAlignment.RIGHT); 
		this.player1HasInvincibility.setTextAlignment(TextAlignment.RIGHT); 
		this.player1HasCrackInTheRoad.setTextAlignment(TextAlignment.RIGHT); 
		this.player1HasOilSpill.setTextAlignment(TextAlignment.RIGHT); 

		this.player2Name.setTextAlignment(TextAlignment.LEFT); 
		this.timeElapsed.setTextAlignment(TextAlignment.LEFT); 
		this.player2Score.setTextAlignment(TextAlignment.LEFT); 
		this.player2HasSpeed.setTextAlignment(TextAlignment.LEFT); 
		this.player2HasInsurance.setTextAlignment(TextAlignment.LEFT); 
		this.player2HasInvincibility.setTextAlignment(TextAlignment.LEFT); 
		this.player2HasCrackInTheRoad.setTextAlignment(TextAlignment.LEFT); 
		this.player2HasOilSpill.setTextAlignment(TextAlignment.LEFT); 
		this.sceneManager = sceneManager;
		


		this.root.getChildren().addAll(this.bg, this.canvas, fpsCounter, timeElapsed, player1Score, player2Score, 
		                               player1Name, player2Name, 
		                               player1HasSpeed, player1HasInsurance, player1HasInvincibility,
		                               player2HasSpeed, player2HasInsurance, player2HasInvincibility,
		                               player1HasCrackInTheRoad, player1HasOilSpill,
		                               player2HasCrackInTheRoad, player2HasOilSpill);
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