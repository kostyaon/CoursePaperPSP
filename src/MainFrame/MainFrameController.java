package MainFrame;

import Client.Client;
import Models.Answer;
import Models.Question;
import Models.Rating;
import Models.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;


public class MainFrameController {
    private User user;
    private List<Question> questionList;
    private  List<Answer> answerList;
    private Rating rating;
    private int index;
    private float ratePerQuest;
    private float testRating;

    @FXML
    private ToggleGroup toggleGroup;

    @FXML
    private Label TQuest;

    @FXML
    private Circle IMIconUser;

    @FXML
    private Label TNickname;

    @FXML
    private Label TRating;

    @FXML
    private ComboBox MCourseName;

    @FXML
    private ComboBox MLevel;

    @FXML
    private ComboBox MQuestNumber;

    @FXML
    private Button BStart;

    @FXML
    private Button BNext;

    @FXML
    private Button BFinish;

    @FXML
    private ProgressBar ProgressBar;

    @FXML
    private RadioButton RBAnswer1;

    @FXML
    private RadioButton RBAnswer2;

    @FXML
    private RadioButton RBAnswer3;

    @FXML
    private void initialize(){
        rating = new Rating();

        String[] course = {"Java", "C#", "C++", "Python"};
        String[] level = {"0", "1", "2"};
        String[] number = {"3", "5", "8"};

        MCourseName.setItems(FXCollections.observableArrayList(course));

        MLevel.setItems(FXCollections.observableArrayList(level));

        MQuestNumber.setItems(FXCollections.observableArrayList(number));
    }

    @FXML
    void finishTest(ActionEvent event) {
        //We have our final rating
        countRating();
        rating.setRating(testRating);

        //Insert rating in DB
        Client client = new Client();


        //Update final rating
    }


    @FXML
    void nextQuestion(ActionEvent event) {
        //TODO: Add a true answer to the rating; add a rating for one test
        countRating();
        index++;

        Client client = new Client();

        answerList = client.getAnswer(questionList, index);
        viewQuestAndAnswer(TQuest, RBAnswer1, RBAnswer2, RBAnswer3, index);

        if (questionList.get(index) == questionList.get(questionList.size()-1)){
            BNext.setVisible(false);
            BFinish.setVisible(true);
        }

    }

    @FXML
    void startTest(ActionEvent event) {
        //Reset our rating for the test
        index = 0;
        testRating = 0;
        BFinish.setVisible(false);

        Client client = new Client();

        //Get questionLis
        String theme = (String) MCourseName.getValue();
        int level = Integer.parseInt((String) MLevel.getValue());
        int numberQuest = Integer.parseInt((String) MQuestNumber.getValue());
        questionList = client.getQuestList(theme, level, numberQuest);

        ratePerQuest = 100f/numberQuest;

        rating.setTestTheme(theme);
        rating.setTestLevel(level);

        //Get answers for our first question and view it
        answerList = client.getAnswer(questionList, index);

        //Viewing our question and answer
        viewQuestAndAnswer(TQuest, RBAnswer1, RBAnswer2, RBAnswer3, index);

    }

    void viewQuestAndAnswer(Label quest, RadioButton rb1, RadioButton rb2, RadioButton rb3, int questNumber){
        //Group buttons
        toggleGroup = new ToggleGroup();
        rb1.setToggleGroup(toggleGroup);
        rb2.setToggleGroup(toggleGroup);
        rb3.setToggleGroup(toggleGroup);

        quest.setText("Question " + ++questNumber + ": " + questionList.get(--questNumber).getQuestion());
        rb1.setText(answerList.get(0).getAnswer());
        rb2.setText(answerList.get(1).getAnswer());
        rb3.setText(answerList.get(2).getAnswer());
    }

    void countRating(){
        RadioButton selectedButton = (RadioButton) toggleGroup.getSelectedToggle();
        boolean correctAnswer;

        if (selectedButton == RBAnswer1){
            correctAnswer = answerList.get(0).isCorrect();
        }
        else{
            if (selectedButton == RBAnswer2){
                correctAnswer = answerList.get(1).isCorrect();
            }
            else{
                correctAnswer = answerList.get(2).isCorrect();
            }
        }

        if (correctAnswer){
            testRating += ratePerQuest;
            System.out.println("CLIENT >> YOU ARE RIGHT");
            System.out.println("CKIENT >> TEST RATING = " + testRating);
        }
        else{
            System.out.println("CLIENT >> YOU ARE NOT RIGHT");
        }
    }

}
