// A tile that breaks after the user steps off it
import javafx.scene.image.Image;

/** The class for the cracked floor tile*/
public class crackedFloor extends Tile{
	private boolean broke;
	
	/** The constructor for the cracked floor*/
	public crackedFloor(boolean cracked) {
		setBroke(!cracked);
		setPassableEnemy(false);
		setPassable(true);
		if (cracked) {
			setImage(new Image("/crackedfloor.png"));
		} else {
			setImage(new Image("/brokefloor.png"));
		}
	}
	
	/** The on touch method for the crackedfloor*/
	public void onTouch(Player player) {
		if (isBroke()) {
			player.setDead(true);
		} else {
			setBroke(true);
		}
	}

	public boolean isBroke() {
		return broke;
	}

	public void setBroke(boolean broke) {
		this.broke = broke;
	}

}
