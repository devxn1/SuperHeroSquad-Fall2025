import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Puzzle {
    protected String puzzleID;
    protected String puzzleName;
    protected String roomID;
    protected String puzzleDescription;
    protected int puzzleAttempts;
    protected int remainingAttempts;
    protected boolean isSolved;
    protected String rewardID;
    protected int hintsUsed;
    protected List<String> hints;

    public Puzzle(String puzzleID, String puzzleName, String roomID,
                  String puzzleDescription, int puzzleAttempts, String rewardID) {
        this.puzzleID = puzzleID;
        this.puzzleName = puzzleName;
        this.roomID = roomID;
        this.puzzleDescription = puzzleDescription;
        this.puzzleAttempts = puzzleAttempts;
        this.remainingAttempts = puzzleAttempts;
        this.isSolved = false;
        this.rewardID = rewardID;
        this.hintsUsed = 0;
        this.hints = new ArrayList<>();
        loadHintsFromFile();
    }

    // to get hints
    private void loadHintsFromFile() {
        try (Scanner scanner = new Scanner(new File("PuzzleHints"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("/");

                // parts[1] is puzzleID, parts[3] is hint text
                if (parts[1].equals(this.puzzleID)) {
                    hints.add(parts[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading hints for puzzle " + puzzleID);
        }
    }

    public String getHint() {
        if (hintsUsed >= 3) {
            this.markSolved();
            System.out.println("You've used all 3 hints.");
            System.out.println("Puzzle will be automatically solved.");

        }

        String hint = hints.get(hintsUsed);
        hintsUsed++;
        return hint;
    }

    //prob not needed
    //public abstract boolean attemptSolve(String playerInput);

    public void decrementAttempts() {
        if (remainingAttempts > 0) {
            remainingAttempts--;
        }
    }

    public void markSolved() {
        this.isSolved = true;
    }


    public String getPuzzleID() {
        return puzzleID;
    }

    public String getPuzzleName() {
        return puzzleName;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getPuzzleDescription() {
        return puzzleDescription;
    }

    public int getPuzzleAttempts() {
        return puzzleAttempts;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public String getRewardID() {
        return rewardID;
    }
}