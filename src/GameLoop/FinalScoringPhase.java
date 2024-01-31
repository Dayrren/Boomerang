package GameLoop;

import Content.ContentManager;
import Player.Player;

import java.io.IOException;
import java.util.ArrayList;

public class FinalScoringPhase implements Phase {

    private final ArrayList<Player> players;

    public FinalScoringPhase(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public void run() {

        ContentManager.getContentPack().getScoringStrategy().ScoreEndOfGame(players);

        //sort based on points/throwCatch(if tie)
        this.players.sort(Comparable::compareTo);


        Player winner = players.getFirst();

        int place = 1;
        System.out.println("***** "+ "Player " + winner.getID() + " won with " + winner.getScoreSheet().getFinalScore()+" *****");
        for (Player player : players) {
            try {
                ContentManager.getContentPack().getMessages().sendFinalScore(player);

                if (player == winner)
                    ContentManager.getContentPack().getMessages().sendWinnerMessage(player);
                else
                    ContentManager.getContentPack().getMessages().sendEndMessage(player,winner,place);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            place++;
        }
    }

    @Override
    public Phase nextPhase() {
        return new EndPhase();
    }
}