package SignUpFrame;

import AlertFrame.AlertFrameView;
import Client.Client;
import Models.PrivateData;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField TNickname;

    @FXML
    private TextField TSpecialization;

    @FXML
    private TextField TCountry;

    @FXML
    private TextField TPassword;

    @FXML
    private Button BSignUp;

    @FXML
    private Button BClose;

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) BClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void signUp(ActionEvent event) throws Exception {
        String errMSG;
        try {
            //Throw an exceptions, if some fields is empty
            if (TNickname.getText().equals("")) {
                errMSG = "Nickname field is empty!";
                throw new Exception(errMSG);
            }
            if (TPassword.getText().equals("")) {
                errMSG = "Password is empty!";
                throw new Exception(errMSG);
            }
            if (TSpecialization.getText().equals("")) {
                errMSG = "Specialization is empty!";
                throw new Exception(errMSG);
            }

            Client client = Client.getInstance();
            User user = new User(TNickname.getText(), TSpecialization.getText(), TCountry.getText());
            String success = client.signUp(user, TPassword.getText());
            if (success.equalsIgnoreCase("Success")) {
                //Close SignUpView
                Stage stage = (Stage) BSignUp.getScene().getWindow();
                stage.close();
            }
        }catch (Exception e){
            errMSG = "Error: You are not unique! Come up with a new nickname!";

            //Send errMSG to the errorWindow
            Stage loginStage = new Stage();
            AlertFrameView frameView = new AlertFrameView();
            frameView.start(loginStage);

            e.printStackTrace();
        }
    }

}
