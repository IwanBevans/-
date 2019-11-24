// A tile that breaks after the user steps off it
import javafx.scene.image.Image;

/** The class for the cracked floor tile*/
public class crackedFloor extends Tile{
	/** The constructor for the cracked floor*/
	public crackedFloor(boolean cracked) {
		setPassable(cracked);
		setPassableEnemy(false);
		if (isPassable()) {
			setImage(new Image("/crackedfloor.png"));
		} else {
			setImage(new Image("/brokefloor.png"));
		}
	}
	
	/** The on touch method for the crackedfloor*/
	public void onTouch(Player player) {
		setPassable(false);
	}

}
