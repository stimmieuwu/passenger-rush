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
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class GameTimer extends AnimationTimer {
	private GraphicsContext gc;
	private Scene scene;
	private Player player1;
	private Player player2;
	private CollisionDetector collision;
	GridMap map = new GridMap(gc);

	public GameTimer(GraphicsContext gc, Game game) {

		this.gc = gc;
		this.scene = game.getScene();
		this.collision = new CollisionDetector();
		this.map = new GridMap(gc);
		player1 = new Player(100, 100, Player.PLAYER_IMAGE,KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
		player2 = new Player(100, 200, Player.PLAYER_IMAGE,KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
		keydetection();

	}
	
	//TODO fix the bug when player hits both up and down button
	// implementation of hash set
	public void keydetection() {
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
	

	public void handle(long currentNanoTime) {
		gc.clearRect(0, 0, 800, 800);

		map.drawMap(gc);
		player1.render(gc);
		player2.render(gc);

		this.player1.move();
		this.player2.move();
		
	}


}
