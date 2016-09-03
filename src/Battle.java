import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Battle {
	private static final int PLAYER_WIDTH = 50;
	private static final int PLAYER_HEIGHT = 50;
	private static final int ARROW_SHIFT = 10;
	private static final int LASER_SIZE = 10;
	private static final int ENEMY_SIZE = 50;
	
	private Scene battleScene;
	private Group root;
	private Rectangle player;
	private Group lasers;
	private Group enemies;
	
	private int level;
	private double timer = 0.0;
	private double TIMER_LIMIT = 5.0;
	private long stepCounter = 0L;
	
	/**
	 * Creates the battle scene
	 */
	public Scene init(int width, int height, int level) {
		this.level = level;
		
		root = new Group();
		battleScene = new Scene(root, width, height, Color.BLACK);
		
		Text levelText = createLevelText();
		root.getChildren().add(levelText);
		
		createPlayer();
		root.getChildren().add(player);
		
		lasers = new Group();
		root.getChildren().add(lasers);
		
		enemies = new Group();
		root.getChildren().add(enemies);
		
		battleScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		
		return battleScene;
	}
	
	private Text createLevelText() {
		Text levelText = new Text();
		levelText.setFill(Color.WHITE);
		levelText.setX(10);
		levelText.setY(Main.SIZE - 10);
		levelText.setText("Level " + level);
		
		return levelText;
	}
	
	private void createPlayer() {
		player = new Rectangle();
		player.setFill(Color.GREEN);
		player.setWidth(PLAYER_WIDTH);
		player.setHeight(PLAYER_HEIGHT);
		player.setX(Main.SIZE / 2 - PLAYER_WIDTH / 2);
		player.setY(Main.SIZE - PLAYER_HEIGHT / 2 - 75);	
	}
	
	private void shootLaser() {
		Rectangle laser = new Rectangle();
		laser.setFill(Color.YELLOW);
		laser.setWidth(LASER_SIZE);
		laser.setHeight(LASER_SIZE);
		laser.setX(player.getX() + PLAYER_WIDTH / 2 - LASER_SIZE / 2);
		laser.setY(player.getY() - LASER_SIZE);
	
		lasers.getChildren().add(laser);
	}
	
	public void step(double elapsedTime) {
		checkTimeUp(elapsedTime);
		
		moveLasersUp();
		moveEnemiesDown();
		checkLaserEnemyIntersect();
		checkPlayerEnemyIntersect();
		checkEnemyReachBottom();
		
		if (stepCounter % 100 == 0) {
			createEnemy();
		}
		stepCounter++;
	}
	
	private void checkTimeUp(double elapsedTime) {
		timer += elapsedTime;
		if (timer > TIMER_LIMIT) {
			// player wins level
			SceneManager.goToNextLevelScene(level + 1);
		}
	}
	
	private void moveLasersUp() {
		for (Node laserNode : lasers.getChildren()) {
			Rectangle laser = (Rectangle) laserNode;
			laser.setY(laser.getY() - 5);
		}
	}
	
	private void moveEnemiesDown() {
		for (Node enemyNode : enemies.getChildren()) {
			Rectangle enemy = (Rectangle) enemyNode;
			enemy.setY(enemy.getY() + 1);
		}
	}
	
	private void checkLaserEnemyIntersect() {
		outer: for (Node enemyNode : enemies.getChildren()) {
			Rectangle enemy = (Rectangle) enemyNode;
			for (Node laserNode : lasers.getChildren()) {
				Rectangle laser = (Rectangle) laserNode;
				if (laser.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
					enemies.getChildren().remove(enemyNode);
					lasers.getChildren().remove(laserNode);
					break outer;
				}
			}
		}
	}
	
	private void checkPlayerEnemyIntersect() {
		for (Node enemyNode : enemies.getChildren()) {
			Rectangle enemy = (Rectangle) enemyNode;
			if (player.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
				SceneManager.goToGameOverScene();
			}
		}
	}
	
	private void checkEnemyReachBottom() {
		for (Node enemyNode : enemies.getChildren()) {
			Rectangle enemy = (Rectangle) enemyNode;
			if (enemy.getY() + ENEMY_SIZE > Main.SIZE) {
				SceneManager.goToGameOverScene();
			}
		}
	}
	
	private void createEnemy() {
		Rectangle enemy = new Rectangle();
		enemy.setFill(Color.RED);
		enemy.setWidth(ENEMY_SIZE);
		enemy.setHeight(ENEMY_SIZE);
		enemy.setX(generateRandomEnemyX());
		enemy.setY(0);
		
		enemies.getChildren().add(enemy);
	}
	
	private double generateRandomEnemyX() {
		Random r = new Random();
		return (Main.SIZE - ENEMY_SIZE) * r.nextDouble();
	}
		
	private void handleKeyInput(KeyCode code) {
		switch(code) {
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
			case SPACE:
				shootLaser();
				break;
			case Q:
				// quit Battle and go back to Menu
				SceneManager.goToMenuScene();
				break;
			default:
				// do nothing
		}
	}
}
