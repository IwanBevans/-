import javafx.scene.image.Image;
import java.lang.Math;

public class dumbEnemy extends Enemy{
	public dumbEnemy(int x, int y) {
		this.locationX = x;
		this.locationY = y;
		this.image = new Image("/dumbEnemy.png");
	}
	
	public void move(Level level,Player player) {
		double move = Math.round(Math.random() * 4);
		if (move == 0 && level.tiles[locationX-1][locationY].isPassableEnemy) {
			locationX = locationX - 1;
		} if (move == 1 && level.tiles[locationX+1][locationY].isPassableEnemy) {
			locationX = locationX + 1;
		} if (move == 2 && level.tiles[locationX][locationY-1].isPassableEnemy) {
			locationY = locationY - 1;
		} if (move == 3 && level.tiles[locationX][locationY+1].isPassableEnemy) {
			locationY = locationY + 1;
		}
	}
}
