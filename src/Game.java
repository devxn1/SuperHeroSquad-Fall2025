import java.sql.*;
import java.util.*;
import java.io.*;

public class Game {
    public static ArrayList<Room> RoomData;
    public static ArrayList<Item> ItemData;
    public static ArrayList<Puzzle> PuzzleData;
    public static ArrayList<Monster> MonsterData;
    public static HashMap<String, Runnable> commandInputs = new HashMap<>();
    public static Player player;

    public static void loadGame(){
        welcomeMessage();
        RoomData = ParseRoomdata();
        ItemData = ParseItemData();
        MonsterData = ParseMonsterData();
        PuzzleData = ParsePuzzleData();
        assignPuzzlesToRooms();

        assignMonstersToRooms();
        assignItemsToRooms();
        assignPuzzlesToRooms();

        player = new Player(
                "A1",         // starting room ID
                100,          // HP
                10,           // attackDMG
                5,            // defense
                5,            // evasion
                50,           // hunger
                50,           // thirst
                new ArrayList<Item>(),
                new ArrayList<Artifact>(),
                new ArrayList<Recipe>()// empty inventory at start
        );
        registerCommands();

    }

    public static void registerCommands(){
        NaviagationCommands();
        GameCommands();
        ItemCommands();
    }

    public static void NaviagationCommands(){
        commandInputs.put("north", () -> player.PlayerMoveDirection("north"));
        commandInputs.put("south", () -> player.PlayerMoveDirection("south"));
        commandInputs.put("east", () -> player.PlayerMoveDirection("east"));
        commandInputs.put("west", () -> player.PlayerMoveDirection("west"));
        commandInputs.put("n", () -> player.PlayerMoveDirection("north"));
        commandInputs.put("s", () -> player.PlayerMoveDirection("south"));
        commandInputs.put("e", () -> player.PlayerMoveDirection("east"));
        commandInputs.put("w", () -> player.PlayerMoveDirection("west"));
        commandInputs.put("move north", () -> player.PlayerMoveDirection("north"));
        commandInputs.put("move south", () -> player.PlayerMoveDirection("south"));
        commandInputs.put("move east", () -> player.PlayerMoveDirection("east"));
        commandInputs.put("move west", () -> player.PlayerMoveDirection("west"));
    }

    public static void GameCommands(){
        commandInputs.put("help", Game::help);
        commandInputs.put("?", Game::help);
        commandInputs.put("quit", Game::quitGame);
        commandInputs.put("inventory", () -> player.displayInventory());
        commandInputs.put("i", () -> player.displayInventory());
        commandInputs.put("stats", () -> player.displayStats());
        commandInputs.put("look", () -> lookAround());
        commandInputs.put("inspect", () -> lookAround());
        commandInputs.put("solve", Game::solvePuzzle);
        commandInputs.put("puzzle", Game::solvePuzzle);
    }

    public static void ItemCommands(){
        // These will be handled separately in processCommand since they need parameters
    }

    public static void lookAround() {
        Room currentRoom = null;
        for (Room r : RoomData) {
            if (r.getRoomID().equals(player.getCurrentRoom())) {
                currentRoom = r;
                break;
            }
        }

        if (currentRoom == null) {
            System.out.println("Error: current room not found!");
            return;
        }

        System.out.println(currentRoom.getFullRoomInfo());
    }

    /**
     * Helper method to get the player's current room
     */
    private static Room getCurrentRoom() {
        for (Room r : RoomData) {
            if (r.getRoomID().equals(player.getCurrentRoom())) {
                return r;
            }
        }
        return null;
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
        System.out.println("solve puzzle - Attempt to solve a puzzle in the current room");
        System.out.println("hint - Get a hint for the current puzzle");
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


                Room room = new Room(id,name, description,isVisited,directions,"");
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
                if (!trimmed.isEmpty()) {
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
                        items.add(new Artifact(id, name, desc, locs));
                        break;
                    }
                    case "material": {
                        List<String> locs = parseList(parts[4]);
                        items.add(new Material(id, name, desc,  locs));
                        break;
                    }
                    case "armor": {
                        List<String> locs = parseList(parts[4]);
                        int defensePoints = Integer.parseInt(parts[5].trim());
                        items.add(new Armor(id, name, desc, locs, defensePoints));
                        break;
                    }
                    case "consumable": {
                        List<String> locs = parseList(parts[4]);
                        int healingAmount = Integer.parseInt(parts[5].trim());
                        items.add(new Consumable(id, name, desc, locs, healingAmount));
                        break;
                    }
                    case "weapon": {
                        List<String> locs = parseList(parts[4]);
                        int damage = Integer.parseInt(parts[5].trim());
                        int dot = Integer.parseInt(parts[6].trim());
                        items.add(new Weapon(id, name, desc, locs, damage, dot));
                        break;
                    }
                    case "craftable": {
                        List<String> locs = parseList(parts[4]);
                        int damage = Integer.parseInt(parts[5].trim());
                        int dot = Integer.parseInt(parts[6].trim());
                        List<String> materialIDs = parseList(parts[7]);
                        items.add(new CraftableItem(id, name, desc, locs, damage, dot, materialIDs));
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
                for (String dropID : drops) {
                    for (Item item : ItemData) {
                        if (item.getID().equalsIgnoreCase(dropID)) {
                            tempItems.add(item);
                            break; // Found the item, no need to keep searching
                        }
                    }
                }


                // tempItems.add(drops);

                Monster monster = new Monster(id, name, description, location, health, damage, tempItems);
                monsters.add(monster);

                for(Monster monster1 : monsters) {

                }
            }

        } catch (Exception e) {
            System.err.println("Failed to load MonsterData.txt: " + e.getMessage());
            e.printStackTrace();
        }

        return monsters;
    }
    public static void assignMonstersToRooms() {
        for (Monster monster : MonsterData) {
            String roomID = monster.getIDLocation();

            // Find the room with matching ID
            for (Room room : RoomData) {
                if (room.getRoomID().equalsIgnoreCase(roomID)) {
                    room.setMonster(monster);
                    break;
                }
            }
        }
        System.out.println("Assigned " + MonsterData.size() + " monsters to rooms.");
    }

    public static void assignItemsToRooms() {
        int itemsAssigned = 0;

        for (Item item : ItemData) {
            List<String> roomIDs = item.getRoomID();

            // Skip items with no room assignments or "Crafted"/"Anywhere" locations
            if (roomIDs == null || roomIDs.isEmpty()) {
                continue;
            }

            for (String roomID : roomIDs) {
                // Skip special locations
                if (roomID.equalsIgnoreCase("Crafted") || roomID.equalsIgnoreCase("Anywhere")) {
                    continue;
                }

                // Find the room and add the item
                for (Room room : RoomData) {
                    if (room.getRoomID().equalsIgnoreCase(roomID)) {
                        room.addItemToRoom(item);
                        itemsAssigned++;
                        break;
                    }
                }
            }
        }
        System.out.println("Assigned " + itemsAssigned + " item instances to rooms.");
    }

    /**
     * Parses puzzle data from PuzzleData.txt
     * Format: puzzleID/puzzleName/roomID/rewardType/puzzleDescription/puzzleAttempts/rewardID
     */
    public static ArrayList<Puzzle> ParsePuzzleData() {
        ArrayList<Puzzle> puzzles = new ArrayList<>();

        try (InputStream inputStream = Game.class.getClassLoader().getResourceAsStream("data/PuzzleData.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("/", -1);
                if (parts.length < 7) {
                    System.err.println("Malformed puzzle line: " + line);
                    continue;
                }

                String puzzleID = parts[0].trim();
                String puzzleName = parts[1].trim();
                String roomID = parts[2].trim();
                String rewardType = parts[3].trim();
                String puzzleDescription = parts[4].trim();
                int puzzleAttempts = Integer.parseInt(parts[5].trim());
                String rewardID = parts[6].trim();

                Puzzle puzzle = new Puzzle(puzzleID, puzzleName, roomID,
                        puzzleDescription, puzzleAttempts,
                        rewardType, rewardID);
                puzzles.add(puzzle);
            }

        } catch (Exception e) {
            System.err.println("Failed to load PuzzleData.txt: " + e.getMessage());
            e.printStackTrace();
        }

        return puzzles;
    }

    /**
     * Assigns puzzles to their corresponding rooms after both have been parsed
     */
    public static void assignPuzzlesToRooms() {
        registerCustomPuzzles();
        for (Puzzle puzzle : PuzzleData) {
            String roomID = puzzle.getRoomID();

            // Find the room with matching ID
            for (Room room : RoomData) {
                if (room.getRoomID().equalsIgnoreCase(roomID)) {
                    room.setPuzzle(puzzle);
                    break;
                }
            }
        }
        System.out.println("Assigned " + PuzzleData.size() + " puzzles to rooms.");
    }

    public static void registerCustomPuzzles() {
        for (Room room : RoomData) {
            Puzzle existing = room.getPuzzle();

            if (existing == null) continue;

            String pid = existing.getPuzzleID();

            if (pid.equalsIgnoreCase("P03")) {
                room.setPuzzle(new TimingWithTides(player));
            }
            else if (pid.equalsIgnoreCase("P04")) {
                room.setPuzzle(new CrystalResonance());
            }
        }
    }

    // Puzzle
    public static void solvePuzzle() {
        Room currentRoom = getCurrentRoom();

        if (currentRoom == null) {
            System.out.println("Error: Can't find current room!");
            return;
        }

        if (!currentRoom.hasPuzzle()) {
            System.out.println("There's no puzzle in this room.");
            return;
        }

        Puzzle puzzle = currentRoom.getPuzzle();

        if (puzzle.isSolved()) {
            System.out.println("This puzzle has already been solved!");
            return;
        }

        puzzleLoop(puzzle, currentRoom);
    }

    /**
     * Interactive puzzle solving loop
     */
    private static void puzzleLoop(Puzzle puzzle, Room currentRoom) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║  " + puzzle.getPuzzleName());
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.println(puzzle.getPuzzleDescription());
        System.out.println("\nType: " + puzzle.getRewardType());
        System.out.println("Attempts remaining: " + puzzle.getRemainingAttempts());
        System.out.println("\nCommands: solve <answer> | hint | quit");

        while (!puzzle.isSolved() && puzzle.getRemainingAttempts() > 0) {
            System.out.print("\n > ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            switch (command) {
                case "solve":
                    if (parts.length < 2) {
                        System.out.println("Usage: solve <your answer>");
                        break;
                    }

                    // Attempt to solve the puzzle
                    if (puzzle.attemptSolve(parts[1])) {
                        System.out.println("\nCORRECT! Puzzle solved!");
                        puzzle.markSolved();
                        givePuzzleReward(puzzle, currentRoom);
                        return;
                    } else {
                        puzzle.decrementAttempts();
                        System.out.println("\n✗ Incorrect!");
                        if (puzzle.getRemainingAttempts() > 0) {
                            System.out.println("Attempts remaining: " +
                                    puzzle.getRemainingAttempts());
                        }
                    }
                    break;

                case "hint":
                    String hint = puzzle.getHint();
                    System.out.println("\nHint: " + hint);
                    if (puzzle.isSolved()) {
                        System.out.println("\n✓ Auto-solved after 3 hints!");
                        givePuzzleReward(puzzle, currentRoom);
                        return;
                    }
                    break;

                case "info":
                    showPuzzleInfo(puzzle);
                    break;

                case "quit":
                case "exit":
                    System.out.println("Exiting puzzle...");
                    return;

                default:
                    System.out.println("Unknown command. Try: solve <answer>, hint, info, quit");
            }
        }

        if (puzzle.getRemainingAttempts() <= 0) {
            System.out.println("\nOut of attempts! Puzzle failed.");
            System.out.println("You can try again later.");
        }
    }

    /**
     * Display detailed puzzle information
     */
    private static void showPuzzleInfo(Puzzle puzzle) {
        System.out.println("\n=== Puzzle Information ===");
        System.out.println("Name: " + puzzle.getPuzzleName());
        System.out.println("Type: " + puzzle.getRewardType());
        System.out.println("Description: " + puzzle.getPuzzleDescription());
        System.out.println("Attempts: " + puzzle.getRemainingAttempts() + "/" +
                puzzle.getPuzzleAttempts());
        System.out.println("Status: " + (puzzle.isSolved() ? "SOLVED" : "UNSOLVED"));
    }


    private static void givePuzzleReward(Puzzle puzzle, Room currentRoom) {
        String rewardType = puzzle.getRewardType();
        String rewardID = puzzle.getRewardID();

        System.out.println("\n Reward: " + rewardID);

        // Check if reward is a room unlock
        boolean isRoomID = false;
        for (Room room : RoomData) {
            if (room.getRoomID().equalsIgnoreCase(rewardID)) {
                isRoomID = true;
                System.out.println("✓ New area unlocked: " + room.getRoomName());
                break;
            }
        }

        // Check if reward is an item
        if (!isRoomID) {
            for (Item item : ItemData) {
                if (item.getID().equalsIgnoreCase(rewardID)) {
                    player.getPlayerInventory().add(item);
                    System.out.println("✓ Received: " + item.getName());
                    System.out.println("   " + item.getDescription());
                    break;
                }
            }
        }

        currentRoom.removePuzzle();
    }


    public static void getPuzzleHint() {
        Room currentRoom = getCurrentRoom();

        if (currentRoom == null || !currentRoom.hasPuzzle()) {
            System.out.println("There's no puzzle here!");
            return;
        }

        Puzzle puzzle = currentRoom.getPuzzle();

        if (puzzle.isSolved()) {
            System.out.println("This puzzle is already solved!");
            return;
        }

        String hint = puzzle.getHint();
        System.out.println("\n💡 Hint: " + hint);

        if (puzzle.isSolved()) {
            System.out.println("\n✓ Auto-solved after 3 hints!");
            givePuzzleReward(puzzle, currentRoom);
        }
    }


    //Trying ending

    /* Goes through every monster in the list,
     * which is all monster in the game to
     * check if they're alive
    */
    public static boolean allMonstersDefeated() {

        for (Monster m : MonsterData) {

            if (m.isAlive()) {
             return false;
            }
        }
        // if never found a living monster, they’re all defeated
        return true;
    }

    public static void checkForGameCompletion() {
        if (allMonstersDefeated()) {
            System.out.println("\nYou have defeated all monsters!");
            System.out.println("Congratulations, you beat the game!!!");
            quitGame();
        }
    }



}