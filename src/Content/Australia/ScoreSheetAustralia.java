package Content.Australia;

import Content.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ScoreSheetAustralia implements ScoreSheet {

    private final HashSet<String> activities = new HashSet<>();
    final ArrayList<HashMap<ScoreSource, Integer>> rScore = new ArrayList<>(); //ArrayList<HashMap<Source, score>>
    final HashMap<ScoreSource, Integer> currentRoundScore = new HashMap<>(); //Score source, score
    final HashSet<Card> visitedSites = new HashSet<>();
    final ContentPack contentPack;

    public ScoreSheetAustralia() {
        this.contentPack = ContentManager.getContentPack();

        for(Card card: contentPack.getDeck()){
            AustraliaCard c = (AustraliaCard) card;
            this.activities.add(c.activity);
        }
    }

    /**
     * @return the HashSet containing the available activities
     */
    public HashSet<String> getRemainingActivities() {
        return this.activities;
    }

    /**
     * @param activity removes activity from the remaining ones
     */
    public void removeActivityFromAvailable(String activity) {
        this.activities.remove(activity);
    }

    public int CompletedRounds() {
        return this.rScore.size();
    }

    public void addToRoundScore(ScoreSource Source, int score) {
        currentRoundScore.put(Source, score);
    }

    public void addToRoundsScoresList() {
        rScore.add(new HashMap<>(currentRoundScore));
        this.currentRoundScore.clear();
    }

    public void addToVisitedSites(ArrayList<Card> cards) {
        visitedSites.addAll(cards);
    }

    public HashSet<Card> getVisitedSites() {
        return this.visitedSites;
    }

    public int getFinalScore() {
        int finalScore = 0;
        for (HashMap<ScoreSource, Integer> roundMap : rScore)
            for (Integer score : roundMap.values())
                finalScore += score;

        return finalScore;
    }

    public int getScoreTotalCategory(ScoreSource category){
        int returnVal = 0;
        for(HashMap<ScoreSource, Integer> roundMap : rScore){
            returnVal += roundMap.getOrDefault(category, 0);
        }
        return returnVal;
    }

    public int getScoreCategory(ScoreSource category, int roundIndex){
        return rScore.get(roundIndex).getOrDefault(category, 0);
    }

    public ArrayList<HashMap<ScoreSource, Integer>> getRScore() {
        return rScore;
    }
}
