import javafx.scene.image.Image;

/** The class for the token door*/
public class tokenDoor extends Door{
	private int amount;
	
	/** The constructor for the token door takes in a amount of tokens to unlock it*/
	public tokenDoor(int amount) {
		this.amount = amount;
		setPassable(true);
		setLocked(true);
		setPassableEnemy(false);
		setImage(new Image("/tokendoor.png"));
	}
	
	/** The touch method for the token door class*/
	public void onTouch(Player player) {
		if (isLocked() && player.getTokens() >= amount) {
			setLocked(false);
			setPassableEnemy(true);
			setImage(new Image("/floor.png"));
		}
	}
	
	/** The getter to return the amount of tokens a door needs to be unlocked
	 * @return int amount of tokens needed*/
	public int getAmount() {
		return this.amount;
	}
}
