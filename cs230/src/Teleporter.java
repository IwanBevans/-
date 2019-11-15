import javafx.scene.image.Image;

public class Teleporter extends Tile{
	int toLocationX;
	int toLocationY;
	
	public Teleporter(int toLocationX, int toLocationY) {
		this.toLocationX = toLocationX;
		this.toLocationY = toLocationY;
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.image = new Image("/teleporter.png");
	}
	
	public void onTouch(Player player) {
		player.locationX = toLocationX;
		player.locationY = toLocationY;
	}
}
