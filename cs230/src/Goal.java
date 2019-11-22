import javafx.scene.image.Image;

/** The goal tile type class, contains data relating to the goal tile type*/
public class Goal extends Tile {
	/** The goal constructor*/
	public Goal() {
		this.setPassable(true);
		this.setPassableEnemy(false);
		this.setImage(new Image("/goal.png"));
	}
	
	/** The touch method for the goal tile*/
	public void onTouch(Player player) {
		player.setWon(true);
	}

}
