package GameLoop;

import Content.Card;
import Content.ContentManager;
import Player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class CatchPhase implements Phase{

    private final ArrayList<Player> players;

    public CatchPhase(ArrayList<Player> players){
        this.players = players;
    }

    @Override
    public void run() {
        //Requirement 9  first -> last
        Collections.reverse(players); //flip list to allow for the same method

        ArrayList<Card> passHand = new ArrayList<>(players.getLast().getHand());

        for (Player player : players){

            ArrayList<Card> hand = new ArrayList<>(player.getHand());
            player.setHand(new ArrayList<>(passHand));
            passHand = hand;
        }
        Collections.reverse(players); //un-flip list to restore previous indexes

        for(Player player: players) {
            player.addCardToDraft(player.getHand().remove(0));

            //Requirement 8 show other players
            for (Player otherPlayer : players) {
                if (otherPlayer != player) {
                    try {
                        ArrayList<Card> cards = player.getDraftCards();
                        otherPlayer.inOut().sendMessage(
                                "Player " + player.getID() + "\n Drafted:\n" + ContentManager.getContentPack().getMessages().printCards(cards) + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }
    }


    @Override
    public Phase nextPhase() {
        return new RoundScoringPhase(this.players);
    }

}
