public class Puzzle {
    int ID;
    String Name;
    String Description;
    int PuzzleAtmpts;
    int Location;

    public Puzzle(int ID, String Name, String Description, int PuzzleAtmpts, int Location){
        this.ID = ID;
        this.Name = Name;
        this.Description = Description;
        this.PuzzleAtmpts = PuzzleAtmpts;
        this.Location = Location;
    }

    String giveHint(String str){
        return str;
    }
    String givePuzzleDescription(){
        return this.Description;
    }
}
