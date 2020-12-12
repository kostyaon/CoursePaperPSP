package AdminFrame.CreateTestFrame;

import Client.Client;
import Models.Answer;
import Models.Question;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TestFrameController {

    @FXML
    private TextField TTheme;

    @FXML
    private TextField TQuest;

    @FXML
    private TextField TAnswer;

    @FXML
    private ComboBox MLevel;

    @FXML
    private RadioButton RBTrue;

    @FXML
    private RadioButton RBFalse;

    @FXML
    private Button BAdd;

    @FXML
    private Button BClose;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    void initialize(){
        toggleGroup = new ToggleGroup();
        RBTrue.setToggleGroup(toggleGroup);
        RBFalse.setToggleGroup(toggleGroup);

        String[] level = {"0", "1", "2"};
        MLevel.setItems(FXCollections.observableArrayList(level));
    }

    @FXML
    void addTest(ActionEvent event) {
        RadioButton selectedButton = (RadioButton) toggleGroup.getSelectedToggle();
        boolean correctness;
        if (selectedButton == RBFalse){
            correctness = false;
        }else{
            correctness = true;
        }

        Question quest = new Question(TTheme.getText(), Integer.parseInt((String) MLevel.getValue()),TQuest.getText());
        Answer answer = new Answer(TAnswer.getText(), correctness);

        Client.getInstance().createTest(quest, answer);

        Stage stage = (Stage) BClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void closeWindow(ActionEvent event) {
        Stage stage = (Stage) BClose.getScene().getWindow();
        stage.close();
    }
}
