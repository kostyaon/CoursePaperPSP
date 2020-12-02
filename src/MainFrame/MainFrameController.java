package MainFrame;

import Models.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainFrameController {
    private Question question;

    private List<Question> getList(){
        //TODO: Receive a list from a server
        //All, that we need -> strings (our questions)
        //Return String list
        return null;
    }

    @FXML
    private Label TQuest;

    @FXML
    private Circle IMIconUser;

    @FXML
    private Label TNickname;

    @FXML
    private Label TRating;

    @FXML
    private SplitMenuButton MCourseName;

    @FXML
    private SplitMenuButton MLevel;

    @FXML
    private SplitMenuButton MQuestNumber;

    @FXML
    private Button BStart;

    @FXML
    private Button BNext;

    @FXML
    private ProgressBar ProgressBar;

    @FXML
    private RadioButton RBAnswer1;

    @FXML
    private RadioButton RBAnswer2;

    @FXML
    private RadioButton RBAnswer3;

    @FXML
    void nextQuestion(ActionEvent event) {
        //Received list
    }

    @FXML
    void startTest(ActionEvent event) {
        //TODO:Question List, that we receive from server

        //Set Label TQuest for the first question


    }

}
