import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
	private Rectangle player;
	private int width;
	private int height;
	
	public Player(int width, int height) {
		this.width = width;
		this.height = height;
		init();
	}
	
	private void init() {
		player = new Rectangle();
		player.setFill(Color.GREEN);
		player.setWidth(width);
		player.setHeight(height);
		player.setX(Main.SIZE / 2 - width / 2);
		player.setY(Main.SIZE - height - 25);	
	}
	
	public Rectangle getPlayer() {
		return player;
	}
}