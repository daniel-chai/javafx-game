import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Instructions {
	private Scene instructionsScene;
	private Group root;
	
	/**
	 * Creates the instructions scene
	 */
	public Scene init(int width, int height) {
		root = new Group();
		instructionsScene = new Scene(root, width, height, Color.AZURE);
       
		Text instructionsText = createInstructionsText();
		root.getChildren().add(instructionsText);
		
		Button backButton = createBackButton();
		root.getChildren().add(backButton);
		
        return instructionsScene;
	}
	
	private Text createInstructionsText() {
		Text instructionsText = new Text();
		instructionsText.setX(50);
		instructionsText.setY(50);
		instructionsText.setText("Arrow keys to move.\n" + 
								"Space to shoot lasers.\n" +
								"Q to quit to menu.");
		
		return instructionsText;
	}
	
	private Button createBackButton() {
		Button backButton = new Button();
        backButton.setText("Back to Menu");
        backButton.setLayoutX(50);
        backButton.setLayoutY(200);
        
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	SceneManager.goToMenuScene();
            }
        });
        
        return backButton;
	}
}
