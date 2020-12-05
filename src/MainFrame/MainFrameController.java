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
    private List<Question> questionList;
    private  List<Answer> answerList;
    private int index;

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
        //TODO: Add a true answer to the rating; add a rating for one test

        index++;
        if (questionList.get(index) != null){
            Client client = new Client();

            answerList = client.getAnswer(questionList, index);
            viewQuestAndAnswer(TQuest, RBAnswer1, RBAnswer2, RBAnswer3, index);
        }else{
            BNext.setText("Finish");
        }
    }

    @FXML
    void startTest(ActionEvent event) {
        Client client = new Client();
        //TODO: add not 3 but choosen by user id
        //Get questionList
        questionList = client.getQuestList("Java", 0, 5);

        index = 0;

        //Get answers for our first question and view it
        answerList = client.getAnswer(questionList, index);

        //Viewing our question and answer
        viewQuestAndAnswer(TQuest, RBAnswer1, RBAnswer2, RBAnswer3, index);

    }

    void viewQuestAndAnswer(Label quest, RadioButton rb1, RadioButton rb2, RadioButton rb3, int questNumber){
        quest.setText("Question " + ++questNumber + ": " + questionList.get(--questNumber).getQuestion());
        rb1.setText(answerList.get(0).getAnswer());
        rb2.setText(answerList.get(1).getAnswer());
        rb3.setText(answerList.get(2).getAnswer());
    }

    void countRating(){
        //
    }
}
