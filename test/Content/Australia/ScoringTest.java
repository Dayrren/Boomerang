package Content.Australia;

import Content.Card;
import Content.ContentPack;
import Content.ScoreSheet;
import Content.ScoreSource;
import GameLoop.Phase;
import GameLoop.RoundScoringPhase;
import Player.Bot;
import Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoringTest {

    HashMap<String, Player> TestPlayers;
    ContentPack contentPack;

    int expected;
    Phase scoringPhase;

    @BeforeEach
    void setUp() {

        contentPack = new Australia();
        TestPlayers = new HashMap<>() {{
            for (int i = 0; i < 4; i++) {
                put("playerCards 1-7 1",new Bot(1));
                put("playerCards 1-7 2",new Bot(2));
                put("playerCards Collection Score 7",new Bot(3));
                put("playerCards Collection Score 8",new Bot(4));
                put("playerCards Activity x2",new Bot(6));
                put("playerCards Activity x3",new Bot(6));
            }}};

        for (Card card: contentPack.getDeck().subList(0,7)) {
            TestPlayers.get("playerCards 1-7 1").addCardToDraft(card);
            TestPlayers.get("playerCards 1-7 2").addCardToDraft(card);
        }

        Player player = TestPlayers.get("playerCards Collection Score 7");
        player.addCardToDraft(new AustraliaCard("","","",1,"Souvenirs","",""));
        player.addCardToDraft(new AustraliaCard("","","",1,"Wildflowers","","Swimming"));

        player = TestPlayers.get("playerCards Collection Score 8");
        player.addCardToDraft(new AustraliaCard("","","",1,"Souvenirs" ,"",""));
        player.addCardToDraft(new AustraliaCard("","","",1,"Shells","","Swimming"));

        player = TestPlayers.get("playerCards Activity x2");
        player.addCardToDraft(new AustraliaCard("","","",1,"" ,"","Swimming"));
        player.addCardToDraft(new AustraliaCard("","","",1,"","","Swimming"));

        player = TestPlayers.get("playerCards Activity x3");
        player.addCardToDraft(new AustraliaCard("","","",1,"" ,"","Swimming"));
        player.addCardToDraft(new AustraliaCard("","","",1,"","","Swimming"));
        player.addCardToDraft(new AustraliaCard("","","",1,"","","Swimming"));



        scoringPhase = new RoundScoringPhase(new ArrayList<>(TestPlayers.values()));
        scoringPhase.run();

    }

    @Test
    void testScoreThrowCatch() {
        Player player = TestPlayers.get("playerCards 1-7 1");
        ScoreSheet sheet = player.getScoreSheet();

        // requirement 10 a
        expected = Math.abs(((AustraliaCard) player.getDraftCards().getFirst()).number - ((AustraliaCard)player.getDraftCards().getLast()).number);
        assertEquals(expected, sheet.getRScore().get(0).get(ScoreSource.ThrowCatch));

    }

    @Test
    void testFirstToRegion() {

        Player player1 = TestPlayers.get("playerCards 1-7 1");
        ScoreSheet sheet1 = player1.getScoreSheet();
        Player player2 = TestPlayers.get("playerCards 1-7 2");
        ScoreSheet sheet2 = player2.getScoreSheet();

        // requirement 10 b i
        expected = 3;
        assertEquals(expected, sheet1.getRScore().get(0).get(ScoreSource.Region));
        // requirement 10 b ii
        assertEquals(expected, sheet2.getRScore().get(0).get(ScoreSource.Region));

    }

    @Test
    void testScoreCompletedRegion(){

        Player player1 = TestPlayers.get("playerCards 1-7 1");
        ScoreSheet sheet1 = player1.getScoreSheet();
        Player player2 = TestPlayers.get("playerCards 1-7 1");
        ScoreSheet sheet2 = player2.getScoreSheet();

        // requirement 10 b
        //attempt to complete region again
        scoringPhase.run();
        assertFalse(sheet1.getRScore().get(1).containsKey(ScoreSource.Region));
        assertFalse(sheet2.getRScore().get(1).containsKey(ScoreSource.Region));

    }

    @Test
    void testCollections() {
        Player player1 = TestPlayers.get("playerCards 1-7 1");
        ScoreSheet sheet1 = player1.getScoreSheet();

        Player player2 = TestPlayers.get("playerCards Collection Score 7");
        ScoreSheet sheet2 = player2.getScoreSheet();

        // requirement 10 c
        expected = Math.abs(((AustraliaCard)player1.getDraftCards().getFirst()).number- ((AustraliaCard)player1.getDraftCards().getLast()).number);
        assertEquals(expected, sheet1.getRScore().get(0).get(ScoreSource.ThrowCatch));

        expected = Math.abs(((AustraliaCard)player2.getDraftCards().getFirst()).number- ((AustraliaCard)player2.getDraftCards().getLast()).number);
        assertEquals(expected, sheet2.getRScore().get(0).get(ScoreSource.ThrowCatch));

    }


    @Test
    void testAnimalScore(){
        Player player = TestPlayers.get("playerCards 1-7 1");
        ScoreSheet sheet = player.getScoreSheet();

        //requirement 10 d
        expected = 3; //pair of kangaroos
        assertEquals(expected,sheet.getRScore().get(0).get(ScoreSource.Animal));
    }

    @Test
    void testCollectionScore(){
        Player player1 = TestPlayers.get("playerCards Collection Score 8");
        ScoreSheet sheet1 = player1.getScoreSheet();

        Player player2 = TestPlayers.get("playerCards Collection Score 7");
        ScoreSheet sheet2 = player2.getScoreSheet();


        // requirement 10 e ii
        expected = 8; //Souvenirs Shells
        assertEquals(expected,sheet1.getRScore().get(0).get(ScoreSource.Collections));

        // requirement 10 e i
        expected = 7 * 2; //Souvenirs Wildflowers with x2 modifier
        assertEquals(expected,sheet2.getRScore().get(0).get(ScoreSource.Collections));

    }


    @Test
    void testActivityScore(){
        Player player1 = TestPlayers.get("playerCards Activity x2");
        ScoreSheet sheet1 = player1.getScoreSheet();

        Player player2 = TestPlayers.get("playerCards Activity x3");
        ScoreSheet sheet2 = player2.getScoreSheet();

        // x0
        Player player3 = TestPlayers.get("playerCards Collection Score 8");
        ScoreSheet sheet3 = player3.getScoreSheet();


        // requirement 10 d
        expected = 2;
        assertEquals(expected,sheet1.getRScore().get(0).get(ScoreSource.Activity));

        expected = 4;
        assertEquals(expected,sheet2.getRScore().get(0).get(ScoreSource.Activity));

        expected = 0;
        assertEquals(expected,sheet3.getRScore().get(0).get(ScoreSource.Activity));

        // requirement 10 d i
        scoringPhase.run();
        expected = 0; //error
        assertFalse(sheet1.getRScore().get(1).containsKey(ScoreSource.Activity));
    }

    @Test
    void testEndOfGame(){
        Player player = TestPlayers.get("playerCards 1-7 1");
        ScoreSheet sheet = player.getScoreSheet();

        contentPack.getScoringStrategy().ScoreEndOfGame(new ArrayList<>(List.of(player)));

        assertTrue(sheet.getRScore().getLast().containsKey(ScoreSource.Sites));
    }
}