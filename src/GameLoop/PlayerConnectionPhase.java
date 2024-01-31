package GameLoop;

import Comunication.IOPlayer;
import Player.Bot;
import Player.Human;
import Player.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class PlayerConnectionPhase implements Phase {

    private final ArrayList<Player> players = new ArrayList<>();

    private final int numberPlayers;
    private final int numberOfBots;
    public PlayerConnectionPhase(int numberPlayers, int numberOfBots){

        this.numberPlayers = numberPlayers;
        this.numberOfBots = numberOfBots;

    }

    @Override
    public Phase nextPhase() {

        return new CardDrawPhase(this.players);
    }

    @Override
    public void run() {

        for(int i=0; i<numberOfBots; i++) {
            this.players.add(new Bot(i)); //add a bot
            System.out.println("Added bot ID: " + i);
        }

        if(numberPlayers == 0){ return; }
        try {
            //Open for connections if there are online players
            ServerSocket aSocket = null;
            aSocket = new ServerSocket(2048);

            for(int i=numberOfBots; i<numberPlayers+numberOfBots; i++) {

                System.out.println("Waiting for client");
                Socket connectionSocket = aSocket.accept();
                ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
                ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());

                this.players.add(new Human(i, new IOPlayer(inFromClient, outToClient))); //add an online client

                System.out.println("Connected to player " + i);

                outToClient.writeObject("You connected to the server as player " + i + "\n");
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
