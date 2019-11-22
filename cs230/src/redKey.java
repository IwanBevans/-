import javafx.scene.image.Image;

/** The red key object class, contains data relating to the red key*/
public class redKey extends objectTile {
	/** The red key constructor*/
	public redKey() {
		this.setImage(new Image("/redkey.png"));
		this.setPassable(true);
		this.setPassableEnemy(false);
		this.setPickedUp(false);
	}
	
	/** The touch method for the red key class*/
	public void onTouch(Player player) {
		if (!this.isPickedUp()) {
			this.setPickedUp(true);
			this.setPassableEnemy(true);
			player.setRedKeys(player.getRedKeys() + 1);
			this.setImage(new Image("/floor.png"));
		}
	}
}
