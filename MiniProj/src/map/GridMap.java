package map;

import java.io.BufferedReader;
import java.io.FileReader;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 * 
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class GridMap {
	GraphicsContext gc;
	Tile tiles[] = new Tile[3];
	int gridMap[][];

	public GridMap(GraphicsContext gc) {
		this.gc = gc;
		

		this.gridMap = new int[40][40];

		// if ever we want to change maps
		this.loadMap("assets/maps/map.txt");
		loadTileAssets();
	}
	
	
	private void loadTileAssets() {
		for(int i = 0; i < 3; i++) {
			tiles[i] = new Tile();
			tiles[i].img = new Image("../assets/tiles/tile" + i + ".png");
			
			if(i == 2) tiles[i].isWall = true;
		}
	}

	private void loadMap(String mapDir) {
		try {
			// get data from text file
			FileReader get = new FileReader(mapDir);

			// to read text file data

			BufferedReader br = new BufferedReader(get);
			// testing things out might cause performance issues
			for (int r = 0; r < 40; r++) {
				String line = br.readLine();

				for (int c = 0; c < 40; c++) {
					String temp[] = line.split(" "); // won't read the spaces between map data
					int mapData = Integer.parseInt(temp[c]);
					gridMap[r][c] = mapData;
				}

			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void drawMap(GraphicsContext gc) {

		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		// assuming that a single tile is 20x20px I'll make this smaller in a later
		// version
		// use of constants for tile size and other things will be done.
		// single while loop to avoid performance issues
		while (row < 40) {

			int tileData = gridMap[row][col];

			gc.drawImage(tiles[tileData].img, x, y);
			col++;
			x += 20;
			if (col == 40) {
				col = 0;
				x = 0;
				row++;
				y += 20;
			}
		}
	}

}
