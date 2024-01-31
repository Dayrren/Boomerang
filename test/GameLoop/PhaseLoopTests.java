package GameLoop;

import Content.Australia.Australia;
import Content.ContentPack;
import Content.ScoreSheet;
import Player.Bot;
import Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhaseLoopTests {
    final ArrayList<Player> players = new ArrayList<>();
    ContentPack contentPack;
    @BeforeEach
    void setUp() {

        contentPack = new Australia();
        for (int i = 0; i < 4; i++) {
            players.add(new Bot(i));
        }
    }

    @Test
    void testDraftPassLoop(){
        Phase currentPhase = new CardDrawPhase(players);

        //requirement 8
        //custom controller
        while(!(currentPhase instanceof CatchPhase)){

            currentPhase.run();
            currentPhase = currentPhase.nextPhase();
        }

        for(Player player : players)
            assertEquals(1,player.getHand().size());
    }

    @Test
    void testRoundLoop(){
        Phase currentPhase = new CardDrawPhase(players);
        int rounds = 0;

        //requirement 12
        //custom controller
        while(!(currentPhase instanceof FinalScoringPhase)){
            if(currentPhase instanceof RoundScoringPhase)
                rounds++;

            currentPhase.run();
            currentPhase = currentPhase.nextPhase();
        }

        assertEquals(4,rounds);
    }


}
