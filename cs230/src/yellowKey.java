import javafx.scene.image.Image;
/** The yellow key class contains data relating to the yellow key object*/
public class yellowKey extends objectTile {
	/** The yellow key object constructor*/
	public yellowKey() {
		setImage(new Image("/yellowkey.png"));
		setPassable(true);
		setPassableEnemy(false);
		setPickedUp(false);
	}
	
	/** The touch method for the yellow key object*/
	public void onTouch(Player player) {
		if (!this.isPickedUp()) {
			this.setPickedUp(true);
			this.setPassableEnemy(true);
			player.setYellowKeys(player.getYellowKeys() + 1);
			this.setImage(new Image("/floor.png"));
		}
	}
}
