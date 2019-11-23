import javafx.scene.image.Image;

/** The floor class*/
public class Floor extends Tile{
	/** The floor class constructor, the values will always be fixed*/
	public Floor() {
		this.setPassable(true);
		this.setPassableEnemy(true);
		this.setImage(new Image("/floor.png"));
	}

	/** The floor has no effect when touched but must be inherited from the abstract class*/
	public void onTouch(Player player) {}
}
