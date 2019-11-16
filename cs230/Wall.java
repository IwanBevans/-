import javafx.scene.image.Image;

public class Wall extends Tile{
	public Wall() {
		this.isPassable = false;
		this.isPassableEnemy = false;
		this.image = new Image("/wall.png");
	}

	public void onTouch(Player player) {}
}
