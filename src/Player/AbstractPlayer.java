package Player;

import Comunication.IO;
import Content.Card;
import Content.ScoreSheet;
import Content.ScoreSource;

import java.util.ArrayList;

/**
 * Bots and players had stuff shared to warrant an abstract implementation.
 */
public class AbstractPlayer implements Player {

    protected final IO inOut;
    public final int playerID;
    public ArrayList<Card>
            draft = new ArrayList<>(),
            hand = new ArrayList<>();
    public final ScoreSheet scoreSheet;


    public AbstractPlayer(int playerID, IO inOut, ScoreSheet scoreSheet) {
        this.playerID = playerID;
        this.inOut = inOut;
        this.scoreSheet = scoreSheet;
    }

    @Override
    public IO inOut() {
        return this.inOut;
    }

    @Override
    public ArrayList<Card> getHand() {
        return hand;
    }

    @Override
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    @Override
    public int getID() {
        return playerID;
    }

    @Override
    public void addCardToDraft(Card card) {
        this.draft.add(card);
    }

    @Override
    public ArrayList<Card> getDraftCards() {
        return this.draft;
    }

    @Override
    public void removeCards() {
        forgetCards();
    }

    @Override
    public ScoreSheet getScoreSheet() {
        return this.scoreSheet;
    }

    private void forgetCards() {
        this.hand = new ArrayList<>();
        this.draft = new ArrayList<>();
    }

    /**
     * @param player the player to be compared.
     * @return positive or negative int, with the purpose to assist sorting the list based of the final score.
     */
    @Override
    public int compareTo(Player player) {
        int scoreDifference = player.getScoreSheet().getFinalScore() - this.getScoreSheet().getFinalScore();
        if (scoreDifference != 0) {
            return Integer.compare(scoreDifference, 0); // Higher final score comes first
        }

        int throwCatchScoreDifference = player.getScoreSheet().getScoreTotalCategory(ScoreSource.ThrowCatch) - this.getScoreSheet().getScoreTotalCategory(ScoreSource.ThrowCatch);
        if (throwCatchScoreDifference != 0) {
            return Integer.compare(throwCatchScoreDifference, 0); // Higher Throw Catch Score comes first
        }

        // If Throw Catch Scores are equal, compare by player IDs (as a tiebreaker)
        return Integer.compare(this.getID(), player.getID());
    }
}