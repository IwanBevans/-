import java.util.Arrays;
import java.util.Scanner;

public class Room {

	private Tile[][] grid;
	private int playerStartX; // unnecessary?
	private int playerStartY; // " "?
	private int roomWidth;
	private int roomHeight;

	public Room(Scanner format) {

		// handles exceptions due to invalid file format
		try {
			// read and set size of room grid
			String line = format.nextLine();
			String[] coords = line.split(",");
			System.out.println(Arrays.toString(coords));
			this.roomWidth = Integer.valueOf(coords[0]);
			this.roomHeight = Integer.valueOf(coords[1]);
			this.grid = new Tile[roomWidth][roomHeight];

			// read the file for wall layout
			for (int i = 0; i < roomHeight; i++) {
				line = format.nextLine();
				String[] wallLine = line.split(",");
				System.out.println(Arrays.toString(wallLine));
				for (int j = 0; j < wallLine.length; j++) {
					if (wallLine[j].equals("#")) {
						this.grid[j][i] = new Wall();
					}
				}
			}

			// read file for placing game objects
			while(format.hasNextLine()) {
				line = format.nextLine();
				String[] objData = line.split(",");
				System.out.println(Arrays.toString(objData));
				int objX = Integer.valueOf(objData[1]);
				int objY = Integer.valueOf(objData[2]);
				String objType = objData[0];
				switch (objType) {
				case "goal":
					//this.grid[objX][objY] = new Goal();
					break;
				case "colDoor":
					//this.grid[objX][objY] = new ColouredDoor(objData[3]); // colour as param
					break;
				case "tokDoor":
					int tokensReq = Integer.valueOf(objData[3]);
					//this.grid[objX][objY] = new TokenDoor(tokensReq); // token req as param
					break;
				case "water":
					//this.grid[objX][objY] = new Water();
					break;
				case "fire":
					//this.grid[objX][objY] = new Fire();
					break;
				case "teleporter":
					int partnerX = Integer.valueOf(objData[3]);
					int partnerY = Integer.valueOf(objData[4]);
					//this.grid[objX][objY] = new Teleporter(partnerX, partnerY);
					// location of partner as param (could be reference to other tp obj?)
					break;
				case "token":
					//this.grid[objX][objY] = new Token();
					break;
				case "flipper":
					//this.grid[objX][objY] = new Flipper();
					break;
				case "fireBoots":
					//this.grid[objX][objY] = new FireBoots();
					break;
				case "key":
					//this.grid[objX][objY] = new Key(objData[3]); // colour as param
					break;
				case "player":
					this.playerStartX = objX; // unnecessary?
					this.playerStartY = objY; // " "?
					//this.grid[objX][objY] = new Player();
					break;
				case "wallEnemy":
					//this.grid[objX][objY] = new wallEnemy(objData[3]); // direction as param
					break;
				case "strEnemy":
					//this.grid[objX][objY] = new strEnemy(objData[3]); //direction as param
					break;
				case "dumbEnemy":
					//this.grid[objX][objY] = new dumbEnemy();
					break;
				case "smrtEnemy":
					//this.grid[objX][objY] = new smrtEnemy();
					break;
				}
			}
		format.close();
		} catch (Exception e) {
			System.out.println("ERROR: Invalid file format");
			//e.printStackTrace();
		}
	}

	public Tile[][] getGrid() {
		return grid;
	}

	public int getRoomWidth() {
		return roomWidth;
	}

	public int getRoomHeight() {
		return roomHeight;
	}

}
