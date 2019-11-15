import javafx.scene.image.Image;

public class Flippers extends objectTile {
	public Flippers() {
		this.pickedUp = false;
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.image = new Image("/flippers.png");
	}
	
	public void onTouch(Player player) {
		if (!this.pickedUp) {
			player.flippers = true;
			this.pickedUp = true;
			this.image = new Image("/floor.png");
		}
	}

}
