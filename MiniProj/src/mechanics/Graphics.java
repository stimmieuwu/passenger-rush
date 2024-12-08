package mechanics;

import javafx.scene.image.Image;

/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */

public class Graphics {
	public static final Image background = new Image("../assets/tiles/bg.png");
	public static final Image routes = new Image("../assets/sprites/stops.png");
	public static Image[] menuAnimation = new Image[120];
	public static final int FRAMES = 41;
	
	public static void generateMenuGraphics() {
		for (int i = 0; i < FRAMES; i++) {
        	menuAnimation[i] = new Image("../assets/menuAnimation/menu" + i + ".png" );
        }
	}
}
