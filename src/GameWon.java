import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameWon implements SceneInterface {
	private SceneManager sceneManager;
	private Scene gameWonScene;
	private Group root;
	
	public GameWon(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Creates GameWon scene
	 */
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
