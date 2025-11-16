abstract class TimingWithTides extends Puzzle{
    String Prompt;
    public TimingWithTides(String puzzleID, String puzzleName, String roomID,
                           String puzzleDescription, int puzzleAttempts, String rewardID,String Prompt) {
        super(puzzleID,puzzleName,roomID,puzzleDescription,puzzleAttempts,rewardID);
        this.Prompt = Prompt;
    }
}
