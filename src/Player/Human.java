package Player;

import Comunication.IO;
import Content.ContentManager;

public class Human extends AbstractPlayer {

    public Human(int id, IO inOut) {
        super(id, inOut, ContentManager.getContentPack().getScoreSheet());
    }

}