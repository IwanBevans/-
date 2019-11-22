import javafx.scene.image.Image;

/** The tile class for the water tile type*/
public class Water extends Tile{
	/** The constructor for the water tile class*/
	public Water() {
		setPassable(true);
		setPassableEnemy(false);
		setImage(new Image("/water.png"));
	}
	
	/** The touch method for the water tile clase*/
	public void onTouch(Player player) {
		if (!player.isFlippers()) {
			player.setDead(true);
		}
	}
	
}
