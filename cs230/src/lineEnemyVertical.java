// The vertical enemy class is an enemy type that moves up and down untill it hits a wall and changes direction

import javafx.scene.image.Image;

/** The vertical enemy is another basic enemy types that will move down untill it hits a wall and then turn up*/
public class lineEnemyVertical extends Enemy{
	private boolean movingUp;
	
	/** The constructor of the vertical enemy, takes two locations as inputs*/
	public lineEnemyVertical(int x,int y) {
		setImage(new Image("/lineenemy.png"));
		setLocationX(x);
		setLocationY(y);
		this.movingUp = true;
	}

	/** The momvement function of the vertical enemy moves up untill it can not anymore and then turns down, it knows
	 * which direction to walk by a boolean*/
	public void move(Level level,Player player) {
		if (level.getTiles()[getLocationX()][getLocationY()-1].isPassableEnemy() && movingUp) {
			setLocationY(getLocationY() - 1);
		} else {
			if (level.getTiles()[getLocationX()][getLocationY()+1].isPassableEnemy()) {
				setLocationY(getLocationY() + 1);
				movingUp = false;
			} else {
				movingUp = true;
				setLocationY(getLocationY() - 1);
			}
		}
	}
}
