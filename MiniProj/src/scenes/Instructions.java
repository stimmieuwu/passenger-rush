package scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This class represents the "Instructions" scene in the application. It
 * displays instructions for the game and provides a button to return to the
 * main menu.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Instructions {

    /**
     * The Scene object representing the instructions scene.
     */
    private Scene instructionsScene;
    /**
     * The root node of the scene layout.
     */
    private Canvas canvas;
    /**
     * The Text object displaying the game instructions.
     */
    private final Text intro = new Text("In Passenger Rush, two players compete for passengers in the UPLB campus.\n"
            + "By controlling two separate jeepneys on the same computer, the goal is\n"
            + "to transport as many passengers as possible to the Junction Unloading Area\n"
            + "and score higher than your opponent. Control your jeepney, pick up passengers,\n"
            + "avoid obstacles, and utilize power-ups in this intense five-minute battle to\n"
            + "dominate the Kaliwa and Kanan routes.");

    /**
     * Constructs an Instructions object. Initializes the scene with a back button
     * to return to the main menu.
     *
     * @param sceneManager The SceneManager object used for switching between
     *                     scenes.
     */
    public Instructions(SceneManager sceneManager) {
        // Go back to the main menu upon the click of this button
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneManager.switchToMainMenu();
            }
        });

        this.canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Group layout = new Group();
        this.instructionsScene = new Scene(layout, SceneManager.getWindowWidth(), SceneManager.getWindowHeight());

        // Create and position the intro section
        Rectangle introRect = createRectangle(50, 50, 700, 220, Color.BLACK, 0.5);
        Text introText = createText(intro.getText(), 75, 75, Color.BLACK);

        // Create and position the player controls sections
        Rectangle player1Controls = createRectangle(50, 290, 340, 220, Color.BLACK, 0.5);
        Rectangle player2Controls = createRectangle(410, 290, 340, 220, Color.BLACK, 0.5);

        Text player1ControlsLabel = createText("Player 1 Controls", 60, 280, Color.BLACK);
        Text player2ControlsLabel = createText("Player 2 Controls", 420, 280, Color.BLACK);
        
        Rectangle powerUps = createRectangle(50, 530, 223.33, 220, Color.BLACK, 0.5);
        Rectangle debuffs = createRectangle(286.33, 530, 223.33, 220, Color.BLACK, 0.5);
        Rectangle disasters = createRectangle(526.66, 530, 223.33, 220, Color.BLACK, 0.5);
        
        layout.getChildren().addAll(introRect, introText, player1Controls, player1ControlsLabel,
                player2Controls, player2ControlsLabel, powerUps, debuffs, disasters, back);
    }

    /**
     * Creates a Rectangle object with the specified properties.
     *
     * @param x      The x-coordinate of the top-left corner.
     * @param y      The y-coordinate of the top-left corner.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color  The fill color of the rectangle.
     * @param opacity The opacity of the rectangle.
     * @return The created Rectangle object.
     */
    private Rectangle createRectangle(double x, double y, double width, double height, Color color, double opacity) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFill(color);
        rectangle.setOpacity(opacity);
        return rectangle;
    }

    /**
     * Creates a Text object with the specified properties.
     *
     * @param text  The text content.
     * @param x     The x-coordinate of the text.
     * @param y     The y-coordinate of the text.
     * @param color The fill color of the text.
     * @return The created Text object.
     */
    private Text createText(String text, double x, double y, Color color) {
        Text textObject = new Text(x, y, text);
        textObject.setFill(color);
        return textObject;
    }

    /**
     * Returns the Scene object representing the instructions scene.
     *
     * @return The instructions scene.
     */
    public Scene getScene() {
        return instructionsScene;
    }
}