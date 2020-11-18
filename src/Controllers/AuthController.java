package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

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
            Parent root = FXMLLoader.load(getClass().getResource("../Fxml/SignUpFrame.fxml"));
            Stage signStage = new Stage();
            signStage.setTitle("Hello World");
            signStage.setScene(new Scene(root, 325, 452));
            //signStage.initStyle(StageStyle.TRANSPARENT);
            signStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
