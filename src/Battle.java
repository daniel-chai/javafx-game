import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Battle {
	public static final int PLAYER_WIDTH = 50;
	public static final int PLAYER_HEIGHT = 50;
	public static final int ARROW_SHIFT = 10;
	public static final int ENEMY_SIZE = 50;
	public static final int LASER_SIZE = 10;
	
	private Scene battleScene;
	private Group root;
	private Rectangle player;
	private Group enemies;
	private Group lasers;
	
	private int level;
	private double timer = 0.0;
	private static double TIMER_LIMIT = 0.0;
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
		addEnemies();
		addLasers();
		
		battleScene.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
		
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
	
	private void addEnemies() {
		enemies = new Group();
		root.getChildren().add(enemies);
	}
	
	private void addLasers() {
		lasers = new Group();
		root.getChildren().add(lasers);
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
		
		moveEnemiesDown();
		moveLasersUp();
		checkLaserHitEnemy();
		checkPlayerEnemyIntersect();
		checkEnemyReachBottom();	
		
		stepCounter++;
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
	
	private void checkLaserHitEnemy() {
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
			case W:
				shootLaser("UP");
				break;
			case Q:
				// quit Battle and go back to Menu
				SceneManager.goToMenuScene();
				break;
			default:
				// do nothing
		}
	}
	
	private void shootLaser(String direction) {
		Laser laserObject = new Laser(LASER_SIZE, Color.YELLOW, direction, player);
		Rectangle laser = laserObject.getLaser();	
		lasers.getChildren().add(laser);
	}
}
