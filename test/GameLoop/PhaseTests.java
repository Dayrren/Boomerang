package GameLoop;

import Content.Australia.Australia;
import Content.Australia.AustraliaCard;
import Content.Card;
import Content.ContentManager;
import Content.ContentPack;
import Content.ScoreSheet;
import Player.Bot;
import Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhaseTests {

    final ArrayList<Player> players = new ArrayList<>();
    ContentPack contentPack;

    @BeforeEach
    void setUp() {
        ContentManager.setContentPack(new Australia());
        contentPack = ContentManager.getContentPack();
        for (int i = 0; i < 4; i++) {
            players.add(new Bot(i));
        }
    }


    @Test
    void testCardDraw() {

        Phase drawPhase = new CardDrawPhase(players);
        drawPhase.run();

        // requirement 4
        for (Player player : players) {
            assertEquals(7,player.getHand().size());
        }
    }

    @Test
    void testPassHand() {

        Phase drawPhase = new CardDrawPhase(players);
        drawPhase.run();

        //save a copy of the players current hand
        ArrayList<ArrayList<Card>> oldHands = new ArrayList<>();
        for(Player player: players)
            oldHands.add(new ArrayList<>(player.getHand()));

        //run the passPhase
        Phase passPhase = new PassPhase(players);
        passPhase.run();

        // requirement 6 pass last -> first
        int playerCount = players.size();
        for (int i = 0; i<playerCount;i++) {
            int prevIndex = (i-1+playerCount) % playerCount;
            assertArrayEquals(oldHands.get(prevIndex).toArray(), players.get(i).getHand().toArray());
        }
    }


    @Test
    void testCatchPhase() {

        for(Player player: players)
            player.setHand(new ArrayList<>(List.of(new AustraliaCard("null", "A", "null", 5, "null", "null", "null"))));

        //save a copy of the players last card
        ArrayList<Card> oldHands = new ArrayList<>();
        for(Player player: players)
            oldHands.add(new ArrayList<>(player.getHand()).getLast());

        Phase catchPhase = new CatchPhase(players);
        catchPhase.run();

        // requirement 9 pass first -> last
        int playerCount = players.size();
        for (int i = 0; i<playerCount; i++) {
            int prevIndex = (i+1+playerCount) % playerCount;
            assertEquals(oldHands.get(prevIndex), players.get(i).getDraftCards().getLast());
        }

    }

    @Test
    void testShuffle() {

        new CardDrawPhase(players).run();

        ArrayList<Card> deck = ContentManager.getContentPack().getDeck();
        ArrayList<Card> hands = new ArrayList<>();

        for (Player player : players){
            //unshuffled deck would be result in the same deck as...
            hands.addAll(player.getHand());
        }

        assertNotEquals(deck, hands);
    }
}