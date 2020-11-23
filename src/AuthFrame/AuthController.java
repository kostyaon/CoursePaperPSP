package AuthFrame;

import MainFrame.MainFrameView;
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
        try{
            Stage loginStage = new Stage();
            MainFrameView mainFrameView = new MainFrameView();
            mainFrameView.start(loginStage);
        }catch (Exception e){
            System.out.println("Cannot open MainFrame form! Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
