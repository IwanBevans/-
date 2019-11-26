import javafx.scene.image.Image;

/** Blue door class, contains data relating to the blue door*/
public class blueKeyDoor extends Door {
	/** Blue door constructor*/
	public blueKeyDoor() {
		setPassable(true);
		setPassableEnemy(false);
		setLocked(true);
		setImage(new Image("/bluekeydoor.png"));
	}

	/** The touch function for the blue door*/
	public void onTouch(Player player) {
		if (isLocked() && player.getBlueKeys() > 0) {
			player.setBlueKeys(player.getBlueKeys() - 1);
			setLocked(false);
			setPassableEnemy(true);
			this.setImage(new Image("/floor.png"));
		}
	}
}
