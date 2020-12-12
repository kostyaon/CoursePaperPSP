package AdminFrame.CreateTestFrame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TestFrameController {
    @FXML
    private Button BClose;

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) BClose.getScene().getWindow();
        stage.close();
    }

}
