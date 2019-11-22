import javafx.scene.image.Image;

/** The greenkey class, contains data relating to the green key object*/
public class greenKey extends objectTile {
	/** The green keys object constructor*/
	public greenKey() {
		this.setImage(new Image("/greenkey.png"));
		this.setPassable(true);
		this.setPassableEnemy(false);
		this.setPickedUp(false);
	}
	
	/** The touch method for the green key class*/
	public void onTouch(Player player) {
		if (!this.isPickedUp()) {
			this.setPickedUp(true);
			this.setPassableEnemy(true);
			player.setGreenKeys(player.getGreenKeys() + 1);
			this.setImage(new Image("/floor.png"));
		}
	}
}
