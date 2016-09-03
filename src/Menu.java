import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Menu implements SceneInterface {
	private Scene menuScene;
	private Group root;
	
	/**
	 * Creates the menu scene
	 */
	public Scene init(int width, int height) {
		root = new Group();
		menuScene = new Scene(root, width, height, Color.AZURE);
		
		Button startBtn = createStartBtn();
		Button instructBtn = createInstructBtn();
       
        root.getChildren().add(startBtn);
        root.getChildren().add(instructBtn);
       
        return menuScene;
	}
	
	private Button createStartBtn() {
		Button startBtn = new Button();
        startBtn.setText("Start Game");
        startBtn.setLayoutX(50);
        startBtn.setLayoutY(50);
        
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	SceneManager.goToBattleScene(0);
            }
        });
        
        return startBtn;
	}
	
	private Button createInstructBtn() {
		Button instructBtn = new Button();
        instructBtn.setText("Instructions");
        instructBtn.setLayoutX(50);
        instructBtn.setLayoutY(100);
        
        instructBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	SceneManager.goToInstructionsScene();
            }
        });
        
        return instructBtn;
	}
}
