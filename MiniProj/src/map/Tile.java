package map;

import javafx.scene.image.Image;

/**
 * This class manages the loading and storage of tile images used to render the game map.
 * It loads a set of (currently only three) images representing different tile types.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class Tile {
	/** The number of tiles */
    protected static final int NO_OF_TILES = 3;
    /** An array to store the loaded tile images. */
    protected Image[] img = new Image[NO_OF_TILES]; 
    /** The height of each tile */
    protected static final int TILE_HEIGHT = 20;
    /** The width of each tile */
    protected static final int TILE_WIDTH = 20;

    /**
     * Constructs a Tile object.
     * Loads the tile images from the "assets/tiles/" directory.
     * The images are assumed to be named "tile0.png", "tile1.png", "tile2.png", etc.
     */
    public Tile() {
        for (int i = 0; i < img.length; i++) { 
            img[i] = new Image("../assets/tiles/tile" + i + ".png"); 
        }
    }
}