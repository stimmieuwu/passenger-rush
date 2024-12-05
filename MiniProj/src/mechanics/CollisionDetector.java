package mechanics;
import entities.Player;
import javafx.scene.input.KeyCode;
import map.GridMap;
import map.Tile;

public class CollisionDetector {
	public void detectTile(Player player, KeyCode key) {
		int playerLeftLocation = player.hitbox.x;
		int playerRightLocation = player.hitbox.x;
		int playerTopLocation = player.hitbox.y;
		int playerBottomLocation = player.hitbox.y;
		
		
		// gets tile index based on player position for now
		int playerLeftColumn = playerLeftLocation / Tile.TILE_WIDTH;
		int playerRightColumn = playerRightLocation / Tile.TILE_WIDTH;
		int playerTopRow = playerTopLocation / Tile.TILE_HEIGHT;
		int playerBottomRow = playerBottomLocation / Tile.TILE_HEIGHT;
		
		int detectedTile1, detectedTile2;
		
		System.out.println(playerTopRow + " " + playerRightColumn);
//		System.out.println(GridMap.gridMap[playerLeftColumn][playerTopRow]);
		
		// predicts what the next tile will be hit based on the player move amount
		if(key == player.up) {
			playerTopRow = (int) ((playerLeftLocation - Player.MOVE_AMOUNT) / Tile.TILE_WIDTH);
			
			detectedTile1 = GridMap.gridMap[playerLeftColumn][playerTopRow];
			detectedTile2 = GridMap.gridMap[playerRightColumn][playerTopRow];
			System.out.println(detectedTile1);
			if(GridMap.tiles[detectedTile1].isWall || GridMap.tiles[detectedTile2].isWall) {
				player.isColliding = true;
			}
		}
		
		if(key == player.down) {
			
		}
		
		if(key == player.left) {
			
		}
		
		if(key == player.right) {
			
		}
	}
}
