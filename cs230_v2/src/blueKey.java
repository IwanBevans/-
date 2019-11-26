import javafx.scene.image.Image;

/** Blue keys class, contains data relating to the blue key object*/
public class blueKey extends objectTile {
	/** Constructor for the blue key object*/
	public blueKey() {
		setImage(new Image("/bluekey.png"));
		setPassable(true);
		setPassableEnemy(false);
		setPickedUp(false);
	}
	
	/** The touch function for the blue key object*/
	public void onTouch(Player player) {
		if (!isPickedUp()) {
			setPickedUp(true);
			setPassableEnemy(true);
			player.setBlueKeys(player.getBlueKeys() + 1);
			setImage(new Image("/floor.png"));
		}
	}
}
