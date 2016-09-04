import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class NextLevel {
	private Scene nextLevelScene;
	private Group root;
	
	private int level;
	
	/**
	 * adds the NextLevel scene
	 */
	public Scene init(int width, int height, int level) {
		this.level = level;
		
		root = new Group();
		nextLevelScene = new Scene(root, width, height, Color.AZURE);
		
		addNextLevelButton();
		
		return nextLevelScene;
	}
	
	private void addNextLevelButton() {
		Button nextLevelButton = new Button();
        nextLevelButton.setLayoutX(50);
        nextLevelButton.setLayoutY(50);
        
        if (isNextLevelBoss()) {
        	nextLevelButton.setText("Go to boss level");
        }
        else {
        	nextLevelButton.setText("Go to level " + level);
        }
        
        nextLevelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if (isNextLevelBoss()) {
            		SceneManager.goToBossBattleScene();
            	}
            	else {
            		SceneManager.goToBattleScene(level);
            	}
            }
        });
        
        root.getChildren().add(nextLevelButton);
	}
	
	private boolean isNextLevelBoss() {
		return level >= 2 ? true : false;
	}
}
