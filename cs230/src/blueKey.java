import javafx.scene.image.Image;

public class blueKey extends objectTile {
	public blueKey() {
		this.image = new Image("/bluekey.png");
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.pickedUp = false;
	}
	
	public void onTouch(Player player) {
		if (!this.pickedUp) {
			this.pickedUp = true;
			this.isPassableEnemy = true;
			player.blueKeys = player.blueKeys + 1;
			this.image = new Image("/floor.png");
		}
	}
}
