// The ice tile is slippery and when stepped on will send the player forward untill they hit a wall or get off the ice
import javafx.scene.image.Image;

/** The ice class*/
public class Ice extends Tile{
	/** The ice class constructor, the values will always be fixed*/
	public Ice() {
		setPassable(true);
		setPassableEnemy(false);
		setImage(new Image("/ice.png"));
	}

	public void onTouch(Player player) {}
}