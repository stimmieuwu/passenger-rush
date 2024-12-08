package mechanics;

import java.util.ArrayList;
import scenes.SkinSwitching;
import entities.Passenger;
import entities.Player;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import map.GridMap;
import map.Tile;
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
    public GridMap map; 
    private Game game;
    /** Timer for spawning */
    ArrayList<Passenger> passengers = new ArrayList<>();
    private Spawn passengerSpawn;
    private Spawn powerUpSpawn;
    
   
    // scatched hashset based input due to performance issues
//    private final HashSet<KeyCode> inputs = new HashSet<KeyCode>();

	public GameTimer(GraphicsContext gc, GraphicsContext bg, Game game) {
		this.gc = gc;
		this.scene = game.getScene();
		this.game = game;
		
		this.map = new GridMap(bg);
		
		player1 = new Player(300, 20, SkinSwitching.selectedImageP1, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
		player2 = new Player(400, 20, SkinSwitching.selectedImageP2, KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
		keyDetection();
		bg.drawImage(Graphics.background, 0, 0);
		map.drawMap(bg);
		bg.drawImage(Graphics.routes, 0, 0);
		passengerSpawn = new Spawn();
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
				if(code == player1.up || code == player1.down || code == player1.left || code == player1.right) {
					System.out.print(player1.isColliding);
					player1.simulateMove(code);
					double dx = player1.getPredictDX();
					double dy = player1.getPredictDY();
	                player1.isColliding = player1.collision.detectTile(player1, code, dx, dy);
	                player1.setPlayerMovement(code);
				}
				
				if(code == player2.up || code == player2.down || code == player2.left || code == player2.right) {
					System.out.print(player2.isColliding);
					player2.simulateMove(code);
					double dx = player2.getPredictDX();
					double dy = player2.getPredictDY();
	                player2.isColliding = player2.collision.detectTile(player2, code, dx, dy);
	                player2.setPlayerMovement(code);
				}

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
	
		player1.hitbox = player1.generateHitBox();
		player2.hitbox = player2.generateHitBox();
		player1.collisionBox = player1.generateCollisionBox();
		player2.collisionBox = player2.generateCollisionBox();
		player1.renderBox(gc, player1.collisionBox);
		player1.renderBox(gc, player1.hitbox);
		player1.renderBox(gc, player2.collisionBox);
		player1.renderBox(gc, player2.hitbox);
		
		this.player1.move();
		this.player2.move();
		game.fpsCounter.setText(Double.toString(FPS.getAverageFPS()));
		game.timeElapsed.setText(Double.toString(TimeElapsed.getElapsedSeconds()));
		if(passengerSpawn.shouldSpawn(currentNanoTime)) {
			Tile tempTile = map.getRandomTile(10);
			passengers.add(new Passenger(tempTile.x, tempTile.y, Passenger.PASSENGER));
		}
		
	
		
		for(Passenger passenger : passengers) {
			passenger.render(gc);
		}
		
		player1.render(gc);
		player2.render(gc);

	}

}