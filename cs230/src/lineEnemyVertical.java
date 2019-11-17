import javafx.scene.image.Image;

public class lineEnemyVertical extends Enemy{
	boolean movingUp;
	
	public lineEnemyVertical(int x,int y) {
		this.image = new Image("/lineenemy.png");
		this.locationX = x;
		this.locationY = y;
		this.movingUp = true;
	}

	public void move(Level level,Player player) {
		if (level.tiles[locationX][locationY-1].isPassableEnemy && movingUp) {
			locationY = locationY - 1;
		} else {
			if (level.tiles[locationX][locationY+1].isPassableEnemy) {
				locationY = locationY + 1;
				movingUp = false;
			} else {
				movingUp = true;
				locationY = locationY - 1;
			}
		}
	}
}
