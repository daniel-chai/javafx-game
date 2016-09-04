import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy {
	private Rectangle enemy;
	private int width;
	private int height;
	
	public Enemy(int width, int height) {
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init() {
		enemy = new Rectangle();
		enemy.setFill(Color.RED);
		enemy.setWidth(width);
		enemy.setHeight(height);
		enemy.setX(generateRandomEnemyX());
		enemy.setY(0);	
	}
	
	private double generateRandomEnemyX() {
		Random r = new Random();
		return (Main.SIZE - width) * r.nextDouble();
	}
	
	public Rectangle getEnemy() {
		return enemy;
	}
}
