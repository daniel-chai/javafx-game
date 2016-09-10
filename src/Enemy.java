import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Enemy {
	public static final double WIDTH = 50;
	public static final double HEIGHT = 50;
	
	private Rectangle enemy;
	
	public Enemy() {
		this.enemy = UIGenerator.createRectangle(Color.RED, generateRandomEnemyX(), 0, WIDTH, HEIGHT);
	}
	
	public Rectangle getEnemy() {
		return enemy;
	}
	
	private double generateRandomEnemyX() {
		Random r = new Random();
		return (Main.SIZE - WIDTH) * r.nextDouble();
	}
}
