abstract class CrystalResonance extends Puzzle{
    String TuneDescription;
    public CrystalResonance(String puzzleID, String puzzleName, String roomID,
                            String puzzleDescription, int puzzleAttempts, String rewardID, String rotateDescription) {
        super(puzzleID,puzzleName,roomID,puzzleDescription,puzzleAttempts,rotateDescription);
        this.TuneDescription = rotateDescription;
    }

    void tuneCrystals(){

    }
}
