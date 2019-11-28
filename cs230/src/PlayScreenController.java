import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class PlayScreenController {

    @FXML
    private AnchorPane AnchorPane;
    
    @FXML
    private BorderPane BorderPane;

    @FXML
    private Button restartButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button quitButton;

    @FXML
    
    
    void handleQuitAction(ActionEvent event) {
    	Stage stage = (Stage) quitButton.getScene().getWindow();
    	stage.close();
		Game.mainmenu(stage);
    }

    @FXML
    void handleRestartAction(ActionEvent event) {
    	Game.restartGame();
    }

    @FXML
    void handleSaveAction(ActionEvent event) {
    	Game.saveGame();
    	Stage stage = (Stage) saveButton.getScene().getWindow();
		stage.close();
		Game.mainmenu(stage);
    }

}
