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
    void logInFrame(ActionEvent event) throws IOException {

       //Send to the server TNickname and found the ID
        try {
            Client client = Client.getInstance();
            String access = client.auth(TNickname.getText(), TPassword.getText());
            if (access.equalsIgnoreCase("Access")){
                client.setUser(client.getUserDB(TNickname.getText()));

                //Count Summary Rating
                float sumRating = client.countSumRate(TNickname.getText());
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
                throw new Exception("Invalid nickname or password" );
            }
        }catch (Exception e){
            System.out.println("Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
