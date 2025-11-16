import java.util.Scanner;
import java.util.*;

public class Main {
    private Player player;

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Superhero Squad Final Project Implementation");
        System.out.println("Commands: North/n, South/s, East/e, West/w,\nLook/Inspect, Take/Grab, Gather,\nCraft, Build, Use, Map/m, Journal/j,\nInventory/i, Sleep, Stats, Save/Load, Help/?, Quit");

        //Game loop
        while (true) {

            //Player inputs
            System.out.println("Enter a direction or a command:");
            input = scanner.nextLine().trim().toUpperCase();
            //I find it easier to separate one words from the two words
            switch (input) {
                case "QUIT" -> {
                    System.out.println("Quitting the game");
                    System.exit(0);
                }
                case "STATS" -> player.showStats();
                case "HELP" -> player.showHelp();
                //case "LOOK", "INSPECT" -> System.out.println(currentRoom.getRoomDescription());
                //case "INVENTORY", "I" -> displayInventory();
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

    private void commandUsage(String command, String object) {
        switch (command) {
            //case "USE" -> useItem(object);
            //case "NORTH", "N" -> player.move("NORTH");
            //case "SOUTH", "S" -> player.move("SOUTH");
            //case "EAST", "E" -> player.move("EAST");
            //case "WEST", "W" -> player.move("WEST");
            default -> System.out.println("Unknown command, type HELP for list of commands");
        }
    }

    /*
    private void useItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Type what item you want to use; (Use 'name-of-item')");
            return;
        }

        Item found = itemInInventory(itemName);
        if (found != null) {
            //Call the items abstract use method.
            found.use(player);

            if ("Consumable".equalsIgnoreCase(found.getType())) {
                player.itemInventory().remove(found);
                System.out.println(found.getName() + " consumed and removed from your inventory.");
            }
        } else {
            System.out.println("You do not have an item named '" + itemName + "' in your inventory!");
        }
    }

    private Item itemInInventory(String itemName) {
        if (itemName == null) return null;
        for (Item it : player.itemInventory()) {
            if (it.getName().equalsIgnoreCase(itemName)) return it;
        }
        return null;
    }

    private void displayInventory() {
        System.out.println("Current inventory:\n");
        if (player.itemInventory().isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }
        for (Item it : player.itemInventory()) {
            System.out.println("- " + it.getName() + " (" + it.getType() + ") : " + it.getDescription());
        }
    }

    public void addToInventory(Item item) {
        if (item != null) player.itemInventory().add(item);
    }
     */
}