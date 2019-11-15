import javafx.scene.image.Image;

public class Floor extends Tile{
	public Floor() {
		this.isPassable = true;
		this.isPassableEnemy = true;
		this.image = new Image("/floor.png");
	}

	public void onTouch(Player player) {}
}
