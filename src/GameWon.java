import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * This class represents the GameWon Scene for when the player wins the game. 
 * This Scene should only be accessible from the BossBattle Scene, as the player
 * must beat all the levels to win the game. From this Scene, the player can
 * go back to the Menu Scene to play again.
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public class GameWon implements SceneInterface {
	private SceneManager sceneManager;
	private Scene gameWonScene;
	private Group root;
	
	/**
	 * Constructor for GameWon class
	 * @param sceneManager SceneManager currently being used
	 */
	public GameWon(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Returns the GameWon Scene
	 */
	@Override
	public Scene init(int width, int height) {
		root = new Group();
		gameWonScene = new Scene(root, width, height, Color.AZURE);
		
		addGameWonText();
		addMenuButton();
		
		return gameWonScene;
	}
	
	private void addGameWonText() {
		Text gameWonText = UIGenerator.createText("You won the game! Congrats!", 50, 50);
		root.getChildren().add(gameWonText);
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