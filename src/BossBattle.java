import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BossBattle {
	public static final int PLAYER_WIDTH = 50;
	public static final int PLAYER_HEIGHT = 50;
	public static final int ARROW_SHIFT = 10;
	public static final int ENEMYBOSS_WIDTH = 50;
	public static final int ENEMYBOSS_HEIGHT = 50;
	public static final int ENEMY_SHIFT = 10;
	public static final int PLAYER_LASER_SIZE = 10;
	public static final int ENEMY_LASER_SIZE = 20;
	
	private Scene bossBattleScene;
	private Group root;
	private Rectangle player;
	private Rectangle enemyBoss;
	private Group playerLasers;
	private Group enemyLasers;
	
	private Map<Rectangle, String> laserDirection;
	private long stepCounter = 0L;
	
	/**
	 * Creates the boss battle scene
	 */
	public Scene init(int width, int height) {
		laserDirection = new HashMap<Rectangle, String>();
		
		root = new Group();
		bossBattleScene = new Scene(root, width, height, Color.BLACK);
		
		addBossLevelText();
		addPlayer();
		addEnemyBoss();
		addLasers();
		
		bossBattleScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		
		return bossBattleScene;
	}

	private void addBossLevelText() {
		Text bossLevelText = new Text();
		bossLevelText.setFill(Color.WHITE);
		bossLevelText.setX(10);
		bossLevelText.setY(Main.SIZE - 10);
		bossLevelText.setText("Boss Level");
		
		root.getChildren().add(bossLevelText);
	}
	
	private void addPlayer() {
		Player playerObject = new Player(PLAYER_WIDTH, PLAYER_HEIGHT);
		player = playerObject.getPlayer();
		root.getChildren().add(player);
	}
	
	private void addEnemyBoss() {
		EnemyBoss enemyBossObject = new EnemyBoss(ENEMYBOSS_WIDTH, ENEMYBOSS_HEIGHT);
		enemyBoss = enemyBossObject.getEnemyBoss();
		root.getChildren().add(enemyBoss);
	}
	
	private void addLasers() {
		playerLasers = new Group();
		root.getChildren().add(playerLasers);
		
		enemyLasers = new Group();
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
		
		moveLasers();
		checkPlayerLaserHitEnemy();
		checkEnemyLaserHitPlayer();
		checkLaserIntersect();
		checkPlayerEnemyIntersect();
		
		stepCounter++;
	}
	
	/**
	 * If enemy boss is not in center: move towards center
	 * If enemy boss is in center: move randomly
	 */
	private void moveEnemyBoss() {
		int centerBoxSize = 200;
		int min = Main.SIZE / 2 - centerBoxSize / 2;
		int max = Main.SIZE / 2 + centerBoxSize / 2;
		
		if (isEnemyBossInCenter(min, max)) {
			moveEnemyBossRandomly();
		}
		else {
			moveEnemyBossToCenter(min, max);
		}
	}
	
	private boolean isEnemyBossInCenter(int min, int max) {
		return enemyBoss.getX() >= min && enemyBoss.getX() + ENEMYBOSS_WIDTH <= max &&
				enemyBoss.getY() >= min && enemyBoss.getY() + ENEMYBOSS_HEIGHT <= max;
	}
	
	private void moveEnemyBossToCenter(int min, int max) {
		if (enemyBoss.getX() < min) {
			enemyBoss.setX(enemyBoss.getX() + ENEMY_SHIFT);
		}
		if (enemyBoss.getX() + ENEMYBOSS_WIDTH > max) {
			enemyBoss.setX(enemyBoss.getX() - ENEMY_SHIFT);
		}
		if (enemyBoss.getY() < min) {
			enemyBoss.setY(enemyBoss.getY() + ENEMY_SHIFT);
		}
		if (enemyBoss.getY() + ENEMYBOSS_HEIGHT > max) {
			enemyBoss.setY(enemyBoss.getY() - ENEMY_SHIFT);
		}
	}
	
	private void moveEnemyBossRandomly() {
		int random = generateRandomIntFrom0To3();
		switch (random) {
			case 0: 
				// move left
				enemyBoss.setX(enemyBoss.getX() - ENEMY_SHIFT);
				break;
			case 1:
				// move right
				enemyBoss.setX(enemyBoss.getX() + ENEMY_SHIFT);
				break;
			case 2:
				// move up
				enemyBoss.setY(enemyBoss.getY() - ENEMY_SHIFT);
				break;
			case 3:
				enemyBoss.setY(enemyBoss.getY() + ENEMY_SHIFT);
				// move down
				break;
			default:
				// do nothing
		}
	}
	
	private int generateRandomIntFrom0To3() {
		Random r = new Random();
		return r.nextInt(4);
	}
	
	private void shootEnemyLaser(String direction) {
		Laser laserObject = new Laser(ENEMY_LASER_SIZE, Color.PINK, direction, enemyBoss);
		Rectangle laser = laserObject.getLaser();	
		enemyLasers.getChildren().add(laser);
		
		laserDirection.put(laser, direction);
	}
	
	private void moveLasers() {
		for (Node laserNode : playerLasers.getChildren()) {
			moveSingleLaser(laserNode);
		}
		for (Node laserNode : enemyLasers.getChildren()) {
			moveSingleLaser(laserNode);
		}
	}
	
	private void moveSingleLaser(Node laserNode) {
		Rectangle laser = (Rectangle) laserNode;
		String direction = laserDirection.get(laser);
		
		if (direction.equals("UP")) {
			laser.setY(laser.getY() - 5);
		}
		if (direction.equals("DOWN")) {
			laser.setY(laser.getY() + 5);
		}
		if (direction.equals("LEFT")) {
			laser.setX(laser.getX() - 5);
		}
		if (direction.equals("RIGHT")) {
			laser.setX(laser.getX() + 5);
		}
	} 
	
	private void checkPlayerLaserHitEnemy() {
		for (Node laserNode : playerLasers.getChildren()) {
			Rectangle laser = (Rectangle) laserNode;
			if (laser.getBoundsInParent().intersects(enemyBoss.getBoundsInParent())) {
				// TODO: player laser hits enemy
				SceneManager.goToGameWonScene(); 	// test
			}
		}
	}
	
	private void checkEnemyLaserHitPlayer() {
		for (Node laserNode : enemyLasers.getChildren()) {
			Rectangle laser = (Rectangle) laserNode;
			if (laser.getBoundsInParent().intersects(player.getBoundsInParent())) {
				SceneManager.goToGameOverScene();
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
			SceneManager.goToGameOverScene();
		}
	}
	
	private void handleKeyPressed(KeyCode code) {
		switch (code) {
			case LEFT:
				if (player.getX() > 0) {
					player.setX(player.getX() - ARROW_SHIFT);
				}
				break;
			case RIGHT:
				if (player.getX() + PLAYER_WIDTH < Main.SIZE) {
					player.setX(player.getX() + ARROW_SHIFT);
				}
				break;
			case UP:
				if (player.getY() > 0) {
					player.setY(player.getY() - ARROW_SHIFT);
				}
				break;
			case DOWN:
				if (player.getY() + PLAYER_HEIGHT < Main.SIZE) {
					player.setY(player.getY() + ARROW_SHIFT);
				}
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
			case Q:
				// quit Battle and go back to Menu
				SceneManager.goToMenuScene();
				break;
			default:
				// do nothing
		}
	}
	
	private void shootPlayerLaser(String direction) {
		Laser laserObject = new Laser(PLAYER_LASER_SIZE, Color.YELLOW, direction, player);
		Rectangle laser = laserObject.getLaser();	
		playerLasers.getChildren().add(laser);
		
		laserDirection.put(laser, direction);
	}
}
