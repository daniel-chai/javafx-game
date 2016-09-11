import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Potion {
	public static final double SIZE = 20;
	
	private Rectangle potion;
	
	public Potion() {
		this.potion = UIGenerator.createRectangle(Color.MEDIUMPURPLE, generateRandomXorY(), generateRandomXorY(), SIZE, SIZE);
	}
	
	public Rectangle getPotion() {
		return potion;
	}
	
	private double generateRandomXorY() {
		Random r = new Random();
		return (Main.SIZE - SIZE) * r.nextDouble();
	}
}
