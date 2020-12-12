package AdminFrame;

import AdminFrame.CreateTestFrame.TestFrameView;
import AdminFrame.SelectFrame.SelectFrameView;
import AlertFrame.AlertFrameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminFrameController {
    @FXML
    private Button BTest;

    @FXML
    private Button BSetAdmin;

    @FXML
    private Button BDelete;

    @FXML
    private Button BClose;

    @FXML
    void adminSetFrame(ActionEvent event) throws Exception {
        String errMSG;
        try {
            Stage adminStage = new Stage();
            SelectFrameView selectFrameView = new SelectFrameView();
            selectFrameView.start(adminStage);
        }catch (Exception e){
            errMSG = "You are not admin!";
            e.printStackTrace();

            AlertFrameView.errMSG = errMSG;
            Stage errStage = new Stage();
            AlertFrameView frameView = new AlertFrameView();
            frameView.start(errStage);
        }
    }


    @FXML
    void deleteFrame(ActionEvent event) throws Exception {
        String errMSG;
        try {
            Stage deleteStage = new Stage();
            SelectFrameView selectFrameView = new SelectFrameView();
            selectFrameView.start(deleteStage);
        }catch (Exception e){
            errMSG = "You are not admin!";
            e.printStackTrace();

            AlertFrameView.errMSG = errMSG;
            Stage errStage = new Stage();
            AlertFrameView frameView = new AlertFrameView();
            frameView.start(errStage);
        }
    }

    @FXML
    void testFrame(ActionEvent event) throws Exception {
        String errMSG;
        try {
            Stage testStage = new Stage();
            TestFrameView testFrameView = new TestFrameView();
            testFrameView.start(testStage);
        }catch (Exception e){
            errMSG = "You are not admin!";
            e.printStackTrace();

            AlertFrameView.errMSG = errMSG;
            Stage errStage = new Stage();
            AlertFrameView frameView = new AlertFrameView();
            frameView.start(errStage);
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) BClose.getScene().getWindow();
        stage.close();
    }
}
