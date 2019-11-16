import javafx.scene.image.Image;

public class Water extends Tile{
	public Water() {
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.image = new Image("/water.png");
	}
	
	public void onTouch(Player player) {
		if (!player.flippers) {
			player.dead = true;
		}
	}
	
}
