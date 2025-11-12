public class Room {
    int roomLocation;
    private String roomName;
    private String roomDescription;
    private String North;
    private String East;
    private String South;
    private String West;
    private Puzzle puzzle;
    private boolean visited;

    public Room(int roomLocation, String roomName, String roomDescription, String North, String East, String South, String West, Boolean visited) {
        this.roomLocation = roomLocation;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.North = North;
        this.East = East;
        this.South = South;
        this.West = West;
        this.visited = false;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    void moveRoom(){

    }
    void getNextRoom(){
    }

    public void visit() {
        visited = true;
    }
}
