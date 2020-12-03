package MainFrame;

import Client.Client;
import Models.Answer;
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
        Client client = new Client();
        //TODO: add not 3 but choosen by user id
        //Get questionList
        List<Question> questionList = client.getQuestList("Java", 0, 3);

        //For each question get AnswerList
        //In a loop (getAnswer -> view -> next quest and answer load -> view)
        List<Answer> answerList = client.getAnswer(questionList, 0);
        for(int i=0; i<3; i++){
            System.out.println("CLIENT >> ANSWER " + i + ":" + answerList.get(i).getAnswer());
        }
    }

}
