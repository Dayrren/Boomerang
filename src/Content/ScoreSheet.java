package Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public interface ScoreSheet {
    /**
     * @return The number of completed rounds.
     */
    int CompletedRounds();

    /**
     * Adds score to the rounds score
     * @param Source of the score to add
     * @param score integer value of the score
     */
    void addToRoundScore(ScoreSource Source, int score);

    /**
     * Adds the current rounds score to list off all the rounds and their respective score
     */
    void addToRoundsScoresList();

    /**
     * Adds the current rounds visited sites to a List to allow for
     * the visited sites bonus to be applied att the end of the game
     */
    void addToVisitedSites(ArrayList<Card> cards);

    /**
     *
     * @return Set containing all the unique sites visited
     */
    HashSet<Card> getVisitedSites();

    /**
     * @return the calculated final score
     */
    int getFinalScore();

    /**
     *
     * @param category the category of score to calculate for
     * @return amount of points that category has contributed.
     */
    int getScoreTotalCategory(ScoreSource category);

    /**
     *
     * @param category the category of to get
     * @param roundIndex the round index to get that score from
     * @return The score of the category in that round.
     */
    int getScoreCategory(ScoreSource category, int roundIndex);

    /**
     * @return the Rounds Score list each arraylist index represent a round,
     * with the hashMap containing the score sources and their respective point contributions for that round
     */
    ArrayList<HashMap<ScoreSource, Integer>> getRScore();
}
