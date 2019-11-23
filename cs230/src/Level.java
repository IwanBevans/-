// The level class contains a file reader that inputs a file and creates a 2d array of tiles from it
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/** The level class*/
public class Level {
	private int startingLocationX;
	private int startingLocationY;
	private int width;
	private int height;
	private Tile[][] tiles;
	private String fileName;
	private ArrayList<Enemy> allEnemies;
	
	/** The constructor and file reader of the level class*/
	public Level(String inputtedFile) {
		allEnemies = new ArrayList<Enemy>();
		fileName = inputtedFile;
		try { 
			File file = new File(fileName);
			Scanner input = new Scanner(file);
			input.useDelimiter("\r\n|,");
			if (!input.hasNextInt()) {
				fileName = input.next();
			}
			width = input.nextInt();
			height = input.nextInt();
			tiles = new Tile[width][height];
			for (int y = 0; y < height; y ++) {
				String line = input.next();
				for (int x = 0; x < width; x ++) {
					if (line.charAt(x) == '#') {
						tiles[x][y] = new Wall();
					} if (line.charAt(x) == ' ') {
						tiles[x][y] = new Floor();
					} if (line.charAt(x) == '@') {
						tiles[x][y] = new Goal();
					} if (line.charAt(x) == 'W') {
						tiles[x][y] = new Water();
					} if (line.charAt(x) == 'F') {
						tiles[x][y] = new Fire();
					} if (line.charAt(x) == 't') {
						tiles[x][y] = new tokenTile();
					} if (line.charAt(x) == 'f') {
						tiles[x][y] = new fireBoots();
					} if (line.charAt(x) == 'w') {
						tiles[x][y] = new Flippers();
					} if (line.charAt(x) == 'r') {
						tiles[x][y] = new redKey();
					} if (line.charAt(x) == 'b') {
						tiles[x][y] = new blueKey();
					} if (line.charAt(x) == 'g') {
						tiles[x][y] = new greenKey();
					} if (line.charAt(x) == 'y') {
						tiles[x][y] = new yellowKey();
					} if (line.charAt(x) == 'R') {
						tiles[x][y] = new redKeyDoor();
					} if (line.charAt(x) == 'B') {
						tiles[x][y] = new blueKeyDoor();
					} if (line.charAt(x) == 'G') {
						tiles[x][y] = new greenKeyDoor();
					} if (line.charAt(x) == 'Y') {
						tiles[x][y] = new yellowKeyDoor();
					}
				}
			} while (input.hasNext()) {
				int x = input.nextInt();
				int y = input.nextInt();
				String tileType = input.next();
				if (tileType.equals("START")) {
					startingLocationX = x;
					startingLocationY = y;
				}
				if (tileType.equals("TOKENDOOR")) {
					tiles[x][y] = new tokenDoor(input.nextInt());
				} if (tileType.equals("TELEPORTER")) {
					tiles[x][y] = new Teleporter(input.nextInt(),input.nextInt());
				} if (tileType.equals("DUMBENEMY")) {
					allEnemies.add(new dumbEnemy(x,y));
				} if (tileType.equals("WALLENEMY")) {
					allEnemies.add(new wallEnemy(x,y));
				} if (tileType.equals("VERTICALENEMY")) {
					allEnemies.add(new lineEnemyVertical(x,y));
				} if (tileType.equals("HORIZONTALENEMY")) {
					allEnemies.add(new lineEnemyHorizontal(x,y));
				} if (tileType.equals("SMARTENEMY")) {
					allEnemies.add(new smartEnemy(x,y));
				} if (tileType.equals("HELPTILE")) {
					tiles[x][y] = new helpTile(input.next());
				}
			}
			input.close();
		} catch (FileNotFoundException e) { // If no file is found 
			System.out.println("Error file not found");
		}
	}
	/** Returns the location of starting x
	 * @return int locationX*/
	public int getStartingLocationX() {
		return startingLocationX;
	}
	/** Returns the location of starting y
	 * @return startingLocationY*/
	public int getStartingLocationY() {
		return startingLocationY;
	}

	/** Returns the width of the level
	 * @return int width of level*/
	public int getWidth() {
		return width;
	}
	/** Returns the height of the level
	 * @return int height of level*/
	public int getHeight() {
		return height;
	}

	/** Returns the 2d array of tiles
	 * @return Tile[][] the tiles of the level*/
	public Tile[][] getTiles() {
		return tiles;
	}

	/** Returns string of the filename
	 * @return String filename*/
	public String getFileName() {
		return fileName;
	}

	/** Returns the arraylist of all enemies
	 * @return ArrayList<Enemy> all enemies*/
	public ArrayList<Enemy> getAllEnemies() {
		return allEnemies;
	}
}
