import java.util.HashSet;
import java.util.Set;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public abstract class SimpleBattle {
	protected SceneManager sceneManager;
	protected Group root;
	
	protected Player playerObject;
	protected Rectangle player;
	protected Set<Laser> playerLaserObjects;
	protected Group playerLasers;
	
	protected long stepCounter = 0L;
	
	protected void quitToMenu() {
		sceneManager.goToMenuScene(sceneManager);
	}
	
	protected void playerLoses() {
		sceneManager.goToGameOverScene(sceneManager);
	}
	
	protected void addBottomLeftText(String text) {
		Text bottomLeftText = UIGenerator.createText(text, 10, Main.SIZE - 10, 15);
		bottomLeftText.setFill(Color.WHITE);
		
		root.getChildren().add(bottomLeftText);
	}
	
	protected void addPlayer() {
		playerObject = new Player();
		player = playerObject.getPlayer();
		root.getChildren().add(player);
	}
	
	protected void addPlayerLasers() {
		playerLaserObjects = new HashSet<Laser>();
	}
	
	protected void updatePlayerLasers() {
		root.getChildren().remove(playerLasers);
		playerLasers = new Group();
		transferLasersToGroup(playerLaserObjects, playerLasers);
	}
	
	protected void transferLasersToGroup(Set<Laser> laserSet, Group lasers) {
		for (Laser laserObject : laserSet) {
			lasers.getChildren().add(laserObject.getLaser());
		}
		root.getChildren().add(lasers);
	}
	
	protected void movePlayerLasers() {
		for (Laser playerLaserObject : playerLaserObjects) {
			playerLaserObject.moveLaser();
		}
		updatePlayerLasers();
	}
	
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
	
	protected void playerLosesIfIntersectEnemy(Rectangle enemy) {
		if (player.getBoundsInLocal().intersects(enemy.getBoundsInLocal())) {
			playerLoses();
		}
	}
	
	protected void shootPlayerLaser(String direction) {
		Laser playerLaserObject = new Laser(Player.LASER_SIZE, Color.YELLOW, direction, player);
		playerLaserObjects.add(playerLaserObject);
		updatePlayerLasers();
	}	
}
