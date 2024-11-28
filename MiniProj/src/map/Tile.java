package map;

import javafx.scene.image.Image;

// represents single class
public class Tile {
	public Image[] img = new Image[3];
	
	Tile(){
		for(int i = 0; i < 3; i++) {
			img[i] = new Image("../../assets/tiles/tile" + i + ".png" );
		}
	}
}
