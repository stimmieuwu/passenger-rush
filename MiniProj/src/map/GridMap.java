package map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represents the grid-based map of the game.
 * It handles loading the map from a text file, loading tile assets, and drawing the map on the canvas.
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class GridMap {
    /** Number of distinct tiles */
    private static final int TILES = 11;
    /** Number of rows */
    private static final int ROWS = 40;
    /** Number of columns*/
    private static final int COLUMNS = 40;
    /** The GraphicsContext used for drawing on the Canvas object */
    private GraphicsContext gc;
    /** Array to store the different tile textures*/
    public static Tile tiles[] = new Tile[TILES]; 
    /** 2D array representing the map grid. */
    public static Tile gridMap[][];

    /**
     * Construct a GridMap object, which loads the map array and the map data.
     *
     * @param gc The GraphicsContext to use for drawing.
     */
    public GridMap(GraphicsContext gc) {
        this.gc = gc;
        GridMap.gridMap = new Tile[ROWS][COLUMNS]; 
        this.loadTileAssets(); // Load the tile images
		this.loadMap("./assets/maps/map.txt");
    }

    /**
     * Loads the tile assets (images) and sets the isWall property for applicablle tiles.
     */
    private void loadTileAssets() {
        for (int i = 0; i < TILES; i++) {
            tiles[i] = new Tile();
            tiles[i].img = new Image("./../assets/tiles/tile" + i + ".png"); 
            if (i < 9) {
                tiles[i].isWall = true;
            }
        }
    }

    /**
     * Loads the map data from the specified text file.
     * It is assumed that the text file follows the format of being a grid of numbers.
     *
     * @param mapDir The path to the map file.
     */
    private void loadMap(String mapDir) {
        try {
            FileReader get = new FileReader(mapDir);
            BufferedReader br = new BufferedReader(get);

            for (int row = 0; row < ROWS; row++) {
                String line = br.readLine();
                String temp[] = line.split(" "); 
                for (int col = 0; col < COLUMNS; col++) {
                    int tileData = Integer.parseInt(temp[col]);
                    gridMap[row][col] = new Tile();
                    gridMap[row][col].img = tiles[tileData].img;
                    gridMap[row][col].number = tileData;
                    gridMap[row][col].x = col * 20;
                    gridMap[row][col].y = row * 20;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Tile getRandomTile(int tileNumber) {
        ArrayList<Tile> matchingTiles = new ArrayList<Tile>();
        Random random = new Random();

        // Find all tiles with the given tileNumber
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (gridMap[row][col].number == tileNumber) {
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