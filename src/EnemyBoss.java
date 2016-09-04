import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemyBoss {
	private Rectangle enemyBoss;
	private int width;
	private int height;
	
	public EnemyBoss(int width, int height) {
		this.width = width;
		this.height = height;
		init();	
	}
	
	private void init() {
		enemyBoss = new Rectangle();
		enemyBoss.setFill(Color.RED);
		enemyBoss.setWidth(width);
		enemyBoss.setHeight(height);
		enemyBoss.setX(Main.SIZE / 2 - width / 2);
		enemyBoss.setY(25);	
	}
	
	public Rectangle getEnemyBoss() {
		return enemyBoss;
	}
}
