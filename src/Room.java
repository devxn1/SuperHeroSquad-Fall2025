import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Class: Room
 * @author Devin Gomez
 * @version 1.0
 * Course:  ITEC3860 Fall 2025
 * Written: November 13, 2025
 * Purpose:
 */
public class Room {
    private String roomID;
    private String roomName;
    private String roomDescription;
    private String north;
    private String east;
    private String south;
    private String west;
    private boolean visited;
    public Map<String, Integer> exits;

    //Meant for checking instance of another Objects in the room
    private final List<Item> items = new ArrayList<>();
    private Puzzle puzzle;
    private Monster monster;



    public Room(String roomID, String roomName, String roomDescription, String north, String east, String south, String west, Boolean visited) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
        this.visited = false;


        this.exits = new HashMap<>();
        this.puzzle = null;
        this.monster = null;
    }

    public String getRoomID() {
        return roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getNorth() {
        return north;
    }

    public String getEast() {
        return east;
    }

    public String getSouth() {
        return south;
    }

    public String getWest() {
        return west;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Map<String, Integer> getExits() {
        return exits;
    }

    public void setExits(Map<String, Integer> exits) {
        this.exits = exits;
    }

    public void checkIfVisited() {

    }





    //Handling Items

    public void addItemToRoom(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    public Item removeItemFromRoom(String itemName) {
        if (itemName == null) {
            return null;
        }
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(i);
                return item;
            }
        }
        return null;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }






    //Handling Puzzle in room
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public boolean hasPuzzle() {
        return puzzle != null;
    }

    //Handling Monster in Monster
    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Monster getMonster() {
        return monster;
    }

//    public boolean hasMonster() {
//        return monster != null && monster.isAlive();
//    }

    void moveRoom(){

    }
    void getNextRoom(){

    }

    public void visit() {
        visited = true;
    }
}
