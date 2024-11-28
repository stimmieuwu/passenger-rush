package map;

import java.io.BufferedReader;
import java.io.FileReader;

import javafx.scene.canvas.GraphicsContext;

public class GridMap {
	GraphicsContext gc;
	Tile tiles;
	int gridMap[][];

	public GridMap(GraphicsContext gc) {
		this.gc = gc;
		this.tiles = new Tile();
		
		this.gridMap = new int [40][40];
		
		// if ever we want to change maps
		loadMap("../../assets/maps/map.txt");
	}

	private void loadMap(String mapDir){
		try {
			// get data from text file
			 FileReader get = new FileReader(mapDir); 
			
			// to read text file data
			
			BufferedReader br = new BufferedReader(get);
			System.out.println("hey");
			// testing things out might cause performance issues
			for(int r = 0; r < 40; r++) {
				String line = br.readLine();
				
				for(int c = 0; c < 40; c++) {
					String temp[] = line.split(" "); // won't read the spaces between map data
					int mapData = Integer.parseInt(temp[c]);
					gridMap[r][c] = mapData;
				}
				
			}
			br.close();
		}catch(Exception e) {
		}
		
		
	}

	public void drawMap(GraphicsContext gc) {
	
	int col = 0;
	int row = 0;
	int x = 0;
	int y = 0;
	
	// assuming that a single tile is 20x20px I'll make this smaller in a later version
	// use of constants for tile size and other things will be done.
	// single while loop to avoid performance issues
	while(row < 40) {
		
		int tileData = gridMap[row][col];
		
		gc.drawImage(tiles.img[tileData], x, y);
		col++;
		x += 20;
		if(col == 40) {
			col = 0;
			x = 0;
			row++;
			y+= 20;
		}
	}
}


}

