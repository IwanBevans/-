import java.util.Scanner;

public class Level {

	private Room room;
	private String path;
	private Scanner file;

	public Level(String path) {
		this.path = path;
		this.file = FileAccess.readFile(path);
		if (file != null) {
			this.room = new Room(file);
		}
	}

	public void resetRoom() {
		this.room = new Room(file);
	}

	public Room getRoom() {
		return room;
	}
}
