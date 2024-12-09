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
	public static final Image background = new Image("../assets/backgrounds/bg.png");
	public static final Image JEEPSELECT = new Image("../assets/backgrounds/skinSelection.png");
	public static final Image routes = new Image("../assets/sprites/stops.png");
	public static Image[] menuAnimation = new Image[120];
	public static final int FRAMES = 41;
	/** The image for skin 1 */
	public static final Image SKIN_1 = new Image("./../assets/sprites/jeep0.png", 80, 80, true, true);
	/** The image for skin 2 */
	public static final Image SKIN_2 = new Image("./../assets/sprites/jeep1.png", 80, 80, true, true);
	/** The image for skin 3 */
	public static final Image SKIN_3 = new Image("./../assets/sprites/jeep2.png", 80, 80, true, true);
	/** The image for skin 4 */
	public static final Image SKIN_4 = new Image("./../assets/sprites/jeep3.png", 80, 80, true, true);
	/** The image for skin 5 */
	public static final Image SKIN_5 = new Image("./../assets/sprites/jeep4.png", 80, 80, true, true);
	/** The image for skin 6 */
	public static final Image SKIN_6 = new Image("./../assets/sprites/jeep5.png", 80, 80, true, true);

	public static void generateMenuGraphics() {
		for (int i = 0; i < FRAMES; i++) {
			menuAnimation[i] = new Image("../assets/menuAnimation/menu" + i + ".png");
		}
	}
}
