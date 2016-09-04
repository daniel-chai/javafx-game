import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Laser {
	private Rectangle laser;
	private int size;
	
	public Laser(int size, double playerX, double playerY, double playerWidth) {
		this.size = size;
		init(playerX, playerY, playerWidth);
	}
	
	private void init(double playerX, double playerY, double playerWidth) {
		laser = new Rectangle();
		laser.setFill(Color.YELLOW);
		laser.setWidth(size);
		laser.setHeight(size);
		laser.setX(playerX + playerWidth / 2 - size / 2);
		laser.setY(playerY - size);
	}
	
	public Rectangle getLaser() {
		return laser;
	}
}
