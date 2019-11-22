package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;

public class Profile extends Application {
	
	private String name;
	private int totalTimePlayed;
	private ArrayList<Integer> savedScores;
	private int currentTime;
	//private Player player;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("profileCreation.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalTimePlayed() {
		return totalTimePlayed;
	}
	public void updateTotalTimePlayed(int totalTimePlayed) {
		//updates after each level (or maybe) after player closes game 
		this.totalTimePlayed = getTotalTimePlayed() + totalTimePlayed;
	}
	public ArrayList<Integer> getSavedScores() {
		return savedScores;
	}
	public void setSavedScores(ArrayList<Integer> savedScores) {
		this.savedScores = savedScores;
	}
	public int getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	//public Player getPlayer() {
	//	return player;
	//}
	//public void setPlayer(Player player) {
	//	this.player = player;
	//}
	
}

