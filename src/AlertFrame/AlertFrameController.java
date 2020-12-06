package AlertFrame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AlertFrameController {

    @FXML
    private Button BUnderstand;

    @FXML
    private Label TErrorMsg;

    @FXML
    private void initialize(){
        TErrorMsg.setText(AlertFrameView.errMSG);
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) BUnderstand.getScene().getWindow();
        stage.close();
    }

}
