import javafx.scene.image.Image;

public class fireBoots extends objectTile {
	public fireBoots() {
		this.pickedUp = false;
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.image = new Image("/fireboots.png");
	}
	
	public void onTouch(Player player) {
		if (!this.pickedUp) {
			player.fireBoots = true;
			this.pickedUp = true;
			this.isPassableEnemy = true;
			this.image = new Image("/floor.png");
		}
	}

}
