package Content.Australia;

import Comunication.IO;
import Content.Card;
import Content.Messages;
import Content.ScoreSheet;
import Content.ScoreSource;
import Player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AustraliaMessages implements Messages {
    @Override
    public void sendLastRoundsScore(Player player) throws IOException {

        IO inOut = player.inOut();
        HashMap<ScoreSource, Integer> roundScore = player.getScoreSheet().getRScore().getLast();

        String message = "\n";
        for (Map.Entry<ScoreSource, Integer> entry : roundScore.entrySet()) {
            String key = entry.getKey().getAbbreviation();
            Integer value = entry.getValue();
            message += "\nThis round you scored: " + value + " as your " + key;
        }
        inOut.sendMessage(message + "\n");
    }
    @Override
    public void sendFinalScore(Player player) throws IOException {

        IO inOut = player.inOut();
        ScoreSheet sheet = player.getScoreSheet();
        ArrayList<HashMap<ScoreSource, Integer>> rScore = sheet.getRScore();
        ScoreSource
                t=ScoreSource.ThrowCatch,
                to=ScoreSource.Sites,
                c=ScoreSource.Collections,
                a=ScoreSource.Animal,
                ac=ScoreSource.Activity,
                r=ScoreSource.Region;

        int totalPoints = 0;


        ArrayList<Integer> roundScores = new ArrayList<>();
        for (int i = 0; i < rScore.size() ; i++) {
            roundScores.add(0);
            for (Map.Entry<ScoreSource, Integer> entry : rScore.get(i).entrySet()) {
                Integer val = entry.getValue();
                totalPoints += val;
                roundScores.set(i, roundScores.get(i) + val);
            }
        }


        //Requirement 12
        String finalScore =  "                       Round 1\tRound 2\tRound 3\tRound 4\n";
        finalScore += "Throw and Catch score:   " + sheet.getScoreCategory(t,0)  + "\t\t" +  sheet.getScoreCategory(t,1)  + "\t\t" +  sheet.getScoreCategory(t,2)  + "\t\t" +  sheet.getScoreCategory(t,3)+"\n";
        finalScore += "    Collections score:   " + sheet.getScoreCategory(c,0)  + "\t\t" +  sheet.getScoreCategory(c,1)  + "\t\t" +  sheet.getScoreCategory(c,2)  + "\t\t" +  sheet.getScoreCategory(c,3)+"\n";
        finalScore += "        Animals score:   " + sheet.getScoreCategory(a,0)  + "\t\t" +  sheet.getScoreCategory(a,1)  + "\t\t" +  sheet.getScoreCategory(a,2)  + "\t\t" +  sheet.getScoreCategory(a,3)+"\n";
        finalScore += "     Activities score:   " + sheet.getScoreCategory(ac,0)  + "\t\t" +  sheet.getScoreCategory(ac,1)  + "\t\t" +  sheet.getScoreCategory(ac,2)  + "\t\t" +  sheet.getScoreCategory(ac,3)+"\n";
        finalScore += "       Region bonuses:   " + sheet.getScoreCategory(r,0)  + "\t\t" +  sheet.getScoreCategory(r,1)  + "\t\t" +  sheet.getScoreCategory(r,2)  + "\t\t" +  sheet.getScoreCategory(r,3)+"\n";
        finalScore += "                         " + "-------------------------\n";
        finalScore += "    Total round score:   " + roundScores.get(0)+"\t\t"+ roundScores.get(1)+"\t\t"+ roundScores.get(2)+"\t\t"+ roundScores.get(3)+"\n";
        finalScore += "  visited sites bonus:   " + roundScores.get(4)+"\n"; //This is not implemented the same way as the spageti, but it is implemented to follow the official rules
        finalScore += "     Game total score:   ["+ totalPoints +"]";
        inOut.sendMessage("\n*********************************************\n"+finalScore);
        System.out.println("\n*********************************************\n"+finalScore);
    }

    @Override
    public void sendWinnerMessage(Player player) throws IOException {
        player.inOut().sendMessage("**********************************************\n" +
                "You won with a score of: " + player.getScoreSheet().getFinalScore() +
                " points");
    }

    @Override
    public void sendEndMessage(Player player, Player winner, int place) throws IOException {
        player.inOut().sendMessage("**********************************************\n" +
                "Player " + winner.getID() + " won with " + winner.getScoreSheet().getFinalScore() +
                " points" + "\nYou placed " + place + "nd with a score of: " + player.getScoreSheet().getFinalScore());

    }

    @Override
    public String printCards(List<Card> cards) {
        // String.format("%-20s", str); % = format sequence. - = string is left-justified (no - = right-justified). 20 = string will be 20 long. s = character string format code
        String printString = String.format("%27s", "Site [letter] (nr):  ");
        for(Card c : cards) { //print name letter and number of each card
            AustraliaCard card = (AustraliaCard) c;
            printString += String.format("%-35s", card.name+ " ["+card.letter+"] ("+card.number+")");
        }
        printString += "\n" + String.format("%27s", "Region:  ");
        for(Card c : cards) { //print name letter and number of each card
            AustraliaCard card = (AustraliaCard) c;
            printString += String.format("%-35s", card.region);
        }
        printString +="\n" + String.format("%27s", "Collections:  ");
        for(Card c : cards) { //print collections of each card, separate with tab
            AustraliaCard card = (AustraliaCard) c;
            printString  += String.format("%-35s", card.collection);
        }
        printString +="\n" + String.format("%27s", "Animals:  ");
        for(Card c : cards) { //print animals of each card, separate with tab
            AustraliaCard card = (AustraliaCard) c;
            printString += String.format("%-35s", card.animal);
        }
        printString +="\n" + String.format("%27s", "Activities:  ");
        for(Card c : cards) { //print activities of each card, separate with tab
            AustraliaCard card = (AustraliaCard) c;
            printString += String.format("%-35s", card.activity);
        }
        return printString;
    }
}
