import java.lang.Math;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Game extends Application{
	private static final int WINDOW_WIDTH = 900;
	private static final int WINDOW_HEIGHT = 800;
	private static final int CANVAS_WIDTH = 700;
	private static final int CANVAS_HEIGHT = 700;
	private static int GRID_CELL_WIDTH = 100;
	private static int GRID_CELL_HEIGHT = 100;
	
	Level level;
	Player player;
	ArrayList<userProfile> users;
	userProfile currentUser;
	Canvas canvas;
	VBox inventory;
	VBox message;
	
	public void start(Stage stage) {
		stage.setTitle("Pac Quest");
		loadProfiles();
		if (users.isEmpty()) {
			createNewProfile(stage);
		} else{
			mainmenu(stage);
		}
	}
		
	public void game(Stage stage) {		
		// creates the canvas used for all the images of the tiles and places it on the pane along with the help message
		message = new VBox();
		message.setLayoutX(CANVAS_WIDTH);
		message.setLayoutY(CANVAS_HEIGHT);
		BorderPane root = new BorderPane();
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setLeft(canvas);
		
		// creates the savebar at the bottom of the screen and the buttons it uses
		HBox saveBar = new HBox();
		Button restartButton = new Button("Restart");
		restartButton.setMinSize((WINDOW_HEIGHT - CANVAS_HEIGHT),(WINDOW_HEIGHT - CANVAS_HEIGHT)/2);
		Button saveButton = new Button("Quick save");
		saveBar.getChildren().addAll(restartButton,saveButton);
		saveButton.setMinSize((WINDOW_HEIGHT - CANVAS_HEIGHT),(WINDOW_HEIGHT - CANVAS_HEIGHT)/2);
		saveBar.setLayoutY((double) WINDOW_HEIGHT - (WINDOW_HEIGHT - CANVAS_HEIGHT));
		restartButton.setOnAction(e -> {
			restartGame();
		});
		saveButton.setOnAction(e -> {
			saveGame();
		});
		
		// creates the inventory sidebar
		inventory = new VBox(CANVAS_HEIGHT/GRID_CELL_HEIGHT);
		root.getChildren().addAll(message,saveBar,inventory);
		
		// draws the map
		draw();
		
		// creates the scene and takes key presses into account
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event,stage));
		stage.setScene(scene);
		stage.show();
	}

	public void loadProfiles() {
		// Creates an arraylist based on all the userprofiles from the savefile
		users = new ArrayList<userProfile>();
		try { 
			File file = new File("savefile.txt");
			Scanner input = new Scanner(file);
			input.useDelimiter("\r\n|,");
			while (input.hasNext()) {
				users.add(new userProfile(input.next(),input.nextInt()));
			} input.close();
		} catch (FileNotFoundException e) {}
	}
	
	private void createNewProfile(Stage stage) {
		// creating the new profile menu
		VBox menu = new VBox();
		TextField nameInput = new TextField();
		Label nameInputText = new Label("Please input a username");
		Button confirmName = new Button("Create new userprofile");
		menu.getChildren().addAll(nameInputText,nameInput,confirmName);
		
		confirmName.setOnAction(e -> {
			String inputtedName = nameInput.getText().replace(',',' ');
			if (inputtedName.equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error: Cannot create user with no name");
				alert.setHeaderText(null);
				alert.setContentText("Cannot make a user with less then one character");
				alert.showAndWait();
			} else {
				try {
					File file = new File("savefile.txt");
					if (file.exists()) {
						Files.write(Paths.get("savefile.txt"), (inputtedName+",1,").getBytes(),StandardOpenOption.APPEND);
					} else {
						Files.write(Paths.get("savefile.txt"), (inputtedName+",1,").getBytes());
					} 
				}catch (Exception e1) {e1.printStackTrace();}
			} loadProfiles();
			mainmenu(stage);
		});
		
		Scene scene = new Scene(menu, WINDOW_WIDTH/3,WINDOW_HEIGHT/5);
		stage.setScene(scene);
		stage.show();
	}

	public void mainmenu(Stage stage) {
		BorderPane loadFile = new BorderPane();
		ListView<String> userList = new ListView<String>();
		for (userProfile x : users) {
			userList.getItems().add(x.getInfo());
		}
		loadFile.setCenter(userList);
		HBox profileButtons = new HBox();
		Button load = new Button("Load file");
		Button deleteUser = new Button("Delete profile");
		Button newUser = new Button("New profile");
		load.setOnAction(e -> {
			int selectedIndex = userList.getSelectionModel().getSelectedIndex();
			if (selectedIndex < 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error: Cannot load user");
				alert.setHeaderText(null);
				alert.setContentText("No user is currently selected to be loaded");
				alert.showAndWait();
			} else {
				currentUser = users.get(selectedIndex);
				userMenu(stage);
			}
		});
		deleteUser.setOnAction(e -> {
			int selectedIndex = userList.getSelectionModel().getSelectedIndex();
			if (selectedIndex < 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error: Cannot delete user");
				alert.setHeaderText(null);
				alert.setContentText("No user is currently selected to be deleted");
				alert.showAndWait();
			}
			else {
				users.remove(selectedIndex);
				updateProfiles();
				loadProfiles();
				mainmenu(stage);
			}
		});
		newUser.setOnAction(e -> {
			createNewProfile(stage);
		});
		
		profileButtons.getChildren().addAll(load,deleteUser,newUser);
		loadFile.setBottom(profileButtons);
		Scene scene = new Scene(loadFile, WINDOW_WIDTH,WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.show();
	}

	private void userMenu(Stage stage) {
		BorderPane loadLevel = new BorderPane();
		ListView<String> levelList = new ListView<String>();
		for (int x = 1; x <= currentUser.maxLevel;x++) {
			levelList.getItems().add(Integer.toString(x));
		} loadLevel.setCenter(levelList);
		Button load = new Button("Load level");
		load.setOnAction(e -> {
			int selectedIndex = levelList.getSelectionModel().getSelectedIndex();
			if (selectedIndex < 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error: Cannot load level");
				alert.setHeaderText(null);
				alert.setContentText("No level is currently selected to be loaded");
				alert.showAndWait();
			} else {
				level = new Level("level"+Integer.toString(selectedIndex+1)+".txt");
				player = new Player(level);
				game(stage);
			}
		});
		loadLevel.setCenter(levelList);
		loadLevel.setBottom(load);
		Scene scene = new Scene(loadLevel, WINDOW_WIDTH,WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.show();
	}

	private void updateProfiles() {
		try {
			if (users.isEmpty()) {
				Files.write(Paths.get("savefile.txt"), ("").getBytes());
			} else {
				Files.write(Paths.get("savefile.txt"), (users.get(0).userName+","+users.get(0).maxLevel+",").getBytes());
				users.remove(0);
				for (userProfile x : users) {
					Files.write(Paths.get("savefile.txt"), (x.userName+","+x.maxLevel+",").getBytes(),StandardOpenOption.APPEND);
				}
			} 
		} catch (IOException e) {e.printStackTrace();}
	}

	private void saveGame() {
		message.getChildren().clear();
		Label messageText = new Label("Game has been saved");
		messageText.setMinSize(GRID_CELL_WIDTH*2, GRID_CELL_HEIGHT);
		message.getChildren().add(messageText);
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
				for (int i = 0; i < level.allEnemies.size(); i++) {
					if (x == level.allEnemies.get(i).locationX && y == level.allEnemies.get(i).locationY) {
						gc.drawImage(level.allEnemies.get(i).image, (x - maxX + (CANVAS_WIDTH/GRID_CELL_WIDTH)) * GRID_CELL_WIDTH, (y - maxY + (CANVAS_HEIGHT/GRID_CELL_HEIGHT)) * GRID_CELL_HEIGHT);
					}
				}
			}
		} gc.drawImage(player.playerImage, (player.locationX - maxX + (CANVAS_WIDTH/GRID_CELL_WIDTH))* GRID_CELL_WIDTH, (player.locationY - maxY + (CANVAS_HEIGHT/GRID_CELL_HEIGHT))* GRID_CELL_HEIGHT);
		
		inventory.setLayoutX(WINDOW_WIDTH - (WINDOW_WIDTH - CANVAS_WIDTH));
		inventory.getChildren().clear();
		Label tokens = new Label("Tokens: ".concat(Integer.toString(player.tokens)));
		tokens.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		Label redKeys = new Label("Red keys: ".concat(Integer.toString(player.redKeys)));
		redKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		Label blueKeys = new Label("Blue keys: ".concat(Integer.toString(player.blueKeys)));
		blueKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		Label greenKeys = new Label("Green keys: ".concat(Integer.toString(player.greenKeys)));
		greenKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		Label yellowKeys = new Label("Yellow Keys: ".concat(Integer.toString(player.yellowKeys)));
		yellowKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		Label fireBoots = new Label("Fire Boots: ".concat(String.valueOf(player.fireBoots)));
		fireBoots.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		Label flippers = new Label("Flippers: ".concat(String.valueOf(player.flippers)));
		flippers.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT - CANVAS_HEIGHT));
		inventory.getChildren().addAll(tokens,redKeys,blueKeys,greenKeys,yellowKeys,fireBoots,flippers);
	}
	
	public void processKeyEvent(KeyEvent event,Stage stage) {
		message.getChildren().clear();
		switch (event.getCode()) {
		    case DOWN:
	        	player.moveDown(level);
	        	if (level.tiles[player.locationX][player.locationY + 1] instanceof Door) {
	    			Door door = (Door) level.tiles[player.locationX][player.locationY + 1];
	    			if (door.isLocked) {
	    				Label messageText = new Label("The door is locked");
	    	    		messageText.setMinSize(GRID_CELL_WIDTH*2, 30);
	    	    		message.getChildren().add(messageText);
	    				if (door instanceof tokenDoor) {
	    					tokenDoor door2 = (tokenDoor) door;
	    					Label messageText2 = new Label("Requires ".concat(Integer.toString(door2.amount).concat(" Token(s)")));
	    					messageText2.setMinSize(GRID_CELL_WIDTH*2, 30);
	    		    		message.getChildren().add(messageText2);
	    				}
	    			}
	    		}
	        	break;	
		    case UP:
		    	player.moveUp(level);
	        	if (level.tiles[player.locationX][player.locationY - 1] instanceof Door) {
	    			Door door = (Door) level.tiles[player.locationX][player.locationY - 1];
	    			if (door.isLocked) {
	    				Label messageText = new Label("The door is locked");
	    	    		messageText.setMinSize(GRID_CELL_WIDTH*2, 30);
	    	    		message.getChildren().add(messageText);
	    				if (door instanceof tokenDoor) {
	    					tokenDoor door2 = (tokenDoor) door;
	    					Label messageText2 = new Label("Requires ".concat(Integer.toString(door2.amount).concat(" Token(s)")));
	    					messageText2.setMinSize(GRID_CELL_WIDTH*2, 30);
	    		    		message.getChildren().add(messageText2);
	    				}
	    			}
	    		}
		    	break;
		    case RIGHT:
		    	player.moveRight(level);
	        	if (level.tiles[player.locationX + 1][player.locationY] instanceof Door) {
	    			Door door = (Door) level.tiles[player.locationX + 1][player.locationY];
	    			if (door.isLocked) {
	    				Label messageText = new Label("The door is locked");
	    	    		messageText.setMinSize(GRID_CELL_WIDTH*2, 30);
	    	    		message.getChildren().add(messageText);
	    				if (door instanceof tokenDoor) {
	    					tokenDoor door2 = (tokenDoor) door;
	    					Label messageText2 = new Label("Requires ".concat(Integer.toString(door2.amount).concat(" Token(s)")));
	    					messageText2.setMinSize(GRID_CELL_WIDTH*2, 30);
	    		    		message.getChildren().add(messageText2);
	    				}
	    			}
	    		}
		    	break;
		    case LEFT:
		    	player.moveLeft(level);
		    	if (level.tiles[player.locationX - 1][player.locationY] instanceof Door) {
	    			Door door = (Door) level.tiles[player.locationX - 1][player.locationY];
	    			if (door.isLocked) {
	    				Label messageText = new Label("The door is locked");
	    	    		messageText.setMinSize(GRID_CELL_WIDTH*2, 30);
	    	    		message.getChildren().add(messageText);
	    				if (door instanceof tokenDoor) {
	    					tokenDoor door2 = (tokenDoor) door;
	    					Label messageText2 = new Label("Requires ".concat(Integer.toString(door2.amount).concat(" Token(s)")));
	    					messageText2.setMinSize(GRID_CELL_WIDTH*2, 30);
	    		    		message.getChildren().add(messageText2);
	    				}
	    			}
	    		}
		    	break;
	        default:
	        	break;
		}
		if (player.dead) {
    		Label messageText = new Label("You died!");
    		messageText.setMinSize(GRID_CELL_WIDTH, GRID_CELL_HEIGHT);
    		message.getChildren().add(messageText);
    		restartGame();
    	} if (player.won) {
    		int levelNumber =  Integer.parseInt((level.fileName.substring(5)).substring(0,(level.fileName.substring(5).length()-4)));
    		if (currentUser.maxLevel == levelNumber) {
    			currentUser.maxLevel = currentUser.maxLevel + 1;
    			updateProfiles();

    		} loadProfiles();
    		mainmenu(stage);
    	} 
		draw();
		event.consume();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
