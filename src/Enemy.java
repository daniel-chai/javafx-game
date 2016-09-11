import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class represents an Emeny that is present in the four normal levels.
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public class Enemy {
	public static final double WIDTH = 50;
	public static final double HEIGHT = 50;
	
	private Rectangle enemy;
	
	/**
	 * Constructor for Enemy class
	 */
	public Enemy() {
		this.enemy = UIGenerator.createRectangle(Color.RED, generateRandomEnemyX(), 0, WIDTH, HEIGHT);
	}
	
	/**
	 * @return the Rectangle representing the Enemy
	 */
	public Rectangle getEnemy() {
		return enemy;
	}
	
	private double generateRandomEnemyX() {
		Random r = new Random();
		return (Main.SIZE - WIDTH) * r.nextDouble();
	}
}