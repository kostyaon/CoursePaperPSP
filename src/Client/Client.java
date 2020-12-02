package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client  {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() throws IOException{
        try{
            this.socket = new Socket("localhost", 3333);
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
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

}
