public class CrystalResonance extends Puzzle{
    String TuneDescription;

    public CrystalResonance(int ID, String Name, String Description, int PuzzleAtmpts, int Location, String rotateDescription) {
        super(ID, Name, Description, PuzzleAtmpts, Location);
        this.TuneDescription = rotateDescription;
    }

    void tuneCrystals(){

    }
}
