package AdminFrame.SelectFrame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SelectFrameController {
    @FXML
    private Button BClose;

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) BClose.getScene().getWindow();
        stage.close();
    }

}
