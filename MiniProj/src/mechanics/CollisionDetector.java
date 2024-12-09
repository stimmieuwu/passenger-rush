package mechanics;

import entities.Player;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import map.GridMap;

/**
 * The `CollisionDetector` class provides methods for detecting collisions
 * between game entities and the map
 *
 * @author Simonee Ezekiel M. Mariquit
 * @author Jan Zuriel Camba
 * @author Norman Marfa III
 * @created_date 2024-12-09
 */
public class CollisionDetector {

	/**
	 * Detects if a player would collide with a wall tile after moving in a specific
	 * direction.
	 *
	 * @param player The Player object to check for collision.
	 * @param key    The KeyCode representing the direction of movement.
	 * @param dx     The change in x-coordinate (horizontal movement).
	 * @param dy     The change in y-coordinate (vertical movement).
	 * @return true if a collision is detected, false otherwise.
	 */
	public boolean detectTile(Player player, KeyCode key, double dx, double dy) {
		player.isColliding = false;
		Rectangle2D predictedHitbox = new Rectangle2D(player.collisionBox.getMinX() + dx,
				player.collisionBox.getMinY() + dy, player.collisionBox.getWidth(), player.collisionBox.getHeight());

		// Check if the predicted hitbox intersects with any corner
		if (GridMap.isWallAt((int) predictedHitbox.getMinX(), (int) predictedHitbox.getMinY()) || // Top-left
				GridMap.isWallAt((int) predictedHitbox.getMaxX(), (int) predictedHitbox.getMinY()) || // Top-right
				GridMap.isWallAt((int) predictedHitbox.getMinX(), (int) predictedHitbox.getMaxY()) || // Bottom-left
				GridMap.isWallAt((int) predictedHitbox.getMaxX(), (int) predictedHitbox.getMaxY())) { // Bottom-right
			return true; // Collision detected
		}
		return false; // No collision
	}

}
