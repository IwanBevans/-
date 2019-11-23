import javafx.scene.image.Image;

/** The teleporter tile type class*/
public class Teleporter extends Tile{
	private int toLocationX;
	private int toLocationY;
	
	/** The constructor of the teleporter class*/
	public Teleporter(int toLocationX, int toLocationY) {
		this.toLocationX = toLocationX;
		this.toLocationY = toLocationY;
		setPassable(true);
		setPassableEnemy(false);
		setImage(new Image("/teleporter.png"));
	}
	
	/** The touch method for the teleporter class*/
	public void onTouch(Player player) {
		player.setLocationX(getToLocationX());
		player.setLocationY(getToLocationY());
	}

	/** The getter for the teleporter x value
	 * @return int locationX*/
	public int getToLocationX() {
		return toLocationX;
	}
	/** The getter for the teleporter y value
	 * @return int locationY*/
	public int getToLocationY() {
		return toLocationY;
	}
}
