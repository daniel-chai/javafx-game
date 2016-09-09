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
		this.enemy = UIGenerator.createRectangle(Color.RED, generateRandomEnemyX(), 0, width, height);
	}
	
	public Rectangle getEnemy() {
		return enemy;
	}
	
	private double generateRandomEnemyX() {
		Random r = new Random();
		return (Main.SIZE - width) * r.nextDouble();
	}
}
