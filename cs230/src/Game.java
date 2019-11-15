import java.lang.Math;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.control.Label;

public class Game extends Application{
	private static final int WINDOW_WIDTH = 900;
	private static final int WINDOW_HEIGHT = 800;
	private static final int CANVAS_WIDTH = 700;
	private static final int CANVAS_HEIGHT = 700;
	private static int GRID_CELL_WIDTH = 100;
	private static int GRID_CELL_HEIGHT = 100;
	
	Level level;
	Player player;
	Canvas canvas;
	VBox inventory;
	Label message;
	
	public void start(Stage stage) {
		// Level needs to be set to whatever level the player chooses
		// make a gui for user profile and menu for selecting levels
		
		level = new Level("level1.txt");
		player = new Player(level);
		
		// creates the borderpane used for the game's gui and the canvas of all the tile images
		BorderPane root = new BorderPane();
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setLeft(canvas);
		
		// creates the savebar at the bottom of the screen and the buttons it uses
		HBox saveBar = new HBox();
		Button restartButton = new Button("Restart");
		restartButton.setMinSize((WINDOW_HEIGHT - CANVAS_HEIGHT),(WINDOW_HEIGHT - CANVAS_HEIGHT)/2);
		saveBar.getChildren().add(restartButton);
		Button saveButton = new Button("Save");
		saveBar.getChildren().add(saveButton);
		saveButton.setMinSize((WINDOW_HEIGHT - CANVAS_HEIGHT),(WINDOW_HEIGHT - CANVAS_HEIGHT)/2);
		saveBar.setLayoutY((double) WINDOW_HEIGHT - (WINDOW_HEIGHT - CANVAS_HEIGHT));
		restartButton.setOnAction(e -> {
			restartGame();
		});
		saveButton.setOnAction(e -> {
			saveGame();
		});
		root.getChildren().add(saveBar);
		
		// creates the inventory sidebar
		inventory = new VBox(CANVAS_HEIGHT/GRID_CELL_HEIGHT);
		root.getChildren().add(inventory);
		inventory.setLayoutX(WINDOW_WIDTH - (WINDOW_WIDTH - CANVAS_WIDTH));
		Label tokens = new Label("Tokens: ".concat(Integer.toString(player.tokens)));
		tokens.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(tokens);
		Label redKeys = new Label("Red keys: ".concat(Integer.toString(player.redKeys)));
		redKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(redKeys);
		Label blueKeys = new Label("Blue keys: ".concat(Integer.toString(player.blueKeys)));
		blueKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(blueKeys);
		Label greenKeys = new Label("Green keys: ".concat(Integer.toString(player.greenKeys)));
		greenKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(greenKeys);
		Label yellowKeys = new Label("Yellow Keys: ".concat(Integer.toString(player.yellowKeys)));
		yellowKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(yellowKeys);
		Label fireBoots = new Label("Fire Boots: ".concat(String.valueOf(player.fireBoots)));
		fireBoots.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(fireBoots);
		Label flippers = new Label("Flippers: ".concat(String.valueOf(player.flippers)));
		flippers.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(flippers);
		
		// draws the map
		draw();
		
		// creates the scene and takes key presses into account
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
		stage.setScene(scene);
		stage.show();	
	}

	private void saveGame() {
		// - add save game feature
	}

	private void restartGame() {
		this.level = new Level(level.fileName);
		this.player = new Player(level);
		draw();
	}

	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		// This is the camera controls, ensures that the camera will always be on the player
		int minX = (int) (player.locationX - (Math.floor((CANVAS_WIDTH/GRID_CELL_WIDTH)/2)));
		int minY = (int) (player.locationY - (Math.floor((CANVAS_HEIGHT/GRID_CELL_HEIGHT)/2)));
		if (minX < 0) {
			minX = 0;
		} if (minY < 0) {
			minY = 0;
		} int maxX = minX + (CANVAS_WIDTH/GRID_CELL_WIDTH);
		int maxY = minY + (CANVAS_HEIGHT/GRID_CELL_HEIGHT);
		if (maxX > level.width) {
			minX = minX - (maxX - level.width);
			maxX = level.width;
		} if (maxY > level.height) {
			minY = minY - (maxY - level.height);
			maxY = level.height;
		}
		
		// draws in all the objects
		for (int x = minX; x < maxX; x ++) {
			for (int y = minY; y < maxY; y++) {
				gc.drawImage(level.tiles[x][y].image, (x - maxX + (CANVAS_WIDTH/GRID_CELL_WIDTH)) * GRID_CELL_WIDTH, (y - maxY + (CANVAS_HEIGHT/GRID_CELL_HEIGHT)) * GRID_CELL_HEIGHT);
			}
		} gc.drawImage(player.playerImage, (player.locationX - maxX + (CANVAS_WIDTH/GRID_CELL_WIDTH))* GRID_CELL_WIDTH, (player.locationY - maxY + (CANVAS_HEIGHT/GRID_CELL_HEIGHT))* GRID_CELL_HEIGHT);
		
		inventory.getChildren().clear();
		Label tokens = new Label("Tokens: ".concat(Integer.toString(player.tokens)));
		tokens.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(tokens);
		Label redKeys = new Label("Red keys: ".concat(Integer.toString(player.redKeys)));
		redKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(redKeys);
		Label blueKeys = new Label("Blue keys: ".concat(Integer.toString(player.blueKeys)));
		blueKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(blueKeys);
		Label greenKeys = new Label("Green keys: ".concat(Integer.toString(player.greenKeys)));
		greenKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(greenKeys);
		Label yellowKeys = new Label("Yellow Keys: ".concat(Integer.toString(player.yellowKeys)));
		yellowKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(yellowKeys);
		Label fireBoots = new Label("Fire Boots: ".concat(String.valueOf(player.fireBoots)));
		fireBoots.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(fireBoots);
		Label flippers = new Label("Flippers: ".concat(String.valueOf(player.flippers)));
		flippers.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().add(flippers);
	}
	
	public void processKeyEvent(KeyEvent event) {
		// move all enemies
		switch (event.getCode()) {
		    case DOWN:
	        	player.moveDown(level);
	        	if (player.dead) {
	        		// display lose screen
	        		restartGame();
	        	} if (player.won) {
	        		// display win screen
	        	}
	        	break;	
		    case UP:
		    	player.moveUp(level);
		    	if (player.dead) {
	        		// display lose screen
		    		restartGame();
	        	} if (player.won) {
	        		// display win screen
	        	}
		    	break;
		    case RIGHT:
		    	player.moveRight(level);
		    	if (player.dead) {
	        		// display lose screen
		    		restartGame();
	        	} if (player.won) {
	        		// display win screen
	        	}
		    	break;
		    case LEFT:
		    	player.moveLeft(level);
		    	if (player.dead) {
	        		// display lose screen
		    		restartGame();
	        	} if (player.won) {
	        		// display win screen
	        	}
		    	break;
	        default:
	        	break;
		}
		draw();
		event.consume();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
