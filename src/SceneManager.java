import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneManager {
	public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
    // TODO: NO STATIC VARIABLES!!!
	private static Stage stage;
	private static Timeline animation;
	
	/**
	 * Initiates the SceneManager
	 */
	public static void init(Stage primaryStage) {
		animation = new Timeline();
		stage = primaryStage;
		stage.show();
	}
	
	public static void goToMenuScene() {
		animation.stop();
		Menu menu = new Menu();
		Scene menuScene = menu.init(Main.SIZE, Main.SIZE);
		stage.setScene(menuScene);
	}
	
	public static void goToInstructionsScene() {
		animation.stop();
		Instructions instructions = new Instructions();
		Scene instructionsScene = instructions.init(Main.SIZE, Main.SIZE);
		stage.setScene(instructionsScene);
	}
	
	public static void goToGameOverScene() {
		animation.stop();
		GameOver gameOver = new GameOver();
		Scene gameOverScene = gameOver.init(Main.SIZE, Main.SIZE);
		stage.setScene(gameOverScene);
	}
	
	public static void goToGameWonScene() {
		animation.stop();
		GameWon gameWon = new GameWon();
		Scene gameWonScene = gameWon.init(Main.SIZE, Main.SIZE);
		stage.setScene(gameWonScene);
	}
	
	public static void goToNextLevelScene(int level) {
		animation.stop();
		NextLevel nextLevel = new NextLevel();
		Scene nextLevelScene = nextLevel.init(Main.SIZE, Main.SIZE, level);
		stage.setScene(nextLevelScene);
	}
	
	public static void goToBattleScene(int level) {
		Battle battle = new Battle();
		Scene battleScene = battle.init(Main.SIZE, Main.SIZE, level);
		stage.setScene(battleScene);
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> battle.step(SECOND_DELAY));
		setGameLoop(frame);
	}
	
	public static void goToBossBattleScene() {
		BossBattle bossBattle = new BossBattle();
		Scene bossBattleScene = bossBattle.init(Main.SIZE, Main.SIZE);
		stage.setScene(bossBattleScene);
		
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> bossBattle.step(SECOND_DELAY));
		setGameLoop(frame);
	}
	
	private static void setGameLoop(KeyFrame frame) {
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
}
