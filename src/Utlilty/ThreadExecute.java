package Utlilty;

import Content.Card;
import Content.ContentManager;
import Player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThreadExecute {
    public static void execute(ArrayList<Player> players, Task task){
        ExecutorService threadPool = Executors.newFixedThreadPool(players.size());
        CountDownLatch latch = new CountDownLatch(players.size());

            for(Player player : players) {
                switch (task){
                    case Throw -> threadPool.execute(getThrowSeclectionRunnable(player, latch));
                    case Draft -> threadPool.execute(getCardDraftRunnable(player,latch));
                    case Chose -> threadPool.execute(ContentManager.getContentPack().getScoringStrategy().getSelectScoreRunnable(player,latch));
                }
        }

        try {
            latch.await(); // Wait for all tasks to complete
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }
    }
    private static Runnable getThrowSeclectionRunnable(Player player, CountDownLatch latch) {
        //Requirement 5
        return () -> {

            String cardPrintOut = ContentManager.getContentPack().getMessages().printCards(player.getHand());
            String response;

            try {
                response = player.inOut().requestResponse(cardPrintOut + "\nSelect a card by Typing the letter in the []").toUpperCase();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            ArrayList<Card> hand = player.getHand();

            //finds the index of the Selected letter using a filter
            OptionalInt index = IntStream.range(0, hand.size())
                    .filter(card -> hand.get(card).letterEquals(response))
                    .findFirst();
            if (index.isPresent()){
                player.addCardToDraft(player.getHand().remove(index.getAsInt()));
            }
            else{throw new Error("Invalid card choice");}

            latch.countDown();
        };
    }

    private static Runnable getCardDraftRunnable(Player player, CountDownLatch latch) {
        //Requirement 7 - Make sure every player can draft their card at the same time
        return () -> {
            String cardPrintOut = ContentManager.getContentPack().getMessages().printCards(player.getHand());
            String response;

            try {
                response = player.inOut().requestResponse(
                        "Select the card you want by typing the cards site (the letter in the square brackets):\n"
                                + cardPrintOut + "\n ");
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            ArrayList<Card> hand = player.getHand();

            //finds the index of the Selected letter using a filter
            OptionalInt index = IntStream.range(0, hand.size())
                    .filter(card -> hand.get(card).letterEquals(response))
                    .findFirst();
            if (index.isPresent()){
                player.addCardToDraft(player.getHand().remove(index.getAsInt()));
            }
            else{
                try {
                    player.inOut().sendMessage("Invalid card choice");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                throw new Error("Invalid card choice");
            }

            latch.countDown();
        };
    }


}
