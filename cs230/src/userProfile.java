public class userProfile {
	String userName;
	int maxLevel;
	
	public userProfile(String name, int level) {
		userName = name;
		maxLevel = level;
	}
	
	public String getInfo() {
		return userName + ": Level: " + Integer.toString(maxLevel);
	}
}
