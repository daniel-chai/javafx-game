import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * This class represents the GameOver Scene for when the player loses the game.
 * There should be a path to this Scene from every level, and this Scene can 
 * lead back to the Menu Scene.
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public class GameOver implements SceneInterface {
	private SceneManager sceneManager;
	private Scene gameOverScene;
	private Group root;
	
	/**
	 * Constructor for GameOver class
	 * @param sceneManager SceneManager currently being used
	 */
	public GameOver(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Returns the GameOver Scene
	 */
	@Override
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