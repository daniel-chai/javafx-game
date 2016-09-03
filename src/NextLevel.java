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
	 * Creates the NextLevel scene
	 */
	public Scene init(int width, int height, int level) {
		this.level = level;
		
		root = new Group();
		nextLevelScene = new Scene(root, width, height, Color.AZURE);
		
		Button nextLevelButton = createNextLevelButton();
		root.getChildren().add(nextLevelButton);
		
		return nextLevelScene;
	}
	
	private Button createNextLevelButton() {
		Button nextLevelBtn = new Button();
        nextLevelBtn.setLayoutX(50);
        nextLevelBtn.setLayoutY(50);
        
        if (isNextLevelBoss()) {
        	nextLevelBtn.setText("Go to boss level");
        }
        else {
        	nextLevelBtn.setText("Go to level " + level);
        }
        
        nextLevelBtn.setOnAction(new EventHandler<ActionEvent>() {
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
        
        return nextLevelBtn;
	}
	
	private boolean isNextLevelBoss() {
		return level >= 4 ? true : false;
	}
}
