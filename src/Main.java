/*
import java.util.*;
import java.io.*;

public class Main {
    private Player player;
    private Map<String, Room> world = new HashMap<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.loadRooms("RoomData.txt");
        main.start();
    }

    private void loadRooms(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                //ID/Name/Description/Locked/North,South,East,West/Items/Puzzles
                String[] parts = line.split("/", -1);
                String id = parts[0];
                String name = parts[1];
                String description = parts[2];
                boolean locked = Boolean.parseBoolean(parts[3]);

                // Exits
                String[] exitParts = parts[4].split(",", -1);
                String north = exitParts[0].equals("0") ? null : exitParts[0];
                String south = exitParts[1].equals("0") ? null : exitParts[1];
                String east = exitParts[2].equals("0") ? null : exitParts[2];
                String west = exitParts[3].equals("0") ? null : exitParts[3];

                //Items
                List<Item> items = new ArrayList<>();
                if (parts.length > 5 && !parts[5].isBlank()) {
                    String[] itemIds = parts[5].split(",");
                    for (String itemId : itemIds) {
                        items.add(new Item(itemId));
                    }
                }

                //Puzzles
                List<String> puzzles = new ArrayList<>();
                if (parts.length > 6 && !parts[6].isBlank()) {
                    puzzles.addAll(Arrays.asList(parts[6].split(",")));
                }

                Room room = new Room(id, name, description, locked, north, south, east, west, items, puzzles);
                world.put(id, room);
            }
        } catch (IOException e) {
            System.out.println("Error loading rooms: " + e.getMessage());
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Superhero Squad Final Project Implementation");
        System.out.println("Commands: North/n, South/s, East/e, West/w,\nLook/Inspect, Take/Grab, Gather,\nCraft, Build, Use, Map/m, Journal/j,\nInventory/i, Sleep, Stats, Save/Load, Help/?, Quit");

        List<Item> playerInventory = new ArrayList<>();
        player = new Player("A1", 100, 5, 5, 5, 100, 100, playerInventory);

        //Game loop
        while (true) {
            //Player inputs
            System.out.println("Enter a direction or a command:");
            input = scanner.nextLine().trim().toUpperCase();
            //I find it easier to separate one words from the two words
            switch (input) {
                case "QUIT" -> {
                    System.out.println("Quitting Survival Island Game");
                    System.exit(0);
                }
                case "STATS" -> player.displayStats();
                case "HELP", "?" -> player.showHelp();
                case "LOOK", "INSPECT" -> lookAround();
                //case "NORTH", "N", "SOUTH", "S", "EAST", "E", "WEST", "W" -> player.playerMoveDirection(input);
                default -> {
                    //if more than one word is typed in, this runs. Separates first word (command) from
                    // next word (usually an object the user is trying to use)
                    String command = input;
                    String object = null;
                    if (input.contains(" ")) {
                        int index = input.indexOf(' ');
                        command = input.substring(0, index).trim();
                        object = input.substring(index + 1).trim();
                    }
                    commandUsage(command, object);
                }
            }
        }
    }

    private void lookAround() {
        Room currentRoom = world.get(player.getCurrentRoom());
        if (currentRoom == null) {
            System.out.println("Error: current room not found!");
            return;
        }
        System.out.println(currentRoom.getRoomDescription());
        if (!currentRoom.getItems().isEmpty()) {
            System.out.println("Items in this room:");
            for (Item it : currentRoom.getItems()) {
                System.out.println("- " + it.getName());
            }
        }
        //shows exits, removable
        System.out.println(currentRoom.getExitDirection());
    }

    private void commandUsage(String command, String object) {
        switch (command) {
            case "USE" -> useItem(object);
            case "TAKE", "GRAB" -> takeItem(object);
            default -> System.out.println("Unknown command, type HELP for list of commands");
        }
    }

    private void takeItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Specify an item name.");
            return;
        }

        Room currentRoom = world.get(player.getCurrentRoom());
        if (currentRoom == null) return;

        List<Item> items = currentRoom.getItems();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                player.itemInventory().add(item);
                currentRoom.removeItemFromRoom(item.getName());
                System.out.println("You have taken: " + item.getName());
                return;
            }
        }
        System.out.println("No such item found: " + itemName);
    }

    private void useItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Use what? Specify an item name.");
            return;
        }

        for (Item item : player.itemInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.use(player);
                if (item instanceof Consumable) {
                    player.itemInventory().remove(item);
                }
                return;
            }
        }
        System.out.println("You do not have an item named: " + itemName);
    }
}
*/
