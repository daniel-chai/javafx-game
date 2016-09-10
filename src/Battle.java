import java.util.HashSet;
import java.util.Set;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Battle implements SceneInterface {
	private SceneManager sceneManager;
	private Scene battleScene;
	private Group root;
	
	private Player playerObject;
	private Rectangle player;
	private Group enemies;
	private Set<Laser> playerLaserObjects;
	private Group playerLasers;
	
	private int level;
	private double timer = 0.0;
	private static double TIMER_LIMIT = 10.0;
	private long stepCounter = 0L;
	
	public Battle(SceneManager sceneManager, int level) {
		this.sceneManager = sceneManager;
		this.level = level;
	}
	
	/**
	 * Creates the battle scene
	 */
	public Scene init(int width, int height) {
		root = new Group();
		battleScene = new Scene(root, width, height, Color.BLACK);
		
		addLevelText();
		addPlayer();
		addEnemies();
		addPlayerLasers();
		
		battleScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		
		return battleScene;
	}
	
	private void playerLoses() {
		sceneManager.goToGameOverScene(sceneManager);
	}
	
	private void playerWinsLevel() {
		sceneManager.goToNextLevelScene(sceneManager, level + 1);
	}
	
	private void addLevelText() {
		Text levelText = UIGenerator.createText("Level " + level, 10, Main.SIZE - 10, 15);
		levelText.setFill(Color.WHITE);
		
		root.getChildren().add(levelText);
	}
	
	private void addPlayer() {
		playerObject = new Player();
		player = playerObject.getPlayer();
		root.getChildren().add(player);
	}
	
	private void addEnemies() {
		enemies = new Group();
		root.getChildren().add(enemies);
	}
	
	private void addPlayerLasers() {
		playerLaserObjects = new HashSet<Laser>();
	}
	
	private void updatePlayerLasers() {
		root.getChildren().remove(playerLasers);
		playerLasers = new Group();
		
		for (Laser playerLaserObject : playerLaserObjects) {
			playerLasers.getChildren().add(playerLaserObject.getLaser());
		}
		
		root.getChildren().add(playerLasers);
	}
	
	private int getEnemyCreationRate() {
		int[] enemyCreationRate = {150, 125, 100, 75};
		return enemyCreationRate[level];
	}
	
	private double getEnemyTravelRate() {
		double[] enemyTravelRate = {0.9, 1.0, 1.1, 1.2};
		return enemyTravelRate[level];
	}
	
	public void step(double elapsedTime) {
		checkTimeUp(elapsedTime);
		
		if (stepCounter % getEnemyCreationRate() == 0) {
			createEnemy();
		}
		
		moveEnemies();
		movePlayerLasers();
		checkPlayerLaserHitEnemy();
		checkPlayerEnemyIntersect();
		checkEnemyReachBottom();	
		
		stepCounter++;
	}
	
	private void checkTimeUp(double elapsedTime) {
		timer += elapsedTime;
		if (timer > TIMER_LIMIT) {
			playerWinsLevel();
		}
	}
	
	private void createEnemy() {
		Enemy enemyObject = new Enemy();
		Rectangle enemy = enemyObject.getEnemy();
		enemies.getChildren().add(enemy);
	}
	
	private void moveEnemies() {
		for (Node enemyNode : enemies.getChildren()) {
			Rectangle enemy = (Rectangle) enemyNode;
			enemy.setY(enemy.getY() + getEnemyTravelRate());
		}
	}
	
	private void movePlayerLasers() {
		for (Laser playerLaserObject : playerLaserObjects) {
			playerLaserObject.moveLaser();
		}
		updatePlayerLasers();
	}
	
	private void checkPlayerLaserHitEnemy() {
		outer: for (Node enemyNode : enemies.getChildren()) {
			Rectangle enemy = (Rectangle) enemyNode;
			for (Laser playerLaserObject : playerLaserObjects) {
				Rectangle playerLaser = playerLaserObject.getLaser();
				if (playerLaser.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
					enemies.getChildren().remove(enemyNode);
					playerLaserObjects.remove(playerLaserObject);
					updatePlayerLasers();
					break outer;
				}
			}
		}
	}
	
	private void checkPlayerEnemyIntersect() {
		for (Node enemyNode : enemies.getChildren()) {
			Rectangle enemy = (Rectangle) enemyNode;
			if (player.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
				playerLoses();
			}
		}
	}
	
	private void checkEnemyReachBottom() {
		for (Node enemyNode : enemies.getChildren()) {
			Rectangle enemy = (Rectangle) enemyNode;
			if (enemy.getY() + Enemy.HEIGHT > Main.SIZE) {
				playerLoses();
			}
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
