import javafx.scene.image.Image;

/** The class for the tokentile object*/
public class tokenTile extends objectTile {
	/** The constructor for the tokentile object*/
	public tokenTile() {
		this.setImage(new Image("/token.png"));
		this.setPassable(true);
		this.setPassableEnemy(false);
		this.setPickedUp(false);
	}
	
	/** The touch method for the token tile object*/
	public void onTouch(Player player) {
		if (!this.isPickedUp()) {
			player.setTokens(player.getTokens() + 1);
			this.setPickedUp(true);
			this.setPassableEnemy(true);
			this.setImage(new Image("/floor.png"));
		}
	}
}
