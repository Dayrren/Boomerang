package GameLoop;

import Content.Card;
import Content.ContentManager;
import Player.Player;
import Utlilty.Task;
import Utlilty.ThreadExecute;

import java.io.IOException;
import java.util.ArrayList;

public class DraftPhase implements Phase{

    private final ArrayList<Player> players;

    public DraftPhase(ArrayList<Player> players){
        this.players = players;

    }

    @Override
    public void run() {
        //allow the clients/bots to preform their actions in parallel
        //Requirement 7 Draft

        ThreadExecute.execute(players, Task.Draft);

        //Requirement 7 Show
        for(Player player : players) {
            for (Player otherPlayer : players) {
                if (otherPlayer != player) {
                    try {
                        ArrayList<Card> cards = player.getDraftCards();
                        otherPlayer.inOut().sendMessage(
                                "Player " + player.getID() + "\n Drafted:\n" +
                                        ContentManager.getContentPack().getMessages().printCards(
                                                cards.subList(cards.size() - 1, cards.size())
                                        ) + "\n"
                        );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public Phase nextPhase() {
        //Requirement 8
        if (players.get(0).getHand().size() == 1) {
            return new CatchPhase(this.players);
        }
        return new PassPhase(this.players);
    }


}