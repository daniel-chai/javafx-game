import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class NextLevel {
	private SceneManager sceneManager;
	private Scene nextLevelScene;
	private Group root;
	
	private int level;
	
	public NextLevel(SceneManager sceneManager, int level) {
		this.sceneManager = sceneManager;
		this.level = level;
	}
	
	/**
	 * adds the NextLevel scene
	 */
	public Scene init(int width, int height) {
		root = new Group();
		nextLevelScene = new Scene(root, width, height, Color.AZURE);
		
		addNextLevelButton();
		
		return nextLevelScene;
	}
	
	private void addNextLevelButton() {
		String text = isNextLevelBoss() ? "Go to boss level" : "Go to level " + level;
		Button nextLevelButton = UIGenerator.createButton(text, 50, 50);
		
        nextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if (isNextLevelBoss()) {
            		sceneManager.goToBossBattleScene(sceneManager);
            	}
            	else {
            		sceneManager.goToBattleScene(sceneManager, level);
            	}
            }
        });
        
        root.getChildren().add(nextLevelButton);
	}
	
	private boolean isNextLevelBoss() {
		return level >= Battle.TOTAL_LEVELS ? true : false;
	}
}
