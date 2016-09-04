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
	private double TIMER_LIMIT = 10.0;
	private long stepCounter = 0L;
	
	/**
	 * Creates the battle scene
	 */
	public Scene init(int width, int height, int level) {
		this.level = level;
		
		root = new Group();
		battleScene = new Scene(root, width, height, Color.BLACK);
		
		addLevelText();
		addPlayer();
		addLasers();
		addEnemies();
		
		battleScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
		
		return battleScene;
	}
	
	private void addLevelText() {
		Text levelText = new Text();
		levelText.setFill(Color.WHITE);
		levelText.setX(10);
		levelText.setY(Main.SIZE - 10);
		levelText.setText("Level " + level);
		
		root.getChildren().add(levelText);
	}
	
	private void addPlayer() {
		Player playerObject = new Player(PLAYER_WIDTH, PLAYER_HEIGHT);
		player = playerObject.getPlayer();
		root.getChildren().add(player);
	}
	
	private void addLasers() {
		lasers = new Group();
		root.getChildren().add(lasers);
	}
	
	private void addEnemies() {
		enemies = new Group();
		root.getChildren().add(enemies);
	}
	
	private int getEnemyCreationRate() {
		int[] enemyCreationRate = {150, 125, 100, 75};
		return enemyCreationRate[level];
	}
	
	private double getEnemyTravelRate() {
		double[] enemyTravelRate = {0.9, 1, 1.1, 1.2};
		return enemyTravelRate[level];
	}
	
	public void step(double elapsedTime) {
		checkTimeUp(elapsedTime);
		
		if (stepCounter % getEnemyCreationRate() == 0) {
			createEnemy();
		}
		stepCounter++;
		
		moveLasersUp();
		moveEnemiesDown();
		checkLaserEnemyIntersect();
		checkPlayerEnemyIntersect();
		checkEnemyReachBottom();	
	}
	
	private void createEnemy() {
		Enemy enemyObject = new Enemy(ENEMY_SIZE, ENEMY_SIZE);
		Rectangle enemy = enemyObject.getEnemy();
		enemies.getChildren().add(enemy);
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
			enemy.setY(enemy.getY() + getEnemyTravelRate());
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
		
	private void shootLaser() {
		Laser laserObject = new Laser(LASER_SIZE, player.getX(), player.getY(), PLAYER_WIDTH);
		Rectangle laser = laserObject.getLaser();	
		lasers.getChildren().add(laser);
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
