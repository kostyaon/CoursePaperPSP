package Client;

import Models.Answer;
import Models.Question;
import Models.Rating;
import Models.User;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client  {
    private Socket socket;
    private ObjectInputStream inObj;
    private ObjectOutputStream outObj;

    private static Client client = null;

    private Client() {
        try{
            this.socket = new Socket("localhost", 3333);
            this.outObj = new ObjectOutputStream(socket.getOutputStream());
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

            inObj = new ObjectInputStream(socket.getInputStream());
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

            inObj = new ObjectInputStream(socket.getInputStream());
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

            inObj = new ObjectInputStream(socket.getInputStream());
            success = (String) inObj.readObject();

        }catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public void getUser(){
        try{
            outObj.writeObject("Get User");
            outObj.flush();



        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
