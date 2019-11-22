// The object tile abstract class contains the value of if the object has been picked up
/** The object tile class, contains data relating to if the object has been picked up*/
public abstract class objectTile extends Tile{
	private boolean pickedUp;

	/** Returns the state of if the item has been picked up
	 * @return true if item has been picked up, false otherwise*/
	protected boolean isPickedUp() {
		return pickedUp;
	}
	/** Sets the value of picked up to the boolean value parameter*/
	protected void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
}
