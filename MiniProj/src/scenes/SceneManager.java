package scenes;

import entities.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import mechanics.Audio;
import mechanics.GameTimer;

/**
 * This class manages the different scenes in the Passenger Rush game. It
 * handles switching between scenes, such as the main menu, instructions, game
 * scene, developer information, and the winning/game over scene.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class SceneManager {
	/** The main Stage object for displaying scenes. */
	private Stage stage;

	// Initialize all the scenes
	/** The main menu scene. */
	private MainMenu mainMenu;
	/** The instructions scene. */
	private Instructions instructionsScene;
	/** The main game scene. */
	private Game gameScene;
	/** The developers scene. */
	private Developers developersScene;
	/** The winning/game over scene. */
	private Winning winningScene;
	/** The skin switching scene. */
	private SkinSwitching skinSwitchingScene;

	// Layout variables
	/** The width of the game window. */
	private final static int WINDOW_WIDTH = 800;
	/** The height of the game window. */
	private final static int WINDOW_HEIGHT = 800;
	/** The spacing of buttons */
	protected final static int BUTTON_SPACING = 10;

	// Audio variables
	/** Music for the lobby, i.e. the Main Menu, About, and Instructions */
	private Audio lobbyMusic;
	/** Music for the game */
	private Audio bgMusic;

	private GameTimer gameTimer;

	/**
	 * Returns the width of the game window.
	 *
	 * @return The window width.
	 */
	public static int getWindowWidth() {
		return WINDOW_WIDTH;
	}

	/**
	 * Returns the height of the game window.
	 *
	 * @return The window height.
	 */
	public static int getWindowHeight() {
		return WINDOW_HEIGHT;
	}

	/**
	 * Constructs a SceneManager object. Initializes all the scenes and associates
	 * them with the provided Stage.
	 *
	 * @param stage The main Stage for displaying scenes.
	 */
	public SceneManager(Stage stage) {
		this.stage = stage;
		mainMenu = new MainMenu(this, stage);
		instructionsScene = new Instructions(this);
		gameScene = new Game(this);
		developersScene = new Developers(this);
		winningScene = new Winning(this);
		skinSwitchingScene = new SkinSwitching(this);
		this.lobbyMusic = new Audio("assets/music/lobby_music.mp3", 0.4f, false);
	}

	/**
	 * Switches the current scene to the main menu.
	 */
	public void switchToMainMenu() {
		stage.setScene(mainMenu.getScene());

	}

	/**
	 * Switches the current scene to the instructions scene.
	 */
	public void switchToInstructionsScene() {
		stage.setScene(instructionsScene.getScene());
	}

	/**
	 * Switches the current scene to the developers scene.
	 */
	public void switchToDevelopersScene() {
		stage.setScene(developersScene.getScene());
	}

	/**
	 * Switches the current scene to the winning/game over scene.
	 */
	public void switchToWinningScene() {
		winningScene.displayResult();
		stage.setScene(winningScene.getScene());
		bgMusic.stopMusic();
		gameTimer.resetGame();
		gameTimer.stop();
	}

	/**
	 * Switches the current scene to the winning/game over scene.
	 */
	public void switchToSkinSwitchingScene() {
		stage.setScene(skinSwitchingScene.getScene());
	}

	/**
	 * Switches the current scene to the game scene and starts the game loop.
	 * Initializes the GameTimer and starts the animation.
	 */
	public void switchToGameScene() {
		GraphicsContext gc = gameScene.canvas.getGraphicsContext2D();
		GraphicsContext bg = gameScene.bg.getGraphicsContext2D();
		
		stage.setScene(gameScene.getScene());
		// Start the game loop using GameTimer
		
		this.gameTimer = new GameTimer(gc, bg, gameScene);
		gameTimer.start();
		

		
		lobbyMusic.stopMusic();
		this.bgMusic = new Audio("./assets/music/game_music.mp3", 0.4f, false);
	}
}