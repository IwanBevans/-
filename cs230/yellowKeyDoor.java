import javafx.scene.image.Image;

public class yellowKeyDoor extends Door {
	public yellowKeyDoor() {
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.isLocked = true;
		this.image = new Image("/yellowkeydoor.png");
	}

	public void onTouch(Player player) {
		if (isLocked && player.yellowKeys > 0) {
			player.yellowKeys = player.yellowKeys - 1;
			isLocked = false;
			isPassableEnemy = true;
			this.image = new Image("/floor.png");
		}
	}
}
