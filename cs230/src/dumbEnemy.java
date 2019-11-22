// The dumb enemy will mindlessly walk in the direction of player getting stuck on walls if they get in the way

import javafx.scene.image.Image;

/** Dumb enemys are the most basic enemy type walking directly to the player without thinking about what is in front of them*/
public class dumbEnemy extends Enemy{
	/** The constructor for the dumb enemy class*/
	public dumbEnemy(int x, int y) {
		setLocationX(x);
		setLocationY(y);
		setImage(new Image("/dumbEnemy.png"));
	}
	
	/** The dumb enemy movement function calculates the distance of the player in all four directions from the enemy and will walk
	 * the path with the biggest distance*/
	public void move(Level level,Player player) {
		boolean isNegativeX = false;
		boolean isNegativeY = false;
		int differenceX = getLocationX() - player.getLocationX();
		int differenceY = getLocationY() - player.getLocationY();
		if (differenceX < 0) {
			differenceX = differenceX * -1;
			isNegativeX = true;
		} if (differenceY < 0) {
			differenceY = differenceY * - 1;
			isNegativeY = true;
		} if (differenceX > differenceY) {
			if (!isNegativeX && level.getTiles()[getLocationX()-1][getLocationY()].isPassableEnemy()) {
				setLocationX(getLocationX() - 1);
			} else if (isNegativeX && level.getTiles()[getLocationX()+1][getLocationY()].isPassableEnemy()){
				setLocationX(getLocationX() + 1);
			}
		} else {
			if (!isNegativeY && level.getTiles()[getLocationX()][getLocationY()-1].isPassableEnemy()) {
				setLocationY(getLocationY() - 1);
			} else if (isNegativeY && level.getTiles()[getLocationX()][getLocationY()+1].isPassableEnemy()){
				setLocationY(getLocationY() + 1);
			}
		}
	}
}
