import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Battle {
	private Scene battleScene;
	
	/**
	 * Creates the battle scene
	 */
	public Scene init(int width, int height) {
		Group root = new Group();
		battleScene = new Scene(root, width, height, Color.BLACK);
		
		return battleScene;
	}
	
	
}
