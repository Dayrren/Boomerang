package Game;


import GameLoop.EndPhase;
import GameLoop.Phase;

public class PhaseController {

    /**
     * @param initialPhase Takes a phase and runs it and proceeds to the next phase
     */
    public PhaseController(Phase initialPhase) {

        Phase currentPhase = initialPhase;


        while (!(currentPhase instanceof EndPhase)) {
            currentPhase.run();
            currentPhase = currentPhase.nextPhase();
        }
    }
}
