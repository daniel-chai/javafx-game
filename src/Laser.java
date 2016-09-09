import java.awt.geom.Point2D;

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
		
		Point2D.Double position = initPosition(r.getX(), r.getY(), r.getWidth(), r.getHeight());
		this.laser = UIGenerator.createRectangle(color, position.getX(), position.getY(), size, size);
	}
	
	public Rectangle getLaser() {
		return laser;
	}
	
	private Point2D.Double initPosition(double X, double Y, double width, double height) {
		Point2D.Double position = new Point2D.Double();
		
		if (direction.equals("UP")) {
			position.setLocation(X + width / 2 - size / 2, Y - size);
		}
		if (direction.equals("DOWN")) {
			position.setLocation(X + width / 2 - size / 2, Y + height);
		}
		if (direction.equals("LEFT")) {
			position.setLocation(X - size, Y + height / 2 - size / 2);
		}
		if (direction.equals("RIGHT")) {
			position.setLocation(X + width, Y + height / 2 - size / 2);
		}
		
		return position;
	}
}
