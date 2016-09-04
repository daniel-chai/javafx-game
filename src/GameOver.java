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
	 * adds the GameOver Scene
	 */
	public Scene init(int width, int height) {
		root = new Group();
		gameOverScene = new Scene(root, width, height, Color.AZURE);
		
		addGameOverText();
		addMenuButton();
		
		return gameOverScene;
	}
	
	private void addGameOverText() {
		Text gameOverText = new Text();
		gameOverText.setX(50);
		gameOverText.setY(50);
		gameOverText.setText("GAME OVER");
		
		root.getChildren().add(gameOverText);
	}
	
	private void addMenuButton() {
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
		
		root.getChildren().add(menuButton);
	}
}
