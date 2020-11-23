package AuthFrame;

import MainFrame.MainFrameView;
import Modules.PrivateData;
import Modules.User;
import SignUpFrame.SignUpView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    void logInFrame(ActionEvent event) {
        //Fake Data
        user = new User("Channy33", "GameDev", "Belarus");
        authData = new PrivateData("Hello");

        try{
            //Authorization
            if (user.getNickname().equals(TNickname.getText()) && authData.getPassword().equals(TPassword.getText())){
                Stage loginStage = new Stage();
                MainFrameView mainFrameView = new MainFrameView();
                mainFrameView.start(loginStage);
            }
            else{
                throw new Exception("Invalid nickname or password");
            }
        }catch (Exception e){
            System.out.println("Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
