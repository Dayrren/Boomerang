package Content.Australia;

import Content.Card;
import Content.ContentManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AustraliaTest {

    @BeforeEach
    void setupSingleton(){
        ContentManager.setContentPack(new Australia());
    }

    @Test
    void testDeckSize() {
        ArrayList<Card> deck = ContentManager.getContentPack().getDeck();

        assertEquals(28,deck.size());
    }

    @Test
    void testAdditionalSymbols(){

        for(Card card: ContentManager.getContentPack().getDeck()){
            AustraliaCard c = (AustraliaCard) card;
            int additionalSymbols = 0;
            if(!c.collection.isEmpty())
                additionalSymbols++;
            if(!c.animal.isEmpty())
                additionalSymbols++;
            if(!c.activity.isEmpty())
                additionalSymbols++;

            assertEquals(2, additionalSymbols);
        }
    }
}