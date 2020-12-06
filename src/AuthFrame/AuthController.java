package AuthFrame;

import AlertFrame.AlertFrameView;
import Client.Client;
import MainFrame.MainFrameView;
import Models.PrivateData;
import Models.User;
import SignUpFrame.SignUpView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class AuthController {

    @FXML
    private TextField TNickname;

    @FXML
    private TextField TPassword;

    @FXML
    private Label BSignUp;

    @FXML
    private Button BLogIn;

    @FXML
    private Button BClose;

    @FXML
    void closeWindow(ActionEvent event) {
        //Close the connection
        Client.getInstance().closeConnection(BClose);
    }

    @FXML
    void signUpFrame(MouseEvent event) {
        try{
            Stage signStage = new Stage();
            SignUpView signUpView = new SignUpView();
            signUpView.start(signStage);
        }catch (Exception e){
            System.out.println("Cannot open SignUp form! Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void logInFrame(ActionEvent event) throws Exception {
        String errMSG;
       //Send to the server TNickname and found the ID
        try {
            Client client = Client.getInstance();
            String access = client.auth(TNickname.getText(), TPassword.getText());
            if (access.equalsIgnoreCase("Access")){
                client.setUser(client.getUserDB(TNickname.getText()));

                //Count Summary Rating
                float sumRating = client.countSumRate(TNickname.getText());
                if (Float.isNaN(sumRating)){
                    sumRating = 0f;
                }
                client.setSumRate(sumRating);

                System.out.println("CLIENT >> SUM RATE = " + sumRating);

                //Add rating to parameters
                Stage loginStage = new Stage();
                MainFrameView mainFrameView = new MainFrameView();
                mainFrameView.start(loginStage);

               //Close AuthView
                Stage stage = (Stage) BLogIn.getScene().getWindow();
                stage.close();
            }
            else{
                errMSG = "Invalid nickname or password";
                throw new Exception(errMSG);
            }
        }catch (Exception e){
            System.out.println("Exception: " + e.getLocalizedMessage());

            //Send errMSG to the errorWindow
            Stage loginStage = new Stage();
            AlertFrameView frameView = new AlertFrameView();
            frameView.start(loginStage);

            e.printStackTrace();
        }
    }
}
