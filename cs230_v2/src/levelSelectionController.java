import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class levelSelectionController {

    @FXML
    private Button prevLevelButton;

    @FXML
    private Button nextLevelButton;

    @FXML
    private Label levelNumber;

    @FXML
    private ImageView levelImage;
    
    @FXML
    private Button playButton;
    
    @FXML
    private Button leaderBoardButton;
   
    @FXML
    private Button backButton;
    
    @FXML
    private AnchorPane AnchorPane;
    
    private int levelNum = 1;

    @FXML
	 public void initialize() {
    	levelNumber.setText("LEVEL " + levelNum);
    	levelImage.setImage(new Image(getClass().getResource("levelImages\\Level" + levelNum + ".png").toExternalForm()));
    	if (Game.currentUser.getMaxLevel() == levelNum) {
    		nextLevelButton.setOpacity(0.5);
    		nextLevelButton.setDisable(true);
    	} else {
    		nextLevelButton.setOpacity(1);
    		nextLevelButton.setDisable(false);
    	}
    	if (levelNum == 1) {
    		prevLevelButton.setOpacity(0.5);
    		prevLevelButton.setDisable(true);
    	} else {
    		prevLevelButton.setOpacity(1);
    		prevLevelButton.setDisable(false);
    	}
    }
    
    @FXML
    void handleNextLevelAction(ActionEvent event) {
    	levelNum++;
    	initialize();  	
    }

    @FXML
    void handlePrevLevelAction(ActionEvent event) {
    	levelNum--;
    	initialize();
    }
    
    @FXML
    void handlePlayLevelAction(ActionEvent event) {
    	Game.level = new Level("level"+Integer.toString(levelNum)+".txt");
		Game.player = new Player(Game.level);
		Game.timeDelay = 0;		
		Stage stage = (Stage) playButton.getScene().getWindow();
		Game.game(stage);
    }
    
    @FXML
    void handleBackAction(ActionEvent event) {
    	Stage stage = (Stage) backButton.getScene().getWindow();
    	Game.mainmenu(stage);
    }
    
    @FXML
    void handleLeaderboardAction(ActionEvent event) {
    	Game.chosenLevel = levelNum-1;
    	Stage stage = (Stage) leaderBoardButton.getScene().getWindow();
    	Game.displayHighScores(stage);
    }

}
