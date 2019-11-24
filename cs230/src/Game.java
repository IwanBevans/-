// The game class, contains the javafx for all the of the screens, and the game itself
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** The game class, controls all of the menus and the javafx 
 * controls the gui for the user*/
public class Game extends Application{
	// All of the global variables
	// The size of the window and canvas for the level
	private static final int WINDOW_WIDTH = 1024;
	private static final int WINDOW_HEIGHT = 786;
	private static final int CANVAS_WIDTH = WINDOW_WIDTH - (WINDOW_WIDTH/5);
	private static final int CANVAS_HEIGHT = WINDOW_HEIGHT - (WINDOW_HEIGHT/5);
	// Changable stats based on the settings of the game
	private static int GRID_CELL_WIDTH = 50;
	private static int GRID_CELL_HEIGHT = 50;
	private static int FINAL_LEVEL_NUMBER = 10;
	private static int INVENTORY_SIZE = 8;
	// Attributes that are called in mutiple disjoint functions
	private Level level;
	private Player player;
	private ArrayList<userProfile> users;
	private userProfile currentUser;
	private Canvas canvas;
	private VBox inventory;
	private VBox message;
	private int timeDelay;
	private long startTime;
	private long endTime;
	
	// The start function, sets the name of the game and then loads profiles or creates a new profile,
	/** The inital method for the game, ensures a user exist before entering the main menu*/
	public void start(Stage stage) {
		stage.setTitle("Pac Quest");
		loadProfiles();
		if (users.isEmpty()) {
			createNewProfile(stage);
		} else{
			mainmenu(stage);
		}
	}
	
	// The main game screen, starts the timer and sets the message box, save bar and restart buttons and the inventory screen	
	/** The game screen, displays the map of all the tiles and the inventory/buttons*/
	public void game(Stage stage) {
		startTime = System.currentTimeMillis();
		// creates the canvas used for all the images of the tiles and places it on the pane along with the help message
		message = new VBox();
		message.setLayoutY(CANVAS_HEIGHT+GRID_CELL_HEIGHT/2);
		message.setMinSize(CANVAS_WIDTH, GRID_CELL_HEIGHT*3);
		BorderPane root = new BorderPane();
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setLeft(canvas);
		
		// creates the savebar at the bottom of the screen and the buttons it uses
		HBox saveBar = new HBox();
		Button restartButton = new Button("Restart");
		restartButton.setMinSize((WINDOW_HEIGHT - CANVAS_HEIGHT),(WINDOW_HEIGHT - CANVAS_HEIGHT)/4);
		Button saveButton = new Button("Quick save");
		saveButton.setMinSize((WINDOW_HEIGHT - CANVAS_HEIGHT),(WINDOW_HEIGHT - CANVAS_HEIGHT)/4);
		Button quitButton = new Button("Quit");
		quitButton.setMinSize((WINDOW_HEIGHT - CANVAS_HEIGHT),(WINDOW_HEIGHT - CANVAS_HEIGHT)/4);
		saveBar.getChildren().addAll(restartButton,saveButton,quitButton);
		saveBar.setLayoutY((double) WINDOW_HEIGHT - (WINDOW_HEIGHT - CANVAS_HEIGHT));
		restartButton.setOnAction(e -> {
			restartGame();
		});
		saveButton.setOnAction(e -> {
			saveGame();
		});
		quitButton.setOnAction(e -> {
			mainmenu(stage);
		});
		// creates the inventory sidebar
		inventory = new VBox();
		root.getChildren().addAll(message,saveBar,inventory);
		
		// draws the map
		draw();
		
		// creates the scene and takes key presses into account
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event,stage));
		stage.setScene(scene);
		stage.show();
	}

	// Loads all of the profiles from the save file
	/** Reads a user from the userlist as the current user*/
	public void loadProfiles() {
		// Creates an arraylist based on all the userprofiles from the savefile
		users = new ArrayList<userProfile>();
		try { 
			File file = new File("savefile.txt");
			Scanner input = new Scanner(file);
			input.useDelimiter("\r\n|,");
			while (input.hasNext()) {
				userProfile currentProfile = new userProfile(input.next(),input.nextInt());
				while (input.hasNextInt()) {
					currentProfile.fillTime(input.nextInt());
				} users.add(currentProfile);
			} input.close();
		} catch (FileNotFoundException e) {}
	}
	
	// The method to create a new profile
	/** Creates a new profile in the list of users and adds it to the save file*/
	private void createNewProfile(Stage stage) {
		// creating the new profile menu
		VBox menu = new VBox();
		TextField nameInput = new TextField();
		Label nameInputText = new Label("Please input a username");
		Button confirmName = new Button("Create new userprofile");
		menu.getChildren().addAll(nameInputText,nameInput,confirmName);
		
		// Checks to see if the inputted name is an int (Would cause an error when reading save files), if the user didn't enter a name
		// ,if the user entered a name too long or if the user entered a name that would already exist.
		confirmName.setOnAction(e -> {
			boolean flag = false;
			String inputtedName = nameInput.getText().replace(',',' ');
			try {
		        @SuppressWarnings("unused")
				int testing = Integer.parseInt(inputtedName);
		        Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error: Cannot create user with this name");
				alert.setHeaderText(null);
				alert.setContentText("Cannot make a user with no letters in the name");
				alert.showAndWait();
				flag = true;
		    } catch (NumberFormatException | NullPointerException nfe) {
		    	
		    }
			for (int x = 0; x < users.size(); x++) {
				if (inputtedName.equals(users.get(x).getUserName())) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error: Cannot create user with this name");
					alert.setHeaderText(null);
					alert.setContentText("Cannot make a user with the same name as another user");
					alert.showAndWait();
					flag = true;
				}
			} if (inputtedName.length() > 10) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error: Cannot create user with this name");
				alert.setHeaderText(null);
				alert.setContentText("Cannot make a user with more then 10 characters");
				alert.showAndWait();
				flag = true;
			} 
			if (inputtedName.equals("")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error: Cannot create user with no name");
				alert.setHeaderText(null);
				alert.setContentText("Cannot make a user with less then one character");
				alert.showAndWait();
			} else if (!flag){
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

	// The main menu function 
	/** Creates the main menu of the game*/
	public void mainmenu(Stage stage) {
		// creates the main menu
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
		Button quickLoad = new Button("Continue");
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
		quickLoad.setOnAction(e -> {
			int selectedIndex = userList.getSelectionModel().getSelectedIndex();
			loadSave(selectedIndex,stage);
		});
		profileButtons.getChildren().addAll(load,deleteUser,newUser,quickLoad);
		loadFile.setBottom(profileButtons);
		Label dailyMessage = loadDailyMessage(new Label());
		
		loadFile.setTop(dailyMessage);
		dailyMessage.setMinHeight(GRID_CELL_HEIGHT);
		
		Scene scene = new Scene(loadFile, WINDOW_WIDTH,WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.show();
	}

	// The function to load the daily message
	/** Loads a given website, solves a string cipher puzzle and loads a second url
	 * with the solved puzzle to obtain a message and displays it on the main menu*/
	private Label loadDailyMessage(Label dailyMessage) {
		try {
			URL url = new URL("http://cswebcat.swan.ac.uk/puzzle");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent","Mozilla/5.0");
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // success
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String puzzle = in.readLine();
				String solvedPuzzle = "";
				for (int x = 0; x < puzzle.length(); x ++) {
					char characterAtX = puzzle.charAt(x);
					int ascii = (int)characterAtX;
					if (x % 2 == 0) {
						ascii = ascii + 1;
						if (ascii > (int)'Z') {
							ascii = ascii - 26;
						}
					} else {
						ascii = ascii - 1;
						if (ascii < (int)'A') {
							ascii = ascii + 26;
						}
					} solvedPuzzle = solvedPuzzle + (char)ascii;
				}
				URL url2 = new URL("http://cswebcat.swan.ac.uk/message?solution="+solvedPuzzle);
				HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
				con2.setRequestMethod("GET");
				con2.setRequestProperty("User-Agent","Mozilla/5.0");
				int responseCode2 = con.getResponseCode();
				if (responseCode2 == HttpURLConnection.HTTP_OK) { // success
					in = new BufferedReader(new InputStreamReader(con2.getInputStream()));
					String messageOfTheDay = in.readLine();
					dailyMessage = new Label(messageOfTheDay);
					dailyMessage.setWrapText(true);
				in.close();
				}
			}
		} catch (UnknownHostException e1) {}
		catch (MalformedURLException e1) {e1.printStackTrace();}
		catch (IOException e1) {e1.printStackTrace();}
		return dailyMessage;
	}

	// the function that loads the save into a new level
	/** Creates a new level from the saved data of the user and sets the players stats to the saved state of the player*/
	private void loadSave(int selectedIndex,Stage stage) {
		if (selectedIndex < 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error: Cannot load save file");
			alert.setHeaderText(null);
			alert.setContentText("No user is currently selected to load from");
			alert.showAndWait();
		}
		else {
			currentUser = users.get(selectedIndex);
			File file = new File((users.get(selectedIndex).getUserName())+"playersave.txt");
			File file2 = new File((users.get(selectedIndex).getUserName())+"levelsave.txt");
			if (!file.exists()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error: Cannot load save file");
				alert.setHeaderText(null);
				alert.setContentText("No saved data exists for the user");
				alert.showAndWait();
			} else {
				currentUser = users.get(selectedIndex);
				level = new Level((users.get(selectedIndex).getUserName())+"levelsave.txt");
				player = new Player(level);
				try {
					Scanner input = new Scanner(file);
					input.useDelimiter(",");
					player.setBlueKeys(input.nextInt());
					player.setFireBoots(input.nextBoolean());
					player.setFlippers(input.nextBoolean());
					player.setGreenKeys(input.nextInt());
					player.setRedKeys(input.nextInt());
					player.setTokens(input.nextInt());
					player.setYellowKeys(input.nextInt());
					timeDelay = input.nextInt();
					input.close();
					file.delete();
					file2.delete();
					game(stage);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	// the user menu that shows the level select screen
	/** Displays all the levels the user can select*/
	private void userMenu(Stage stage) {
		BorderPane loadLevel = new BorderPane();
		HBox levelButtons = new HBox();
		ListView<String> levelList = new ListView<String>();
		for (int x = 1; x <= currentUser.getMaxLevel();x++) {
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
				timeDelay = 0;
				game(stage);
			}
		});
		Button returnButton = new Button("Return to main menu");
		returnButton.setOnAction(e -> {
			mainmenu(stage);
		});
		Button highScores = new Button("Display high scores");
		highScores.setOnAction(e -> {
			int selectedIndex = levelList.getSelectionModel().getSelectedIndex();
			if (selectedIndex < 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error: Cannot display scores");
				alert.setHeaderText(null);
				alert.setContentText("No level is currently selected for the scores");
				alert.showAndWait();
			} else {
				displayHighScores(stage,selectedIndex);
			}
		});
		loadLevel.setCenter(levelList);
		levelButtons.getChildren().addAll(load,returnButton,highScores);
		loadLevel.setBottom(levelButtons);
		Scene scene = new Scene(loadLevel, WINDOW_WIDTH,WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.show();
	}

	// the screen that displays the high scores
	/** Displays the top high scores*/
	private void displayHighScores(Stage stage,int Index) {
		// creates an array of all the users times for the selected level and sorts them from higest to lowest
		int amountOfScores = 3; // THIS CAN BE CHANGED TO HOW EVER MANY TOP SCORES YOU WANT TO SHOW
		
		int[] topTimes = new int[users.size()];
		userProfile[] profiles = new userProfile[users.size()];
		for (int x = 0; x < users.size();x++) {
			if (users.get(x).getLevelTimes().size()-1 < Index) {
				topTimes[x] = 0;
			} else {
				topTimes[x] = users.get(x).getLevelTimes().get(Index);
			} profiles[x] = users.get(x);
		} for (int x = 0; x < users.size()-1;x++) {
			for (int y =0; y < users.size()-1;y++) {
				if (topTimes[y] < topTimes[y+1]) {
					int temp = topTimes[y];
					userProfile temp2 = profiles[y];
					topTimes[y] = topTimes[y+1];
					profiles[y] = profiles[y+1];
					topTimes[y+1] = temp;
					profiles[y+1] = temp2;
				}
			}
		}
		VBox highScoreScreen = new VBox();
		ListView<String> highScores = new ListView<String>();
		if (amountOfScores > users.size()) {
			amountOfScores = users.size();
		}
		for (int x = 0; x < amountOfScores; x ++) {
			highScores.getItems().add(profiles[x].getUserName()+": "+topTimes[x]+" Seconds");
		}
		Button returnButton = new Button("Return");
		returnButton.setOnAction(e -> {
			userMenu(stage);
		});
		highScoreScreen.getChildren().addAll(highScores,returnButton);
		Scene scene = new Scene(highScoreScreen,WINDOW_WIDTH/2,WINDOW_HEIGHT/2);
		stage.setScene(scene);
		stage.show();
	}

	// function that updates profiles after levels are completed or new userprofiles are made
	/** Write information about all users into a savefile*/
	private void updateProfiles() {
		try {
			if (users.isEmpty()) {
				Files.write(Paths.get("savefile.txt"), ("").getBytes());
			} else {
				Files.write(Paths.get("savefile.txt"), (users.get(0).getUserName()+","+users.get(0).getMaxLevel()+",").getBytes());
				for (int x = 0; x < users.get(0).getLevelTimes().size(); x ++) {
					int currentTime = users.get(0).getLevelTimes().get(x);
					Files.write(Paths.get("savefile.txt"), (Integer.toString(currentTime)+",").getBytes(), StandardOpenOption.APPEND);
				}
				users.remove(0);
				for (userProfile x : users) {
					Files.write(Paths.get("savefile.txt"), (x.getUserName()+","+x.getMaxLevel()+",").getBytes(),StandardOpenOption.APPEND);
					for (int y = 0; y < x.getLevelTimes().size(); y ++) {
						Files.write(Paths.get("savefile.txt"), (Integer.toString(x.getLevelTimes().get(y))+",").getBytes(), StandardOpenOption.APPEND);
					}
				}
			} 
		} catch (IOException e) {e.printStackTrace();}
	}

	// the save game function, a file writer
	/** Writes a quick save file that can be continued from by the user*/
	private void saveGame() {
		endTime = System.currentTimeMillis() - startTime;
		int finalTime = (int) Math.round(endTime/1000F) + timeDelay;
		message.getChildren().clear();
		Label messageText = new Label("Game has been saved");
		messageText.setMinSize(GRID_CELL_WIDTH*3, GRID_CELL_HEIGHT);
		message.getChildren().add(messageText);
		
			try {
				Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"), (level.getFileName()).getBytes());
				Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
				Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"), (Integer.toString(level.getWidth())+","+Integer.toString(level.getHeight())).getBytes(), StandardOpenOption.APPEND);
				Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
				ArrayList<String> extraTiles = new ArrayList<String>();
				for (int y = 0; y < level.getHeight(); y ++) {
					String line = "";
					for (int x = 0; x < level.getWidth(); x ++) {
						if (level.getTiles()[x][y] instanceof Wall) {
							line = line + "#";
						} if (level.getTiles()[x][y] instanceof objectTile) {
							objectTile object = (objectTile) level.getTiles()[x][y];
							if (object.isPickedUp()) {
								line = line + " ";
							} else if (level.getTiles()[x][y] instanceof blueKey) {
								line = line + "b";
							} else if (level.getTiles()[x][y] instanceof fireBoots) {
								line = line + "f";
							} else if (level.getTiles()[x][y] instanceof Flippers) {
								line = line + "w";
							} else if (level.getTiles()[x][y] instanceof greenKey) {
								line = line + "g";
							} else if (level.getTiles()[x][y] instanceof redKey) {
								line = line + "r";
							} else if (level.getTiles()[x][y] instanceof tokenTile) {
								line = line + "t";
							} else if (level.getTiles()[x][y] instanceof yellowKey) {
								line = line + "y";
							}
						} if (level.getTiles()[x][y] instanceof Door) {
							Door door = (Door) level.getTiles()[x][y];
							if (!door.isLocked()) {
								line = line + " ";
							} else if (level.getTiles()[x][y] instanceof blueKeyDoor) {
								line = line + "B";
							} else if (level.getTiles()[x][y] instanceof greenKeyDoor) {
								line = line + "G";
							} else if (level.getTiles()[x][y] instanceof redKeyDoor) {
								line = line + "R";
							} else if (level.getTiles()[x][y] instanceof yellowKeyDoor) {
								line = line + "Y";
							} else if (level.getTiles()[x][y] instanceof tokenDoor) {
								tokenDoor doorTile = (tokenDoor) door;
								extraTiles.add(Integer.toString(x)+","+Integer.toString(y)+",TOKENDOOR,"+doorTile.getAmount());
								line = line + " ";
							}
						} if (level.getTiles()[x][y] instanceof Floor) {
							line = line + " ";
						} if (level.getTiles()[x][y] instanceof Water) {
							line = line + "W";
						} if (level.getTiles()[x][y] instanceof Fire) {
							line = line + "F";
						} if (level.getTiles()[x][y] instanceof Goal) {
							line = line + "@";
						} if (level.getTiles()[x][y] instanceof Teleporter) {
							line = line + " ";
							Teleporter teleTile = (Teleporter) level.getTiles()[x][y];
							extraTiles.add(Integer.toString(x)+","+Integer.toString(y)+",TELEPORTER"+","+Integer.toString(teleTile.getToLocationX())+","+(Integer.toString(teleTile.getToLocationY())));
						} if (level.getTiles()[x][y] instanceof helpTile) {
							line = line + " ";
							helpTile tile = (helpTile) level.getTiles()[x][y];
							extraTiles.add(Integer.toString(x)+","+Integer.toString(y)+",HELPTILE"+","+tile.getHelpMessage());
						} if (level.getTiles()[x][y] instanceof crackedFloor) {
							line = line + " ";
							crackedFloor tile = (crackedFloor) level.getTiles()[x][y];
							extraTiles.add(Integer.toString(x)+","+Integer.toString(y)+",CRACKEDFLOOR"+","+tile.isPassable());
						}
					} Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"), (line).getBytes(), StandardOpenOption.APPEND);
					Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
				} Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"), (Integer.toString(player.getLocationX())+","+Integer.toString(player.getLocationY())+",START").getBytes(), StandardOpenOption.APPEND);
				Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
				for (int x = 0; x < extraTiles.size(); x ++) {
					Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"), (extraTiles.get(x)).getBytes(), StandardOpenOption.APPEND);
					Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
				} for (int y = 0; y < level.getAllEnemies().size(); y ++) {
					Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),(Integer.toString(level.getAllEnemies().get(y).getLocationX())+","+Integer.toString(level.getAllEnemies().get(y).getLocationY())+",").getBytes(), StandardOpenOption.APPEND);
					if (level.getAllEnemies().get(y) instanceof dumbEnemy) {
						Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),("DUMBENEMY").getBytes(), StandardOpenOption.APPEND);
					} if (level.getAllEnemies().get(y) instanceof wallEnemy) {
						Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),("WALLENEMY").getBytes(), StandardOpenOption.APPEND);
					} if (level.getAllEnemies().get(y) instanceof smartEnemy) {
						Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),("SMARTENEMY").getBytes(), StandardOpenOption.APPEND);
					} if (level.getAllEnemies().get(y) instanceof lineEnemyVertical) {
						Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),("VERTICALENEMY").getBytes(), StandardOpenOption.APPEND);
					} if (level.getAllEnemies().get(y) instanceof lineEnemyHorizontal) {
						Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),("HORIZONTALENEMY").getBytes(), StandardOpenOption.APPEND);
					} Files.write(Paths.get(currentUser.getUserName()+"levelsave.txt"),System.getProperty("line.separator").getBytes(), StandardOpenOption.APPEND);
				}
				Files.write(Paths.get(currentUser.getUserName()+"playersave.txt"),(Integer.toString(player.getBlueKeys())+","+Boolean.toString(player.isFireBoots())+","+
						Boolean.toString(player.isFlippers())+","+Integer.toString(player.getGreenKeys())+","+Integer.toString(player.getRedKeys())+","+Integer.toString(player.getTokens())+","+
						Integer.toString(player.getYellowKeys())+","+Integer.toString(finalTime)).getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	// restarts the game
	/** The function to restart the game state*/
	private void restartGame() {
		this.level = new Level(level.getFileName());
		this.player = new Player(level);
		draw();
	}

	// draws on the canvas all the tiles, enemies and the player
	/** Draws all tiles, enemies and the player onto the canvas*/
	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		// This is the camera controls, ensures that the camera will always be on the player
		int minY = (int) (player.getLocationY() - (Math.floor((CANVAS_HEIGHT/GRID_CELL_HEIGHT)/2)));
		int maxY = (int) (player.getLocationY() + (Math.floor((CANVAS_HEIGHT/GRID_CELL_HEIGHT)/2)));
		int minX = (int) (player.getLocationX() - (Math.floor((CANVAS_WIDTH/GRID_CELL_WIDTH)/2)));
		int maxX = (int) (player.getLocationX() + (Math.floor((CANVAS_WIDTH/GRID_CELL_WIDTH)/2)));	
		if (maxX > level.getWidth()) {
			minX = minX - (maxX - level.getWidth());
			maxX = level.getWidth();
		} if (maxY > level.getHeight()) {
			minY = minY - (maxY - level.getHeight());
			maxY = level.getHeight();
		} if (minX < 0) {
			maxX = maxX - minX;
			if (maxX > level.getWidth()) {
				maxX = level.getWidth();
			}
			minX = 0;
		} if (minY < 0) {
			maxY = maxY - minY;
			if (maxY > level.getHeight()) {
				maxY = level.getHeight();
			}
			minY = 0;
		}
		// draws in all the objects
		for (int x = minX; x < maxX; x ++) {
			for (int y = minY; y < maxY; y++) {
				gc.drawImage(level.getTiles()[x][y].getImage(), (x - maxX + (CANVAS_WIDTH/GRID_CELL_WIDTH)) * GRID_CELL_WIDTH, (y - maxY + (CANVAS_HEIGHT/GRID_CELL_HEIGHT)) * GRID_CELL_HEIGHT);
				for (int i = 0; i < level.getAllEnemies().size(); i++) {
					if (x == level.getAllEnemies().get(i).getLocationX() && y == level.getAllEnemies().get(i).getLocationY()) {
						gc.drawImage(level.getAllEnemies().get(i).getImage(), (x - maxX + (CANVAS_WIDTH/GRID_CELL_WIDTH)) * GRID_CELL_WIDTH, (y - maxY + (CANVAS_HEIGHT/GRID_CELL_HEIGHT)) * GRID_CELL_HEIGHT);
					}
				}
			}
		} gc.drawImage(player.getPlayerImage(), (player.getLocationX() - maxX + (CANVAS_WIDTH/GRID_CELL_WIDTH))* GRID_CELL_WIDTH, (player.getLocationY() - maxY + (CANVAS_HEIGHT/GRID_CELL_HEIGHT))* GRID_CELL_HEIGHT);
		
		inventory.setLayoutX(WINDOW_WIDTH - (WINDOW_WIDTH - CANVAS_WIDTH));
		inventory.getChildren().clear();
		Label tokens = new Label("Tokens: ".concat(Integer.toString(player.getTokens())));
		tokens.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT/INVENTORY_SIZE));
		Label redKeys = new Label("Red keys: ".concat(Integer.toString(player.getRedKeys())));
		redKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT/INVENTORY_SIZE));
		Label blueKeys = new Label("Blue keys: ".concat(Integer.toString(player.getBlueKeys())));
		blueKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT/INVENTORY_SIZE));
		Label greenKeys = new Label("Green keys: ".concat(Integer.toString(player.getGreenKeys())));
		greenKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT/INVENTORY_SIZE));
		Label yellowKeys = new Label("Yellow Keys: ".concat(Integer.toString(player.getYellowKeys())));
		yellowKeys.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT/INVENTORY_SIZE));
		Label fireBoots = new Label("Fire Boots: ".concat(String.valueOf(player.isFireBoots())));
		fireBoots.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT/INVENTORY_SIZE));
		Label flippers = new Label("Flippers: ".concat(String.valueOf(player.isFlippers())));
		flippers.setMinSize((WINDOW_WIDTH - CANVAS_WIDTH),(WINDOW_HEIGHT/INVENTORY_SIZE));
		inventory.getChildren().addAll(tokens,redKeys,blueKeys,greenKeys,yellowKeys,fireBoots,flippers);
	}
	
	// when a key is pressed in game
	/** Event when a key is pressed*/
	public void processKeyEvent(KeyEvent event,Stage stage) {
		message.getChildren().clear();
		switch (event.getCode()) {
		    case DOWN:
	        	player.moveDown(level);
	        	if (level.getTiles()[player.getLocationX()][player.getLocationY() + 1] instanceof Door) {
	    			Door door = (Door) level.getTiles()[player.getLocationX()][player.getLocationY() + 1];
	    			if (door.isLocked()) {
	    				Label messageText = new Label("The door is locked");
	    				messageText.setMinSize(GRID_CELL_WIDTH*3, GRID_CELL_HEIGHT);
	    	    		message.getChildren().add(messageText);
	    				if (door instanceof tokenDoor) {
	    					tokenDoor door2 = (tokenDoor) door;
	    					Label messageText2 = new Label("Requires ".concat(Integer.toString(door2.getAmount()).concat(" Token(s)")));
	    					messageText2.setMinSize(CANVAS_WIDTH/2, 100);
	    		    		message.getChildren().add(messageText2);
	    				}
	    			}
	    		}
	        	break;	
		    case UP:
		    	player.moveUp(level);
	        	if (level.getTiles()[player.getLocationX()][player.getLocationY() - 1] instanceof Door) {
	    			Door door = (Door) level.getTiles()[player.getLocationX()][player.getLocationY() - 1];
	    			if (door.isLocked()) {
	    				Label messageText = new Label("The door is locked");
	    				messageText.setMinSize(GRID_CELL_WIDTH*3, GRID_CELL_HEIGHT);
	    	    		message.getChildren().add(messageText);
	    				if (door instanceof tokenDoor) {
	    					tokenDoor door2 = (tokenDoor) door;
	    					Label messageText2 = new Label("Requires ".concat(Integer.toString(door2.getAmount()).concat(" Token(s)")));
	    					messageText2.setMinSize(CANVAS_WIDTH/2, 100);
	    		    		message.getChildren().add(messageText2);
	    				}
	    			}
	    		}
		    	break;
		    case RIGHT:
		    	player.moveRight(level);
	        	if (level.getTiles()[player.getLocationX() + 1][player.getLocationY()] instanceof Door) {
	    			Door door = (Door) level.getTiles()[player.getLocationX() + 1][player.getLocationY()];
	    			if (door.isLocked()) {
	    				Label messageText = new Label("The door is locked");
	    				messageText.setMinSize(GRID_CELL_WIDTH*3, GRID_CELL_HEIGHT);
	    	    		message.getChildren().add(messageText);
	    				if (door instanceof tokenDoor) {
	    					tokenDoor door2 = (tokenDoor) door;
	    					Label messageText2 = new Label("Requires ".concat(Integer.toString(door2.getAmount()).concat(" Token(s)")));
	    					messageText2.setMinSize(CANVAS_WIDTH/2, 100);
	    		    		message.getChildren().add(messageText2);
	    				}
	    			}
	    		}
		    	break;
		    case LEFT:
		    	player.moveLeft(level);
		    	if (level.getTiles()[player.getLocationX() - 1][player.getLocationY()] instanceof Door) {
	    			Door door = (Door) level.getTiles()[player.getLocationX() - 1][player.getLocationY()];
	    			if (door.isLocked()) {
	    				Label messageText = new Label("The door is locked");
	    				messageText.setMinSize(GRID_CELL_WIDTH*3, GRID_CELL_HEIGHT);
	    	    		message.getChildren().add(messageText);
	    				if (door instanceof tokenDoor) {
	    					tokenDoor door2 = (tokenDoor) door;
	    					Label messageText2 = new Label("Requires ".concat(Integer.toString(door2.getAmount()).concat(" Token(s)")));
	    					messageText2.setMinSize(CANVAS_WIDTH/2, 100);
	    		    		message.getChildren().add(messageText2);
	    				}
	    			}
	    		}
		    	break;
	        default:
	        	break;
		} if (level.getTiles()[player.getLocationX()][player.getLocationY()] instanceof helpTile) {
			helpTile tile = (helpTile) level.getTiles()[player.getLocationX()][player.getLocationY()];
			Label helpMessage = new Label(tile.getHelpMessage());
			helpMessage.setMinSize(CANVAS_WIDTH/2, GRID_CELL_HEIGHT*3);
			helpMessage.setWrapText(true);
			message.getChildren().add(helpMessage);
		}
		if (player.isDead()) {
    		Label messageText = new Label("You died!");
    		messageText.setMinSize(GRID_CELL_WIDTH*2, GRID_CELL_HEIGHT);
    		message.getChildren().add(messageText);
    		restartGame();
    	} if (player.isWon()) {
    		endTime = System.currentTimeMillis() - startTime;
    		int finalTime = (int) Math.round(endTime/1000F) + timeDelay;
    		int levelNumber = Integer.parseInt((level.getFileName().substring(5)).substring(0,(level.getFileName().substring(5).length()-4)));
    		if (currentUser.getMaxLevel() == levelNumber && currentUser.getMaxLevel() < FINAL_LEVEL_NUMBER) {
    			currentUser.setMaxLevel(currentUser.getMaxLevel() + 1);
    		} 
    		if (currentUser.getLevelTimes().size() > levelNumber-1) {
    			if (currentUser.getLevelTimes().get(levelNumber-1) > finalTime) {
    				currentUser.getLevelTimes().set(levelNumber-1, finalTime);
    			}
    		} else {
    			currentUser.getLevelTimes().add(finalTime);
    		}
    		int savedIndex = users.indexOf(currentUser);
    		updateProfiles();
    		loadProfiles();
    		currentUser = users.get(savedIndex);
    		userMenu(stage);
    	} 
		draw();
		event.consume();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
