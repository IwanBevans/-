// The horizontal enemy class is an enemy type that moves left and right untill it hits a wall and changes direction

import javafx.scene.image.Image;

/** The horizontal enemy is another basic enemy types that will move right untill it hits a wall and then turn left*/
public class lineEnemyHorizontal extends Enemy{
	private boolean movingRight;
	
	/** The constructor of the horizontal enemy, takes two locations as input*/
	public lineEnemyHorizontal(int x,int y) {
		setImage(new Image("/lineenemy.png"));
		setLocationX(x);
		setLocationY(y);
		this.movingRight = true;
	}

	/** The momvement function of the horizontal enemy moves right untill it can not anymore and then turns left, it knows
	 * which direction to walk by a boolean*/
	public void move(Level level,Player player) {
		if (level.getTiles()[getLocationX()+1][getLocationY()].isPassableEnemy() && movingRight) {
			setLocationX(getLocationX() + 1);
		} else {
			if (level.getTiles()[getLocationX()-1][getLocationY()].isPassableEnemy()) {
				setLocationX(getLocationX() - 1);
				movingRight = false;
			} else {
				movingRight = true;
				setLocationX(getLocationX() + 1);
			}
		}
	}
}
