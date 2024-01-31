package GameLoop;

import Content.ContentManager;
import Player.Player;
import Utlilty.Task;
import Utlilty.ThreadExecute;

import java.io.IOException;
import java.util.ArrayList;

public class RoundScoringPhase implements Phase {

    private final ArrayList<Player> players;
    public RoundScoringPhase(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public void run() {

        ThreadExecute.execute(players, Task.Chose);

            // Requirement 10 a,b,c,d and saves score to rounds array
            ContentManager.getContentPack().getScoringStrategy().ScoreEndOfRound(players);

            for(Player player:players){
            try {
                ContentManager.getContentPack().getMessages().sendLastRoundsScore(player);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public Phase nextPhase() {


        if (players.getFirst().getScoreSheet().CompletedRounds() == 4){
            System.out.println("4 Rounds completed");
            return new FinalScoringPhase(this.players);
        }
        System.out.println("Round complete");
        //begins a new round of the game
        return new CardDrawPhase(this.players);
    }
}