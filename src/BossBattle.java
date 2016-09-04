import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BossBattle {
	private static final int PLAYER_WIDTH = 50;
	private static final int PLAYER_HEIGHT = 50;
	private static final int ARROW_SHIFT = 10;
	private static final int LASER_SIZE = 10;
	private static final int ENEMYBOSS_WIDTH = 50;
	private static final int ENEMYBOSS_HEIGHT = 50;
	
	private Scene bossBattleScene;
	private Group root;
	private Rectangle player;
	private Rectangle enemyBoss;
	
	public Scene init(int width, int height) {
		root = new Group();
		bossBattleScene = new Scene(root, width, height, Color.BLACK);
		
		addBossLevelText();
		addPlayer();
		addEnemyBoss();
		
		return bossBattleScene;
	}
	
	private void addBossLevelText() {
		Text bossLevelText = new Text();
		bossLevelText.setFill(Color.WHITE);
		bossLevelText.setX(10);
		bossLevelText.setY(Main.SIZE - 10);
		bossLevelText.setText("Boss Level");
		
		root.getChildren().add(bossLevelText);
	}
	
	private void addPlayer() {
		Player playerObject = new Player(PLAYER_WIDTH, PLAYER_HEIGHT);
		player = playerObject.getPlayer();
		root.getChildren().add(player);
	}
	
	private void addEnemyBoss() {
		EnemyBoss enemyBossObject = new EnemyBoss(ENEMYBOSS_WIDTH, ENEMYBOSS_HEIGHT);
		enemyBoss = enemyBossObject.getEnemyBoss();
		root.getChildren().add(enemyBoss);
	}
}
