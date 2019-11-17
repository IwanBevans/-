import javafx.scene.image.Image;

public abstract class Enemy {
	Image image;
	int locationX;
	int locationY;
	
	public abstract void move(Level level,Player player);
}
