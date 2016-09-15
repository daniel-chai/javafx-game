import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class represents the Potion item that is present in the boss level.
 * It is most suited to be used when a Potion item needs to be generated. 
 * The class itself actually doesn't do much, because a Potion may need to
 * be used flexibly in different ways. This class just provides the item template.
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