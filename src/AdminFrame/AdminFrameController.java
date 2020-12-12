package AdminFrame;

import AdminFrame.CreateTestFrame.TestFrameView;
import AdminFrame.SelectFrame.SelectFrameView;
import AlertFrame.AlertFrameView;
import Client.Client;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.List;

public class AdminFrameController {
    @FXML
    private TableView table;

    @FXML
    private TableColumn<User, Integer> ColUser;

    @FXML
    private TableColumn<User, String> ColNick;

    @FXML
    private TableColumn<User, String> ColSpec;

    @FXML
    private TableColumn<User, String> ColCountry;

    @FXML
    private Button BTest;

    @FXML
    private Button BSetAdmin;

    @FXML
    private Button BDelete;

    @FXML
    private Button BClose;

    @FXML
    void initialize(){
        ColUser.setCellValueFactory(new PropertyValueFactory<>("userID"));
        ColNick.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        ColSpec.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        ColCountry.setCellValueFactory(new PropertyValueFactory<>("country"));

        //Retrieve List<User> from DB
        List<User> userList = Client.getInstance().getListUserDB();

        final ObservableList<User> data = FXCollections.observableArrayList(userList);

        table.setItems(data);
    }

    @FXML
    void adminSetFrame(ActionEvent event) throws Exception {
        String errMSG;
        try {
            Stage adminStage = new Stage();
            SelectFrameView.whatFrame = true;
            SelectFrameView selectFrameView = new SelectFrameView();
            selectFrameView.start(adminStage);
        }catch (Exception e){
            errMSG = "Something goes wrong!!";
            e.printStackTrace();

            AlertFrameView.errMSG = errMSG;
            Stage errStage = new Stage();
            AlertFrameView frameView = new AlertFrameView();
            frameView.start(errStage);
        }
    }


    @FXML
    void deleteFrame(ActionEvent event) throws Exception {
        String errMSG;
        try {
            Stage deleteStage = new Stage();
            SelectFrameView.whatFrame = false;
            SelectFrameView selectFrameView = new SelectFrameView();
            selectFrameView.start(deleteStage);
        }catch (Exception e){
            errMSG = "Something goes wrong!!";
            e.printStackTrace();

            AlertFrameView.errMSG = errMSG;
            Stage errStage = new Stage();
            AlertFrameView frameView = new AlertFrameView();
            frameView.start(errStage);
        }
    }

    @FXML
    void testFrame(ActionEvent event) throws Exception {
        String errMSG;
        try {
            Stage testStage = new Stage();
            TestFrameView testFrameView = new TestFrameView();
            testFrameView.start(testStage);
        }catch (Exception e){
            errMSG = "You are not admin!";
            e.printStackTrace();

            AlertFrameView.errMSG = errMSG;
            Stage errStage = new Stage();
            AlertFrameView frameView = new AlertFrameView();
            frameView.start(errStage);
        }
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) BClose.getScene().getWindow();
        stage.close();
    }
}
