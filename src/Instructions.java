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
		
		Button menuButton = createMenuButton();
		root.getChildren().add(menuButton);
		
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
	
	private Button createMenuButton() {
		Button menuButton = new Button();
        menuButton.setText("Back to Menu");
        menuButton.setLayoutX(50);
        menuButton.setLayoutY(100);
        
        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	SceneManager.goToMenuScene();
            }
        });
        
        return menuButton;
	}
}
