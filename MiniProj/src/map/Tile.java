package map;

import javafx.scene.image.Image;

/**
 * Represents single class
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Tile {
	public Image[] img = new Image[3];

	Tile() {
		for (int i = 0; i < 3; i++) {
			img[i] = new Image("../assets/tiles/tile" + i + ".png");
		}
	}
}
