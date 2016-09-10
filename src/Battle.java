import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Battle extends SimpleBattle {
	private Scene battleScene;
	private int level;
	
	private Group enemies;
	
	private double timer = 0.0;
	private static double TIMER_LIMIT = 5.0;
	
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
	
	private void playerWinsLevel() {
		sceneManager.goToNextLevelScene(sceneManager, level + 1);
	}
	
	private void addLevelText() {
		addBottomLeftText("Level " + level);
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
	
	private void checkPlayerLaserHitEnemy() {
		checkPlayerLaserHitRectangleInGroup(enemies, true);
	}
	
	private void checkPlayerEnemyIntersect() {
		for (Node enemyNode : enemies.getChildren()) {
			Rectangle enemy = (Rectangle) enemyNode;
			playerLosesIfIntersectEnemy(enemy);
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
				quitToMenu();
				break;
			case W: 
				shootPlayerLaser("UP");
				break;
			default:
				playerObject.handlePlayerKey(code);
		}
	}
}
