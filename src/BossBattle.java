import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BossBattle implements SceneInterface {
	private SceneManager sceneManager;
	private Scene bossBattleScene;
	private Group root;
	
	private Player playerObject;
	private Rectangle player;
	private EnemyBoss enemyBossObject;
	private Rectangle enemyBoss;
	private Set<Laser> playerLaserObjects;
	private Group playerLasers;
	private Set<Laser> enemyLaserObjects;
	private Group enemyLasers;
	
	private long stepCounter = 0L;
	
	public BossBattle(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Creates the boss battle scene
	 */
	public Scene init(int width, int height) {		
		root = new Group();
		bossBattleScene = new Scene(root, width, height, Color.BLACK);
		
		addBossLevelText();
		addPlayer();
		addEnemyBoss();
		addPlayerLasers();
		addEnemyLasers();
		
		bossBattleScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		
		return bossBattleScene;
	}

	private void addBossLevelText() {
		Text bossLevelText = UIGenerator.createText("Boss Level", 10, Main.SIZE - 10, 15);
		bossLevelText.setFill(Color.WHITE);
		
		root.getChildren().add(bossLevelText);
	}
	
	private void addPlayer() {
		playerObject = new Player();
		player = playerObject.getPlayer();
		root.getChildren().add(player);
	}
	
	private void addEnemyBoss() {
		enemyBossObject = new EnemyBoss();
		enemyBoss = enemyBossObject.getEnemyBoss();
		root.getChildren().add(enemyBoss);
	}
	
	private void addPlayerLasers() {
		playerLaserObjects = new HashSet<Laser>();
	}
	
	private void addEnemyLasers() {
		enemyLaserObjects = new HashSet<Laser>();
	}
	
	private void updatePlayerLasers() {
		root.getChildren().remove(playerLasers);
		playerLasers = new Group();
		
		for (Laser playerLaserObject : playerLaserObjects) {
			playerLasers.getChildren().add(playerLaserObject.getLaser());
		}
		
		root.getChildren().add(playerLasers);
	}
	
	private void updateEnemyLasers() {
		root.getChildren().remove(enemyLasers);
		enemyLasers = new Group();
		
		for (Laser enemyLaserObject : enemyLaserObjects) {
			enemyLasers.getChildren().add(enemyLaserObject.getLaser());
		}
		
		root.getChildren().add(enemyLasers);
	}
	
	public void step(double elapsedTime) {
		if (stepCounter % 10 == 0) {
			moveEnemyBoss();
		}
		
		if (stepCounter % 50 == 0 && stepCounter % 100 != 0) {
			shootEnemyLaser("UP");
			shootEnemyLaser("DOWN");
		}
		if (stepCounter % 100 == 0) {
			shootEnemyLaser("LEFT");
			shootEnemyLaser("RIGHT");
		}
		
		movePlayerLasers();
		moveEnemyLasers();
		checkPlayerLaserHitEnemy();
		checkEnemyLaserHitPlayer();
		checkLaserIntersect();
		checkPlayerEnemyIntersect();
		
		stepCounter++;
	}
	
	private void moveEnemyBoss() {
		enemyBossObject.moveEnemyBoss();
	}
	
	private void shootEnemyLaser(String direction) {
		Laser enemyLaserObject = new Laser(EnemyBoss.LASER_SIZE, Color.PINK, direction, enemyBoss);
		enemyLaserObjects.add(enemyLaserObject);
		updateEnemyLasers();
	}
	
	private void movePlayerLasers() {
		for (Laser playerLaserObject : playerLaserObjects) {
			playerLaserObject.moveLaser();
		}
		updatePlayerLasers();
	}
	
	private void moveEnemyLasers() {
		for (Laser enemyLaserObject : enemyLaserObjects) {
			enemyLaserObject.moveLaser();
		}
		updateEnemyLasers();
	}
	
	private void checkPlayerLaserHitEnemy() {
		for (Node laserNode : playerLasers.getChildren()) {
			Rectangle laser = (Rectangle) laserNode;
			if (laser.getBoundsInParent().intersects(enemyBoss.getBoundsInParent())) {
				// TODO: player laser hits enemy
				sceneManager.goToGameWonScene(sceneManager); 	// test
			}
		}
	}
	
	private void checkEnemyLaserHitPlayer() {
		for (Node laserNode : enemyLasers.getChildren()) {
			Rectangle laser = (Rectangle) laserNode;
			if (laser.getBoundsInParent().intersects(player.getBoundsInParent())) {
				// player loses
				sceneManager.goToGameOverScene(sceneManager);
			}
		}
	}
	
	private void checkLaserIntersect() {
		outer: for (Node enemyLaserNode : enemyLasers.getChildren()) {
			Rectangle enemyLaser = (Rectangle) enemyLaserNode;
			for (Node playerLaserNode : playerLasers.getChildren()) {
				Rectangle playerLaser = (Rectangle) playerLaserNode;
				if (enemyLaser.getBoundsInParent().intersects(playerLaser.getBoundsInParent())) {
					playerLasers.getChildren().remove(playerLaserNode);
					break outer;
				}
			}
		}
	}
	
	private void checkPlayerEnemyIntersect() {
		if (player.getBoundsInParent().intersects(enemyBoss.getBoundsInParent())) {
			// player loses
			sceneManager.goToGameOverScene(sceneManager);
		}
	}
	
	private void handleKeyPressed(KeyCode code) {
		switch (code) {
			case Q:
				// quit Battle and go back to Menu
				sceneManager.goToMenuScene(sceneManager);
				break;
			case W: 
				shootPlayerLaser("UP");
				break;
			case A:
				shootPlayerLaser("LEFT");
				break;
			case S:
				shootPlayerLaser("DOWN");
				break;
			case D:
				shootPlayerLaser("RIGHT");
				break;
			default:
				playerObject.handlePlayerKey(code);
		}
	}
	
	private void shootPlayerLaser(String direction) {
		Laser playerLaserObject = new Laser(Player.LASER_SIZE, Color.YELLOW, direction, player);
		playerLaserObjects.add(playerLaserObject);
		updatePlayerLasers();
	}	
}
