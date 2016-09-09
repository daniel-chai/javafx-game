import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemyBoss {
	private Rectangle enemyBoss;
	private int width;
	private int height;
	
	public EnemyBoss(int width, int height) {
		this.width = width;
		this.height = height;
		this.enemyBoss = UIGenerator.createRectangle(Color.RED, Main.SIZE / 2 - width / 2, 25, width, height);
	}
	
	public Rectangle getEnemyBoss() {
		return enemyBoss;
	}
}
