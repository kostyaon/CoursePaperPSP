package AdminFrame.SelectFrame;

import AlertFrame.AlertFrameView;
import Client.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SelectFrameController {
    @FXML
    private Button BMakeAdmin;

    @FXML
    private Button BClose;

    @FXML
    private Button BDelete;

    @FXML
    private TextField TUserID;

    @FXML
    void setAdmin() throws Exception {
        String errMSG = "Something goes wrong!";
        try{
            String msg = Client.getInstance().setAdmin(Integer.parseInt(TUserID.getText()));
            if (msg.equalsIgnoreCase("Success")){
                closeWindow();
            }else{
                errMSG = "There is no user with this ID!";
                throw new Exception(errMSG);
            }
        }catch (Exception e){
            AlertFrameView.errMSG = errMSG;
            Stage loginStage = new Stage();
            AlertFrameView frameView = new AlertFrameView();
            frameView.start(loginStage);
        }
        
    }

    @FXML
    void closeWindow() {
        Stage stage = (Stage) BClose.getScene().getWindow();
        stage.close();
    }

}
