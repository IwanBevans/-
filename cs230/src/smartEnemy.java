import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class smartEnemy extends Enemy{
	public smartEnemy(int x,int y) {
		this.image = new Image("/smartenemy.png");
		this.locationX = x;
		this.locationY = y;
	}

	public void move(Level level,Player player) {
		int currentX = locationX;
		int currentY = locationY;
		String[][] prev = pathFinder(currentX,currentY,level);
		boolean[][] path = reconstructPath(prev,currentX,currentY,player.locationX,player.locationY,level);
		
		if (path[locationX+1][locationY]) {
			locationX = locationX + 1;
		} else if (path[locationX-1][locationY]) {
			locationX = locationX - 1;
		} else if (path[locationX][locationY+1]) {
			locationY = locationY + 1;
		} else if (path[locationX][locationY-1]) {
			locationY = locationY - 1;
		}
	}

	private boolean[][] reconstructPath(String[][] prev,int currentX,int currentY,int goalX, int goalY, Level level) {
		boolean[][] path = new boolean[level.width][level.height];
		for (int x = 0; x < level.height; x++) {
			for (int y = 0; y < level.width; y++) {
				path[y][x] = false;
			}
		} String parentString = prev[goalX][goalY];
		while (parentString != null) {
			Scanner in = new Scanner(parentString);
			in.useDelimiter(",");
			int parentX = in.nextInt();
			int parentY = in.nextInt();
			parentString = prev[parentX][parentY];
			path[parentX][parentY] = true;
			if (parentString != null) {
				in.close();
			}
		} return path;
	} 

	private String[][] pathFinder(int currentX, int currentY, Level level) {
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,1,-1};
		Queue<int[]> bfs = new LinkedList<int[]>();
		bfs.add(new int[] {currentX,currentY});
		
		boolean[][] passed = new boolean[level.width][level.height];
		for (int x = 0; x < level.height; x++) {
			for (int y = 0; y < level.width; y++) {
				passed[y][x] = false;
			}
		} passed[currentX][currentY] = true;
		
		String[][] prev = new String[level.width][level.height];
		for (int x = 0; x < level.height; x++) {
			for (int y = 0; y < level.width; y++) {
				prev[y][x] = null;
			}
		}
		
		while (!bfs.isEmpty()) {
			int[] node = bfs.remove();
			for (int i = 0; i < 4; i++) {
				int rr = node[0] + dr[i];
				int cc = node[1] + dc[i];
				if (level.tiles[rr][cc].isPassableEnemy && !passed[rr][cc]) {
					bfs.add(new int[] {rr,cc});
					passed[rr][cc] = true;
					prev[rr][cc] = (Integer.toString(node[0]).concat(",").concat(Integer.toString(node[1])));
				} 
			} 
		} return prev;
	}
}
