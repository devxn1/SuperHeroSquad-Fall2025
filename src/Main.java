import java.util.*;
import java.io.*;

public class Main {
    private Player player;
    private Map<String, Room> world = new HashMap<>();
    private Map<String, Item> items = new HashMap<>();

    public static void main(String[] args) {
        Main main = new Main();

        Game.loadGame();
        main.start();
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("Superhero Squad Final Project Implementation");
        System.out.println("Commands: North/n, South/s, East/e, West/w,\nLook/Inspect, Take/Grab, Gather,\nCraft, Build, Use, Map/m, Journal/j,\nInventory/i, Sleep, Stats, Save/Load, Help/?, Quit");

        player = Game.player;
        //Game loop
        while (true) {
            //Player inputs
            System.out.println("Enter a direction or a command:");
            input = scanner.nextLine().trim().toUpperCase();
            //I find it easier to separate one words from the two words
            processCommand(input);


        }


    }
    public static void processCommand(String command) {
        Runnable action = Game.commandInputs.get(command.toLowerCase().trim());
        if (action != null) {
            action.run();
        } else {
            System.out.println("Unknown command: " + command);
        }
    }

   /* private void lookAround() {
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
*/
    }