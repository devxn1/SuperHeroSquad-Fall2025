/**Class: PatternMatch
 * @author Devin Gomez
 * @version 1.0
 * Course:  ITEC3860 Fall 2025
 * Written: November 16, 2025tt
 * Purpose:
 */

public class PatternMatch extends Puzzle {

    private String rightPattern;

    public PatternMatch(String puzzleID, String puzzleName, String roomID,
                        String puzzleDescription, int puzzleAttempts,
                        String rewardType, String rewardID,
                        String correctPattern) {
        super(puzzleID, puzzleName, roomID, puzzleDescription,
                puzzleAttempts, rewardType, rewardID);
        this.rightPattern = normalizePattern(correctPattern);
    }

    @Override
    public boolean attemptSolve(String playerInput) {
        //check if is already solve, just in case
        if (isSolved()) return true;


        String attempt = normalizePattern(playerInput);
        boolean isCorrect = attempt.equals(rightPattern);

        if (isCorrect) {
            markSolved();
            System.out.println("Puzzl Solved");
            return true;
        } else {
            decrementAttempts();
            System.out.println("Wrong pattern.");

            if (getRemainingAttempts() > 0) {
                System.out.println("Remaining attempts: " + getRemainingAttempts());
                System.out.println("Type 'hint' for help.");
            } else {
                System.out.println("No remaining attempts.");
            }
            return false;
        }

    }

    public String getCorrectPattern() {
        return rightPattern;
    }


    //AI
    //Purpose: remove space and replace with "-"
    private String normalizePattern(String pattern) {
        if (pattern == null) return "";

        // Remove all whitespace and convert to uppercase
        String normalized = pattern.toUpperCase().replaceAll("\\s+", "");

        // If player typed without dashes, add them
        // Example: "XXOX" becomes "X-X-O-X"
        if (!normalized.contains("-") && normalized.length() > 1) {
            normalized = String.join("-", normalized.split(""));
        }

        return normalized;
    }



}