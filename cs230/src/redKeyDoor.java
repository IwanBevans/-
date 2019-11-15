import javafx.scene.image.Image;

public class redKeyDoor extends Door {
	public redKeyDoor() {
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.isLocked = true;
		this.image = new Image("/redkeydoor.png");
	}

	public void onTouch(Player player) {
		if (isLocked && player.redKeys > 0) {
			player.redKeys = player.redKeys - 1;
			isLocked = false;
			isPassableEnemy = true;
			this.image = new Image("/floor.png");
		}
	}
}
