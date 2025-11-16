import java.sql.*;
import java.util.*;
import java.io.*;

public class Game {
    public static ArrayList<Room> RoomData;
    public static ArrayList<Item> ItemData;
    public static ArrayList<Puzzle> PuzzleData;
    public static ArrayList<Monster> MonsterData;
    public static HashMap<String, Runnable> commandInputs = new HashMap<>();


public static void loadGame(){
    welcomeMessage();
    RoomData = ParseRoomdata();
    ItemData = ParseItemData();
    MonsterData = ParseMonsterData();
    //PuzzleData = ParsePuzzleData();
    registerCommands();

}

public static void registerCommands(){

}
public static void NaviagationCommands(){
    commandInputs.put("north", () -> Player.MoveDirection("north"));
    commandInputs.put("south", () -> Player.MoveDirection("south"));
    commandInputs.put("east", () -> Player.MoveDirection("east"));
    commandInputs.put("west", () -> Player.MoveDirection("west"));
    commandInputs.put("move north", () -> Player.MoveDirection("north"));
    commandInputs.put("move south", () -> Player.MoveDirection("south"));
    commandInputs.put("move east", () -> Player.MoveDirection("east"));
    commandInputs.put("move west", () -> Player.MoveDirection("west"));
}
public static void welcomeMessage(){
    System.out.println("Welcome to the Adventure Game!");
    System.out.println("Type 'help' to see a list of commands.");
}
    public static void help() {
        System.out.println("Available commands:");
        System.out.println(" <direction> - Move in the specified direction (north, south, east, west)");
        System.out.println("pickup <item> - Pick up an item");
        System.out.println("drop <item> - Drop an item from your inventory");
        System.out.println("inspect <item> - Inspect an item in your inventory");
        System.out.println("inventory - View your current inventory");
        System.out.println("help - Show this help message");
        System.out.println("lost - Show your current location and possible directions");
        System.out.println("quit - Exit the game");

}
public static void quitGame(){
    System.out.println("Thank you for playing! Goodbye.");
    System.exit(0);
}
    public static ArrayList<Room> ParseRoomdata() {
        ArrayList<Room> rooms = new ArrayList<>();

        try (InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("data/RoomData.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("/", -1);

                if (parts.length < 7) {
                    System.err.println("Malformed line: " + line);
                    continue;
                }

                String id = parts[0];
                String name = parts[1];
                String description = parts[2];
                boolean isVisited = Boolean.parseBoolean(parts[3]);

                List<String> directions = parseList(parts[4]);
                List<String> items = parseList(parts[5]);
                List<String> monsters = parseList(parts[6]);

                Room room = new Room(id, name, description, isVisited, directions, items, monsters);
                rooms.add(room);
            }

        } catch (Exception e) {
            System.err.println("Error loading RoomData.txt: " + e.getMessage());
            e.printStackTrace();
        }

        return rooms;
    }

    private static List<String> parseList(String raw) {
        List<String> list = new ArrayList<>();
        if (raw != null && !raw.trim().isEmpty()) {
            for (String part : raw.split(",")) {
                String trimmed = part.trim();
                if (!trimmed.isEmpty() && !trimmed.equals("0")) {
                    list.add(trimmed);
                }
            }
        }
        return list;
    }

    public static ArrayList<Item> ParseItemData() {
        ArrayList<Item> items = new ArrayList<>();

        try (InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("data/ItemData.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("/", -1);
                String category = parts[0].trim();
                String id = parts[1].trim();
                String name = parts[2].trim();
                String desc = parts[3].trim();

                switch (category.toLowerCase()) {
                    case "artifact": {
                        List<String> locs = parseList(parts[4]);
                        //items.add(new Artifact(id, name, desc, locs));
                        break;
                    }
                    case "material": {
                        List<String> locs = parseList(parts[4]);
                        //items.add(new Material(id, name, desc, locs));
                        break;
                    }
                    case "armor": {
                        List<String> locs = parseList(parts[4]);
                        int defensePoints = Integer.parseInt(parts[5].trim());
                        //items.add(new Armor(id, name, desc, locs, defensePoints));
                        break;
                    }
                    case "consumable": {
                        List<String> locs = parseList(parts[4]);
                        int healingAmount = Integer.parseInt(parts[5].trim());
                        //items.add(new Consumable(id, name, desc, locs, healingAmount));
                        break;
                    }
                    case "weapon": {
                        List<String> locs = parseList(parts[4]);
                        int damage = Integer.parseInt(parts[5].trim());
                        int dot = Integer.parseInt(parts[6].trim());
                        //items.add(new Weapon(id, name, desc, locs, damage, dot));
                        break;
                    }
                    default:
                        System.err.println("Unknown item category: " + category + " in line: " + line);
                }
            }

        } catch (Exception e) {
            System.err.println("Failed to parse ItemData.txt: " + e.getMessage());
            e.printStackTrace();
        }

        return items;
    }
    public static ArrayList<Monster> ParseMonsterData() {
        ArrayList<Monster> monsters = new ArrayList<>();

        try (InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("data/MonsterData.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("/", -1);
                if (parts.length < 8) {
                    System.err.println("Malformed monster line: " + line);
                    continue;
                }

                String category = parts[0].trim();
                String id = parts[1].trim();
                String name = parts[2].trim();
                String description = parts[3].trim();
                String location = parts[4].trim();
                int health = Integer.parseInt(parts[5].trim());
                int damage = Integer.parseInt(parts[6].trim());
                List<String> drops = parseList(parts[7]);
                ArrayList<Item> tempItems = new ArrayList<>();
                for(int i=0; i<ItemData.size();i++){
                    if(ItemData.get(i).getID().equalsIgnoreCase(String.valueOf(drops))){
                        tempItems.add(ItemData.get(i));
                    }
                }


               // tempItems.add(drops);

                Monster monster = new Monster(id, name, description, location, health, damage, tempItems);
                monsters.add(monster);
            }

        } catch (Exception e) {
            System.err.println("Failed to load MonsterData.txt: " + e.getMessage());
            e.printStackTrace();
        }

        return monsters;
    }

}



