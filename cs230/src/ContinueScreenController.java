import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ContinueScreenController {

    @FXML
    private Button continueButton;

    @FXML
    private Button selectLevelButton;

    @FXML
    private Button backButton;
    
    @FXML
    private AnchorPane AnchorPane;

    @FXML
	 public void initialize() {
    	File file = new File((Game.users.get(Game.chosenProfile).getUserName())+"playersave.txt");
		if (!file.exists()) {
			continueButton.setOpacity(0.5);
			continueButton.setDisable(true);
	    } else {
	    	continueButton.setOpacity(1);
	    	continueButton.setDisable(false);
	    }
	}
    
    @FXML
    void handleBackAction(ActionEvent event) {
    	Stage stage = (Stage) backButton.getScene().getWindow();
    	Game.mainmenu(stage);
    }

    @FXML
    void handleContinueAction(ActionEvent event) {   	
    	Stage stage = (Stage) continueButton.getScene().getWindow();
    	Game.loadSave(Game.chosenProfile, stage);
    }

    @FXML
    void handleSelectLevelAction(ActionEvent event) {
    	Stage stage = (Stage) selectLevelButton.getScene().getWindow();
    	Game.selectLevel(stage);
	}
    
}
