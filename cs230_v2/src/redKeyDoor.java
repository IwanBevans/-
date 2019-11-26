import javafx.scene.image.Image;

/** The red door class, contains information about the reddoor*/
public class redKeyDoor extends Door {
	/** The red door contructor*/
	public redKeyDoor() {
		this.setPassable(true);
		this.setPassableEnemy(false);
		this.setLocked(true);
		this.setImage(new Image("/redkeydoor.png"));
	}

	/** The touch method for the red door*/
	public void onTouch(Player player) {
		if (isLocked() && player.getRedKeys() > 0) {
			player.setRedKeys(player.getRedKeys() - 1);
			setLocked(false);
			setPassableEnemy(true);
			this.setImage(new Image("/floor.png"));
		}
	}
}
