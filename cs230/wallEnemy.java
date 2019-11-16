import javafx.scene.image.Image;

public class wallEnemy extends Enemy {
	public wallEnemy(int x, int y) {
		this.image = new Image("/wallenemy.png");
		this.locationX = x;
		this.locationY = y;
	}
	
	public void move(Level level,Player player) {
		if (level.tiles[locationX][locationY - 1] instanceof Wall) {
			if (level.tiles[locationX+1][locationY].isPassableEnemy) {
				locationX = locationX + 1;
			} else if (level.tiles[locationX][locationY+1].isPassableEnemy) {
				locationY = locationY + 1;
			} else if (level.tiles[locationX-1][locationY].isPassableEnemy) {
				locationX = locationX - 1;
			}
		} else if (level.tiles[locationX-1][locationY] instanceof Wall) {
			if (level.tiles[locationX][locationY-1].isPassableEnemy) {
				locationY = locationY - 1;
			} else if (level.tiles[locationX][locationY+1].isPassableEnemy) {
				locationY = locationY + 1;
			} 
		} else if (level.tiles[locationX][locationY + 1] instanceof Wall) {
			if (level.tiles[locationX-1][locationY].isPassableEnemy) {
				locationX = locationX - 1;
			} else if (level.tiles[locationX+1][locationY].isPassableEnemy) {
				locationX = locationX + 1;
			}
		} else if (level.tiles[locationX+1][locationY] instanceof Wall) {
			if (level.tiles[locationX][locationY+1].isPassableEnemy) {
				locationY = locationY + 1;
			} else if (level.tiles[locationX][locationY-1].isPassableEnemy) {
				locationY = locationY - 1;
			}
		} else if (level.tiles[locationX - 1][locationY - 1] instanceof Wall) {
			if (level.tiles[locationX][locationY-1].isPassableEnemy) {
				locationY = locationY - 1;
			} else if (level.tiles[locationX-1][locationY].isPassableEnemy) {
				locationX = locationX - 1;
			}
		} else if (level.tiles[locationX -1][locationY + 1] instanceof Wall) {
			if (level.tiles[locationX-1][locationY].isPassableEnemy) {
				locationX = locationX - 1;
			} else if (level.tiles[locationX][locationY+1].isPassableEnemy) {
				locationY = locationY + 1;
			}
		} else if (level.tiles[locationX + 1][locationY - 1] instanceof Wall) {
			if (level.tiles[locationX+1][locationY].isPassableEnemy) {
				locationX = locationX + 1;
			} else if (level.tiles[locationX][locationY-1].isPassableEnemy) {
				locationY = locationY - 1;
			}
		} else if (level.tiles[locationX + 1][locationY + 1] instanceof Wall) {
			if (level.tiles[locationX][locationY+1].isPassableEnemy) {
				locationY = locationY + 1;
			} else if (level.tiles[locationX+1][locationY].isPassableEnemy) {
				locationX = locationX + 1;
			}
		}
	}
}
