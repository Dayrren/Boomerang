package Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int SERVER_PORT = 2048;

    /**
     *
     * @param ipAddress the ip address to connect to
     */

    public static void client(String ipAddress) {

        try {
            //Connect to server

            Socket aSocket = new Socket(ipAddress, SERVER_PORT);
            ObjectOutputStream outToServer = new ObjectOutputStream(aSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(aSocket.getInputStream());
            Scanner in = new Scanner(System.in);

            String nextMessage = "";


            while(!nextMessage.contains("winner")){
                nextMessage = (String) inFromServer.readObject();
                System.out.println(nextMessage);

                if(nextMessage.contains("Select") || nextMessage.contains("keep")) {
                    outToServer.writeObject(in.nextLine());
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
