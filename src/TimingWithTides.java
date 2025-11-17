public class TimingWithTides extends Puzzle {

    private Player player;

    // Low-tide hours
    private int lowTideStart1 = 1;
    private int lowTideEnd1   = 6;

    private int lowTideStart2 = 19;
    private int lowTideEnd2   = 24;

    public TimingWithTides(Player player) {
        super("P03", "TideLock", "E1", "You must act during low tide to block the channel.", 5, "access", "sea_chest");
        this.player = player;
        resetPuzzle();
    }

    public void resetPuzzle() {
        this.remainingAttempts = puzzleAttempts;
        this.isSolved = false;
    }

    private boolean isLowTide(int hour) {
        return (hour >= lowTideStart1 && hour <= lowTideEnd1) || (hour >= lowTideStart2 && hour <= lowTideEnd2);
    }

    public boolean attemptSolve(String playerInput) {

        if (isSolved) {
            System.out.println("The channel is already blocked.");
            return true;
        }

        if (remainingAttempts <= 0) {
            System.out.println("No attempts remain. The raging tide blocks your efforts.");
            return false;
        }

        String cmd = playerInput.trim().toLowerCase();

        if (!cmd.equals("block channel")) {
            System.out.println("Unknown command. Use: 'block channel'.");
            return false;
        }

        //placeholder while someone else edits player
        int hour = 5;
        /*Need to update player into THIS line of code, the correct way to do this:
        int hour = player.getCurrentHour();*/
        System.out.println("The current tide hour is: " + hour);

        if (isLowTide(hour)) {
            markSolved();
            System.out.println("You enter during low tide.");
            return true;
        }

        // Wrong timing
        System.out.println("The tide is high — the raging water prevents you from entering this area!");
        decrementAttempts();

        if (remainingAttempts > 0) {
            System.out.println("Attempts remaining: " + remainingAttempts);
        } else {
            System.out.println("The tides overwhelm you… no attempts remain.");
        }
        return false;
    }
}