import javafx.scene.image.Image;

public class tokenTile extends objectTile {
	public tokenTile() {
		this.image = new Image("/token.png");
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.pickedUp = false;
	}
	
	public void onTouch(Player player) {
		if (!this.pickedUp) {
			player.tokens = player.tokens + 1;
			this.pickedUp = true;
			this.image = new Image("/floor.png");
		}
	}
}
