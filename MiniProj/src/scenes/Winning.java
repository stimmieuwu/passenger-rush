package scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import mechanics.GameTimer;
import mechanics.Graphics;

/**
 * This class represents the "Winning" scene in the Passenger rush game, which
 * is displayed after the game is over. It provides a simple interface showing
 * what happened during the game
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Winning {

	/** The Scene object representing the winning scene. */
	private Scene winningScene;
	/** The root node of the scene layout. */
	private StackPane layout = new StackPane();
	
	/** The Text object displaying a message on the winning scene. */
	private Text score = new Text("");
	private Text name = new Text("");

	Button back = new Button("Back");
	Button again = new Button("again");
	Canvas canvas = new Canvas(SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
	GraphicsContext bg = canvas.getGraphicsContext2D();
	
	/**
	 * Constructs a Winning object. Initializes the scene with a back button to
	 * return to the main menu.
	 *
	 * @param sceneManager The SceneManager object used for switching between
	 *                     scenes.
	 */
	public Winning(SceneManager sceneManager) {
		// Go back to the main menu upon the click of this button
		
		buttonEvents(sceneManager);
		Group layout = new Group();
		winningScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		
		
		arrangeElements();

	
		
		
		
	
	
		layout.getChildren().add(canvas);
		layout.getChildren().addAll(back, again);
		layout.getChildren().addAll(score, name);
	}
	
	private void arrangeElements() {
		this.back.getStyleClass().add("back");
		this.again.getStyleClass().add("again");
		
		this.score.getStyleClass().add("winnerScore");
		this.name.getStyleClass().add("winnerName");
		winningScene.getStylesheets().add((getClass()).getResource("skin.css").toExternalForm());
		
		this.again.setLayoutX(529);
		this.again.setLayoutY(628);
		this.back.setLayoutX(25);
		this.back.setLayoutY(667);
		
		
		this.name.setLayoutY(175);
		
		this.score.setLayoutX(490);
		this.score.setLayoutY(600);
		
		
	}
	
	private void buttonEvents(SceneManager sceneManager){
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneManager.switchToMainMenu();
			}
		});
		again.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				sceneManager.switchToGameScene();
			}
		});
	}
	

	
	public void displayResult() {
		score.setText("");
		name.setText("");
		bg.clearRect(0, 0, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		// drawing of background
		bg.drawImage(Graphics.WINNER, 0, 0);
		if(GameTimer.isTied){
			bg.drawImage(Graphics.TIED, 0, 0);
		}else {
			score.setText("" + GameTimer.winningScore);
			name.setText(GameTimer.winningName);
			this.name.setLayoutX(SceneManager.getWindowWidth()/2 - 22*name.getText().length());
		}
		
	}
	/**
	 * Returns the Scene object representing the winning scene.
	 *
	 * @return The winning scene.
	 */
	public Scene getScene() {
		return winningScene;
	}
}