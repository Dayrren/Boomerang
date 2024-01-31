package GameLoop;

public interface Phase {

    /**
     * The part of the Phase that interacts and updates
     * players/scores to progress the game.
     */
    void run();

    /**
     * @return a new instance of the next phase
     */
    Phase nextPhase();
}
