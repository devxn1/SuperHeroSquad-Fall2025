/**Class: Room
 * @author Devin Gomez
 * @version 1.0
 * Course:  ITEC3860 Fall 2025
 * Written: November 16, 2025
 * Purpose: Room/location
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

    public boolean attemptSolve(String playerInput) {
        //check if is already solve, just in case
        if (isSolved) return true;


        String attempt = normalizePattern(playerInput);
        boolean isCorrect = attempt.equals(rightPattern);

        if (isCorrect) {
            markSolved();
            System.out.println("Puzzl Solved");
            return true;
        } else {
            decrementAttempts();
            System.out.println("Puzzl Failed");
            System.out.println("Remaining attempts: " + remainingAttempts);
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
        return pattern.toUpperCase().replaceAll("\\s+", "");
    }



}