import javafx.scene.image.Image;

public class Goal extends Tile {
	public Goal() {
		this.isPassable = true;
		this.isPassableEnemy = false;
		this.image = new Image("/goal.png");
	}
	
	public void onTouch(Player player) {
		player.won = true;
	}

}
