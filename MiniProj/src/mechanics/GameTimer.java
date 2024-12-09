package mechanics;

import java.util.ArrayList;
import java.util.HashMap;

import effects.Debuff;
import effects.Effect;
import effects.OilBarrel;
import effects.OilSpill;
import effects.Speed;
import entities.Obstacle;
import entities.Passenger;
import entities.Player;
import entities.PowerUp;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import map.GridMap;
import map.Tile;
import scenes.Game;
import scenes.SceneManager;
import scenes.SkinSwitching;

/**
 * This class is the game loop. It extends the AnimationTimer of JavaFX and
 * updates the game each frame.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class GameTimer extends AnimationTimer {
	// Fundamental game objects
	/** The GraphicsContext used for drawing on the Canvas. */
	private GraphicsContext gc;
	/** The current Scene of the game. */
	private Scene scene;
	/** The first player object. */
	private Player player1;
	/** The second player object. */
	private Player player2;
	/** The map object. */
	public GridMap map;
	/** The Game scene object */
	private Game game;
	/** The SceneManager for switching scenes */
	private SceneManager sceneManager;
	
	// Objects in the map
	/** List of passengers in the game */
	private ArrayList<Passenger> passengers = new ArrayList<>();
	/** Spawn timer for passengers */
	private Spawn passengerSpawn;

	/** List of power-ups in the game*/
	private ArrayList<PowerUp> powerUps = new ArrayList<>();
	/** Spawn timer for power-ups */
	private Spawn powerUpSpawn;

	/** List of obstacles in the game */
	private ArrayList<Obstacle> obstacles = new ArrayList<>();
	/** Spawn timer for obstacles */
	private Spawn obstacleSpawn;
	
	// ArrayList<Effect> effects = new ArrayList<>();
	/** Map each player to their list of active effects */
	private HashMap<Player, ArrayList<Effect>> activeEffects = new HashMap<>();
	/** Map each player to their list of active debuffs */
	private HashMap<Player, ArrayList<Debuff>> activeDebuffs = new HashMap<>();

	/** Random number for determining what powerup a mystery powerup will become */
	int randomIndex = (int) (Math.random() * PowerUp.TYPES.length);
	/** The random powerup that was selected*/
	String randomType = PowerUp.TYPES[randomIndex];

	/** Indicates whether or not the game is over */
	public boolean isGameOver;

	/** Duration of the game in seconds */
	public final int GAME_DURATION_SECS = 300;

//		scatched hashset based input due to performance issues
//		private final HashSet<KeyCode> inputs = new HashSet<KeyCode>();

    /**
     * Constructs a GameTimer object. Initializes the game elements, sets up
     * the map, players, spawn timers, and key event handling
     *
     * @param gc  The GraphicsContext for drawing on the game canvas.
     * @param bg  The GraphicsContext for drawing on the background canvas.
     * @param game The Game scene object.
     */
	public GameTimer(GraphicsContext gc, GraphicsContext bg, Game game) {
		// Initialize the game elements
		this.gc = gc;
		this.scene = game.getScene();
		this.game = game;
		this.map = new GridMap(bg);

		// Initialize the two players and their mechanics
		player1 = new Player(SkinSwitching.getSelectedNameP1(),300, 20, SkinSwitching.getSelectedImageP1(), KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
		player2 = new Player(SkinSwitching.getSelectedNameP2(),400, 20, SkinSwitching.getSelectedImageP2(), KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT,
				KeyCode.RIGHT);
		keyDetection();
		
		// Initializes the level
		bg.drawImage(Graphics.background, 0, 0);
		bg.drawImage(Graphics.routes, 0, 0);
		map.drawMap(bg);
		
		// Initializes the different spawning timers
		passengerSpawn = new Spawn();
		powerUpSpawn = new Spawn();
		obstacleSpawn = new Spawn();

		// Initialize the player's effects
		activeEffects.put(player1, new ArrayList<>());
		activeEffects.put(player2, new ArrayList<>());
		activeDebuffs.put(player1, new ArrayList<>());
		activeDebuffs.put(player2, new ArrayList<>());

		// Starts the timer
		TimeElapsed.start();
		this.isGameOver = false;
	}

	// TODO fix the bug when player hits both up and down button
	// implementation of hash set
	/**
	 * Sets up key event handling for player movement. When a key is pressed, the
	 * player moves there When a key is released, that movement is stopped.
	 */
	public void keyDetection() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				
				// Player 1 movement
				if (code == player1.up || code == player1.down || code == player1.left || code == player1.right) {
					System.out.print(player1.isColliding);
					player1.simulateMove(code);
					double dx = player1.getPredictDX();
					double dy = player1.getPredictDY();
					player1.isColliding = player1.collision.detectTile(player1, code, dx, dy);
					player1.setPlayerMovement(code);
				}

				// Player 2 movement
				if (code == player2.up || code == player2.down || code == player2.left || code == player2.right) {
					System.out.print(player2.isColliding);
					player2.simulateMove(code);
					double dx = player2.getPredictDX();
					double dy = player2.getPredictDY();
					player2.isColliding = player2.collision.detectTile(player2, code, dx, dy);
					player2.setPlayerMovement(code);
				}

				// Player 1: Place oil spill
				if (code == KeyCode.F) {
					Obstacle newOilSpill = player1.placeOilSpill();
					if (newOilSpill != null) {
						obstacles.add(newOilSpill);
					}
				}

				// Player 2: Place oil spill
				if (code == KeyCode.L) {
					Obstacle newOilSpill = player2.placeOilSpill();
					if (newOilSpill != null) {
						obstacles.add(newOilSpill);
					}
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
	 * This method is called on each frame of the game loop. It clears the canvas,
	 * draws the map and players, and updates the players' positions. This is
	 * overriden from AnimationTimer.
	 *
	 * @param currentNanoTime The current time in nanoseconds.
	 */
	@Override
	public void handle(long currentNanoTime) {
		// Check first if the game is over.
		if (TimeElapsed.getElapsedSeconds() >= GAME_DURATION_SECS) {
			this.isGameOver = true;
			this.game.sceneManager.switchToWinningScene();
		}

		gc.clearRect(0, 0, 800, 800); // Clear the canvas
		
		// Render players and update their hitboxes
		player1.render(gc);
		player2.render(gc);
		player1.hitbox = player1.generateHitBox();
		player2.hitbox = player2.generateHitBox();
		player1.collisionBox = player1.generateCollisionBox();
		player2.collisionBox = player2.generateCollisionBox();
		
		// For debugging, render the hitboxes
		player1.renderBox(gc, player1.collisionBox);
		player1.renderBox(gc, player1.hitbox);
		player1.renderBox(gc, player2.collisionBox);
		player1.renderBox(gc, player2.hitbox);

		// Update player positions
		this.player1.move();
		this.player2.move();
		
		// Update FPS counter and timer
		game.fpsCounter.setText(Double.toString(FPS.getAverageFPS()));
		game.timeElapsed.setText(Double.toString(TimeElapsed.getElapsedSeconds()));
		TimeElapsed.update();

		// Passenger spawning
		if (passengerSpawn.shouldSpawn(currentNanoTime)) {
			Tile tempTile = map.getRandomTile(10);
			passengers.add(new Passenger(tempTile.x, tempTile.y, Passenger.PASSENGER));
		}

		for (Passenger passenger : passengers) {
			passenger.render(gc);
		}

		// Power-up spawning
		if (powerUpSpawn.shouldSpawn(currentNanoTime)) {
//			Tile tempTile = map.getRandomTile(9);
//			powerUps.add(new PowerUp(tempTile.x, tempTile.y, PowerUp.SPEED_BUFF, "speed"));
//			
//			Tile tempTile2 = map.getRandomTile(9);
//			powerUps.add(new PowerUp(tempTile2.x, tempTile2.y, PowerUp.OILSPILL_DEBUFF, "oilspill"));
//			
			Tile tempTile = map.getRandomTile(9);
			powerUps.add(new PowerUp(tempTile.x, tempTile.y, PowerUp.POWERUP_ICON, randomType));
		}

		for (PowerUp powerUp : powerUps) {
			powerUp.render(gc);
		}

		// Collision for powerups
		for (int i = 0; i < powerUps.size(); i++) {
			PowerUp powerUp = powerUps.get(i);
			if (player1.hitbox.intersects(powerUp.getHitbox())) {
				Effect effect = createEffectFromPowerUp(powerUp);
				applyEffect(player1, effect);
				powerUps.remove(i);
				i--;
			} else if (player2.hitbox.intersects(powerUp.getHitbox())) {
				Effect effect = createEffectFromPowerUp(powerUp);
				applyEffect(player2, effect);
				powerUps.remove(i);
				i--;
			}
		}

//		//Obstacles Spawning Code
//		if (obstacleSpawn.shouldSpawn(currentNanoTime)) {
//			Tile tempTile = map.getRandomTile(9);
//			obstacles.add(new Obstacle(tempTile.x, tempTile.y, Obstacle.OILSPILL_OBSTACLE, "oilspill_obstacle"));
//		}
//		
		for (Obstacle obstacle : obstacles) {
			obstacle.render(gc);
		}

		// Collision for obstacles
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle obstacle = obstacles.get(i);
			if (player1.hitbox.intersects(obstacle.getHitbox())) {
				Debuff debuff = createObstacleFromDebuff(obstacle);
				applyDebuff(player1, debuff);
				obstacles.remove(i);
			} else if (player2.hitbox.intersects(obstacle.getHitbox())) {
				Debuff debuff = createObstacleFromDebuff(obstacle);
				applyDebuff(player2, debuff);
				obstacles.remove(i);
			}
		}
	}

    /**
     * Applies an effect to the player for a specific duration.
     *
     * @param player The player to apply the effect to.
     * @param effect The effect to apply.
     */
	private void applyEffect(Player player, Effect effect) {
		effect.apply(player);
		activeEffects.get(player).add(effect);

		new Thread(() -> {
			try {
				Thread.sleep(effect.getDuration());
				effect.remove(player);
				activeEffects.get(player).remove(effect);
			} catch (InterruptedException e) {

			}
		}).start();
	}

    /**
     * Applies a debuff to the player for a specific duration.
     *
     * @param player The player to apply the debuff to.
     * @param debuff The debuff to apply.
     */
	private void applyDebuff(Player player, Debuff debuff) {
		debuff.apply(player);
		activeDebuffs.get(player).add(debuff);

		new Thread(() -> {
			try {
				Thread.sleep(debuff.getDuration());
				debuff.remove(player);
				activeDebuffs.get(player).remove(debuff);
			} catch (InterruptedException e) {

			}
		}).start();
	}

    /**
     * Creates an Effect object based on the type of PowerUp.
     *
     * @param powerUp The PowerUp object.
     * @return The Effect corresponding to the power-up type.
     */
	private Effect createEffectFromPowerUp(PowerUp powerUp) {
		switch (powerUp.getType()) {
		case "speed":
			return new Speed(5000, 2.0);
		case "oilbarrel":
			return new OilBarrel(5000, false) {
				public void apply(Player player) {
					player.setOilSpillDebuff(true);
				}

				public void remove(Player player) {
				}
			};
		case "oilspill_obstacle":
			//
		default:
			throw new IllegalArgumentException("Unknown power-up type");
		}
	}
	
    /**
     * Creates a Debuff object based on the type of Obstacle.
     *
     * @param obstacle The Obstacle object.
     * @return The Debuff corresponding to the obstacle type.
     */
	private Debuff createObstacleFromDebuff(Obstacle obstacle) {
		switch (obstacle.getType()) {
		case "oilspill_obstacle":
			return new OilSpill(5000, true);
		default:
			throw new IllegalArgumentException("Unknown obstacle type");
		}
	}
}