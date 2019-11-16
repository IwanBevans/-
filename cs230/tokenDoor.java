import javafx.scene.image.Image;

public class tokenDoor extends Door{
	int amount;
	
	public tokenDoor(int amount) {
		this.amount = amount;
		this.isPassable = true;
		this.isLocked = true;
		this.isPassableEnemy = false;
		this.image = new Image("/tokenDoor.png");
	}
	
	public void onTouch(Player player) {
		if (this.isLocked && player.tokens >= this.amount) {
			this.isLocked = false;
			this.isPassableEnemy = false;
			this.image = new Image("/floor.png");
		}
	}
}
