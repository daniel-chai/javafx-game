import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
	private static Stage stage;
	
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
	
	public static void goToBattleScene() {
		Battle battle = new Battle();
		Scene battleScene = battle.init(Main.SIZE, Main.SIZE);
		stage.setScene(battleScene);
	}
}
