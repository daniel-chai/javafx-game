import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class represents the EnemyBoss that is present in the boss level.
 * 
 * @author Daniel Chai (dhc10)
 * @version 1.0
 */
public class EnemyBoss {
	public static final double WIDTH = 50;
	public static final double HEIGHT = 50;
	public static final double LASER_SIZE = 25;
	public static final double MOVE_SHIFT = 15;
	
	private Rectangle enemyBoss;
	
	/**
	 * Constructor for EnemyBoss class
	 */
	public EnemyBoss() {
		double xPosition = Main.SIZE / 2 - WIDTH / 2;
		double yPosition = 25;
		this.enemyBoss = UIGenerator.createRectangle(Color.RED, xPosition, yPosition, WIDTH, HEIGHT);
	}
	
	/**
	 * @return the Rectangle representing the EnemyBoss
	 */
	public Rectangle getEnemyBoss() {
		return enemyBoss;
	}
	
	/**
	 * Move EnemyBoss according to following specifications:
	 * If enemy boss is not in center: move towards center
	 * If enemy boss is in center: move randomly
	 */
	public void moveEnemyBoss() {
		double centerBoxSize = 200;
		double min = Main.SIZE / 2 - centerBoxSize / 2;
		double max = Main.SIZE / 2 + centerBoxSize / 2;
		
		if (isEnemyBossInCenter(min, max)) {
			moveEnemyBossRandomly();
		}
		else {
			moveEnemyBossToCenter(min, max);
		}
	}
	
	private boolean isEnemyBossInCenter(double min, double max) {
		return enemyBoss.getX() >= min && enemyBoss.getX() + EnemyBoss.WIDTH <= max &&
				enemyBoss.getY() >= min && enemyBoss.getY() + EnemyBoss.HEIGHT <= max;
	}
	
	private void moveEnemyBossToCenter(double min, double max) {
		if (enemyBoss.getX() < min) {
			moveRight();
		}
		if (enemyBoss.getX() + EnemyBoss.WIDTH > max) {
			moveLeft();
		}
		if (enemyBoss.getY() < min) {
			moveDown();
		}
		if (enemyBoss.getY() + EnemyBoss.HEIGHT > max) {
			moveUp();
		}
	}	
	
	private void moveEnemyBossRandomly() {
		int random = generateRandomIntFrom0To3();
		
		switch (random) {
			case 0: 
				moveLeft();
				break;
			case 1:
				moveRight();
				break;
			case 2:
				moveUp();
				break;
			case 3:
				moveDown();
				break;
			default:
				// do nothing
		}
	}
	
	private int generateRandomIntFrom0To3() {
		Random r = new Random();
		return r.nextInt(4);
	}
	
	private void moveLeft() {
		enemyBoss.setX(enemyBoss.getX() - MOVE_SHIFT);
	}
	
	private void moveRight() {
		enemyBoss.setX(enemyBoss.getX() + MOVE_SHIFT);
	}
	
	private void moveUp() {
		enemyBoss.setY(enemyBoss.getY() - MOVE_SHIFT);
	}
	
	private void moveDown() {
		enemyBoss.setY(enemyBoss.getY() + MOVE_SHIFT);
	}
}