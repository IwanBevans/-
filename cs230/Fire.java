import javafx.scene.image.Image;

public class Fire extends Tile {
	public Fire() {
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.image = new Image("/fire.png");
	}
	
	public void onTouch(Player player) {
		if (!player.fireBoots) {
			player.dead = true;
		}
	}
}
