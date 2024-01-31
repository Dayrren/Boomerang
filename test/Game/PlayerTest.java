package Game;

import Content.Australia.Australia;
import Content.ContentManager;
import Content.ScoreSheet;
import Content.ScoreSource;
import Player.Bot;
import Player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    @Test
    public void testCompareTo() {

        ContentManager.setContentPack(new Australia());

        // Create player instances with specific scores and scoresheets
        Player player1 = new Bot(1);
        ScoreSheet sheet1 = player1.getScoreSheet();
        sheet1.addToRoundScore(ScoreSource.ThrowCatch, 20);
        sheet1.addToRoundScore(ScoreSource.Sites, 80);
        sheet1.addToRoundsScoresList();

        Player player2 = new Bot(2);
        ScoreSheet sheet2 = player2.getScoreSheet();
        sheet2.addToRoundScore(ScoreSource.ThrowCatch, 25);
        sheet2.addToRoundScore(ScoreSource.Sites, 65);
        sheet2.addToRoundsScoresList();

        Player player3 = new Bot(3);
        ScoreSheet sheet3 = player3.getScoreSheet();
        sheet3.addToRoundScore(ScoreSource.ThrowCatch, 22);
        sheet3.addToRoundScore(ScoreSource.Sites, 68);
        sheet3.addToRoundsScoresList();

        Player player4 = new Bot(4);
        ScoreSheet sheet4 = player4.getScoreSheet();
        sheet4.addToRoundScore(ScoreSource.ThrowCatch, 18);
        sheet4.addToRoundScore(ScoreSource.Sites, 67);
        sheet4.addToRoundsScoresList();

        // Test players with different final scores
        assertEquals(-1, player1.compareTo(player2)); // player1 < player2
        assertEquals(1, player2.compareTo(player1)); // player2 > player1

        // Test players with the same final score and different throw catch scores
        assertEquals(-1, player2.compareTo(player3)); // player2 < player3
        assertEquals(1, player3.compareTo(player2)); // player3 > player2

        // Test players with the same final score and the same throw catch score but different IDs
        assertEquals(-1, player3.compareTo(player4)); // player3 < player4
        assertEquals(1, player4.compareTo(player3)); // player4 > player3

        // Test players with the same final score, throw catch score, and IDs (should be considered equal)
        assertEquals(0, player3.compareTo(player3)); // player3 equals player3
    }

}