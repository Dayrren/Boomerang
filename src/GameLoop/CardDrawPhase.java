package GameLoop;

import Content.Card;
import Content.ContentManager;
import Player.Player;

import java.util.ArrayList;
import java.util.Collections;

public class CardDrawPhase implements Phase {

    private final ArrayList<Player> players;

    public CardDrawPhase(ArrayList<Player> players){
        this.players = players;
    }


    @Override
    public void run() {

        ArrayList<Card> deck = ContentManager.getContentPack().getDeck();
        Collections.shuffle(deck);  //Requirement 3

        for(Player player : players){

            player.removeCards();

            for(int i = 0; i<7; i++) {
                player.getHand().add(deck.remove(0));
                //Requirement 4
            }
        }
    }

    @Override
    public Phase nextPhase() {
        return new ThrowSelectionPhase(this.players);
    }
}
