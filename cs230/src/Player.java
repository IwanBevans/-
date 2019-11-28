// The player class, contains the information about the player such as their inventory and all of the movement functions
import javafx.scene.image.Image;

/** The player class contains the location of the player, their inventory and functions for movements*/
public class Player {
	private Image playerImage;
	private int locationX;
	private int locationY;
	private boolean fireBoots;
	private boolean flippers;
	private int tokens;
	private int redKeys;
	private int blueKeys;
	private int yellowKeys;
	private int greenKeys;
	private boolean dead;
	private boolean won;
	
	/** The constructor for the player class, takes a level as an input and creates their inventory and default state*/
	public Player(Level level) {
		setDead(false);
		setWon(false);
		setPlayerImage(new Image("/player.png"));
		setLocationX(level.getStartingLocationX());
		setLocationY(level.getStartingLocationY());
		setFireBoots(false);
		setFlippers(false);
		setTokens(0);
		setRedKeys(0);
		setBlueKeys(0);
		setYellowKeys(0);
		setGreenKeys(0);
	}
	
	/** Checks if the player can move down and then moves the player down*/
	public void moveDown(Level level) {
		if (level.getTiles()[locationX][locationY+1].isPassable()) {
			for (int x = 0; x < level.getAllEnemies().size(); x++) {
				if (level.getAllEnemies().get(x).getLocationX() == locationX && level.getAllEnemies().get(x).getLocationY() == locationY+1) {
					setDead(true);
				} level.getAllEnemies().get(x).move(level,this);
				if (level.getAllEnemies().get(x).getLocationX() == locationX && level.getAllEnemies().get(x).getLocationY() == locationY+1) {
					setDead(true);
				}
			}
			level.getTiles()[locationX][locationY+1].onTouch(this);
			if (level.getTiles()[locationX][locationY+1] instanceof Door) {
				Door door = (Door) level.getTiles()[locationX][locationY+1];
				if (door.isLocked()) {
					locationY = locationY - 1;
				}
			} if (level.getTiles()[locationX][locationY] instanceof crackedFloor) {
				level.getTiles()[locationX][locationY].setImage(new Image("/brokefloor.png"));
			} setPlayerImage(new Image("/playerdown.png"));
			locationY = locationY + 1;
		}
	}
	
	/** Checks if the player can move up and then moves the player up*/
	public void moveUp(Level level) {
		if (level.getTiles()[locationX][locationY-1].isPassable()) {
			for (int x = 0; x < level.getAllEnemies().size(); x++) {
				if (level.getAllEnemies().get(x).getLocationX() == locationX && level.getAllEnemies().get(x).getLocationY() == locationY-1) {
					setDead(true);
				} level.getAllEnemies().get(x).move(level,this);
				if (level.getAllEnemies().get(x).getLocationX() == locationX && level.getAllEnemies().get(x).getLocationY() == locationY-1) {
					setDead(true);
				}
			}
			level.getTiles()[locationX][locationY-1].onTouch(this);
			if (level.getTiles()[locationX][locationY-1] instanceof Door) {
				Door door = (Door) level.getTiles()[locationX][locationY-1];
				if (door.isLocked()) {
					locationY = locationY + 1;
				}
			} if (level.getTiles()[locationX][locationY] instanceof crackedFloor) {
				level.getTiles()[locationX][locationY].setImage(new Image("/brokefloor.png"));
			} setPlayerImage(new Image("/playerup.png"));
			locationY = locationY - 1;
		}
	}
	
	/** Checks if the player can move right and then moves them right*/
	public void moveRight(Level level) {
		if (level.getTiles()[locationX+1][locationY].isPassable()) {
			for (int x = 0; x < level.getAllEnemies().size(); x++) {
				if (level.getAllEnemies().get(x).getLocationX() == locationX+1 && level.getAllEnemies().get(x).getLocationY() == locationY) {
					setDead(true);
				} level.getAllEnemies().get(x).move(level,this);
				if (level.getAllEnemies().get(x).getLocationX() == locationX+1 && level.getAllEnemies().get(x).getLocationY() == locationY) {
					setDead(true);
				}
			}
			level.getTiles()[locationX+1][locationY].onTouch(this);
			if (level.getTiles()[locationX+1][locationY] instanceof Door) {
				Door door = (Door) level.getTiles()[locationX+1][locationY];
				if (door.isLocked()) {
					locationX = locationX - 1;
				}
			} if (level.getTiles()[locationX][locationY] instanceof crackedFloor) {
				level.getTiles()[locationX][locationY].setImage(new Image("/brokefloor.png"));
			} setPlayerImage(new Image("/player.png"));
			locationX = locationX + 1;
		}
	}
	
	/** Checks if the player can move left and then moves left*/
	public void moveLeft(Level level) {
		if (level.getTiles()[locationX-1][locationY].isPassable()) {
			for (int x = 0; x < level.getAllEnemies().size(); x++) {
				if (level.getAllEnemies().get(x).getLocationX() == locationX-1 && level.getAllEnemies().get(x).getLocationY() == locationY) {
					setDead(true);
				} level.getAllEnemies().get(x).move(level,this);
				if (level.getAllEnemies().get(x).getLocationX() == locationX-1 && level.getAllEnemies().get(x).getLocationY() == locationY) {
					setDead(true);
				}
			}
			level.getTiles()[locationX-1][locationY].onTouch(this);
			if (level.getTiles()[locationX-1][locationY] instanceof Door) {
				Door door = (Door) level.getTiles()[locationX-1][locationY];
				if (door.isLocked()) {
					locationX = locationX + 1;
				}
			} if (level.getTiles()[locationX][locationY] instanceof crackedFloor) {
				level.getTiles()[locationX][locationY].setImage(new Image("/brokefloor.png"));
			} setPlayerImage(new Image("/playerleft.png"));
			locationX = locationX - 1;
		}
	}

	/** Returns the x location of the player
	 * @return locationX of player*/
	public int getLocationX() {
		return locationX;
	} /** Sets the x location of the player as the int parameter*/
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	/** Returns the y location of the player
	 * @return locationY of player*/
	public int getLocationY() {
		return locationY;
	} /** Sets the y location of the player as the int parameter*/
	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}
	
	/** Returns the imageof the player
	 * @return image of player*/
	public Image getPlayerImage() {
		return playerImage;
	} /** Sets the image of the player as the image parameter*/
	public void setPlayerImage(Image playerImage) {
		this.playerImage = playerImage;
	}

	/** Returns true if the player has fireboots
	 * @return fireBoots state of player*/
	public boolean isFireBoots() {
		return fireBoots;
	} /** Sets the fireBoots state as the boolean parameter*/
	public void setFireBoots(boolean fireBoots) {
		this.fireBoots = fireBoots;
	}

	/** Returns true if the player has flippers
	 * @return flippers state of player*/
	public boolean isFlippers() {
		return flippers;
	} /** Sets the flipper state as the boolean parameter*/
	public void setFlippers(boolean flippers) {
		this.flippers = flippers;
	}

	/** Returns the amount of tokens
	 * @return tokens of player*/
	public int getTokens() {
		return tokens;
	} /** Sets the token amount to the int parameter*/
	public void setTokens(int tokens) {
		this.tokens = tokens;
	}

	/** Returns the amount of red keys
	 * @return amount of red keys of player*/
	public int getRedKeys() {
		return redKeys;
	} /** Sets the amount of red keys the player to the int parameter*/
	public void setRedKeys(int redKeys) {
		this.redKeys = redKeys;
	}

	/** Returns the amount of blue keys
	 * @return amount of bluekeys of player*/
	public int getBlueKeys() {
		return blueKeys;
	} /** Sets the amount of blue keys the player to the int parameter*/
	public void setBlueKeys(int blueKeys) {
		this.blueKeys = blueKeys;
	}

	/** Returns the amount of yellow keys
	 * @return amount of yellowkeys of player*/
	public int getYellowKeys() {
		return yellowKeys;
	} /** Sets the amount of yellow keys to the int parameter*/
	public void setYellowKeys(int yellowKeys) {
		this.yellowKeys = yellowKeys;
	}

	/** Returns the amount of green keys
	 * @return amount of greenkeys of player*/
	public int getGreenKeys() {
		return greenKeys;
	} /** Sets the amount of green keys to the int parameter*/
	public void setGreenKeys(int greenKeys) {
		this.greenKeys = greenKeys;
	}
	
	/** Returns the dead state of the player
	 * @return true if player is dead, false otherwise*/
	public boolean isDead() {
		return dead;
	} /** Sets the player dead state to the boolean parameter*/
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	/** Returns the won state of the player
	 * @return true if player has won, false otherwise*/
	public boolean isWon() {
		return won;
	} /** Sets the won state of the player*/
	public void setWon(boolean won) {
		this.won = won;
	}
}
