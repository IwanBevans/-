import javafx.scene.image.Image;

public class yellowKey extends objectTile {
	public yellowKey() {
		this.image = new Image("/yellowkey.png");
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.pickedUp = false;
	}
	
	public void onTouch(Player player) {
		if (!this.pickedUp) {
			this.pickedUp = true;
			this.isPassableEnemy = true;
			player.yellowKeys = player.yellowKeys + 1;
			this.image = new Image("/floor.png");
		}
	}
}
