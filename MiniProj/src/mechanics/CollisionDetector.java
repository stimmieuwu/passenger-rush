package mechanics;

import entities.Player;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import map.GridMap;

public class CollisionDetector {

	public boolean detectTile(Player player, KeyCode key, double dx, double dy) {
		player.isColliding = false;
		Rectangle2D predictedHitbox = new Rectangle2D(player.collisionBox.getMinX() + dx,
				player.collisionBox.getMinY() + dy, player.collisionBox.getWidth(), player.collisionBox.getHeight());

		// Check if the predicted hitbox intersects with any corner
		if (GridMap.isWallAt((int) predictedHitbox.getMinX(), (int) predictedHitbox.getMinY()) || // Top-left
				GridMap.isWallAt((int) predictedHitbox.getMaxX(), (int) predictedHitbox.getMinY()) || // Top-right
				GridMap.isWallAt((int) predictedHitbox.getMinX(), (int) predictedHitbox.getMaxY()) || // Bottom-left
				GridMap.isWallAt((int) predictedHitbox.getMaxX(), (int) predictedHitbox.getMaxY())) { // Bottom-right
			return true;
		}
		return false;
	}

}
