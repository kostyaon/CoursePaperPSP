package SignUpFrame;

import Models.PrivateData;
import Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    void signUp(ActionEvent event) {
        try{
            //Throw an exceptions, if some fields is empty
            if (TNickname.getText().equals("")){
                throw new Exception("Nickname field is empty!");
            }
            if (TPassword.getText().equals("")){
                throw new Exception("Password is empty!");
            }
            if (TSpecialization.getText().equals("")){
                throw new Exception("Specialization is empty!");
            }

            //Add data to User model
            User user = new User(TNickname.getText(),TSpecialization.getText(), TCountry.getText());
            PrivateData signData = new PrivateData(TPassword.getText());


            //Check the data
            System.out.println(user.toString());

            //Send data to the server
            //......
            //......
        }catch(Exception e){
            System.out.println("Exception: " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

}
