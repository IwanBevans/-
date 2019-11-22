// The help tile class, an additional tile in the game that when the player steps on displays a help message
import javafx.scene.image.Image;

/** The class for the help tile, contains the data for the help tiles*/
public class helpTile extends Tile{
	private String helpMessage;
	
	/** The help tile constructer, takes in a string as an input*/
	public helpTile(String helpMessage) {
		setImage(new Image("/helptile.png"));
		setPassable(true);
		setPassableEnemy(true);
		this.helpMessage = helpMessage;
	}
	
	public void onTouch(Player player) {}

	/** The getter for the help message
	 * @return String help message*/
	public String getHelpMessage() {
		return helpMessage;
	}
}
