import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Class: Room
 * @author Devin Gomez
 * @version 1.0
 * Course:  ITEC3860 Fall 2025
 * Written: November 13, 2025
 * Purpose: Room/location
 */
public class Room {
    private String roomID;
    private String biome;
    private String roomName;
    private String roomDescription;
    private Map<String, String> exits;
    private String isLockedBy;
    private boolean visited;


    //Meant for checking instance of another Objects in the room
    private List<Item> items;
    private Puzzle puzzle;
    private Monster monster;
    private AmbushMonster ambushMonster;



    public Room(String roomID, String biome,String roomName, String roomDescription, String north, String east, String south, String west, String isLockedBy) {
        this.roomID = roomID;
        this.biome = biome;
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.exits = new HashMap<>();
        if (north != null && !north.trim().isEmpty()) {
            exits.put("north", north.trim());
        }
        if (east != null && !east.trim().isEmpty()) {
            exits.put("east", east.trim());
        }
        if (south != null && !south.trim().isEmpty()) {
            exits.put("south", south.trim());
        }
        if (west != null && !west.trim().isEmpty()) {
            exits.put("west", west.trim());
        }

        if (isLockedBy == null || isLockedBy.trim().isEmpty()) {
            this.isLockedBy = null;
        } else {
            this.isLockedBy = isLockedBy.trim();
        }

        //this.isLockedBy = isLockedBy;
        if (isLockedBy == null || isLockedBy.trim().isEmpty()) {
            this.isLockedBy = null;
        } else {
            this.isLockedBy = isLockedBy.trim();
        }

        this.visited = false;


        this.items = new ArrayList<>();
        this.puzzle = null;
        this.monster = null;
        this.ambushMonster = null;

    }

    public String getRoomID() {
        return roomID;
    }

    public String getBiome() {
        return biome;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public String getNorth() {
        return exits.get("north");
    }

    public String getEast() {
        return exits.get("east");
    }

    public String getSouth() {
        return exits.get("south");
    }

    public String getWest() {
        return exits.get("west");
    }

    public String getIsLockedBy() {
        return isLockedBy;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    //Handling direction

    public String getExit(String direction) {
        String dir = direction.toLowerCase();
        switch (dir) {
            case "north":
            case "n":
                return exits.get("north");
            case "east":
            case "e":
                return exits.get("east");
            case "south":
            case "s":
                return exits.get("south");
            case "west":
            case "w":
                return exits.get("west");
            default:
                return null;
        }
    }

    public List<String> getAvailableDirections() {
        return new ArrayList<>(exits.values());
    }

    public boolean hasExit(String direction) {
        return getExit(direction) != null;
    }

    public static String getExitDirection() {
        List<String> directions = new ArrayList<>();

        if (exits.containsKey("north")) {
            directions.add("↑ North");
        }
        if (exits.containsKey("east")) {
            directions.add("→ East");
        }
        if (exits.containsKey("south")) {
            directions.add("↓ South");
        }
        if (exits.containsKey("west")) {
            directions.add("← West");
        }

        if (directions.isEmpty()) {
            return "Exits: None";
        }

        return "Exits: " + String.join("  ", directions);
    }

    public boolean isLocked() {
        return isLockedBy != null && !isLockedBy.isEmpty();
    }

    // Unlocks the room by setting said ID that is locking it to null

    public void unlock() {
        isLockedBy = null;
    }




    //Handling Items


    /* Adds item to the current room.
     * Really to meant to called when a player drops an item
     * or in the case an item is drop as a reward from puzzle or monster.
     * As well to initialize items that are already in rooms by default
     */
    public void addItemToRoom(Item item) {
        if (item != null) {
            items.add(item);
        }
    }

    /* Removes item from the current room.
     * Really to meant to called when a player pickups an item
    */
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

    public boolean hasItems() {
        return !items.isEmpty();
    }


    // Display items in current room
//    public void displayItems() {
//        if(!items.isEmpty() && items.size() > 1) {
//            System.out.println("\nItems in room:");
//            for (Item item : items) {
//                System.out.print("[" + item.getName() + "] ");
//            }
//            System.out.println();
//        }
//    }

    public String displayItems() {
        if (items != null && !items.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("\nItems in room:\n");
            for (Item item : items) {
                sb.append("[").append(item.getName()).append("]");
            }
            sb.append("\n");
            return sb.toString();
        }
        return "";
    }








    //Handling Puzzle in room
    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void removePuzzle() {
        this.puzzle = null;
    }

    public boolean hasPuzzle() {
        return puzzle != null;
    }

    //Handling Monster in Monster
    public void setMonster(Monster monster) {
        this.monster = monster;
    }
    public void setMonster(AmbushMonster ambushMonster) {
        this.ambushMonster = ambushMonster;
    }


    public Monster getMonster() {
        return monster;
    }

    public void removeMonster() {
        monster = null;
    }

    public boolean hasMonster() {
        return monster != null;
    }

    public String getMonstersList() {
        if (ambushMonster != null && ambushMonster.isAlive()) {
            return ambushMonster.getName() + " - " + ambushMonster.getDescription();
        }

        if (monster != null && monster.isAlive()) {
            return monster.getName() + " - " + monster.getDescription();
        }

        return "";
    }



    public String getFullRoomInfo() {
        StringBuilder sb = new StringBuilder();

        sb.append("[").append(roomName).append("]").append("\n");
        sb.append(roomDescription).append("\n");

        sb.append("\n").append(getExitDirection()).append("\n");

        if (hasItems()) {
            sb.append(displayItems());
        }

        String monsterInfo = getMonstersList();
        if (!monsterInfo.isEmpty()) {
            sb.append("Monster in the room!").append("\n").append(monsterInfo).append("\n");
        }

        if (hasPuzzle() && !getPuzzle().isSolved()) {
            sb.append("\nThere's a puzzle here to solve!");
        }

        return sb.toString();
    }

    public void visit() {
        visited = true;
    }

}
