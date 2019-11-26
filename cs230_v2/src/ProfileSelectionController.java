import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProfileSelectionController {

	@FXML
    private Label currentLevel;

    @FXML
    private Label UserName;

    @FXML
    private Label dailyMessage;

    @FXML
    private Button prevProfileButton;

    @FXML
    private Label currentScore;

    @FXML
    private Button nextProfileButton;

    @FXML
    private Button editProfileButton;

    @FXML
    private Button deleteProfileButton;

    @FXML
    private Button playButton;
    
    @FXML
    private Button newProfileButton;
    
    private int currentProfile = 0;
    private Boolean createProfile = false;
    
    @FXML
	 public void initialize() {
    	if (Game.users.isEmpty() || createProfile) {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlScenes\\CreateProfile.fxml"));     

			// Run the loader
			AnchorPane editRoot;
			try {
				editRoot = (AnchorPane)fxmlLoader.load();
			
			// Create a scene based on the loaded FXML scene graph
			Scene editScene = new Scene(editRoot);
		    
			// Create a new stage (i.e., window) based on the edit scene
			Stage editStage = new Stage();
			editStage.setScene(editScene);
			editStage.setTitle("Create Profile");
			
			// Make the stage a modal window.
			// This means that it must be closed before you can interact with any other window from this application.
			editStage.initModality(Modality.APPLICATION_MODAL);
			
			// Show the edit scene and wait for it to be closed
			editStage.showAndWait();
//    		Stage stage = new Stage();
//    		Game.createNewProfile(stage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
			createProfile = false;
    	}
    	if (currentProfile == Game.users.size() - 1) {
    		nextProfileButton.setOpacity(0.5);
    		nextProfileButton.setDisable(true);
    	} else {
    		nextProfileButton.setOpacity(1);
    		nextProfileButton.setDisable(false);
    	}
    	if (currentProfile == 0) {
    		prevProfileButton.setOpacity(0.5);
    		prevProfileButton.setDisable(true);
    	} else {
    		prevProfileButton.setOpacity(1);
    		prevProfileButton.setDisable(false);
    	}
    	dailyMessage.setText(Game.loadDailyMessage());
    	UserName.setText(Game.users.get(currentProfile).getUserName());
    	currentScore.setText("CURRENT SCORE: " + Integer.toString(Game.users.get(currentProfile).getCurrentScore()));
    	currentLevel.setText(Game.users.get(currentProfile).getInfo());    	
	}
    
    public void createFirstProfile() {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlScenes\\CreateProfile.fxml"));     

			// Run the loader
			AnchorPane editRoot;
			try {
				editRoot = (AnchorPane)fxmlLoader.load();
			
			// Create a scene based on the loaded FXML scene graph
			Scene editScene = new Scene(editRoot);
		    
			// Create a new stage (i.e., window) based on the edit scene
			Stage editStage = new Stage();
			editStage.setScene(editScene);
			editStage.setTitle("To delete the last profile, you must create a new one");
			
			// Make the stage a modal window.
			// This means that it must be closed before you can interact with any other window from this application.
			editStage.initModality(Modality.APPLICATION_MODAL);
			
			// Show the edit scene and wait for it to be closed
			editStage.showAndWait();
//    		Stage stage = new Stage();
//    		Game.createNewProfile(stage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
			
    	dailyMessage.setText(Game.loadDailyMessage());
    	UserName.setText(Game.users.get(currentProfile).getUserName());
    	currentScore.setText("CURRENT SCORE: " + Integer.toString(Game.users.get(currentProfile).getCurrentScore()));
    	currentLevel.setText(Game.users.get(currentProfile).getInfo());    	
	}
		 
    @FXML
    void handleDeleteProfileAction(ActionEvent event) {	
    	if (Game.users.size() == 1) {
        	createFirstProfile();
    	} else {
    		Game.users.remove(currentProfile);
    		Game.updateProfiles();
    		Game.loadProfiles();
    		currentProfile = 0;
    		initialize();
    	}
    }

    @FXML
    void handleEditProfileAction(ActionEvent event) {
    	int sizeChanged = Game.users.size();
    	createProfile = true;
    	initialize();
    	if ((sizeChanged + 1) == Game.users.size()) {
    		Game.users.remove(currentProfile);
    		Game.updateProfiles();
    		Game.loadProfiles();
    		currentProfile = 0;
    		initialize();
    	}
    }

    @FXML
    void handleNewProfileAction(ActionEvent event) {
    	createProfile = true;
    	initialize();
    }

    @FXML
    void handleNextProfileAction(ActionEvent event) {
    	currentProfile = currentProfile + 1;
    	initialize();
    }

    @FXML
    void handlePrevProfileAction(ActionEvent event) {
    		currentProfile = currentProfile - 1;
    		initialize();
    }
    
    @FXML
    void handlePlayAction(ActionEvent event) {
    	Game.chosenProfile = currentProfile;
    	Stage stage = (Stage) playButton.getScene().getWindow();
    	Game.currentUser = Game.users.get(currentProfile);
    	Game.userMenu(stage);
    }
    
    @FXML
    void handleQuitAction(ActionEvent event) {
    	System.exit(0);
    }
	
    	
   }