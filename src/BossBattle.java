import java.util.HashSet;
import java.util.Set;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BossBattle extends SimpleBattle {
	private Scene bossBattleScene;
	
	private EnemyBoss enemyBossObject;
	private Rectangle enemyBoss;
	private Set<Laser> enemyLaserObjects;
	private Group enemyLasers;
	
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

	private void playerWins() {
		sceneManager.goToGameWonScene(sceneManager);
	}
	
	private void addBossLevelText() {
		addBottomLeftText("Boss Level");
	}
	
	private void addEnemyBoss() {
		enemyBossObject = new EnemyBoss();
		enemyBoss = enemyBossObject.getEnemyBoss();
		root.getChildren().add(enemyBoss);
	}
	
	private void addEnemyLasers() {
		enemyLaserObjects = new HashSet<Laser>();
	}
	
	private void updateEnemyLasers() {
		root.getChildren().remove(enemyLasers);
		enemyLasers = new Group();
		transferLasersToGroup(enemyLaserObjects, enemyLasers);
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
	
	private void moveEnemyLasers() {
		for (Laser enemyLaserObject : enemyLaserObjects) {
			enemyLaserObject.moveLaser();
		}
		updateEnemyLasers();
	}
	
	private void checkPlayerLaserHitEnemy() {
		if (doLasersHitSingleRectangle(playerLaserObjects, playerLasers, enemyBoss)) {
			playerWins();
		}
	}
	
	private void checkEnemyLaserHitPlayer() {
		if (doLasersHitSingleRectangle(enemyLaserObjects, enemyLasers, player)) {
			playerLoses();
		}
	}
	
	private void checkLaserIntersect() {
		checkPlayerLaserHitRectangleInGroup(enemyLasers, false);
	}
	
	private void checkPlayerEnemyIntersect() {
		playerLosesIfIntersectEnemy(enemyBoss);
	}
	
	private void handleKeyPressed(KeyCode code) {
		switch (code) {
			case Q:
				quitToMenu();
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
}
