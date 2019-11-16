import javafx.scene.image.Image;

public class greenKey extends objectTile {
	public greenKey() {
		this.image = new Image("/greenkey.png");
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.pickedUp = false;
	}
	
	public void onTouch(Player player) {
		if (!this.pickedUp) {
			this.pickedUp = true;
			player.greenKeys = player.greenKeys + 1;
			this.image = new Image("/floor.png");
		}
	}
}
