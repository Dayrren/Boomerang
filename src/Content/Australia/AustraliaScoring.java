package Content.Australia;

import Content.Card;
import Content.ScoreSheet;
import Content.ScoreSource;
import Content.Scoring;
import Player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class AustraliaScoring implements Scoring {

    private ArrayList<String> completedRegions = new ArrayList<>();
    private final ArrayList<String> completedRegionsNext = new ArrayList<>();

    public void scoreRegion(Player player) {
        //To score a region 4 cards with the same Region
        HashMap<String,ArrayList<String>> instancesOfRegion = new HashMap<>();   //region, cardLetter
        player.getScoreSheet().addToVisitedSites(player.getDraftCards());


        for(Card card : player.getScoreSheet().getVisitedSites()){
            AustraliaCard australiaCard = (AustraliaCard) card;
            instancesOfRegion.computeIfAbsent(australiaCard.region, k -> new ArrayList<>()).add(australiaCard.letter);
        }

        for(String region : instancesOfRegion.keySet()){
            if(instancesOfRegion.get(region).size() == 4 && !completedRegions.contains(region)){
                // Requirement 10 b ii
                // Requirement 10 b i
                completedRegionsNext.add(region);
                player.getScoreSheet().addToRoundScore(ScoreSource.Region,3);
            }
        }
    }
    public void updateCompletedRegions(){
        this.completedRegions = new ArrayList<>(this.completedRegionsNext);
    }

    @Override
    public Runnable getSelectScoreRunnable(Player player, CountDownLatch latch) {
        // Requirement 10 e
        return ()->{
            this.scoreActivities(player);
            latch.countDown();
        };
    }

    public void scoreCollections(Player player) {

        int collectionsScore = 0;
        ArrayList<Card> playedCards = player.getDraftCards();
        HashMap<String, Integer> pointMap = new HashMap<>() {{
            put("", 0);
            put("Leaves", 1);
            put("Wildflowers", 2);
            put("Shells", 3);
            put("Souvenirs",5 );
        }};

        for(Card card: playedCards) {
            AustraliaCard c = (AustraliaCard) card;
            collectionsScore += pointMap.get(c.collection);
        }

        // Requirement 10 b i
        if (collectionsScore > 7)
            player.getScoreSheet().addToRoundScore(ScoreSource.Collections, collectionsScore);
        // Requirement 10 b i
        else
            player.getScoreSheet().addToRoundScore(ScoreSource.Collections, collectionsScore * 2);
    }
    public void scoreThrowCatch(Player player) {
        int throwCardNumber = ((AustraliaCard)player.getDraftCards().getFirst()).number;
        int catchCardNumber = ((AustraliaCard)player.getDraftCards().getLast()).number;

        int ThrowCatchScore = Math.abs(throwCardNumber-catchCardNumber);
        player.getScoreSheet().addToRoundScore(ScoreSource.ThrowCatch, ThrowCatchScore);
    }
    public void scoreAnimals(Player player) {
        HashMap<String,ArrayList<String>> instancesOfAnimal = new HashMap<>();   //Animal, cardLetter
        ArrayList<Card> playedCards = player.getDraftCards();

        for(Card card : playedCards){
            AustraliaCard c = (AustraliaCard) card;
            instancesOfAnimal.computeIfAbsent(c.animal, k -> new ArrayList<>()).add(c.letter);
        }

        int animalScore = 0;

        for(String animal : instancesOfAnimal.keySet()){
            HashMap<String, Integer> pointMap = new HashMap<>() {{
                put("",0);
                put("Kangaroos", 3);
                put("Emus", 4);
                put("Wombats", 5);
                put("Koalas", 7);
                put("Platypuses", 9);
            }};

            animalScore += instancesOfAnimal.get(animal).size() / 2 * pointMap.get(animal);
        }
        player.getScoreSheet().addToRoundScore(ScoreSource.Animal, animalScore);
    }
    public void scoreActivities(Player player) {
        HashMap<String,ArrayList<String>> instancesOfActivity = new HashMap<>();   //Activity, cardLetter/s
        ArrayList<Card> playedCards = player.getDraftCards();

        for (Card card: playedCards){
            AustraliaCard c = (AustraliaCard) card;
            if(!c.activity.isEmpty())
                instancesOfActivity.computeIfAbsent(c.activity, k -> new ArrayList<>()).add(c.letter);
        }
        String chosenActivity;
        try {
            chosenActivity = player.inOut().requestResponse(
                            instancesOfActivity.keySet() +
                            "\nSelect an Activity by typing the name of the Activity:");

        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }

        ScoreSheetAustralia sheet = (ScoreSheetAustralia) player.getScoreSheet();
        if (instancesOfActivity.containsKey(chosenActivity) &&
                sheet.getRemainingActivities().contains(chosenActivity))
        {
            int nrOfActivity = instancesOfActivity.get(chosenActivity).size();
            int[] nrOfActivityPointValue = new int[]{0, 0, 2, 4, 7, 10, 15};
            sheet.addToRoundScore(ScoreSource.Activity,nrOfActivityPointValue[nrOfActivity]);
            sheet.removeActivityFromAvailable(chosenActivity);
        }
    }

    public void scoreVisitedSites(Player player) {
       ScoreSheet sheet = player.getScoreSheet();
       int score =  sheet.getVisitedSites().size();
       sheet.addToRoundScore(ScoreSource.Sites, score);
    }

    /**
     * @param players
     */
    @Override
    public void ScoreEndOfRound(ArrayList<Player> players) {
        for(Player player: players){
            // Requirement 10 a
            this.scoreThrowCatch(player);
            // Requirement 10 b
            this.scoreRegion(player);
            // Requirement 10 c
            this.scoreCollections(player);
            // Requirement 10 d
            this.scoreAnimals(player);

            player.getScoreSheet().addToRoundsScoresList();
        }
        this.updateCompletedRegions();
    }

    /**
     * @param players
     */
    @Override
    public void ScoreEndOfGame(ArrayList<Player> players) {

        // 1 point per visited site
        // For each site you have ‘visited’ this round (the locations
        //  depicted on each card you drafted), cross out its corresponding letter on the map on your scoresheet.
        //  At the end of the game, you will get one point for each site you visited.
        for(Player player : players) {
            this.scoreVisitedSites(player);

            player.getScoreSheet().addToRoundsScoresList();
        }
    }
}
