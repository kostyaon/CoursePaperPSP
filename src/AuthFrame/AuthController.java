package AuthFrame;

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

public class AuthController {
    private User user;
    private PrivateData authData;

    @FXML
    private TextField TNickname;

    @FXML
    private TextField TPassword;

    @FXML
    private Label BSignUp;

    @FXML
    private Button BLogIn;

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
    void logInFrame(ActionEvent event) throws IOException {

       //Send to the server TNickname and found the ID
        try {
            Client client = new Client(); //TODO: MAKE THIS CLIENT TO WORK FOR ONE SESSION
            String access = client.auth(TNickname.getText(), TPassword.getText());
            if (access.equalsIgnoreCase("Access")){
                Stage loginStage = new Stage();
                MainFrameView mainFrameView = new MainFrameView();
                mainFrameView.start(loginStage);
            }
            else{
                throw new Exception("Invalid nickname or password" );
            }
        }catch (Exception e){
            System.out.println("Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

    }
}
