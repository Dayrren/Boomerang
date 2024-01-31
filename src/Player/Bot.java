package Player;

import Comunication.IOBot;
import Content.ContentManager;

//Template Method Design Pattern.
public class Bot extends AbstractPlayer {

    public Bot(int id) {
        super(id, new IOBot(), ContentManager.getContentPack().getScoreSheet());
    }

}