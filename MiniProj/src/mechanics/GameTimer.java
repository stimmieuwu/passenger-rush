package mechanics;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import entities.Player;
import map.GridMap;
import scenes.Game;
import scenes.SceneManager;

/**
 * This class is responsible for updating and rendering the game elements on the
 * canvas. It handles player movement based on keyboard input and draws the game
 * on the screen.
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class GameTimer extends AnimationTimer {

	/** The GraphicsContext draws elements on the Canvas object. */
	private GraphicsContext gc;
	/** The Scene containing the elements of the game */
	private Scene scene;

	/** The first player object */
	private Player player1;
	/** The second player object */
	private Player player2;

	/** GridMap object is responsible for displaying tiled map to the players. */
	protected GridMap map;

	private static int moveSpeed;

	/**
	 * Constructs a GameTimer object. Initializes the GraphicsContext, Scene, map,
	 * player objects, and ___ This also sets up keyboard event handlers for player
	 * movement.
	 *
	 * @param gc   The GraphicsContext for drawing on the canvas.
	 * @param game The Game object containing the Scene.
	 */
	public GameTimer(GraphicsContext gc, Game game) {
		this.gc = gc;
		this.scene = game.getScene();
		this.map = new GridMap(gc);

		player1 = new Player(300, 20, Player.SKIN_1);
		player2 = new Player(400, 20, Player.SKIN_1);
		
		moveSpeed = 10;

		this.handleKeyPressEvent();
	}

	/**
	 * This method overrides the handle method of AnimationTimer. This method is
	 * called every frame to update and render the game elements. Every time, it
	 * clears the canvas, draws the map, renders the players, and updates their
	 * positions.
	 *
	 * @param currentNanoTime The current time in nanoseconds.
	 */
	@Override
	public void handle(long currentNanoTime) {
		gc.clearRect(0, 0, SceneManager.getWindowHeight(), SceneManager.getWindowWidth());

		map.drawMap(gc);

		player1.render(gc);
		player2.render(gc);

		this.player1.move();
		this.player2.move();
	}

	/**
	 * This method sets up keyboard event handlers for player movement using WASD
	 * and arrow keys. When a key is pressed, the corresponding player moves in the
	 * specified direction. When a key is released, the corresponding player stops
	 * moving in that direction.
	 */
	private void handleKeyPressEvent() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				movePlayer(code);
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				stopPlayer(code);
			}
		});
	}

	/**
	 * Sets the movement direction of the players based on the pressed key and the
	 * player's speed multiplier
	 *
	 * @param key The KeyCode of the pressed key.
	 */
	private void movePlayer(KeyCode key) {
		// Player 1
		if (key == KeyCode.W)
			this.player1.setDY(-moveSpeed);
		if (key == KeyCode.S)
			this.player1.setDY(moveSpeed);
		if (key == KeyCode.A)
			this.player1.setDX(-moveSpeed);
		if (key == KeyCode.D)
			this.player1.setDX(moveSpeed);

		// Player 2
		if (key == KeyCode.UP)
			this.player2.setDY(-moveSpeed);
		if (key == KeyCode.DOWN)
			this.player2.setDY(moveSpeed);
		if (key == KeyCode.LEFT)
			this.player2.setDX(-moveSpeed);
		if (key == KeyCode.RIGHT)
			this.player2.setDX(moveSpeed);
	}

	/**
	 * Stops the movement of the players in the direction corresponding to the
	 * released key.
	 *
	 * @param key The KeyCode of the released key.
	 */
	private void stopPlayer(KeyCode key) {
		// Player 1
		if (key == KeyCode.W)
			this.player1.setDY(0);
		if (key == KeyCode.S)
			this.player1.setDY(0);
		if (key == KeyCode.A)
			this.player1.setDX(0);
		if (key == KeyCode.D)
			this.player1.setDX(0);

		// Player 2
		if (key == KeyCode.UP)
			this.player2.setDY(0);
		if (key == KeyCode.DOWN)
			this.player2.setDY(0);
		if (key == KeyCode.LEFT)
			this.player2.setDX(0);
		if (key == KeyCode.RIGHT)
			this.player2.setDX(0);
	}
}
