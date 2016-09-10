import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EnemyBoss {
	public static final double WIDTH = 50;
	public static final double HEIGHT = 50;
	public static final double LASER_SIZE = 20;
	public static final double MOVE_SHIFT = 10;
	
	private Rectangle enemyBoss;
	
	public EnemyBoss() {
		double xPosition = Main.SIZE / 2 - WIDTH / 2;
		double yPosition = 25;
		this.enemyBoss = UIGenerator.createRectangle(Color.RED, xPosition, yPosition, WIDTH, HEIGHT);
	}
	
	public Rectangle getEnemyBoss() {
		return enemyBoss;
	}
	
	/**
	 * If enemy boss is not in center: move towards center
	 * If enemy boss is in center: move randomly
	 */
	public void moveEnemyBoss() {
		int centerBoxSize = 200;
		int min = Main.SIZE / 2 - centerBoxSize / 2;
		int max = Main.SIZE / 2 + centerBoxSize / 2;
		
		if (isEnemyBossInCenter(min, max)) {
			moveEnemyBossRandomly();
		}
		else {
			moveEnemyBossToCenter(min, max);
		}
	}
	
	private boolean isEnemyBossInCenter(int min, int max) {
		return enemyBoss.getX() >= min && enemyBoss.getX() + EnemyBoss.WIDTH <= max &&
				enemyBoss.getY() >= min && enemyBoss.getY() + EnemyBoss.HEIGHT <= max;
	}
	
	private void moveEnemyBossToCenter(int min, int max) {
		if (enemyBoss.getX() < min) {
			enemyBoss.setX(enemyBoss.getX() + MOVE_SHIFT);
		}
		if (enemyBoss.getX() + EnemyBoss.WIDTH > max) {
			enemyBoss.setX(enemyBoss.getX() - MOVE_SHIFT);
		}
		if (enemyBoss.getY() < min) {
			enemyBoss.setY(enemyBoss.getY() + MOVE_SHIFT);
		}
		if (enemyBoss.getY() + EnemyBoss.HEIGHT > max) {
			enemyBoss.setY(enemyBoss.getY() - MOVE_SHIFT);
		}
	}
	
	private void moveEnemyBossRandomly() {
		int random = generateRandomIntFrom0To3();
		switch (random) {
			case 0: 
				// move left
				enemyBoss.setX(enemyBoss.getX() - MOVE_SHIFT);
				break;
			case 1:
				// move right
				enemyBoss.setX(enemyBoss.getX() + MOVE_SHIFT);
				break;
			case 2:
				// move up
				enemyBoss.setY(enemyBoss.getY() - MOVE_SHIFT);
				break;
			case 3:
				enemyBoss.setY(enemyBoss.getY() + MOVE_SHIFT);
				// move down
				break;
			default:
				// do nothing
		}
	}
	
	private int generateRandomIntFrom0To3() {
		Random r = new Random();
		return r.nextInt(4);
	}
}
