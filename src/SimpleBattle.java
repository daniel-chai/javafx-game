// This entire file is part of my masterpiece.
// Daniel Chai

/**
 * This class is well designed as a superclass because it extracts out all the 
 * common functionality from the Battle classes. It also has very descriptive
 * naming methods and doesn't use static variables. To extend it, a subclass
 * can simply use "extend". An example is the BossBattle class, which is also
 * part of my code masterpiece.
 */

import java.util.HashSet;
import java.util.Set;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This class is abstract because it is never meant to be instantiated. Instead, it is
 * a super class for all types of "battles," such as Battle and BossBattle. It contains
 * common functionality that is present in all of these "battles." This functionality 
 * includes adding Text, the Player, the Lasers, and moving some of the characters/items
 * around. All "battles" should extend this class to limit duplicated code.
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public abstract class SimpleBattle {
	protected SceneManager sceneManager;
	protected Group root;
	
	protected Player playerObject;
	protected Rectangle player;
	protected Set<Laser> playerLaserObjects;
	protected Group playerLasers;
	
	protected long stepCounter = 0L;
	
	/**
	 * Go to the Menu Scene
	 */
	protected void quitToMenu() {
		sceneManager.goToMenuScene(sceneManager);
	}
	
	/**
	 * Go to the GameOver Scene
	 */
	protected void playerLoses() {
		sceneManager.goToGameOverScene(sceneManager);
	}
	
	/**
	 * Add Text to the bottom left corner
	 * @param text String to fill the text
	 */
	protected void addBottomLeftText(String text) {
		Text bottomLeftText = UIGenerator.createText(text, 10, Main.SIZE - 10);
		bottomLeftText.setFill(Color.WHITE);
		
		root.getChildren().add(bottomLeftText);
	}
	
	/**
	 * Add a Player to the Scene
	 */
	protected void addPlayer() {
		playerObject = new Player();
		player = playerObject.getPlayer();
		root.getChildren().add(player);
	}
	
	/**
	 * Add Set of player's lasers to the Scene
	 */
	protected void addPlayerLasers() {
		playerLaserObjects = new HashSet<Laser>();
	}
	
	/**
	 * Update Group of player's lasers based off Set of player's lasers
	 */
	protected void updatePlayerLasers() {
		root.getChildren().remove(playerLasers);
		playerLasers = new Group();
		transferLasersToGroup(playerLaserObjects, playerLasers);
	}
	
	/**
	 * For each Laser in the Set, add the corresponding Rectangle to the Group
	 * @param laserSet Set of Lasers
	 * @param lasers Group of laser Rectangles
	 */
	protected void transferLasersToGroup(Set<Laser> laserSet, Group lasers) {
		for (Laser laserObject : laserSet) {
			lasers.getChildren().add(laserObject.getLaser());
		}
		root.getChildren().add(lasers);
	}
	
	/**
	 * Move player's lasers in the correct direction
	 */
	protected void movePlayerLasers() {
		for (Laser playerLaserObject : playerLaserObjects) {
			playerLaserObject.moveLaser();
		}
		updatePlayerLasers();
	}
	
	/**
	 * Check if a Set of Lasers intersects a particular Rectangle
	 * @param laserSet Set of Lasers
	 * @param lasers Group of laser Rectangles
	 * @param rect Rectangle to check against
	 * @return boolean representing if the intersection occurs
	 */
	protected boolean doLasersHitSingleRectangle(Set<Laser> laserSet, Group lasers, Rectangle rect) {
		for (Laser laserObject : laserSet) {
			Rectangle laser = laserObject.getLaser();
			if (laser.getBoundsInLocal().intersects(rect.getBoundsInLocal())) {
				laserSet.remove(laserObject);
				lasers.getChildren().remove(laser);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if player's lasers intersect any Rectangle in a Group
	 * @param group Group of Rectangles to check against
	 * @param removeRect boolean representing whether or not to remove the Rectangle that is intersected
	 */
	protected void checkPlayerLaserHitRectangleInGroup(Group group, boolean removeRect) {
		for (Node node : group.getChildren()) {
			Rectangle rectInGroup = (Rectangle) node;
			if (doLasersHitSingleRectangle(playerLaserObjects, playerLasers, rectInGroup)) {
				if (removeRect) {
					group.getChildren().remove(rectInGroup);
				}
				return;
			}
		}
	}
	
	/**
	 * If player intersects the enemy, then player loses
	 * @param enemy Rectangle representing the enemy
	 */
	protected void playerLosesIfIntersectEnemy(Rectangle enemy) {
		if (player.getBoundsInLocal().intersects(enemy.getBoundsInLocal())) {
			playerLoses();
		}
	}
	
	/**
	 * Shoots a player laser in the specified direction
	 * @param direction "UP", "DOWN", "LEFT", or "RIGHT"
	 */
	protected void shootPlayerLaser(String direction) {
		Laser playerLaserObject = new Laser(Player.LASER_SIZE, Color.YELLOW, direction, player);
		playerLaserObjects.add(playerLaserObject);
		updatePlayerLasers();
	}	
}