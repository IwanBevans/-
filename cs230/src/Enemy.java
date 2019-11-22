// All enemies inherit from the Enemy abstract class, it contains all the getters and setters required as well as the abstract
// method move as all enemies have a unique movement type

import javafx.scene.image.Image;

/** The enemy class is an abstract class that contains the image and location of the enemy, the move method is abstract as
 * each type of enemy has a different method of movement*/
public abstract class Enemy {
	private Image image;
	private int locationX;
	private int locationY;
	
	public abstract void move(Level level,Player player);

	/** Returns the image of the enemy
	 * @return image*/
	public Image getImage() {
		return image;
	}
	/** Sets the image of the enemy to the image file passed*/
	public void setImage(Image image) {
		this.image = image;
	}

	/** Returns the x location of the enemy
	 * @return int locationX*/
	public int getLocationX() {
		return locationX;
	}
	/** Sets the x location of the enemy to the int passed*/
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	/** Returns the y location of the enemy
	 * @return int locationY*/
	public int getLocationY() {
		return locationY;
	}
	/** Sets the y location of the enemy*/
	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}
}
