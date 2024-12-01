package map;

import java.io.BufferedReader;
import java.io.FileReader;

import javafx.scene.canvas.GraphicsContext;

/**
 * This class represents a grid-based map for the game. It loads map data from a
 * text file and renders the map using tiles.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 * @reference https://www.youtube.com/watch?v=ugzxCcpoSdE
 */
public class GridMap {
	/** The GraphicsContext used for drawing the map. */
	private GraphicsContext gc;
	/** The Tile object containing tile images. */
	private Tile tiles;
	/** The 2D array storing the map data. */
	private int[][] gridMap;
	/** The number of columns */
	private static final int COLUMNS = 40;
	/** The number of rows */
	private static final int ROWS = 40;

	/**
	 * Constructs a GridMap object. Initializes the GraphicsContext, Tile object,
	 * and loads the map data from the specified file.
	 *
	 * @param gc The GraphicsContext for drawing the map.
	 */
	public GridMap(GraphicsContext gc) {
		this.gc = gc;
		this.tiles = new Tile();
		this.gridMap = new int[ROWS][COLUMNS];
		this.loadMap("assets/maps/map.txt");
	}

	/**
	 * Loads the map data from the specified text file. The file should contain a
	 * grid of numbers representing different tile types.
	 *
	 * @param mapDir The path to the map file.
	 */
	private void loadMap(String mapDir) {
		try { // In case the map file is not a text file containing a grid of numbers, print
				// an error.
			FileReader get = new FileReader(mapDir); // Gets the data from the text file
			BufferedReader br = new BufferedReader(get); // Reads the data from the text file

			// This nested loop puts the map data inside an array.
			for (int row = 0; row < ROWS; row++) {
				String line = br.readLine();
				for (int col = 0; col < COLUMNS; col++) {
					String[] temp = line.split(" "); // Don't read the spaces in between the map data.
					int mapData = Integer.parseInt(temp[col]);
					gridMap[row][col] = mapData;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Draws the map on the canvas using the loaded tile data. Each tile is assumed
	 * to be 20x20 pixels in size, which fits the 800x800 grid.
	 *
	 * @param gc The GraphicsContext for drawing the map.
	 */
	public void drawMap(GraphicsContext gc) {
		// Initialize the column, row, x position, and y position to be zero. This will
		// be manipulated later.
		int col = 0, row = 0, x = 0, y = 0;

		// assuming that a single tile is 20x20px I'll make this smaller in a later
		// version
		// use of constants for tile size and other things will be done.
		// single while loop to avoid performance issues
		while (row < ROWS) {
			int tileData = gridMap[row][col];
			gc.drawImage(tiles.img[tileData], x, y);

			col++;
			x += Tile.TILE_WIDTH;

			if (col == COLUMNS) {
				col = 0;
				x = 0;
				row++;
				y += Tile.TILE_HEIGHT;
			}
		}
	}
}