import javafx.scene.image.Image;

public abstract class Tile {
	boolean isPassable;
	boolean isPassableEnemy;
	Image image;
	
	public abstract void onTouch(Player player);
}
