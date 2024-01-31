package Content;

import Player.Player;

import java.io.IOException;
import java.util.List;

public interface Messages {

    /**
     * Sends a formatted message with the Last Rounds Score to the player
     * @param player player to base the message numbers on
     * @throws IOException
     */
    void sendLastRoundsScore(Player player) throws IOException;

    /**
     * Sends a formatted message with the Final Score to the player
     * @param player player to base the message numbers on
     * @throws IOException
     */
    void sendFinalScore(Player player) throws IOException;

    /**
     * Sends a formatted message with the final placing Winner variation
     * @param player player to base the message numbers on
     * @throws IOException
     */
    void sendWinnerMessage(Player player) throws IOException;

    /**
     * Sends a formatted message with the final placing
     * @param player player to base the message numbers on
     * @throws IOException
     */
    void sendEndMessage(Player player, Player winner, int place) throws IOException;

    /**
     * @param cards List of Cards
     * @return Formatted message containing the relevant card information
     */
    String printCards(List<Card> cards);
}
