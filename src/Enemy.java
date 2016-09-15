import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class represents an Enemy character that is present in the four normal levels.
 * It is most suited to be used when an Enemy character needs to be generated. The class
 * essentially only instantiates an Enemy object, without adding much additional functionality.
 * The enemies only move straight down from the screen, so public methods are not needed. 
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