package Client;

import Models.User;

import java.io.*;
import java.net.Socket;

public class Client  {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectInputStream inObj;
    private ObjectOutputStream outObj;

    public Client(int i) {
        try{
            this.socket = new Socket("localhost", 3333);
            if (i == 0){
                this.in = new DataInputStream(socket.getInputStream());
                this.out = new DataOutputStream(socket.getOutputStream());
            }else{
                this.outObj = new ObjectOutputStream(socket.getOutputStream());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public String auth (String nickname, String password) {
        String received = null;
        try {
            //Send Nickname to find the password
            out.writeUTF(nickname);

            out.flush();

            out.writeUTF(password);
            received = in.readUTF();
            System.out.println("SERVER >> ACCESS:" + received);

        }catch (Exception e){
            e.printStackTrace();
        }
        return received;
    }

    public String signUp(User user, String password){
        String received = null;
        try{
            outObj.writeObject(user); //Send User for INSERT INTO

            outObj.flush();

            outObj.writeObject(password); //Insert password in PrivateData

            inObj = new ObjectInputStream(socket.getInputStream());
            received = (String) inObj.readObject();//Successfully or not
            System.out.println("SERVER >> ADDED:" + received);
        }catch (Exception e){
            e.printStackTrace();
        }
        return received;
    }

}
