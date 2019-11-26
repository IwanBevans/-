// The abstract class of doors checks if the door is locked or not
/** The door abstract class, contains the boolean value of the locked state of the door*/
public abstract class Door extends Tile{
	private boolean isLocked;

	/** Returns the value of if the door is locked
	 * @return true if door is locked*/
	public boolean isLocked() {
		return isLocked;
	} /** Sets the value of the locked state to the boolean parameter passed
	@param boolean isLocked*/
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	
}
