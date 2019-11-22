import javafx.scene.image.Image;

/** The fireboots object class, contains data related to the fireboots object*/
public class fireBoots extends objectTile {
	/** The fireboots constructor*/
	public fireBoots() {
		setPickedUp(false);
		setPassable(true);
		setPassableEnemy(false);
		setImage(new Image("/fireboots.png"));
	}
	
	/** The touch function for the fireboots class*/
	public void onTouch(Player player) {
		if (!this.isPickedUp()) {
			player.setFireBoots(true);
			this.setPickedUp(true);
			this.setPassableEnemy(true);
			this.setImage(new Image("/floor.png"));
		}
	}

}
