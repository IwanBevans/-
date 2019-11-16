import javafx.scene.image.Image;

public class smartEnemy extends Enemy{
	public smartEnemy(int x,int y) {
		this.image = new Image("/smartenemy.png");
		this.locationX = x;
		this.locationY = y;
	}

	public void move(Level level,Player player) {
		// do some algorithm to return optimum move, i seriously can't figure it out
	}
}
