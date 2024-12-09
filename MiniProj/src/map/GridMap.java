package map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represents the grid-based map of the game. It handles loading the
 * map from a text file, loading tile assets, and drawing the map on the canvas.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class GridMap {
	/** Number of distinct tiles */
	private static final int TILES = 12;
	/** Number of rows */
	public static final int ROWS = 40;
	/** Number of columns */
	public static final int COLUMNS = 40;
	/** Array to store the different tile textures */
	public static Image tiles[] = new Image[TILES];
	/** 2D array representing the map grid. */
	public static Tile gridMap[][];

    public static ArrayList<Rectangle2D> walls = new ArrayList<>();

	/**
	 * Construct a GridMap object, which loads the map array and the map data.
	 *
	 * @param gc The GraphicsContext to use for drawing.
	 */
	public GridMap(GraphicsContext gc) {
		GridMap.gridMap = new Tile[ROWS][COLUMNS];
		loadTileAssets(); // Load the tile images
		loadMap("./assets/maps/map.txt");
	}

	/**
	 * Loads the tile assets in an array. The index of the image will be matched to
	 * determine what image to render in the map.
	 */
	private static void loadTileAssets() {
		for (int i = 0; i < TILES; i++) {
			tiles[i] = new Image("./../assets/tiles/tile" + i + ".png");
		}
	}

	/**
	 * Loads the map data from the specified text file. It is assumed that the text
	 * file follows the format of being a grid of numbers.
	 *
	 * @param mapDir The path to the map file.
	 */
	private static void loadMap(String mapDir) {
		try {
			FileReader get = new FileReader(mapDir);
			BufferedReader br = new BufferedReader(get);

			for (int row = 0; row < ROWS; row++) {
				String line = br.readLine();
				String temp[] = line.split(" ");
				for (int col = 0; col < COLUMNS; col++) {
					int tileData = Integer.parseInt(temp[col]);
					gridMap[row][col] = new Tile();
					gridMap[row][col].img = tiles[tileData];
					gridMap[row][col].number = tileData;
					gridMap[row][col].x = col * 20;
					gridMap[row][col].y = row * 20;
					if (tileData < 9)
						gridMap[row][col].isWall = true;
					
					if (tileData < 9) {
                        walls.add(new Rectangle2D(col * 20, row * 20, Tile.TILE_WIDTH, Tile.TILE_HEIGHT)); 
                    }
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if there is a wall at the specified x and y coordinates.
	 *
	 * @param x The x-coordinate to check.
	 * @param y The y-coordinate to check.
	 * @return true if there is a wall at the coordinates, false otherwise.
	 */
	public static boolean isWallAt(int x, int y) {
		int column = Math.floorDiv(x, Tile.TILE_WIDTH);
		int row = Math.floorDiv(y, Tile.TILE_HEIGHT);

		return gridMap[row][column].isWall;
	}

	/**
	 * Checks if there is a wall at the specified x and y coordinates.
	 *
	 * @param x The x-coordinate to check.
	 * @param y The y-coordinate to check.
	 * @return true if there is a Junction Unloading Area at the coordinates, false
	 *         otherwise.
	 */
	public static boolean isJunctionAt(int x, int y) {
		int column = Math.floorDiv(x, Tile.TILE_WIDTH);
		int row = Math.floorDiv(y, Tile.TILE_HEIGHT);

		return gridMap[row][column].isWall;
	}

	/**
	 * Get a random tile within the set of all tiles of a specified number. This is
	 * used for spawning purposes.
	 * 
	 * @param tileNumber The number of the tile to select from
	 * @return The random tile, if found, otherwise null
	 */
	public static Tile getRandomTile(int tileNumber) {
		ArrayList<Tile> matchingTiles = new ArrayList<Tile>();
		Random random = new Random();

		// Find all tiles with the given tileNumber
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				if (gridMap[row][col].number == tileNumber && !gridMap[row][col].occupied) {
					matchingTiles.add(gridMap[row][col]);
				}
			}
		}

		// Return a random tile from the list
		if (!matchingTiles.isEmpty()) {
			int randomIndex = random.nextInt(matchingTiles.size());
			return matchingTiles.get(randomIndex);
		} else {
			return null; // No tile found with that number
		}
	}

	/**
	 * Draws the map on the canvas using the loaded tile images.
	 * 
	 * @param gc The GraphicsContext to use for drawing.
	 */
	public void drawMap(GraphicsContext gc) {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				gridMap[row][col].draw(gc);
			}
		}
	}
}