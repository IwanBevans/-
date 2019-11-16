import javafx.scene.image.Image;

public class greenKeyDoor extends Door {
	public greenKeyDoor() {
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.isLocked = true;
		this.image = new Image("/greenkeydoor.png");
	}

	public void onTouch(Player player) {
		if (isLocked && player.greenKeys > 0) {
			player.greenKeys = player.greenKeys - 1;
			isLocked = false;
			isPassableEnemy = true;
			this.image = new Image("/floor.png");
		}
	}
}
