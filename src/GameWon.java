import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameWon implements SceneInterface {
	private Scene gameWonScene;
	private Group root;
	
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
		Text gameWonText = new Text();
		gameWonText.setX(50);
		gameWonText.setY(50);
		gameWonText.setText("You won the game! Congrats!");
		
		root.getChildren().add(gameWonText);
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
