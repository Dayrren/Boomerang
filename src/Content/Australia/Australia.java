package Content.Australia;

import Content.*;

import java.util.ArrayList;

public class Australia implements ContentPack {
    private final ArrayList<Card> deck;
    private static Scoring ScoringStrategy;
    private static Messages AustraliaCommunication;

    public Australia(){
        this.deck = new ArrayList<>();
        deck.add(new AustraliaCard("The Bungle Bungles","A","Western Australia", 1, "Leaves", "", "Indigenous Culture"));
        deck.add(new AustraliaCard("The Pinnacles","B","Western Australia", 1, "", "Kangaroos", "Sightseeing"));
        deck.add(new AustraliaCard("Margaret River","C","Western Australia", 1, "Shells", "Kangaroos", ""));
        deck.add(new AustraliaCard("Kalbarri National Park","D","Western Australia", 1, "Wildflowers", "", "Bushwalking"));
        deck.add(new AustraliaCard("Uluru","E","Northern Territory", 4, "", "Emus", "Indigenous Culture"));
        deck.add(new AustraliaCard("Kakadu National Park","F","Northern Territory", 4, "", "Wombats", "Sightseeing"));
        deck.add(new AustraliaCard("Nitmiluk National Park","G","Northern Territory", 4, "Shells", "Platypuses", ""));
        deck.add(new AustraliaCard("King's Canyon","H","Northern Territory", 4, "", "Koalas", "Swimming"));
        deck.add(new AustraliaCard("The Great Barrier Reef","I","Queensland", 6, "Wildflowers", "", "Sightseeing"));
        deck.add(new AustraliaCard("The Whitsundays","J","Queensland", 6, "", "Kangaroos", "Indigenous Culture"));
        deck.add(new AustraliaCard("Daintree Rainforest","K","Queensland", 6, "Souvenirs", "", "Bushwalking"));
        deck.add(new AustraliaCard("Surfers Paradise","L","Queensland", 6, "Wildflowers", "", "Swimming"));
        deck.add(new AustraliaCard("Barossa Valley","M","South Australia", 3, "", "Koalas", "Bushwalking"));
        deck.add(new AustraliaCard("Lake Eyre","N","South Australia", 3, "", "Emus", "Swimming"));
        deck.add(new AustraliaCard("Kangaroo Island","O","South Australia", 3, "", "Kangaroos", "Bushwalking"));
        deck.add(new AustraliaCard("Mount Gambier","P","South Australia", 3, "Wildflowers", "", "Sightseeing"));
        deck.add(new AustraliaCard("Blue Mountains","Q","New South Whales", 5, "", "Wombats", "Indigenous Culture"));
        deck.add(new AustraliaCard("Sydney Harbour","R","New South Whales", 5, "", "Emus", "Sightseeing"));
        deck.add(new AustraliaCard("Bondi Beach","S","New South Whales", 5, "", "Wombats", "Swimming"));
        deck.add(new AustraliaCard("Hunter Valley","T","New South Whales", 5, "", "Emus", "Bushwalking"));
        deck.add(new AustraliaCard("Melbourne","U","Victoria", 2, "", "Wombats", "Bushwalking"));
        deck.add(new AustraliaCard("The MCG","V","Victoria", 2, "Leaves", "", "Indigenous Culture"));
        deck.add(new AustraliaCard("Twelve Apostles","W","Victoria", 2, "Shells", "", "Swimming"));
        deck.add(new AustraliaCard("Royal Exhibition Building","X","Victoria", 2, "Leaves", "Platypuses", ""));
        deck.add(new AustraliaCard("Salamanca Markets","Y","Tasmania", 7, "Leaves", "Emus", ""));
        deck.add(new AustraliaCard("Mount Wellington","Z","Tasmania", 7, "", "Koalas", "Sightseeing"));
        deck.add(new AustraliaCard("Port Arthur","*","Tasmania", 7, "Leaves", "", "Indigenous Culture"));
        deck.add(new AustraliaCard("Richmond","-","Tasmania", 7, "", "Kangaroos", "Swimming"));

        ScoringStrategy = new AustraliaScoring();
        AustraliaCommunication = new AustraliaMessages();
    };

    public ArrayList<Card> getDeck() {
        return new ArrayList<>(deck);
    }

    @Override
    public Scoring getScoringStrategy() {
        return ScoringStrategy;
    }

    public Messages getMessages() {
        return AustraliaCommunication;
    }

    public ScoreSheet getScoreSheet(){
        return (ScoreSheet) new ScoreSheetAustralia();
    }
}




