import java.sql.*;
import java.util.Scanner;

public class Game {
    //Carlos Matos, Keyvaun herring, Clark Newell, Devin Gomez
    private Player player;

    //Instantiate, how to add map??
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Superhero Squad Final Project Implementation");
        System.out.println("Commands: North/n, South/s, East/e, West/w,\nLook/Inspect, Take/Grab, Gather,\nCraft, Build, Use, Map/m, Journal/j,\nInventory/i, Sleep, Save/Load, Help/?");
        //startingRoom.visit();

        //Game loop
        while (true) {

            //Player inputs
            System.out.println("Enter a direction or a command:");
            input = scanner.nextLine().trim().toUpperCase();

            //These can be added to lower loop, I find it easier to separate one words from the two words
            if (input.equals("QUIT")) {
                System.out.println("Quitting Island Survival Game");
                break;
            }
            else if (input.equals("STATS")) {
                player.displayStats();
                continue;
            }
            else if (input.equals("HELP")) {
                player.showHelp();
                continue;
            }

            //if more than one word is typed in, this runs. Separates first word (command) from
            // next word (usually an object the user is trying to use)
            String command = input;
            String object = null;
            if (input.contains(" ")) {
                int index = input.indexOf(' ');
                command = input.substring(0, index).trim();
                object = input.substring(index + 1).trim();
            }


        }
    }
}