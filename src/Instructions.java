import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * This class represents the Instructions Scene where the instructions are listed.
 * It should be accessible from the Menu Scene. From the Instructions Scene, the 
 * player can go back to the Menu to actually start the game.
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public class Instructions implements SceneInterface {
	private SceneManager sceneManager;
	private Scene instructionsScene;
	private Group root;
	
	/**
	 * Constructor for Instructions class
	 * @param sceneManager SceneManager currently being used
	 */
	public Instructions(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Returns the Instructions Scene
	 */
	@Override
	public Scene init(int width, int height) {
		root = new Group();
		instructionsScene = new Scene(root, width, height, Color.AZURE);		
		
		addInstructionsText();
		addMenuButton();
		
        return instructionsScene;
	}
	
	private void addInstructionsText() {
		String text = "There are four normal levels and then a boss level.\n" +
				"You, as the player, are the green rectangle.\n" +
				"The enemies are red rectangles.\n" +
				"Use the arrow keys to move your player around.\n" +
				"Press Q to quit to the menu at any time.\n" +
				"\n" +
				
				"THE FOUR NORMAL LEVELS: \n" +
				"Each level lasts for 20 seconds.\n" +
				"The enemies come from the top and move down to the bottom.\n" +
				"Press W to shoot a laser up to kill enemies.\n" +
				"If an emeny reaches the bottom, you lose.\n" +
				"If you run into an enemy, you also lose.\n" +
				"If you last for 20 seconds, you progress to the next round.\n" +
				"Each progressive level has more and faster enemies.\n" +
				"\n" +
				
				"THE BOSS LEVEL: \n" +
				"If you beat the four normal levels, you progress to the boss level.\n" +
				"There is only one enemy boss, who moves around and shoots lasers.\n" +
				"You can shoot a laser in any direction with WASD.\n" +
				"You have 5 lives, and the enemy boss has 5 lives.\n" +
				"Whoever gets hit by a laser loses a life.\n" +
				"But if your laser intersects an enemy boss's laser, your laser disappears.\n" +
				"If you reach 0 lives first, you lose.\n" +
				"If the enemy boss reaches 0 lives first, you win.\n" +
				"But if you run into the enemy boss, you automatically lose.\n" +
				"Purple potions appear periodically.\n" +
				"Collect a potion by running into it, and you will gain one life.";
				
		Text instructionsText = UIGenerator.createText(text, 30, 40, 15);
		
		root.getChildren().add(instructionsText);
	}
	
	private void addMenuButton() {
		Button menuButton = UIGenerator.createButton("Back to Menu", 30, 540);
        
        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	sceneManager.goToMenuScene(sceneManager);
            }
        });
        
        root.getChildren().add(menuButton);
	}
}