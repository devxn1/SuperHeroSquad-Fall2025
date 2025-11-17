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

        System.out.println("\n=== Superhero Squad Final Project Implementation ===");
        System.out.println("Commands: North/n, South/s, East/e, West/w,");
        System.out.println("Look/Inspect, Take/Grab, Use, Equip, Unequip,");
        System.out.println("Inventory/i, Stats, Help/?, Quit");
        System.out.println("================================================\n");

        player =Game.player;

        // Show starting room
        Game.lookAround();

        //Game loop
        while (true) {
            //Player inputs
            System.out.print("\n> ");
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            //Process the command
            processCommand(input);
        }
    }
    public static void processCommand(String command) {
        String lowerCommand = command.toLowerCase().trim();

        // Check for single-word commands first
        Runnable action = Game.commandInputs.get(lowerCommand);
        if (action != null) {
            action.run();
            return;
        }

        // Handle multi-word commands
        String[] parts = lowerCommand.split("\\s+", 2);
        String verb = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        switch (verb) {
            case "take":
                takeItem(argument);
                break;
            case "attack":
                Monster m=null;
                Room tempRoom=null;
               for(int i=0; i<Game.RoomData.size(); i++){
                   if(Objects.equals(Game.RoomData.get(i).getRoomID(), Game.player.getCurrentRoom())){
                       if(Game.RoomData.get(i).hasMonster()) {
                           m = Game.RoomData.get(i).getMonster();
                           tempRoom = Game.RoomData.get(i);
                       }
                   }
                   else{
                       //System.out.println("No monsters");
                   }
               }
                Game.player.Combat(m,tempRoom);
                break;
            case "pickup":
                takeItem(argument);
                break;
            case "grab":
                takeItem(argument);
                break;
            case "drop":
                dropItem(argument);
                break;
            case "use":
                useItem(argument);
                break;
            case "equip":
                equipItem(argument);
                break;
            case "unequip":
                unequipItem(argument);
                break;
            case "inspect":
                if (argument.isEmpty()) {
                    Game.lookAround();
                } else {
                    inspectItem(argument);
                }
                break;
            case "solve":
                if (argument.equalsIgnoreCase("puzzle") || argument.isEmpty()) {
                    Game.solvePuzzle();
                } else {
                    System.out.println("Usage: solve puzzle");
                }
                break;
            case "puzzle":
                Game.solvePuzzle();
                break;
            case "hint":
                Game.getPuzzleHint();
                break;
            case "save":
                Game.player.savePlayer();
                break;
            case "load":
                Game.player.loadPlayer();
                break;
            default:
                System.out.println("Unknown command: " + command);
                System.out.println("Type 'help' for a list of commands.");
        }
    }

    private static void takeItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Specify an item name.");
            return;
        }

        Room currentRoom = null;
        for (Room r : Game.RoomData) {
            if (r.getRoomID().equals(Game.player.getCurrentRoom())) {
                currentRoom = r;
                break;
            }
        }

        if (currentRoom == null) return;

        Monster m=currentRoom.getMonster();
        boolean monsterAlive = (m != null && m.isAlive()); //True if it not null or alive
        //false when it null or dead

        List<Item> items = currentRoom.getItems();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName.trim())) {
                if (!monsterAlive) {
                    Game.player.getPlayerInventory().add(item);
                    currentRoom.removeItemFromRoom(item.getName());
                    System.out.println("You have taken: " + item.getName());
                    return;
                }
                else{
                    System.out.println("You cannot take that — the monster is guarding it!");
                    return;
                }
            }
        }
        System.out.println("No such item found: " + itemName);
    }

    private static void dropItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Specify an item name.");
            return;
        }

        for (Item item : Game.player.getPlayerInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                Game.player.getPlayerInventory().remove(item);

                // Add to current room
                Room currentRoom = null;
                for (Room r : Game.RoomData) {
                    if (r.getRoomID().equals(Game.player.getCurrentRoom())) {
                        currentRoom = r;
                        break;
                    }
                }
                if (currentRoom != null) {
                    currentRoom.addItemToRoom(item);
                }

                System.out.println("You dropped: " + item.getName());
                return;
            }
        }
        System.out.println("You don't have that item.");
    }

    private static void useItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Type 'use <item-name>'. Specify an item name.");
            return;
        }

        for (Item item : Game.player.getPlayerInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.use(Game.player);
                if (item instanceof Consumable) {
                    Game.player.getPlayerInventory().remove(item);
                }
                return;
            }
        }
        System.out.println("You do not have an item named: " + itemName);
    }

    private static void equipItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Specify an item name to equip.");
            return;
        }

        if (itemName.equalsIgnoreCase("weapon")) {
            System.out.println("Specify the weapon name, not just 'weapon'");
            return;
        }

        Game.player.equipWeapon(itemName);
    }

    private static void unequipItem(String itemName) {
        Game.player.unequipWeapon();
    }

    private static void inspectItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Specify an item name.");
            return;
        }

        for (Item item : Game.player.getPlayerInventory()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.println(item.toString());
                return;
            }
        }
        System.out.println("You don't have that item.");
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