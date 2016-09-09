import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
	private Rectangle player;
	private int width;
	private int height;
	
	public Player(int width, int height) {
		this.width = width;
		this.height = height;
		this.player = UIGenerator.createRectangle(Color.GREEN, Main.SIZE / 2 - width / 2, Main.SIZE - height - 25, width, height);
	}
	
	public Rectangle getPlayer() {
		return player;
	}
}