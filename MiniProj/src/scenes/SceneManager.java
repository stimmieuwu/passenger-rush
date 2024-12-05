package scenes;

import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import mechanics.GameTimer;

/**
 * This class manages the different scenes in the Passenger Rush game.
 * It handles switching between scenes, such as the main menu, instructions, game scene, 
 * developer information, and the winning/game over scene.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class SceneManager {
    /** The main Stage object for displaying scenes. */
    private Stage stage;

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

    /** The width of the game window. */
    private final static int WINDOW_WIDTH = 800;
    /** The height of the game window. */
    private final static int WINDOW_HEIGHT = 800;
    /** The spacing of buttons*/
    protected final static int BUTTON_SPACING = 10;
    

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
     * Constructs a SceneManager object.
     * Initializes all the scenes and associates them with the provided Stage.
     *
     * @param stage The main Stage for displaying scenes.
     */
    public SceneManager(Stage stage) {
        this.stage = stage;
        mainMenu = new MainMenu(this);
        instructionsScene = new Instructions(this);
        gameScene = new Game(this);
        developersScene = new Developers(this);
        winningScene = new Winning(this);
        skinSwitchingScene = new SkinSwitching(this);
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
        stage.setScene(winningScene.getScene());
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
        stage.setScene(gameScene.getScene());

        GraphicsContext gc = gameScene.canvas.getGraphicsContext2D();
        GraphicsContext bg = gameScene.bg.getGraphicsContext2D();
        // Start the game loop using GameTimer
        GameTimer gameTimer = new GameTimer(gc, bg, gameScene); 
        gameTimer.start();
    }
}