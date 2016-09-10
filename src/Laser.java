import java.awt.geom.Point2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Laser {
	private Rectangle laser;
	private double size;
	private String direction;
	
	public Laser(double size, Color color, String direction, Rectangle shooter) {
		this.size = size;
		this.direction = direction;
		
		Point2D.Double position = initPosition(shooter.getX(), shooter.getY(), shooter.getWidth(), shooter.getHeight());
		this.laser = UIGenerator.createRectangle(color, position.getX(), position.getY(), size, size);
	}
	
	public Rectangle getLaser() {
		return laser;
	}
	
	public void moveLaser() {
		switch (direction) {
			case "UP":
				laser.setY(laser.getY() - 5);
				break;
			case "DOWN":
				laser.setY(laser.getY() + 5);
				break;
			case "LEFT":
				laser.setX(laser.getX() - 5);
				break;
			case "RIGHT":
				laser.setX(laser.getX() + 5);
				break;
			default:
				// do nothing
		}
	}
	
	private Point2D.Double initPosition(double shooterX, double shooterY, double shooterWidth, double shooterHeight) {
		Point2D.Double position = new Point2D.Double();
		
		switch (direction) {
			case "UP": 
				position.setLocation(shooterX + shooterWidth / 2 - size / 2, shooterY - size);
				break;
			case "DOWN":
				position.setLocation(shooterX + shooterWidth / 2 - size / 2, shooterY + shooterHeight);
				break;
			case "LEFT":
				position.setLocation(shooterX - size, shooterY + shooterHeight / 2 - size / 2);
				break;
			case "RIGHT":
				position.setLocation(shooterX + shooterWidth, shooterY + shooterHeight / 2 - size / 2);
				break;
			default:
				// do nothing
		}
		
		return position;
	}
}
