package GameLoop;

import Content.Card;
import Player.Player;

import java.util.ArrayList;

public class PassPhase implements Phase {

    private final ArrayList<Player> players;

    public PassPhase(ArrayList<Player> players){
        this.players = players;
    }

    @Override
    public void run() {

        //saves a copy of the last players cards
        ArrayList<Card> passHand = new ArrayList<>(players.getLast().getHand());

        for (Player player : players){
            //Requirement 6 last -> first
            ArrayList<Card> hand = new ArrayList<>(player.getHand());
            player.setHand(new ArrayList<>(passHand));
            passHand = hand;
        }
    }

    @Override
    public Phase nextPhase() {
        return new DraftPhase(this.players);
    }
}
