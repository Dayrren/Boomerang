package Game;

import GameLoop.Phase;
import GameLoop.PlayerConnectionPhase;

public class Server {

    /**
     * Sets up the first phase and starts the games controller
     * @param numberPlayers nr of players to create
     * @param numberOfBots nr of bots to create
     */
    public Server(int numberPlayers, int numberOfBots) {

        Phase setup = new PlayerConnectionPhase(numberPlayers, numberOfBots);

        new PhaseController(setup);

    }
}
