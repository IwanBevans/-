import javafx.scene.image.Image;

/** The fire tile class, contains data relating to the fire tile type*/
public class Fire extends Tile {
	/** The fire tile constructor*/
	public Fire() {
		setPassable(true);
		setPassableEnemy(false);
		setImage(new Image("/fire.png"));
	}
	
	/** The touch function for the fire tile type*/
	public void onTouch(Player player) {
		if (!player.isFireBoots()) {
			player.setDead(true);
		}
	}
}
