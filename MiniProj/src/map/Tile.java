package map;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
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
	/** Texture of the tile */
	protected Image img;
	/** The tile number which denotes what kind of tile it is */
	protected int number;
	/** Indicator as to whether or not the tile is a wall */
	public boolean isWall = false;
	/** Height of each tile */
	public static final int TILE_HEIGHT = 20;
	/** Width of each tile */
	public static final int TILE_WIDTH = 20;
	/** Width of each tile */
	public int x;
	/** Width of each tile */
	public int y;
	/** Is the tile occupied by a something, i.e. a passenger or a powerup? */
	public boolean occupied = false;
	
	public Rectangle2D tileBox = new Rectangle2D(x, y, TILE_HEIGHT, TILE_WIDTH);
	
    public void draw(GraphicsContext gc) {
        gc.drawImage(img, x, y);
        if(isWall) {
        	gc.strokeRect(x, y, TILE_HEIGHT, TILE_WIDTH);
        }
    }	
}