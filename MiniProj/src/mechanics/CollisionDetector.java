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
	public double[] detectTile(Player player, KeyCode key, double dx, double dy) {

		double newX = player.collisionBox.getMinX() + dx;
		double newY = player.collisionBox.getMinY() + dy;

		Rectangle2D predictedHitbox = new Rectangle2D(newX, newY, player.collisionBox.getWidth(),
				player.collisionBox.getHeight());

		// Check if the predicted hitbox intersects with any corner
		for (Rectangle2D wall : GridMap.walls) {
			if (predictedHitbox.intersects(wall)) {

				double overlapX = 0;
				double overlapY = 0;

				if (key == player.up) {
					overlapY = predictedHitbox.getMinY() - wall.getMaxY(); // Correct
				} else if (key == player.down) {
					overlapY = predictedHitbox.getMaxY() - wall.getMinY(); // Corrected
				} else if (key == player.left) {
					overlapX = predictedHitbox.getMinX() - wall.getMaxX(); // Correct
				} else if (key == player.right) {
					overlapX = predictedHitbox.getMaxX() - wall.getMaxX(); // Correct
				}

				return new double[] { overlapX, overlapY }; // Return the overlap values
			}
		}
		return null; // No collision
	}

}
