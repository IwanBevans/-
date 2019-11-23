import javafx.scene.image.Image;

/** The green door class, contains data relating to the green door*/
public class greenKeyDoor extends Door {
	/** The green door constructor*/
	public greenKeyDoor() {
		this.setPassable(true);
		this.setPassableEnemy(false);
		this.setLocked(true);
		this.setImage(new Image("/greenkeydoor.png"));
	}

	/** The touch method for the greendoor*/
	public void onTouch(Player player) {
		if (isLocked() && player.getGreenKeys() > 0) {
			player.setGreenKeys(player.getGreenKeys() - 1);
			setLocked(false);
			setPassableEnemy(true);
			this.setImage(new Image("/floor.png"));
		}
	}
}
