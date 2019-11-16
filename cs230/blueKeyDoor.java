import javafx.scene.image.Image;

public class blueKeyDoor extends Door {
	public blueKeyDoor() {
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.isLocked = true;
		this.image = new Image("/bluekeydoor.png");
	}

	public void onTouch(Player player) {
		if (isLocked && player.blueKeys > 0) {
			player.blueKeys = player.blueKeys - 1;
			isLocked = false;
			isPassableEnemy = true;
			this.image = new Image("/floor.png");
		}
	}
}
