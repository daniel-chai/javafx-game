import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameOver implements SceneInterface {
	private Scene gameOverScene;
	private Group root;
	
	/**
	 * Creates the GameOver Scene
	 */
	public Scene init(int width, int height) {
		root = new Group();
		gameOverScene = new Scene(root, width, height, Color.AZURE);
		
		Text gameOverText = createGameOverText();
		root.getChildren().add(gameOverText);
		
		Button menuButton = createMenuButton();
		root.getChildren().add(menuButton);
		
		return gameOverScene;
	}
	
	private Text createGameOverText() {
		Text gameOverText = new Text();
		gameOverText.setX(50);
		gameOverText.setY(50);
		gameOverText.setText("GAME OVER");
		
		return gameOverText;
	}
	
	private Button createMenuButton() {
		Button menuButton = new Button();
		menuButton.setLayoutX(50);
		menuButton.setLayoutY(100);
		menuButton.setText("Go to Menu");
		
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	SceneManager.goToMenuScene();
            }
        });
		
		return menuButton;
	}
}
