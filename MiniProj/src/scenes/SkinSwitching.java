package scenes;

import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import mechanics.Graphics;

/**
 * This class represents is responsible for switching the jeepney skin of each
 * player.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class SkinSwitching {

	/** The Scene object representing the winning scene. */
	private Scene skinSwitchingScene;
	/** The Text object displaying a message on the winning scene. */
	private Text skinSwitching = new Text("Select Skin");

	private Canvas canvas;
	private Canvas player1C;
	private Canvas player2C;

	private Button start = new Button("Start");
	private Button back = new Button("back");
	private Button next1 = new Button(">");
	private Button prev1 = new Button("<");
	private Button next2 = new Button(">");
	private Button prev2 = new Button("<");
	private int player1Selection = 0;
	private int player2Selection = 0;
	public static Image selectedImageP1 = Graphics.SKIN_1;
	public static Image selectedImageP2 = Graphics.SKIN_1;
	
	boolean[] skinsSelected = new boolean[3];

	/**
	 * Constructs a SkinSwitching object. Initializes the scene with a back button
	 * to return to the main menu.
	 *
	 * @param sceneManager The SceneManager object used for switching between
	 *                     scenes.
	 */
	public SkinSwitching(SceneManager sceneManager) {

		// for background images
		this.canvas = new Canvas(SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		// for player1 chosen images
		this.player1C = new Canvas(SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		// for player 2chosen images
		this.player2C = new Canvas(SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		GraphicsContext p1 = player1C.getGraphicsContext2D();
		GraphicsContext p2 = player2C.getGraphicsContext2D();
		GraphicsContext bg = canvas.getGraphicsContext2D();

		// player 1 next
		next1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (player1Selection < 2)
					player1Selection++;
				else
					player1Selection = 0;
				showSelections(p1, true, player1Selection);
			}
		});
		// player 1 prev
		prev1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (player1Selection > 0)
					player1Selection--;
				else
					player1Selection = 2;
				showSelections(p1, true, player1Selection);
			}
		});

		// player 2 next
		next2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (player2Selection < 2)
					player2Selection++;
				else
					player2Selection = 0;
				showSelections(p2, false, player2Selection);
			}
		});

		// player 2 prev
		prev2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (player2Selection > 0)
					player2Selection--;
				else
					player2Selection = 2;
				showSelections(p2, false, player2Selection);
			}
		});

		// start game
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				sceneManager.switchToGameScene();
			}
		});
		// go back to main menu
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				sceneManager.switchToMainMenu();
			}
		});

		showSelections(p1, true, player1Selection);
		showSelections(p2, false, player2Selection);

		Group layout = new Group();
		skinSwitchingScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		layout.getChildren().add(skinSwitching);

		// drawn bg
		bg.drawImage(Graphics.JEEPSELECT, 0, 0);

		layout.getChildren().addAll(canvas, player1C, player2C);
		layout.getChildren().addAll(start, back, next1, prev1, next2, prev2);

		setButtonLayout();

		// for css
		assignStyleSheets();
	}

	// assigns button locations for each button
	private void setButtonLayout() {

		this.next1.setLayoutX(570);
		this.next1.setLayoutY(210);

		this.prev1.setLayoutX(179);
		this.prev1.setLayoutY(210);

		this.next2.setLayoutX(570);
		this.next2.setLayoutY(460);

		this.prev2.setLayoutX(179);
		this.prev2.setLayoutY(460);

		this.start.setLayoutX(325);
		this.start.setLayoutY(667);

		this.back.setLayoutX(25);
		this.back.setLayoutY(667);

	}

	// assigns css data
	private void assignStyleSheets() {
		skinSwitchingScene.getStylesheets().add((getClass()).getResource("skin.css").toExternalForm());

		this.prev1.getStyleClass().add("prev");
		this.next1.getStyleClass().add("next");
		this.prev2.getStyleClass().add("prev");
		this.next2.getStyleClass().add("next");
		this.start.getStyleClass().add("start");
		this.back.getStyleClass().add("back");
	}

	private void showSelections(GraphicsContext draw, boolean isPlayer1, int selection) {
		draw.clearRect(0, 0, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());
		
	    int finalSelection = selection;

	    if (skinsSelected[selection]) {
	        for (int i = 0; i < skinsSelected.length; i++) {
	            selection = (selection + i) % skinsSelected.length; 
	            if (!skinsSelected[finalSelection]) {
	                break; 
	            }
	        }
	    }

		switch (selection) {
		// case where player selects first skin (white jeep)
		case 0:
			if (isPlayer1)
				selectedImageP1 = Graphics.SKIN_1;
			else
				selectedImageP2 = Graphics.SKIN_4;
			break;
		// case where player selects second skin (red jeep)
		case 1:
			if (isPlayer1)
				selectedImageP1 = Graphics.SKIN_2;
			else
				selectedImageP2 = Graphics.SKIN_5;
			break;
		// case where player selects third skin (blue jeep)
		case 2:
			if (isPlayer1)
				selectedImageP1 = Graphics.SKIN_3;
			else
				selectedImageP2 = Graphics.SKIN_6;
			break;
		// catching case
		default:
			if (isPlayer1)
				selectedImageP1 = Graphics.SKIN_1;
			else
				selectedImageP2 = Graphics.SKIN_4;
			break;
		}
		
	    skinsSelected[finalSelection] = true;
		
		if (isPlayer1)
			draw.drawImage(selectedImageP1, 345, 227, 104, 66);
		else
			draw.drawImage(selectedImageP2, 345, 470, 104, 66);
	}

	/**
	 * Returns the Scene object representing the skin switching scene.
	 *
	 * @return The winning scene.
	 */
	public Scene getScene() {
		return skinSwitchingScene;
	}
}