import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**Class: Room
 * @author Devin Gomez
 * @version 1.0
 * Course:  ITEC3860 Fall 2025
 * Written: November 16, 2025
 * Purpose: Room/location
 */

public class Puzzle {
    protected String puzzleID;
    protected String puzzleName;
    protected String roomID;
    protected String puzzleDescription;
    protected int puzzleAttempts;
    protected int remainingAttempts;
    protected boolean isSolved;
    protected String rewardID;
    protected String rewardType;
    protected int hintsUsed;
    protected List<String> hints;

    public Puzzle(String puzzleID, String puzzleName, String roomID,
                  String puzzleDescription, int puzzleAttempts,
                  String rewardType, String rewardID) {
        this.puzzleID = puzzleID;
        this.puzzleName = puzzleName;
        this.roomID = roomID;
        this.puzzleDescription = puzzleDescription;
        this.puzzleAttempts = puzzleAttempts;
        this.remainingAttempts = puzzleAttempts;
        this.isSolved = false;
        this.rewardType = rewardType;
        this.rewardID = rewardID;
        this.hintsUsed = 0;
        this.hints = new ArrayList<>();
        loadHintsFromFile();
    }

    public String getRewardType() {
        return rewardType;
    }



    // to get hints
    private void loadHintsFromFile() {
        try (Scanner scanner = new Scanner(new File("data/PuzzleHints.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue; // skip blank lines

                String[] parts = line.split("/");

                // hintID / puzzleID / hintNumber / hintText
                if (parts.length >= 4 && parts[1].equals(this.puzzleID)) {
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
            return "Puzzle automatically solved!";

        }

        String hint = hints.get(hintsUsed);
        hintsUsed++;
        return hint;
    }

    //prob not needed
    // DEFAULT implementation so class is NOT abstract
    public boolean attemptSolve(String playerInput) {
        System.out.println("This puzzle does not have a solve mechanic defined.");
        return false;
    }

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