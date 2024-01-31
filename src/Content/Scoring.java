package Content;

import Player.Player;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public interface Scoring {

    /**
     * @param players
     *
     * Updates the players ScoreSheet and calculates the different score sources
     */
    void ScoreEndOfRound(ArrayList<Player> players);

    /**
     * Updates the completed Regions
     * Intended to be used after all users have scored for the round
     */
    void updateCompletedRegions();

    /**
     * @param player
     * @param latch
     * @return Returns a lambda function that decreases the latch whenever the Runnable is complete
     */
    Runnable getSelectScoreRunnable(Player player, CountDownLatch latch);

    /**
     * @param players
     * Updates the Score after the last round
     */
    void ScoreEndOfGame(ArrayList<Player> players);

}
