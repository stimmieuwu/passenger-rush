package mechanics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import effects.Crack;
import effects.CrackInTheRoad;
import effects.Debuff;
import effects.Effect;
import effects.Insurance;
import effects.Missile;
import effects.MissileObstacle;
import effects.OilBarrel;
import effects.OilSpill;
import effects.Speed;
import entities.Obstacle;
import entities.Passenger;
import entities.Player;
import entities.PowerUp;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
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
	public Player player1;
	/** The second player object. */
	public Player player2;
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

	/** List of power-ups in the game */
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

	private Missile missile = null;
	public static boolean isTied = false;
	public static int winningScore = 0;
	public static String winningName = "";

	/** Random number for determining what powerup a mystery powerup will become */
	int randomIndex = (int) (Math.random() * PowerUp.TYPES.length);
	/** The random powerup that was selected */
	String randomType = PowerUp.TYPES[randomIndex];

	/** Indicates whether or not the game is over */
	public static boolean isGameOver = false;

	/** Duration of the game in seconds */
	public final int GAME_DURATION_SECS = 300;

	public Rectangle2D junction = new Rectangle2D(200, 20, 300, 60);
	private boolean isUnloading1 = false; // Flag to track unloading status for player 1
	private boolean isUnloading2 = false; // Flag to track unloading status for player 2
	private long unloadStartTime1 = 0; // To store the start time of unloading for player 1
	private long unloadStartTime2 = 0;

	public static final int LOADING_TIME_SECS = 3;
	public static final int UNLOADING_TIME_SECS = 8;

	private HashMap<Passenger, Long> passengerLoadingTimes = new HashMap<>();

//		scatched hashset based input due to performance issues
//		private final HashSet<KeyCode> inputs = new HashSet<KeyCode>();

	/**
	 * Constructs a GameTimer object. Initializes the game elements, sets up the
	 * map, players, spawn timers, and key event handling
	 *
	 * @param gc   The GraphicsContext for drawing on the game canvas.
	 * @param bg   The GraphicsContext for drawing on the background canvas.
	 * @param game The Game scene object.
	 */
	public GameTimer(GraphicsContext gc, GraphicsContext bg, Game game) {
		// Initialize the game elements
		GameTimer.isGameOver = false;
		this.gc = gc;
		this.scene = game.getScene();
		this.game = game;
		this.map = new GridMap(bg);

		// Initialize the two players and their mechanics
		player1 = new Player(SkinSwitching.getSelectedNameP1(), 305, 25, SkinSwitching.getSelectedImageP1(), KeyCode.W,
				KeyCode.S, KeyCode.A, KeyCode.D);
		player2 = new Player(SkinSwitching.getSelectedNameP2(), 400, 25, SkinSwitching.getSelectedImageP2(), KeyCode.UP,
				KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
		keyDetection();

		// Initializes the level
		bg.drawImage(Graphics.background, 0, 0);
		map.drawMap(bg);
		bg.drawImage(Graphics.routes, 0, 0);
		

		// Initializes the different spawning timers
		passengerSpawn = new Spawn(6, 9);
		powerUpSpawn = new Spawn(20, 30);
		obstacleSpawn = new Spawn(20, 40);

		// Initialize the player's effects
		activeEffects.put(player1, new ArrayList<>());
		activeEffects.put(player2, new ArrayList<>());
		activeDebuffs.put(player1, new ArrayList<>());
		activeDebuffs.put(player2, new ArrayList<>());

		// Starts the timer
		TimeElapsed.start();
		
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
//				player1.simulateMove(code);
//				player2.simulateMove(code);
//				double dx1 = player1.getPredictDX();
//				double dy1 = player1.getPredictDY();
//				double dx2 = player1.getPredictDX();
//				double dy2 = player1.getPredictDY();
//
//				// Perform collision detection
//				double[] overlap1 = player1.collision.detectTile(player1, code, dx1, dy1);
//				double[] overlap2 = player1.collision.detectTile(player1, code, dx1, dy1);
//
//				if (overlap1 != null) { // Collision detected
//					if (code == player1.up) {
//						player1.isCollidingUp = true;
//					} else if (code == player1.down) {
//						player1.isCollidingDown = true;
//					} else if (code == player1.left) {
//						player1.isCollidingLeft = true;
//					} else if (code == player1.right) {
//						player1.isCollidingRight = true;
//					}
//					player1.pushBack(overlap1[0], overlap1[1]);
//
//				} else { // No collision
//					if (code == player1.up) {
//						player1.isCollidingUp = false;
//					} else if (code == player1.down) {
//						player1.isCollidingDown = false;
//					} else if (code == player1.left) {
//						player1.isCollidingLeft = false;
//					} else if (code == player1.right) {
//						player1.isCollidingRight = false;
//					}

				player1.setPlayerMovement(code); // Start movement
//				}
//
//				if (overlap2 != null) { // Collision detected
//					if (code == player2.up) {
//						player2.isCollidingUp = true;
//					} else if (code == player2.down) {
//						player2.isCollidingDown = true;
//					} else if (code == player2.left) {
//						player2.isCollidingLeft = true;
//					} else if (code == player2.right) {
//						player2.isCollidingRight = true;
//					}
//					player1.pushBack(overlap2[0], overlap2[1]);
//
//				} else { // No collision
//					if (code == player2.up) {
//						player2.isCollidingUp = false;
//					} else if (code == player2.down) {
//						player2.isCollidingDown = false;
//					} else if (code == player2.left) {
//						player2.isCollidingLeft = false;
//					} else if (code == player2.right) {
//						player2.isCollidingRight = false;
//					}

				player2.setPlayerMovement(code); // Start movement
//				}

				// Player 1: Place oil spill
				if (code == KeyCode.F) {
					Obstacle newOilSpill = player1.placeOilSpill();
					if (newOilSpill != null) {
						obstacles.add(newOilSpill);
					}
				}

				if (code == KeyCode.E) {
					Obstacle newMissile = player1.launchMissile();
					if (newMissile != null) {
						obstacles.add(newMissile);
					}
				}
				// Player 2: Place oil spill
				if (code == KeyCode.L) {
					Obstacle newOilSpill = player2.placeOilSpill();
					if (newOilSpill != null) {
						obstacles.add(newOilSpill);
					}
				}

				if (code == KeyCode.O) {
					Obstacle newMissile = player2.launchMissile();
					if (newMissile != null) {
						obstacles.add(newMissile);
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
		TimeElapsed.update();
		// Check first if the game is over.
		if (TimeElapsed.getElapsedSeconds() >= GAME_DURATION_SECS) {

			if (player1.score > player2.score) {
				GameTimer.winningScore = player1.score;
				GameTimer.winningName = player1.name;
			}
			if (player2.score > player1.score) {
				GameTimer.winningScore = player2.score;
				GameTimer.winningName = player2.name;
			} else {
				GameTimer.isTied = true;
			}
			GameTimer.isGameOver = true;
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

		HashSet<KeyCode> player1Keys = player1.getCurrentKeyPressed();

		for (KeyCode code : player1Keys) {
			player1.simulateMove(code);
			double dx1 = player1.getPredictDX();
			double dy1 = player1.getPredictDY();

			double[] overlap1 = player1.collision.detectTile(player1, code, dx1, dy1);
			if (overlap1 != null) {
				player1.stopPlayerMovement(code);
				break;
			}

		}

		HashSet<KeyCode> player2Keys = player2.getCurrentKeyPressed();

		for (KeyCode code : player2Keys) {
			player2.simulateMove(code);
			double dx1 = player2.getPredictDX();
			double dy1 = player2.getPredictDY();

			double[] overlap1 = player2.collision.detectTile(player2, code, dx1, dy1);
			if (overlap1 != null) {
				player2.stopPlayerMovement(code);
				break;
			}

		}

		game.player1Name.setText(player1.name);
		game.player2Name.setText(player2.name);

		game.player1Score.setText(Integer.toString(player1.score));
		game.player2Score.setText(Integer.toString(player2.score));

		// Update the Text nodes based on player booleans
		game.player1HasSpeed.setText("Speed: " + player1.hasSpeedBuff);
		game.player1HasInsurance.setText("Insurance: " + player1.hasInsurance);
		game.player1HasInvincibility.setText("Invincibility: " + player1.hasInvincibility());
		
		
		
		game.player2HasSpeed.setText("Speed: " + player2.hasSpeedBuff);
		game.player2HasInsurance.setText("Insurance: " + player2.hasInsurance);
		game.player2HasInvincibility.setText("Invincibility: " + player2.hasInvincibility());

		game.player1HasCrackInTheRoad.setText("Crack: " + player1.hasCrack);
		game.player1HasOilSpill.setText("Oil Spill: " + player1.hasOilSpillDebuff());

		game.player2HasCrackInTheRoad.setText("Crack: " + player2.hasCrack);
		game.player2HasOilSpill.setText("Oil Spill: " + player2.hasOilSpillDebuff());
		
		

		// Update player positions
		this.player1.move();
		this.player2.move();

		// Update FPS counter and timer
		game.fpsCounter.setText("FPS: " + Integer.toString((int) FPS.getAverageFPS()));
		int minutes = TimeElapsed.getElapsedSeconds() / 60;
		int seconds = TimeElapsed.getElapsedSeconds() % 60;
		game.timeElapsed.setText(String.format("%d:%02d", minutes, seconds));

		// Passenger spawning
		if (passengerSpawn.shouldSpawn(currentNanoTime)) {
			Tile tempPassengerTile = map.getRandomTile(10);
			if (tempPassengerTile != null) {
				passengers.add(new Passenger(tempPassengerTile.x, tempPassengerTile.y, Passenger.PASSENGER));
			}
		}

		for (int i = passengers.size() - 1; i >= 0; i--) {
			Passenger passenger = passengers.get(i);
			passenger.render(gc);

			if (player1.hitbox.intersects(passenger.hitBox())) {
				if (!passengerLoadingTimes.containsKey(passenger)) {
					passengerLoadingTimes.put(passenger, currentNanoTime);
				} else {
					long loadStartTime = passengerLoadingTimes.get(passenger);
					if (TimeElapsed.nanoToSeconds(currentNanoTime - loadStartTime) >= LOADING_TIME_SECS) {
						player1.passengers++;
						passengers.remove(i);
						passengerLoadingTimes.remove(passenger);
					}
				}
			} else if (passengerLoadingTimes.containsKey(passenger) && !player2.hitbox.intersects(passenger.hitBox())) {
				passengerLoadingTimes.remove(passenger); // Only remove if player2 is also not intersecting
			}

			if (player2.hitbox.intersects(passenger.hitBox())) {
				if (!passengerLoadingTimes.containsKey(passenger)) { // Start loading this passenger
					passengerLoadingTimes.put(passenger, currentNanoTime);
				} else {
					long loadStartTime = passengerLoadingTimes.get(passenger);
					if (TimeElapsed.nanoToSeconds(currentNanoTime - loadStartTime) >= LOADING_TIME_SECS) { // Loading
																											// complete
						player2.passengers++;
						passengers.remove(i);
						passengerLoadingTimes.remove(passenger); // Remove loading state for this passenger
					}
				}
			} else if (passengerLoadingTimes.containsKey(passenger) && !player1.hitbox.intersects(passenger.hitBox())) {
				passengerLoadingTimes.remove(passenger); // Only remove if player2 is also not intersecting
			}
		}

		if (player1.hitbox.intersects(junction)) {
			if (!isUnloading1) {
				isUnloading1 = true;
				unloadStartTime1 = currentNanoTime;
			} else {
				// Check if 8 seconds have passed
				if (TimeElapsed.nanoToSeconds(currentNanoTime - unloadStartTime1) >= UNLOADING_TIME_SECS) {
					player1.score += player1.passengers;
					player1.passengers = 0;
					isUnloading1 = false;
				}
			}
		} else {
			isUnloading1 = false; // Reset if player leaves the junction
		}

		if (player2.hitbox.intersects(junction)) {
			if (!isUnloading2) {
				isUnloading2 = true;
				unloadStartTime2 = currentNanoTime;
			} else {
				// Check if 8 seconds have passed
				if (TimeElapsed.nanoToSeconds(currentNanoTime - unloadStartTime2) >= UNLOADING_TIME_SECS) {
					player2.score += player2.passengers;
					player2.passengers = 0;
					isUnloading2 = false;
				}
			}
		} else {
			isUnloading2 = false; // Reset if player leaves the junction
		}

		// Power-up spawning
		if (powerUpSpawn.shouldSpawn(currentNanoTime)) {
//			Tile tempTile = map.getRandomTile(9);
//			powerUps.add(new PowerUp(tempTile.x, tempTile.y, PowerUp.SPEED_BUFF, "speed"));
//			
//			Tile tempTile2 = map.getRandomTile(9);
//			if (tempTile2 != null) {
//				powerUps.add(new PowerUp(tempTile2.x, tempTile2.y, PowerUp.INSURANCE_BUFF, "insured"));
//			}
////			
			Tile tempTile = map.getRandomTile(9);
			if (tempTile != null) {
				powerUps.add(new PowerUp(tempTile.x, tempTile.y, PowerUp.POWERUP_ICON, randomType));
			}
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

		// Obstacles Spawning Code
		if (obstacleSpawn.shouldSpawn(currentNanoTime)) {
			Tile tempObstacleTile = map.getRandomTile(9);
			if (tempObstacleTile != null) {
				obstacles.add(
						new Obstacle(tempObstacleTile.x, tempObstacleTile.y, Obstacle.HOLE_OBSTACLE, "crackintheroad"));
			}
		}

		for (Obstacle obstacle : obstacles) {
			obstacle.render(gc);
		}

		// Collision for obstacles
		for (int i = 0; i < obstacles.size(); i++) {
			Obstacle obstacle = obstacles.get(i);
			if (player1.hitbox.intersects(obstacle.getHitbox())) {
				if (player1.hasInvincibility() == false) {
					Debuff debuff = createObstacleFromDebuff(obstacle);
					applyDebuff(player1, debuff);
				}
				obstacles.remove(i);
			} else if (player2.hitbox.intersects(obstacle.getHitbox())) {
				if (player2.hasInvincibility() == false) {
					Debuff debuff = createObstacleFromDebuff(obstacle);
					applyDebuff(player2, debuff);
				}
				obstacles.remove(i);
			}
		}

		if (player1.hitbox.intersects(player2.hitbox)) {
			player1.teleportToStart();
			player2.teleportToStart();
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
			return new OilBarrel(5000) {
				public void apply(Player player) {
					player.setOilSpillDebuff(true);
				}

				public void remove(Player player) {
				}
			};
		case "insured":
			return new Insurance(5000);
		case "missile":
			return new Missile(10000) {
				public void apply(Player player) {
					player.setMissileBuff(true);
				}

				public void remove(Player player) {
				}
			};

		case "crack":
			return new Crack(5000);
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
			return new OilSpill(5000);
		case "crackintheroad":
			return new CrackInTheRoad();
		case "missile_obstacle":
			return new MissileObstacle(10000);
		default:
			throw new IllegalArgumentException("Unknown obstacle type");
		}
	}

	public void resetGame() {
		player1.reset();
		player2.reset();
		player1.setXPos(300);
		player2.setXPos(400);
		player1.setYPos(20);
		player2.setYPos(20);

		if (!passengers.isEmpty()) {
			passengers.clear();
		}
		if (!powerUps.isEmpty()) {
			powerUps.clear();
		}
		if (!obstacles.isEmpty()) {
			obstacles.clear();
		}
		if (!activeEffects.isEmpty()) {
			activeEffects.clear();
		}
		if (!activeDebuffs.isEmpty()) {
			activeDebuffs.clear();
		}

		TimeElapsed.reset();

		isGameOver = false;
	}
}