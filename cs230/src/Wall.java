import javafx.scene.image.Image;

/** The class for the wall tile type*/
public class Wall extends Tile{
	/** The constructor for the wall tile class*/
	public Wall() {
		setPassable(false);
		setPassableEnemy(false);
		setImage(new Image("/wall.png"));
	}

	public void onTouch(Player player) {}
}
