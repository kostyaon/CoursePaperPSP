package Client;

import Models.User;

import java.io.*;
import java.net.Socket;

public class Client  {
    private Socket socket;
    private ObjectInputStream inObj;
    private ObjectOutputStream outObj;

    public Client() {
        try{
            this.socket = new Socket("localhost", 3333);
            this.outObj = new ObjectOutputStream(socket.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
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

}
