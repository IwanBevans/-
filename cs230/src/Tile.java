// The Tile abstract class that all types of tiles inherit from
import javafx.scene.image.Image;

/** The tile class is an abstract class that all tile types inherit from*/
public abstract class Tile {
	private boolean isPassable;
	private boolean isPassableEnemy;
	private Image image;
	
	public abstract void onTouch(Player player);

	/** Returns if the tile is passable or not
	 * @return true if tiles is passable false otherwise*/
	protected boolean isPassable() {
		return isPassable;
	}
	/** Sets the passable state of a tile to the boolean parameter*/
	protected void setPassable(boolean isPassable) {
		this.isPassable = isPassable;
	}

	/** Returns if the tile is passable by enemies
	 * @return true if tile can be passed by enemies false otherwise*/
	protected boolean isPassableEnemy() {
		return isPassableEnemy;
	} /** Sets the passable enemy state of a tile to the boolean parameter*/
	protected void setPassableEnemy(boolean isPassableEnemy) {
		this.isPassableEnemy = isPassableEnemy;
	}

	/** Returns the image file of the tile image
	 * @return Image of the tile*/
	protected Image getImage() {
		return image;
	} /** Sets the image of the tile*/
	protected void setImage(Image image) {
		this.image = image;
	}
}
