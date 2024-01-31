package Content;

import java.util.ArrayList;
public interface ContentPack {
    ArrayList<Card> getDeck();
    Scoring getScoringStrategy();
    Messages getMessages();
    ScoreSheet getScoreSheet();
}