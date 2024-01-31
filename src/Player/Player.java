package Player;

import Comunication.IO;
import Content.Card;
import Content.ScoreSheet;

import java.util.ArrayList;

/**
 * The player keeps track of the players hand, score and IO connection
 */
public interface Player extends Comparable<Player> {


    /**
     * @return the players IO object
     */
    IO inOut();

    /**
     * @return The players hand
     */
    ArrayList<Card> getHand();

    /**
     * Updates the players hand
     */
    void setHand(ArrayList<Card> Hand);

    /**
     * @return The players id
     */
    int getID();

    /**
     * adds the card to the draft ArrayList
     */
    void addCardToDraft(Card card);

    /**
     * @return The players drafted cards
     */
    ArrayList<Card> getDraftCards();

    /**
     * Removes all the cards from the player
     */
    void removeCards();

    /**
     * Removes all the cards from the player
     */

    /**
     * @return the players ScoreSheet object
     */
    ScoreSheet getScoreSheet();

}

