package Comunication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOPlayer implements IO  {
    final ObjectInputStream inFromClient;
    final ObjectOutputStream outToClient;

    public IOPlayer(ObjectInputStream inFromClient, ObjectOutputStream outToClient) {
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;
    }

    public void sendMessage(Object message) throws IOException {
        outToClient.writeObject(message);
    }

    public String requestResponse(Object message) throws ClassNotFoundException, IOException {
        this.sendMessage(message);

        String word = "";
        word = (String) inFromClient.readObject();

        return word;
    }
}
