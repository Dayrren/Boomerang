package GameLoop;


import Player.Player;
import Utlilty.Task;
import Utlilty.ThreadExecute;

import java.util.ArrayList;


public class ThrowSelectionPhase implements Phase {

    private final ArrayList<Player> players;
    public ThrowSelectionPhase(ArrayList<Player> players){
        this.players = players;
    }

    @Override
    public void run() {
        //allow the clients/bots to preform their actions in parallel
        // Requirement 5
        ThreadExecute.execute(players, Task.Throw);

    }

    @Override
    public Phase nextPhase() {
        return new PassPhase(this.players);
    }


}
