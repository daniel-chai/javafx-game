import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BossBattle {
	private Scene bossBattleScene;
	private Group root;
	
	public Scene init(int width, int height) {
		root = new Group();
		bossBattleScene = new Scene(root, width, height, Color.BLACK);
		
		Text bossLevelText = createBossLevelText();
		root.getChildren().add(bossLevelText);
		
		return bossBattleScene;
	}
	
	private Text createBossLevelText() {
		Text levelText = new Text();
		levelText.setFill(Color.WHITE);
		levelText.setX(10);
		levelText.setY(Main.SIZE - 10);
		levelText.setText("Boss Level");
		
		return levelText;
	}
}
