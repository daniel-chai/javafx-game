import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
	public static final double WIDTH = 50;
	public static final double HEIGHT = 50;
	public static final double LASER_SIZE = 10;
	public static final double MOVE_SHIFT = 15;
	
	private Rectangle player;
	
	public Player() {
		double xPosition = Main.SIZE / 2 - WIDTH / 2;
		double yPosition = Main.SIZE - HEIGHT - 25;
		this.player = UIGenerator.createRectangle(Color.GREEN, xPosition, yPosition, WIDTH, HEIGHT);
	}
	
	public Rectangle getPlayer() {
		return player;
	}
	
	public void handlePlayerKey(KeyCode code) {
		switch (code) {
			case LEFT:
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
			case UP:
				moveUp();
				break;
			case DOWN:
				moveDown();
				break;
			default:
				// do nothing
		}
	}
	
	private void moveLeft() {
		if (player.getX() > 0) {
			player.setX(player.getX() - MOVE_SHIFT);
		}
	}
	
	private void moveRight() {
		if (player.getX() + WIDTH < Main.SIZE) {
			player.setX(player.getX() + MOVE_SHIFT);
		}
	}
	
	private void moveUp() {
		if (player.getY() > 0) {
			player.setY(player.getY() - MOVE_SHIFT);
		}
	}
	
	private void moveDown() {
		if (player.getY() + HEIGHT < Main.SIZE) {
			player.setY(player.getY() + MOVE_SHIFT);
		}
	}
}