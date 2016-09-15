import java.util.HashSet;
import java.util.Set;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * This class represents the BossBattle Scene in which the boss level takes place.
 * A different class is needed specifically for this Scene because there is a lot
 * of different functionality from the normal levels. However, it still extends 
 * the abstract superclass, SimpleBattle. Because of that, a lot of the basic
 * components such as the player and the lasers have already been instantiated. 
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
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
	
	/**
	 * Constructor for BossBattle class
	 * @param sceneManager SceneManager currently being used
	 */
	public BossBattle(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Returns the BossBattle Scene
	 */
	@Override
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
	
	/**
	 * Entry point for game loop
	 * @param elapsedTime seconds elapsed since last loop
	 */
	public void step(double elapsedTime) {
		if (stepCounter % 1000 == 0) {
			createPotion();
		}
		
		if (stepCounter % 10 == 0) {
			moveEnemyBoss();
		}
		
		if (stepCounter % 30 == 0 && stepCounter % 60 != 0) {
			shootEnemyLaser("UP");
			shootEnemyLaser("DOWN");
		}
		if (stepCounter % 60 == 0) {
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
			decreaseEnemyLives();
		}
	}
	
	private void checkEnemyLaserHitPlayer() {
		if (doLasersHitSingleRectangle(enemyLaserObjects, enemyLasers, player)) {
			decreasePlayerLives();
		}
	}
	
	private void decreaseEnemyLives() {
		if (enemyLives <= 1) {
			playerWins();
		}
		enemyLives--;
		enemyLivesText.setText("Enemy Lives: " + enemyLives);
	}
	
	private void decreasePlayerLives() {
		if (playerLives <= 1) {
			playerLoses();
		}
		playerLives--;
		playerLivesText.setText("Player Lives: " + playerLives);
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
			case SPACE: 
			// cheat code to decrease enemy lives by 1
				decreaseEnemyLives();
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