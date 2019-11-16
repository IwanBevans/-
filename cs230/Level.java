import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Level {
	int startingLocationX;
	int startingLocationY;
	int width;
	int height;
	Tile[][] tiles;
	String fileName;
	ArrayList<Enemy> allEnemies;
	
	public Level(String fileName) {
		this.allEnemies = new ArrayList<Enemy>();
		this.fileName = fileName;
		File file = new File(fileName);
		try { 
			Scanner input = new Scanner(file);
			input.useDelimiter("\r\n|,");
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
				}
			}
			input.close();
		} catch (FileNotFoundException e) { // If no file is found 
			System.out.println("Error file not found");
		}
	}
}
