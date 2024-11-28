package mechanics;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import entities.Player;
import map.GridMap;

public class GameTimer extends AnimationTimer {
	private GraphicsContext gc;
	private Scene scene;
	private Player player1;
	private Player player2;
	
	GridMap map = new GridMap(gc);
	
	public GameTimer(GraphicsContext gc, Scene scene) {
		
		this.gc = gc;
		this.scene = scene;
		
		this.map = new GridMap(gc);
		player1 = new Player(100, 100, Player.PLAYER_IMAGE);
		player2 = new Player(100, 200, Player.PLAYER_IMAGE);

		this.handleKeyPressEvent();
	}
	
	
	public void handle(long currentNanoTime) {
		gc.clearRect(0, 0, 800, 800);
		
		map.drawMap(gc);
		
		player1.render(gc);
		player2.render(gc);	
		
		this.player1.move();
		this.player2.move();
	}
	
	private void handleKeyPressEvent() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e) {
            	KeyCode code = e.getCode();
                movePlayer(code);
			}	
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e) {
				KeyCode code = e.getCode();
				stopPlayer(code);
			}
		});
    }
	
	private void movePlayer(KeyCode key) {		
		if (key == KeyCode.W) this.player1.setDY(-10);
		if (key == KeyCode.S) this.player1.setDY(10);
		if (key == KeyCode.A) this.player1.setDX(-10);
		if (key == KeyCode.D) this.player1.setDX(10);
		
		if(key == KeyCode.UP) this.player2.setDY(-10);                 
		if(key == KeyCode.LEFT) this.player2.setDX(-10);
		if(key == KeyCode.DOWN) this.player2.setDY(10);
		if(key == KeyCode.RIGHT) this.player2.setDX(10);
	}
	
	private void stopPlayer(KeyCode key) {
		if (key == KeyCode.W) this.player1.setDY(0);
		if (key == KeyCode.S) this.player1.setDY(0);
		if (key == KeyCode.A) this.player1.setDX(0);
		if (key == KeyCode.D) this.player1.setDX(0);
		
		if(key == KeyCode.UP) this.player2.setDY(0);                 
		if(key == KeyCode.LEFT) this.player2.setDX(0);
		if(key == KeyCode.DOWN) this.player2.setDY(0);
		if(key == KeyCode.RIGHT) this.player2.setDX(0);
	}
}

