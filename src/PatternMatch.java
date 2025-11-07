public class PatternMatch extends Puzzle{
    String Pattern;
    public PatternMatch(int ID, String Name, String Description, int PuzzleAtmpts, int Location, String Pattern) {
        super(ID, Name, Description, PuzzleAtmpts, Location);
        this.Pattern = Pattern;
    }
}
