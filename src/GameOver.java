import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameOver implements SceneInterface {
	private SceneManager sceneManager;
	private Scene gameOverScene;
	private Group root;
	
	public GameOver(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Creates the GameOver Scene
	 */
	public Scene init(int width, int height) {
		root = new Group();
		gameOverScene = new Scene(root, width, height, Color.AZURE);
		
		addGameOverText();
		addMenuButton();
		
		return gameOverScene;
	}
	
	private void addGameOverText() {
		Text gameOverText = UIGenerator.createText("GAME OVER", 50, 50);
		root.getChildren().add(gameOverText);
	}
	
	private void addMenuButton() {
		Button menuButton = UIGenerator.createButton("Go to Menu", 50, 100);
		
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sceneManager.goToMenuScene(sceneManager);
            }
        });
		
		root.getChildren().add(menuButton);
	}
}
