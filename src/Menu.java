import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Menu implements SceneInterface {
	private SceneManager sceneManager;
	private Scene menuScene;
	private Group root;
	
	public Menu(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Creates the menu scene
	 */
	public Scene init(int width, int height) {
		root = new Group();
		menuScene = new Scene(root, width, height, Color.AZURE);
		
		addStartButton();
		addInstructionsButton();
		
        return menuScene;
	}
	
	private void addStartButton() {
		Button startButton = UIGenerator.createButton("Start Game", 50, 50);
		
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sceneManager.goToBattleScene(sceneManager, 0);
            }
        });
        
        root.getChildren().add(startButton);
	}
	
	private void addInstructionsButton() {
		Button instructionsButton = UIGenerator.createButton("Instructions", 50, 100);
		
        instructionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sceneManager.goToInstructionsScene(sceneManager);
            }
        });
        
        root.getChildren().add(instructionsButton);
	}
}
