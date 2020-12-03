package SignUpFrame;

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
    void signUp(ActionEvent event) throws IOException {

        try {
            //Throw an exceptions, if some fields is empty
            if (TNickname.getText().equals("")) {
                throw new Exception("Nickname field is empty!");
            }
            if (TPassword.getText().equals("")) {
                throw new Exception("Password is empty!");
            }
            if (TSpecialization.getText().equals("")) {
                throw new Exception("Specialization is empty!");
            }

            Client client = new Client(1);
            User user = new User(TNickname.getText(), TSpecialization.getText(), TCountry.getText());
            String success = client.signUp(user, TPassword.getText());
            if (success.equalsIgnoreCase("Success")) {
                //Close SignUpView
                Stage stage = (Stage) BSignUp.getScene().getWindow();
                stage.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

           /* //Add data to User model
            User user = new User(TNickname.getText(),TSpecialization.getText(), TCountry.getText());
            PrivateData signData = new PrivateData(TPassword.getText());


            //Check the data
            System.out.println(user.toString());

            //TODO:Send data to the server
            //......
            //......
        }catch(Exception e){
            System.out.println("Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }*/
    }

}
