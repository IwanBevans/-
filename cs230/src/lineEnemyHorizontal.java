import javafx.scene.image.Image;

public class lineEnemyHorizontal extends Enemy{
	boolean movingRight;
	
	public lineEnemyHorizontal(int x,int y) {
		this.image = new Image("/lineenemy.png");
		this.locationX = x;
		this.locationY = y;
		this.movingRight = true;
	}

	public void move(Level level,Player player) {
		if (level.tiles[locationX+1][locationY].isPassableEnemy && movingRight) {
			locationX = locationX + 1;
		} else {
			if (level.tiles[locationX-1][locationY].isPassableEnemy) {
				locationX = locationX - 1;
				movingRight = false;
			} else {
				movingRight = true;
				locationX = locationX + 1;
			}
		}
	}
}
