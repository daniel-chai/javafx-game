import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SceneManager {
	public static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
	private static Stage stage;
	private static Timeline animation;
	
	/**
	 * Initiates the SceneManager
	 */
	public static void init(Stage primaryStage) {
		stage = primaryStage;
		stage.show();
	}
	
	public static void goToMenuScene() {
		Menu menu = new Menu();
		Scene menuScene = menu.init(Main.SIZE, Main.SIZE);
		stage.setScene(menuScene);
	}
	
	public static void goToInstructionsScene() {
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
	
	public static void goToBattleScene() {
		Battle battle = new Battle();
		Scene battleScene = battle.init(Main.SIZE, Main.SIZE);
		stage.setScene(battleScene);
		
		// sets the game's loop
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> battle.step(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
}
