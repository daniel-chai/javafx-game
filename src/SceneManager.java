import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class handles all the scene changes. In the whole game, there is only one Stage that is used.
 * Every time a new Scene needs to be displayed, a method from this class can be called to set a 
 * particular Scene to be displayed on the Stage.
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public class SceneManager {
	public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
	private Stage stage;
	private Timeline animation;
	
	/**
	 * Constructor for SceneManager class
	 * @param primaryStage the Stage that this SceneManager uses
	 */
	public SceneManager(Stage primaryStage) {
		this.stage = primaryStage;
		this.animation = new Timeline();
		stage.show();
	}

	/**
	 * Sets the scene to be the Menu Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToMenuScene(SceneManager sceneManager) {
		animation.stop();
		Menu menu = new Menu(sceneManager);
		Scene menuScene = menu.init(Main.SIZE, Main.SIZE);
		stage.setScene(menuScene);
	}
	
	/**
	 * Sets the scene to be the Instructions Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToInstructionsScene(SceneManager sceneManager) {
		animation.stop();
		Instructions instructions = new Instructions(sceneManager);
		Scene instructionsScene = instructions.init(Main.SIZE, Main.SIZE);
		stage.setScene(instructionsScene);
	}
	
	/**
	 * Sets the scene to be GameOver Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToGameOverScene(SceneManager sceneManager) {
		animation.stop();
		GameOver gameOver = new GameOver(sceneManager);
		Scene gameOverScene = gameOver.init(Main.SIZE, Main.SIZE);
		stage.setScene(gameOverScene);
	}
	
	/**
	 * Sets the scene to be the GameWon Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToGameWonScene(SceneManager sceneManager) {
		animation.stop();
		GameWon gameWon = new GameWon(sceneManager);
		Scene gameWonScene = gameWon.init(Main.SIZE, Main.SIZE);
		stage.setScene(gameWonScene);
	}
	
	/**
	 * Sets the scene to be the NextLevel Scene
	 * @param sceneManager SceneManager currently being used
	 * @param level the next level
	 */
	public void goToNextLevelScene(SceneManager sceneManager, int level) {
		animation.stop();
		NextLevel nextLevel = new NextLevel(sceneManager, level);
		Scene nextLevelScene = nextLevel.init(Main.SIZE, Main.SIZE);
		stage.setScene(nextLevelScene);
	}
	
	/**
	 * Sets the scene to be Battle Scene
	 * @param sceneManager SceneManager currently being used
	 * @param level the next level
	 */
	public void goToBattleScene(SceneManager sceneManager, int level) {
		Battle battle = new Battle(sceneManager, level);
		Scene battleScene = battle.init(Main.SIZE, Main.SIZE);
		stage.setScene(battleScene);
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> battle.step(SECOND_DELAY));
		setGameLoop(frame);
	}
	
	/**
	 * Sets the scene to be the BossBattle Scene
	 * @param sceneManager SceneManager currently being used
	 */
	public void goToBossBattleScene(SceneManager sceneManager) {
		BossBattle bossBattle = new BossBattle(sceneManager);
		Scene bossBattleScene = bossBattle.init(Main.SIZE, Main.SIZE);
		stage.setScene(bossBattleScene);
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> bossBattle.step(SECOND_DELAY));
		setGameLoop(frame);
	}
	
	private void setGameLoop(KeyFrame frame) {
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
}