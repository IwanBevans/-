import javafx.scene.image.Image;

public class redKey extends objectTile {
	public redKey() {
		this.image = new Image("/redkey.png");
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.pickedUp = false;
	}
	
	public void onTouch(Player player) {
		if (!this.pickedUp) {
			this.pickedUp = true;
			this.isPassableEnemy = true;
			player.redKeys = player.redKeys + 1;
			this.image = new Image("/floor.png");
		}
	}
}
