import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class represents the Potion that is present in the boss level.
 * It is used for healing the player (increasing the player's lives).
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public class Potion {
	public static final double SIZE = 20;
	
	private Rectangle potion;
	
	/**
	 * Constructor for Potion class
	 */
	public Potion() {
		this.potion = UIGenerator.createRectangle(Color.MEDIUMPURPLE, generateRandomXorY(), generateRandomXorY(), SIZE, SIZE);
	}
	
	/**
	 * @return the Rectangle representing the Potion
	 */
	public Rectangle getPotion() {
		return potion;
	}
	
	private double generateRandomXorY() {
		Random r = new Random();
		return (Main.SIZE - SIZE) * r.nextDouble();
	}
}