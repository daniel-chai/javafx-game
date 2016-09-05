import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Laser {
	private Rectangle laser;
	private int size;
	private Color color;
	private String direction;
	
	public Laser(int size, Color color, String direction, Rectangle r) {
		this.size = size;
		this.color = color;
		this.direction = direction;
		init();
		initPosition(r.getX(), r.getY(), r.getWidth(), r.getHeight());
	}
	
	private void init() {
		laser = new Rectangle();
		laser.setFill(color);
		laser.setWidth(size);
		laser.setHeight(size);
	}
	
	private void initPosition(double X, double Y, double width, double height) {
		if (direction.equals("UP")) {
			laser.setX(X + width / 2 - size / 2);
			laser.setY(Y - size);
		}
		if (direction.equals("DOWN")) {
			laser.setX(X + width / 2 - size / 2);
			laser.setY(Y + height);
		}
		if (direction.equals("LEFT")) {
			laser.setX(X - size);
			laser.setY(Y + height / 2 - size / 2);
		}
		if (direction.equals("RIGHT")) {
			laser.setX(X + width);
			laser.setY(Y + height / 2 - size / 2);
		}
	}
	
	public Rectangle getLaser() {
		return laser;
	}
}
