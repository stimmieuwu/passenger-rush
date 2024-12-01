package map;

import java.io.BufferedReader;
import java.io.FileReader;
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
    private static final int TILES = 3;
    /** Number of rows */
    private static final int ROWS = 3;
    /** Number of columns*/
    private static final int COLUMNS = 3;
    /** The GraphicsContext used for drawing on the Canvas object */
    GraphicsContext gc;
    /** Array to store the different tile objects*/
    Tile tiles[] = new Tile[TILES]; 
    /** 2D array representing the map grid. */
    int gridMap[][];

    /**
     * Construct a GridMap object, which loads the map array and the map data.
     *
     * @param gc The GraphicsContext to use for drawing.
     */
    public GridMap(GraphicsContext gc) {
        this.gc = gc;
        this.gridMap = new int[ROWS][COLUMNS]; 
        this.loadTileAssets(); // Load the tile images
        this.loadMap("map.txt"); // Load the map data
    }

    /**
     * Loads the tile assets (images) and sets the isWall property for applicablle tiles.
     */
    private void loadTileAssets() {
        for (int i = 0; i < TILES; i++) {
            tiles[i] = new Tile();
            tiles[i].img = new Image("../assets/tiles/tile" + i + ".png"); 
            if (i == 2) {
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
     * Draws the map on the canvas using the loaded tile images.
     * 
     * @param gc The GraphicsContext to use for drawing.
     */
    public void drawMap(GraphicsContext gc) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (row < ROWS) {
            int tileData = gridMap[row][col];
            gc.drawImage(tiles[tileData].img, x, y); 
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