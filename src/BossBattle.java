import java.util.HashSet;
import java.util.Set;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BossBattle extends SimpleBattle implements SceneInterface {
	private Scene bossBattleScene;
	
	private EnemyBoss enemyBossObject;
	private Rectangle enemyBoss;
	private Set<Laser> enemyLaserObjects;
	private Group enemyLasers;
	private Group potions;
	
	private int playerLives = 5;
	private int enemyLives = 5;
	private Text playerLivesText;
	private Text enemyLivesText;
	
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
		addLivesText();
		addPlayer();
		addPotions();
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
	
	private void addLivesText() {
		playerLivesText = UIGenerator.createText("Player Lives: " + playerLives, 435, Main.SIZE - 10);
		playerLivesText.setFill(Color.WHITE);
		root.getChildren().add(playerLivesText);
		
		enemyLivesText = UIGenerator.createText("Enemy Lives: " + enemyLives, 435, 25);
		enemyLivesText.setFill(Color.WHITE);
		root.getChildren().add(enemyLivesText);
	}
	
	private void addPotions() {
		potions = new Group();
		root.getChildren().add(potions);
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
		if (stepCounter % 1000 == 0) {
			createPotion();
		}
		
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
		checkPlayerPotionIntersect();
		
		stepCounter++;
	}
	
	private void createPotion() {
		Potion potionObject = new Potion();
		Rectangle potion = potionObject.getPotion();
		potions.getChildren().add(potion);
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
			if (enemyLives <= 1) {
				playerWins();
			}
			enemyLives--;
			enemyLivesText.setText("Enemy Lives: " + enemyLives);
		}
	}
	
	private void checkEnemyLaserHitPlayer() {
		if (doLasersHitSingleRectangle(enemyLaserObjects, enemyLasers, player)) {
			if (playerLives <= 1) {
				playerLoses();
			}
			playerLives--;
			playerLivesText.setText("Player Lives: " + playerLives);
		}
	}
	
	private void checkLaserIntersect() {
		checkPlayerLaserHitRectangleInGroup(enemyLasers, false);
	}
	
	private void checkPlayerEnemyIntersect() {
		playerLosesIfIntersectEnemy(enemyBoss);
	}
	
	private void checkPlayerPotionIntersect() {
		for (Node node : potions.getChildren()) {
			Rectangle potion = (Rectangle) node;
			if (potion.getBoundsInLocal().intersects(player.getBoundsInLocal())) {
				potions.getChildren().remove(potion);
				playerLives++;
				playerLivesText.setText("Player Lives: " + playerLives);
				break;
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
