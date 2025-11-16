abstract class PatternMatch extends Puzzle{
    String Pattern;
    public PatternMatch(String puzzleID, String puzzleName, String roomID,
                        String puzzleDescription, int puzzleAttempts, String rewardID, String Pattern) {
        super(puzzleID,puzzleName,roomID,puzzleDescription,puzzleAttempts,rewardID);
        this.Pattern = Pattern;
    }
}
