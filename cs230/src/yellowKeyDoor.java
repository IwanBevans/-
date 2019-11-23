import javafx.scene.image.Image;

/** The yellow door class, contains data about the yellow door*/
public class yellowKeyDoor extends Door {
	/** The yellow door constructor*/
	public yellowKeyDoor() {
		setPassable(true);
		setPassableEnemy(false);
		setLocked(true);
		setImage(new Image("/yellowkeydoor.png"));
	}

	/** The touch method of the yellow door*/
	public void onTouch(Player player) {
		if (isLocked() && player.getYellowKeys() > 0) {
			player.setYellowKeys(player.getYellowKeys() - 1);
			setLocked(false);
			setPassableEnemy(true);
			setImage(new Image("/floor.png"));
		}
	}
}
