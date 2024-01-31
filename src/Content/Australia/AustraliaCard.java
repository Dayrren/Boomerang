package Content.Australia;

import Content.Card;

public class AustraliaCard implements Card {
    public final String name;
    public final String letter;
    public final String region;
    public final String collection;
    public final String animal;
    public final String activity;
    public final int number;

    public AustraliaCard(String name, String letter, String region, int number, String collection, String animal, String activity) {
        this.name=name; this.letter=letter; this.number=number; this.region=region; this.collection=collection;
        this.animal=animal; this.activity=activity;
    }

    public boolean letterEquals(String letter) {
        return this.letter.equals(letter);
    }

    public String getLetter() {
        return letter;
    }
}
