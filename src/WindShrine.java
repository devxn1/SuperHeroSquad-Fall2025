public class WindShrine extends Puzzle{
    String weightPlacement;
    public WindShrine(int ID, String Name, String Description, int PuzzleAtmpts, int Location, String weightPlacement) {
        super(ID, Name, Description, PuzzleAtmpts, Location);
        this.weightPlacement = weightPlacement;
    }
    void placeWeights(){

    }
}
