import java.awt.geom.Point2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class represents a Laser that is shot by a character in the game. 
 * It is most suited to be used when a Laser item needs to be generated. 
 * Lasers can be used by any character. This class also defines how a laser
 * should move based on the direction it is headed in.
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public class Laser {
	public static final double MOVE_SHIFT = 5;
	
	private Rectangle laser;
	private double size;
	private String direction;
	
	/**
	 * Constructor for Laser class
	 * @param size size of the Laser
	 * @param color color of the Laser
	 * @param direction direction in which the Laser travels
	 * @param shooter shooter of the Laser
	 */
	public Laser(double size, Color color, String direction, Rectangle shooter) {
		this.size = size;
		this.direction = direction;
		
		Point2D.Double position = initPosition(shooter.getX(), shooter.getY(), shooter.getWidth(), shooter.getHeight());
		this.laser = UIGenerator.createRectangle(color, position.getX(), position.getY(), size, size);
	}
	
	/**
	 * @return the Rectangle representing a Laser
	 */
	public Rectangle getLaser() {
		return laser;
	}
	
	/**
	 * Move Laser in the correct direction
	 */
	public void moveLaser() {
		switch (direction) {
			case "UP":
				laser.setY(laser.getY() - MOVE_SHIFT);
				break;
			case "DOWN":
				laser.setY(laser.getY() + MOVE_SHIFT);
				break;
			case "LEFT":
				laser.setX(laser.getX() - MOVE_SHIFT);
				break;
			case "RIGHT":
				laser.setX(laser.getX() + MOVE_SHIFT);
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