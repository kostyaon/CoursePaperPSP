package Client;

import Models.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client  {
    private Socket socket;
    private ObjectInputStream inObj;
    private ObjectOutputStream outObj;
    private User user;
    private PrivateData privateData;
    private float sumRate;

    private static Client client = null;

    private Client() {
        try{
            this.socket = new Socket("localhost", 3333);
            this.outObj = new ObjectOutputStream(socket.getOutputStream());
            this.inObj = new ObjectInputStream(socket.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Client getInstance(){
        if (client == null){
            client = new Client();
        }
        return client;
    }

    public void setSumRate(float sumRate) {
        this.sumRate = sumRate;
    }

    public float getSumRate() {
        return sumRate;
    }

    public PrivateData getPrivateData() {
        return privateData;
    }

    public void setPrivateData(PrivateData privateData) {
        this.privateData = privateData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float countSumRate(String nickname){
        float rating = 0;
        try{
            //Send a nickname
            outObj.writeObject(nickname);
            outObj.flush();

            //Get a summary rating
            rating = (float) inObj.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rating;
    }

    public String auth (String nickname, String password) {
        String received = null;
        try {
            //Choose a Login functionality on Server
            outObj.writeObject("Login");
            outObj.flush();

            //Send Nickname to find the password
            outObj.writeObject(nickname);
            outObj.flush();

            outObj.writeObject(password);
            outObj.flush();

            received = (String) inObj.readObject();
            System.out.println("SERVER >> ACCESS:" + received);

        }catch (Exception e){
            e.printStackTrace();
        }
        return received;
    }

    public String signUp(User user, String password){
        String received = null;
        try{
            //Choose a SignUp functionality on Server
            outObj.writeObject("Signup");
            outObj.flush();

            //Send User for INSERT INTO
            outObj.writeObject(user);
            outObj.flush();

            //Insert password in PrivateData
            outObj.writeObject(password);
            outObj.flush();

            received = (String) inObj.readObject();//Successfully or not
            System.out.println("SERVER >> ADDED:" + received);
        }catch (Exception e){
            e.printStackTrace();
        }
        return received;
    }

    public List<Answer> getAnswer(List<Question> questionList, int index){
        List<Answer> answerList = null;
        try{
            //Choose functionality
            outObj.writeObject("Answer");
            outObj.flush();

            //Send our questList
            outObj.writeObject(questionList);
            outObj.flush();

            //Send index question
            outObj.writeObject(index);
            outObj.flush();

            //Receive answerList for one question
            if (inObj == null){
                inObj = new ObjectInputStream(socket.getInputStream());
            }
            answerList = (List<Answer>) inObj.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return answerList;
    }

    public List<Question> getQuestList(String theme, int level, int numberOfQuest){
        List<Question> questions = null;
        try{
            //TODO: We can send all this as one object
            //Choose functionality
            outObj.writeObject("StartTest");
            outObj.flush();

            //Send topic, level, number of quest
            outObj.writeObject(theme);
            outObj.flush();

            outObj.writeObject(level);
            outObj.flush();

            outObj.writeObject(numberOfQuest);
            outObj.flush();

            //Get a list of question
            questions =  (List<Question>) inObj.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return questions;
    }

    public String insertRating(Rating rate){
        String success = null;
        try{
            //Choose functionality
            outObj.writeObject("Insert rate");
            outObj.flush();

            outObj.writeObject(rate);
            outObj.flush();

            success = (String) inObj.readObject();

        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public PrivateData getPrivateDataDB(int userId){
        PrivateData data = null;
        try{
            outObj.writeObject(userId);
            outObj.flush();

            //get data
            data = (PrivateData) inObj.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    public List<Rating> getRatingDB(int userID){
        List<Rating> ratingList = new ArrayList<>();
        try{
            outObj.writeObject("Rating");
            outObj.flush();

            outObj.writeObject(userID);
            outObj.flush();

            ratingList = (List<Rating>) inObj.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ratingList;
    }

    public User getUserDB(String nickname){
        User user = null;
        try{
            outObj.writeObject(nickname);
            outObj.flush();

            //Get User
            user = (User) inObj.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getListUserDB(){
        List<User> userList = null;
        try{
            outObj.writeObject("User list");
            outObj.flush();

            userList = (List<User>) inObj.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }

    public void closeConnection(Button BClose){
        try {
            Stage stage = (Stage) BClose.getScene().getWindow();
            stage.close();

            outObj.writeObject("Exit");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
