public class CrystalResonance extends Puzzle {

    private int currentPitch;
    private final int targetPitch;
    private final int tuningStep;

    public CrystalResonance() {
        super("P04", "CrystalResonance", "E3", "Tune the crystal's frequency using 'tune left' / 'tune right' to match resonance.", 4, "recipe", "water_still");

        //frequency required to solve
        this.targetPitch = 100;
        //starting frequency
        this.currentPitch = 60;
        //amount adjusted per command
        this.tuningStep = 10;

        resetPuzzle();
    }

    public void resetPuzzle() {
        this.currentPitch = 60;
        this.remainingAttempts = puzzleAttempts;
        this.isSolved = false;
    }

    //Handles tuning commands and resonance matching.
    public boolean attemptSolve(String playerInput) {
        if (isSolved) {
            System.out.println("Crystal already resonates perfectly.");
            return true;
        }

        if (remainingAttempts <= 0) {
            System.out.println("No attempts left. Puzzle failed.");
            return false;
        }

        String cmd = playerInput.trim().toLowerCase();

        if (!cmd.equals("tune left") && !cmd.equals("tune right")) {
            System.out.println("Unknown command. Use: 'tune left' or 'tune right'.");
            decrementAttempts();
            System.out.println("Attempts remaining: " + remainingAttempts);
            return false;
        }

        if (cmd.equals("tune left")) {
            //lower pitch by 10
            currentPitch -= tuningStep;
        } else if (cmd.equals("tune right")) {
            //raises pitch by 10
            currentPitch += tuningStep;
        }

        System.out.println("Crystal pitch is now: " + currentPitch + " Hz");

        if (currentPitch == targetPitch) {
            markSolved();
            System.out.println("The crystal hums in perfect resonance. Puzzle solved!");
            return true;
        }

        decrementAttempts();
        System.out.println("Attempts remaining: " + remainingAttempts);

        if (remainingAttempts == 0) {
            System.out.println("No attempts left. The crystal falls silent...");
        }
        return false;
    }
}