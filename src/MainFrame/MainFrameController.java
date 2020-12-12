package MainFrame;

import AdminFrame.AdminFrameView;
import AlertFrame.AlertFrameView;
import Client.Client;
import Models.Answer;
import Models.Question;
import Models.Rating;
import Models.User;
import RatingFrame.RatingFrameView;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
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
    private Button BRating;

    @FXML
    private ProgressBar ProgressBar;

    @FXML
    private RadioButton RBAnswer1;

    @FXML
    private RadioButton RBAnswer2;

    @FXML
    private RadioButton RBAnswer3;

    @FXML
    private Button BClose;

    @FXML
    private Button AdminClick;

    @FXML
    void openRatingFrame(ActionEvent event) throws Exception {
        String errMSG = null;
        try {
            Stage ratingStage = new Stage();
            RatingFrameView ratingFrameView = new RatingFrameView();
            ratingFrameView.start(ratingStage);
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
    void openAdminWindow(ActionEvent event) throws Exception {
        String errMSG = null;
        try {
            Stage adminStage = new Stage();
            AdminFrameView adminFrameView = new AdminFrameView();
            adminFrameView.start(adminStage);
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
        //Close the connection
        Client.getInstance().closeConnection(BClose);
    }

    @FXML
    private void initialize(){
        DecimalFormat df = new DecimalFormat("0.00");

        TNickname.setText(Client.getInstance().getUser().getNickname());
        TRating.setText("Rating: " + df.format(Client.getInstance().getSumRate()) + "%");

        rating = new Rating();

        String[] course = {"Java", "C#", "C++", "Python"};
        String[] level = {"0", "1", "2"};
        String[] number = {"3", "5", "8"};

        MCourseName.setItems(FXCollections.observableArrayList(course));

        MLevel.setItems(FXCollections.observableArrayList(level));

        MQuestNumber.setItems(FXCollections.observableArrayList(number));

        BFinish.setVisible(false);
        BNext.setDisable(true);
    }

    @FXML
    void finishTest(ActionEvent event) {
        DecimalFormat df = new DecimalFormat("0.00");
        //We have our final rating
        countRating();

        rating.setRating(testRating);
        rating.setUserID(Client.getInstance().getUser().getUserID());

        //Insert rating in DB
        String success = Client.getInstance().insertRating(rating);


        //Update final rating
        float rate = (Client.getInstance().getSumRate() + testRating)/2;
        Client.getInstance().setSumRate(rate);

        //Block finish and upgrade view
        BFinish.setDisable(true);
        BStart.setDisable(false);
        TRating.setText("Rating: " + df.format(Client.getInstance().getSumRate()) + "%");
    }


    @FXML
    void nextQuestion(ActionEvent event) {
        //TODO: Add a true answer to the rating; add a rating for one test
        countRating();
        index++;

        answerList = Client.getInstance().getAnswer(questionList, index);
        viewQuestAndAnswer(TQuest, RBAnswer1, RBAnswer2, RBAnswer3, index);

        if (questionList.get(index) == questionList.get(questionList.size()-1)){
            BNext.setVisible(false);
            BFinish.setVisible(true);
            BFinish.setDisable(false);
        }

    }

    @FXML
    void startTest(ActionEvent event) throws Exception {
        //Reset our rating for the test
        index = 0;
        testRating = 0;
        String errMSG;
        try {
            //Get questionList
            String theme = (String) MCourseName.getValue();
            int level = Integer.parseInt((String) MLevel.getValue());
            int numberQuest = Integer.parseInt((String) MQuestNumber.getValue());
            questionList = Client.getInstance().getQuestList(theme, level, numberQuest);

            if (theme == null){
                errMSG = "Hey! What about theme?";
                throw new Exception(errMSG);
            }

            ratePerQuest = 100f / numberQuest;

            rating.setTestTheme(theme);
            rating.setTestLevel(level);

            //Get answers for our first question and view it
            answerList = Client.getInstance().getAnswer(questionList, index);

            BFinish.setVisible(false);
            BNext.setVisible(true);
            BNext.setDisable(false);
            BStart.setDisable(true);

            //Viewing our question and answer
            viewQuestAndAnswer(TQuest, RBAnswer1, RBAnswer2, RBAnswer3, index);
        }catch (Exception e){
            errMSG = "We haven't got this type of test!";
            e.printStackTrace();

            //Send errMSG to the errorWindow
            AlertFrameView.errMSG = errMSG;
            Stage loginStage = new Stage();
            AlertFrameView frameView = new AlertFrameView();
            frameView.start(loginStage);
        }
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