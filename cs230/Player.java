import javafx.scene.image.Image;

public class Player {
	Image playerImage;
	int locationX;
	int locationY;
	boolean fireBoots;
	boolean flippers;
	int tokens;
	int redKeys;
	int blueKeys;
	int yellowKeys;
	int greenKeys;
	boolean dead;
	boolean won;
	
	public Player(Level level) {
		dead = false;
		won = false;
		playerImage = new Image("/player.png");
		locationX = level.startingLocationX;
		locationY = level.startingLocationY;
		fireBoots = false;
		flippers = false;
		tokens = 0;
		redKeys = 0;
		blueKeys = 0;
		yellowKeys = 0;
		greenKeys = 0;
	}
	
	public void moveDown(Level level) {
		if (level.tiles[locationX][locationY+1].isPassable) {
			for (int x = 0; x < level.allEnemies.size(); x++) {
				if (level.allEnemies.get(x).locationX == locationX && level.allEnemies.get(x).locationY == locationY+1) {
					dead = true;
				} level.allEnemies.get(x).move(level,this);
				if (level.allEnemies.get(x).locationX == locationX && level.allEnemies.get(x).locationY == locationY+1) {
					dead = true;
				}
			}
			level.tiles[locationX][locationY+1].onTouch(this);
			if (level.tiles[locationX][locationY+1] instanceof Door) {
				Door door = (Door) level.tiles[locationX][locationY+1];
				if (door.isLocked) {
					locationY = locationY - 1;
				}
			}
			locationY = locationY + 1;
		}
	}
	
	public void moveUp(Level level) {
		if (level.tiles[locationX][locationY-1].isPassable) {
			for (int x = 0; x < level.allEnemies.size(); x++) {
				if (level.allEnemies.get(x).locationX == locationX && level.allEnemies.get(x).locationY == locationY-1) {
					dead = true;
				} level.allEnemies.get(x).move(level,this);
				if (level.allEnemies.get(x).locationX == locationX && level.allEnemies.get(x).locationY == locationY-1) {
					dead = true;
				}
			}
			level.tiles[locationX][locationY-1].onTouch(this);
			if (level.tiles[locationX][locationY-1] instanceof Door) {
				Door door = (Door) level.tiles[locationX][locationY-1];
				if (door.isLocked) {
					locationY = locationY + 1;
				}
			}
			locationY = locationY - 1;
		}
	}
	
	public void moveRight(Level level) {
		if (level.tiles[locationX+1][locationY].isPassable) {
			for (int x = 0; x < level.allEnemies.size(); x++) {
				if (level.allEnemies.get(x).locationX == locationX+1 && level.allEnemies.get(x).locationY == locationY) {
					dead = true;
				} level.allEnemies.get(x).move(level,this);
				if (level.allEnemies.get(x).locationX == locationX+1 && level.allEnemies.get(x).locationY == locationY) {
					dead = true;
				}
			} 
			level.tiles[locationX+1][locationY].onTouch(this);
			if (level.tiles[locationX+1][locationY] instanceof Door) {
				Door door = (Door) level.tiles[locationX+1][locationY];
				if (door.isLocked) {
					locationX = locationX - 1;
				}
			}
			locationX = locationX + 1;
		}
	}
	
	public void moveLeft(Level level) {
		if (level.tiles[locationX-1][locationY].isPassable) {
			for (int x = 0; x < level.allEnemies.size(); x++) {
				if (level.allEnemies.get(x).locationX == locationX-1 && level.allEnemies.get(x).locationY == locationY) {
					dead = true;
				} level.allEnemies.get(x).move(level,this);
				if (level.allEnemies.get(x).locationX == locationX-1 && level.allEnemies.get(x).locationY == locationY) {
					dead = true;
				}
			}
			level.tiles[locationX-1][locationY].onTouch(this);
			if (level.tiles[locationX-1][locationY] instanceof Door) {
				Door door = (Door) level.tiles[locationX-1][locationY];
				if (door.isLocked) {
					locationX = locationX + 1;
				}
			}
			locationX = locationX - 1;
		}
	}
}
