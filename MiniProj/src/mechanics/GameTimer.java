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

/**
 * This class is the game loop. It extends the AnimationTimer of JavaFX and
 * updates the game each frame.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class GameTimer extends AnimationTimer { /** The GraphicsContext used for drawing on the Canvas. */
    private GraphicsContext gc;
    /** The current Scene of the game. */
    private Scene scene;
    /** The first player object. */
    private Player player1;
    /** The second player object. */
    private Player player2;
    /** The map object. */
    private GridMap map; 

	public GameTimer(GraphicsContext gc, Game game) {
		this.gc = gc;
		this.scene = game.getScene();
//		this.collision = new CollisionDetector();
		this.map = new GridMap(gc);
		
		player1 = new Player(300, 20, Player.SKIN_1, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
		player2 = new Player(400, 20, Player.SKIN_2, KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
		keyDetection();

	}

	// TODO fix the bug when player hits both up and down button
	// implementation of hash set
	/**
     * Sets up key event handling for player movement.
     * When a key is pressed, the player moves there
     * When a key is released, that movement is stopped.
     */
	public void keyDetection() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				player1.setPlayerMovement(code);
				player2.setPlayerMovement(code);
			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				player1.stopPlayerMovement(code);
				player2.stopPlayerMovement(code);
			}
		});
	}
	
	/**
     * This method is called on each frame of the game loop.
     * It clears the canvas, draws the map and players, and updates the players' positions.
     * This is overriden from AnimationTimer.
     *
     * @param currentNanoTime The current time in nanoseconds.
     */
    @Override
	public void handle(long currentNanoTime) {
		gc.clearRect(0, 0, 800, 800);

		map.drawMap(gc);
		player1.render(gc);
		player2.render(gc);

		this.player1.move();
		this.player2.move();

	}

}