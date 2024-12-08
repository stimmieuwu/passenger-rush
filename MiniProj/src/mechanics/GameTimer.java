package mechanics;

import java.util.ArrayList;
import java.util.HashMap;

import effects.Effect;
import effects.OilBarrel;
import effects.Speed;
import effects.Debuff;
import effects.OilSpill;
import entities.Obstacle;
import entities.PowerUp;
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
    private ArrayList<Passenger> passengers = new ArrayList<>();
    private Spawn passengerSpawn;
    
    private ArrayList<PowerUp> powerUps = new ArrayList<>();
    private Spawn powerUpSpawn;
    
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private Spawn obstacleSpawn;
    //ArrayList<Effect> effects = new ArrayList<>();
    private HashMap<Player, ArrayList<Effect>> activeEffects = new HashMap<>();
    private HashMap<Player, ArrayList<Debuff>> activeDebuffs = new HashMap<>();
    
    int randomIndex = (int) (Math.random() * PowerUp.TYPES.length);
    String randomType = PowerUp.TYPES[randomIndex];
   
    // scatched hashset based input due to performance issues
//    private final HashSet<KeyCode> inputs = new HashSet<KeyCode>();

	public GameTimer(GraphicsContext gc, GraphicsContext bg, Game game) {
		this.gc = gc;
		this.scene = game.getScene();
		this.game = game;
		
		this.map = new GridMap(bg);
		
		player1 = new Player(300, 20, Player.SKIN_1, KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D);
		player2 = new Player(400, 20, Player.SKIN_2, KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT);
		keyDetection();
		bg.drawImage(Graphics.background, 0, 0);
		map.drawMap(bg);
		bg.drawImage(Graphics.routes, 0, 0);
		passengerSpawn = new Spawn();
		powerUpSpawn = new Spawn();
		obstacleSpawn = new Spawn();
		
		activeEffects.put(player1, new ArrayList<>());
		activeEffects.put(player2, new ArrayList<>());
		
		activeDebuffs.put(player1, new ArrayList<>());
		activeDebuffs.put(player2, new ArrayList<>());
		
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
				
				if (code == KeyCode.F) {
					Obstacle newOilSpill = player1.placeOilSpill();
					if (newOilSpill != null) {
						obstacles.add(newOilSpill);
					}
				}
				
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
     * This method is called on each frame of the game loop.
     * It clears the canvas, draws the map and players, and updates the players' positions.
     * This is overriden from AnimationTimer.
     *
     * @param currentNanoTime The current time in nanoseconds.
     */
    @Override
	public void handle(long currentNanoTime) {
		gc.clearRect(0, 0, 800, 800);
		player1.render(gc);
		player2.render(gc);
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
		
		//Passenger spawning code
		if(passengerSpawn.shouldSpawn(currentNanoTime)) {
			Tile tempTile = map.getRandomTile(10);
			passengers.add(new Passenger(tempTile.x, tempTile.y, Passenger.PASSENGER));
		}
		
		for(Passenger passenger : passengers) {
			passenger.render(gc);
		}
		
		//Powerups spawning code
		if(powerUpSpawn.shouldSpawn(currentNanoTime)) {
//			Tile tempTile = map.getRandomTile(9);
//			powerUps.add(new PowerUp(tempTile.x, tempTile.y, PowerUp.SPEED_BUFF, "speed"));
//			
//			Tile tempTile2 = map.getRandomTile(9);
//			powerUps.add(new PowerUp(tempTile2.x, tempTile2.y, PowerUp.OILSPILL_DEBUFF, "oilspill"));
//			
			Tile tempTile = map.getRandomTile(9);
			powerUps.add(new PowerUp(tempTile.x, tempTile.y, PowerUp.POWERUP_ICON, randomType));
		}
		
		for (PowerUp powerUp: powerUps) {
			powerUp.render(gc);
		}
		
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
		for (Obstacle obstacle: obstacles) {
			obstacle.render(gc);	
		}
		
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
    private void applyEffect(Player player, Effect effect) {
    	effect.apply(player);
    	activeEffects.get(player).add(effect);
    	
    	new Thread(() -> {
    		try {
    			Thread.sleep(effect.getDuration());
    			effect.remove(player);
    			activeEffects.get(player).remove(effect);
    		} catch (InterruptedException e){
    			
    		}
    	}).start();
    }
    
    private void applyDebuff(Player player, Debuff debuff) {
    	debuff.apply(player);
    	activeDebuffs.get(player).add(debuff);
    	
    	new Thread(() -> {
    		try {
    			Thread.sleep(debuff.getDuration());
    			debuff.remove(player);
    			activeDebuffs.get(player).remove(debuff);
    		} catch (InterruptedException e){
    			
    		}
    	}).start();
    }
    private Effect createEffectFromPowerUp(PowerUp powerUp) {
    	switch(powerUp.getType()) {
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
    
    private Debuff createObstacleFromDebuff(Obstacle obstacle) {
    	switch(obstacle.getType()) {
    		case "oilspill_obstacle":
				return new OilSpill(5000, true);
			default:
				throw new IllegalArgumentException("Unknown obstacle type");
    	}
    }
}