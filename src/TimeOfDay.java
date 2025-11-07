public class TimeOfDay extends Puzzle{
    String rotateDescription;
    public TimeOfDay(int ID, String Name, String Description, int PuzzleAtmpts, int Location, String rotateDescription) {
        super(ID, Name, Description, PuzzleAtmpts, Location);
        this.rotateDescription = rotateDescription;
    }
}
