import java.util.*;
import java.io.*;

public class Main {
    private Player player;
    private Map<String, Room> world = new HashMap<>();
    private Map<String, Item> items = new HashMap<>();

    public static void main(String[] args) {
        Main main = new Main();
        main.loadItems("data/ItemData.txt");
        Game.loadGame();
        main.start();
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

    private void loadItems(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/", -1);

                String type = parts[0];
                String id = parts[1];
                String name = parts[2];
                String desc = parts[3];

                Item item;

                switch (type) {
                    case "Weapon" -> {
                        List<String> locs = parseList(parts[4]);
                        int damage = Integer.parseInt(parts[5]);
                        int element = Integer.parseInt(parts[6]);
                        item = new Weapon(id, name, desc, locs, damage, element);
                    }
                    case "Armor" -> {
                        List<String> locs = parseList(parts[4]);
                        int defense = Integer.parseInt(parts[5]);
                        item = new Armor(id, name, desc, locs, defense);
                    }
                    case "Consumable" -> {
                        List<String> locs = parseList(parts[4]);
                        int heal = Integer.parseInt(parts[5]);
                        item = new Consumable(id, name, desc, locs, heal);
                    }
                    default -> item = new Item(id, name, desc,null );
                }

                items.put(id, item);
            }
        } catch (IOException e) {
            System.out.println("Error loading items: " + e.getMessage());
        }
    }



    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Superhero Squad Final Project Implementation");
        System.out.println("Commands: North/n, South/s, East/e, West/w,\nLook/Inspect, Take/Grab, Gather,\nCraft, Build, Use, Map/m, Journal/j,\nInventory/i, Sleep, Stats, Save/Load, Help/?, Quit");

        player = new Player("A1", 100, 5, 5, 5, 100, 100, new ArrayList<>(), world);

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
                case "NORTH", "N" -> player.PlayerMoveDirection("north");
                case "SOUTH", "S" -> player.PlayerMoveDirection("south");
                case "EAST", "E" -> player.PlayerMoveDirection("east");
                case "WEST", "W" -> player.PlayerMoveDirection("west");
                case "INVENTORY", "I" -> player.displayInventory();
                default -> {
                    /*if more than one word is typed in, this runs. Separates first word (command) from
                     next word (usually an object the user is trying to use) */
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

    private void commandUsage(String command, String object) {
        switch (command) {
            case "USE" -> useItem(object);
            case "TAKE", "GRAB" -> takeItem(object);
            /*
            NOT IMPLEMENTED:
            case "GATHER" -> {}
            case "CRAFT" -> {}
            case "BUILD" -> {}
            case "MAP", "M" -> {}
            case "JOURNAL", "J" -> {}
            case "SLEEP" -> {}
            case "SAVE" -> {}
            case "LOAD" -> {}
            */
            default -> System.out.println("Unknown command, type HELP for list of commands");
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

    private void takeItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Specify an item name.");
            return;
        }

        Room currentRoom = world.get(player.getCurrentRoom());
        if (currentRoom == null) return;

        List<Item> items = currentRoom.getItems();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName.trim())) {
                player.getPlayerInventory().add(item);
                currentRoom.removeItemFromRoom(item.getName());
                System.out.println("You have taken: " + item.getName());
                return;
            }
        }
        System.out.println("No such item found: " + itemName);
    }

    private void useItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Type Use (item-name). Specify an item name.");
            return;
        }

        for (Item item : player.getPlayerInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.use(player);
                if (item instanceof Consumable) {
                    player.getPlayerInventory().remove(item);
                }
                return;
            }
        }
        System.out.println("You do not have an item named: " + itemName);
    }
}