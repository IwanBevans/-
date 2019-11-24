// The wall enemy class, the wall enemy follows the closest wall moving in a square shape
import javafx.scene.image.Image;

/** The wall enemy is a simple enemy that moves following a wall*/
public class wallEnemy extends Enemy {
	/** Wall enemy constructor, takes two locations as input*/
	public wallEnemy(int x, int y) {
		setImage(new Image("/wallenemy.png"));
		setLocationX(x);
		setLocationY(y);
	}
	
	/** The move function for the wall enemy detects the nearest wall and follows alongside it*/
	public void move(Level level,Player player) {
		if (!level.getTiles()[getLocationX()][getLocationY() - 1].isPassableEnemy()) {
			if (level.getTiles()[getLocationX()+1][getLocationY()].isPassableEnemy()) {
				setLocationX(getLocationX() + 1);
				setImage(new Image("/wallenemy_right.png"));
			} else if (level.getTiles()[getLocationX()][getLocationY()+1].isPassableEnemy()) {
				setLocationY(getLocationY() + 1);
				setImage(new Image("/wallenemy_down.png"));
			} else if (level.getTiles()[getLocationX()-1][getLocationY()].isPassableEnemy()) {
				setLocationX(getLocationX() - 1);
				setImage(new Image("/wallenemy.png"));
			}
		} else if (!level.getTiles()[getLocationX()-1][getLocationY()].isPassableEnemy()) {
			if (level.getTiles()[getLocationX()][getLocationY()-1].isPassableEnemy()) {
				setLocationY(getLocationY() - 1);
				setImage(new Image("/wallenemy_up.png"));
			} else if (level.getTiles()[getLocationX()][getLocationY()+1].isPassableEnemy()) {
				setLocationY(getLocationY() + 1);
				setImage(new Image("/wallenemy_down.png"));
			} 
		} else if (!level.getTiles()[getLocationX()][getLocationY() + 1].isPassableEnemy()) {
			if (level.getTiles()[getLocationX()-1][getLocationY()].isPassableEnemy()) {
				setLocationX(getLocationX() - 1);
				setImage(new Image("/wallenemy.png"));
			} else if (level.getTiles()[getLocationX()+1][getLocationY()].isPassableEnemy()) {
				setLocationX(getLocationX() + 1);
				setImage(new Image("/wallenemy_right.png"));
			}
		} else if (!level.getTiles()[getLocationX()+1][getLocationY()].isPassableEnemy()) {
			if (level.getTiles()[getLocationX()][getLocationY()+1].isPassableEnemy()) {
				setLocationY(getLocationY() + 1);
				setImage(new Image("/wallenemy_down.png"));
			} else if (level.getTiles()[getLocationX()][getLocationY()-1].isPassableEnemy()) {
				setLocationY(getLocationY() - 1);
				setImage(new Image("/wallenemy_up.png"));
			}
		} else if (!level.getTiles()[getLocationX() - 1][getLocationY() - 1].isPassableEnemy()) {
			if (level.getTiles()[getLocationX()][getLocationY()-1].isPassableEnemy()) {
				setLocationY(getLocationY() - 1);
				setImage(new Image("/wallenemy_up.png"));
			} else if (level.getTiles()[getLocationX()-1][getLocationY()].isPassableEnemy()) {
				setLocationX(getLocationX() - 1);
				setImage(new Image("/wallenemy.png"));
			}
		} else if (!level.getTiles()[getLocationX() -1][getLocationY() + 1].isPassableEnemy()) {
			if (level.getTiles()[getLocationX()-1][getLocationY()].isPassableEnemy()) {
				setLocationX(getLocationX() - 1);
				setImage(new Image("/wallenemy.png"));
			} else if (level.getTiles()[getLocationX()][getLocationY()+1].isPassableEnemy()) {
				setLocationY(getLocationY() + 1);
				setImage(new Image("/wallenemy_up.png"));
			}
		} else if (!level.getTiles()[getLocationX() + 1][getLocationY() - 1].isPassableEnemy()) {
			if (level.getTiles()[getLocationX()+1][getLocationY()].isPassableEnemy()) {
				setLocationX(getLocationX() + 1);
				setImage(new Image("/wallenemy_right.png"));
			} else if (level.getTiles()[getLocationX()][getLocationY()-1].isPassableEnemy()) {
				setLocationY(getLocationY() - 1);
				setImage(new Image("/wallenemy_down.png"));
			}
		} else if (!level.getTiles()[getLocationX() + 1][getLocationY() + 1].isPassableEnemy()) {
			if (level.getTiles()[getLocationX()][getLocationY()+1].isPassableEnemy()) {
				setLocationY(getLocationY() + 1);
				setImage(new Image("/wallenemy_down.png"));
			} else if (level.getTiles()[getLocationX()+1][getLocationY()].isPassableEnemy()) {
				setLocationX(getLocationX() + 1);
				setImage(new Image("/wallenemy_right.png"));
			}
		}
	}
}
