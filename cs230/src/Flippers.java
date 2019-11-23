import javafx.scene.image.Image;

/** The flippers class, contains data relating to the flippers object*/
public class Flippers extends objectTile {
	/** The flippers object tile constructor */
	public Flippers() {
		this.setPickedUp(false);
		this.setPassable(true);
		this.setPassableEnemy(false);
		this.setImage(new Image("/flippers.png"));
	}
	
	/** The touch method for the flippers tile*/
	public void onTouch(Player player) {
		if (!this.isPickedUp()) {
			player.setFlippers(true);
			this.setPickedUp(true);
			this.setPassableEnemy(true);
			this.setImage(new Image("/floor.png"));
		}
	}

}
