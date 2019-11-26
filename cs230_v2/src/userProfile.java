// The user profile class
import java.util.ArrayList;

/** The user profile class, contains data such as the username, their max level and all of their level times*/
public class userProfile {
	private String userName;
	private int maxLevel;
	private ArrayList<Integer> levelTimes;
	
	/** The userprofile constructor takes a name and int*/
	public userProfile(String name, int level) {
		userName = name;
		maxLevel = level;
		levelTimes = new ArrayList<Integer>();
	}
	
	/** The method to add times into the arraylist of leveltimes*/
	public void fillTime(int time) {
		levelTimes.add(time);
	}
	
	/** The get info method that returns a string of the username and max level
	 * @return String info*/
	public String getInfo() {
		return "CURRENT LEVEL: " + Integer.toString(maxLevel);
	}

	/** Returns the userprofiles name
	 * @return String name*/
	public String getUserName() {
		return userName;
	}

	/** Returns the userProfiles max level
	 * @return int maxLevel*/
	public int getMaxLevel() {
		return maxLevel;
	}

	/** Returns the users times 
	 * @return ArrayList<Integer> levelTimes*/
	public ArrayList<Integer> getLevelTimes() {
		return levelTimes;
	}

	/** The method to change the level into the parameter int*/
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	
	public int getCurrentScore() {
		int currentScore = 0;
		for (int i = 0; i < levelTimes.size(); i++) {
			currentScore =+ i;
		}	
		return currentScore;
	}

}
