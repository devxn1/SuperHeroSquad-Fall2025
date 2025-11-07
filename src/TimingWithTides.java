public class TimingWithTides extends Puzzle{
    String Prompt;
    public TimingWithTides(int ID, String Name, String Description, int PuzzleAtmpts, int Location, String Prompt) {
        super(ID, Name, Description, PuzzleAtmpts, Location);
        this.Prompt = Prompt;
    }
}
