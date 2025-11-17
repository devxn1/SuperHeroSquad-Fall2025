/**Class: Inventory
 * @author Carlos Matos
 * @version 1.0
 * Course:  ITEC3860 Fall 2025
 * Written: November 11, 2025
 * Purpose:To Track Player in the text based adventure Game and all their items they will interact with.
 */
import java.io.FileNotFoundException;
import java.util.*;
import java.io.PrintWriter;
import java.io.File;

public class Player extends Character {
    private String CurrentRoom;
    private  int defense;
    private  int evasion;
    private int hunger;
    private int thrist;
    private boolean DayorNight;
    private  int hp;
    private Weapon equippedWeapon;
    private Armor equippedArmor;
    private ArrayList<Item> PlayerInventory;
    private ArrayList<Artifact> ArtifactInventory;
    private  ArrayList<Recipe> Journal;
    private Map<String, Room> world;


    public Player(String currentRoom,
                  int HP,
                  int attackDMG,
                  int defense,
                  int evasion,
                  int hunger,
                  int thrist,
                  ArrayList<Item> PlayerInventory,
                  ArrayList<Artifact> ArtifactInventory,
                  ArrayList<Recipe> Journal) {
        super(HP,attackDMG);
        CurrentRoom=currentRoom;
        this.HP=HP;
        this.attackDMG=attackDMG;
        this.defense=defense;
        this.evasion=evasion;
        this.hunger=hunger;
        this.thrist=thrist;
        this.DayorNight=true;
        this.equippedWeapon=null;
        this.equippedArmor=null;
        this.PlayerInventory=PlayerInventory;
        this.ArtifactInventory=new ArrayList<>();
        this.Journal=new ArrayList<>();

    }


    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getEvasion() {
        return evasion;
    }

    public void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getThrist() {
        return thrist;
    }

    public void setThrist(int thrist) {
        this.thrist = thrist;
    }

    public boolean isDayorNight() {
        return DayorNight;
    }

    public void setDayorNight(boolean dayorNight) {
        DayorNight = dayorNight;
    }

    public String getCurrentRoom() {
        return CurrentRoom;
    }

    public void setCurrentRoom(String currentRoom) {
        CurrentRoom = currentRoom;
    }


    public ArrayList<Item> getPlayerInventory() {
        return PlayerInventory;
    }

    public void setPlayerInventory(ArrayList<Item> playerInventory) {
        PlayerInventory = playerInventory;
    }
    public ArrayList<Recipe> getRecipeBook(){
        return this.Journal;
    }
    public void viewRecipeBook(){
        if(Journal.isEmpty()){
            System.out.println("Your Recipe Book is empty");
        }
        else{
            System.out.println("\t Recipe Book");
            for(Recipe R: Journal){
                System.out.println(R.getName());
            }
            System.out.println();
        }
    }


    public void displayInventory() {
        if (PlayerInventory.isEmpty()) {
            System.out.println("You didn't pickup any items yet");
        } else {
            System.out.println("\t Inventory");
            for (Item items : PlayerInventory) {
                System.out.print(items.getName());
            }
            System.out.println();
        }
    }

    public void PlayerMoveDirection(String input) {
        String direction;

        switch (input.toLowerCase()) {
            case "1":
            case "north":
                direction = "north";
                break;
            case "2":
            case "east":
                direction = "east";
                break;
            case "3":
            case "south":
                direction = "south";
                break;
            case "4":
            case "west":
                direction = "west";
                break;
            default:
                System.out.println("Invalid direction. Use 1-4 or north/south/east/west.");
                return;
        }

        // 1. Find current room
        Room currentRoom = null;
        for (Room r : Game.RoomData) {
            if (r.getRoomID().equals(this.getCurrentRoom())) {
                currentRoom = r;
                break;
            }
        }

        if (currentRoom == null) {
            System.out.println("ERROR: Player is in an invalid room.");
            return;
        }

        // 2. Get destination
        String nextRoomID = currentRoom.getExit(direction);

        if (nextRoomID == null || nextRoomID.isEmpty() || nextRoomID.equals("0")) {
            System.out.println("You cannot go " + direction + " from here.");
            return;
        }

        // 3. Move player
        this.setCurrentRoom(nextRoomID);

        // 4. Load next room
        Room nextRoom = null;
        for (Room r : Game.RoomData) {
            if (r.getRoomID().equals(nextRoomID)) {
                nextRoom = r;
                break;
            }
        }

        if (nextRoom == null) {
            System.out.println("ERROR: Destination room not found.");
            return;
        }

        // 5. Show room info
        System.out.println("\nYou move " + direction + "...");
        System.out.println(nextRoom.getFullRoomInfo());

        nextRoom.visit();

        if (nextRoom.hasMonster() && nextRoom.getMonster().isAlive()) {
            System.out.println("\n⚠ A monster is here: " + nextRoom.getMonster().getName());
        }
    }

    //Display ALl their Stats
    void displayStats() {
        System.out.println("HP: " + getHP()+"/100");
        System.out.println("AtkDamage: "+getAttackDMG());
        System.out.println("Defense: " + getDefense());
        System.out.println("Evasion: " + getEvasion());
        System.out.println("Thrist: " + getThrist()+"/100");
        System.out.println("Hunger: " + getHunger()+"/100");
        if(equippedWeapon != null){
            System.out.println(getEquippedWeapon());
        }
        if(equippedArmor != null){
            System.out.println(getEquippedArmor());
        }
    }

    public void showHelp() {
        System.out.println("Commands: North/n, South/s, East/e, West/w,");
        System.out.println("Look/Inspect, Take/Grab, Gather, Craft,");
        System.out.println("Build, Use, Map/m, Journal/j,");
        System.out.println("Inventory/i, Sleep, Save/Load, Help/?");
    }


    void savePlayer() {
        PrintWriter fileoutput;
        while(true){
            try{
                fileoutput=new PrintWriter("User/player.txt");
                //Going to print Room,HP,DMG,Defense,Evasion,Hunger,Thirst,(True or false) DayorNight
                //Into player.txt file
                fileoutput.println(getCurrentRoom()+"/"+getHP()+"/"+getAttackDMG()+"/"+getDefense());
                fileoutput.print("/"+getEvasion()+"/"+getHunger()+"/"+getThrist()+"/"+isDayorNight()+"/");
                StringBuilder temp= new StringBuilder();
                for(Item T:getPlayerInventory()){
                    temp.append(T.getName());
                }
                fileoutput.print(temp);
                fileoutput.close();//<Remember to close to save buffer into file

                //Will Add inventory later, it a pain to deal with SORRY!!!
            } catch (FileNotFoundException e) {
                System.out.println("player.txt is not found, please put back text file.");
                System.exit(0);
            }

        }

    }
    //Thank you Devin, You're a Hero.
    public void equipWeapon(String itemName) {
        Item item = findItemFromInventory(itemName);

        if (item == null) {
            System.out.println("You don't have that item in your inventory");
            return;
        }

        if (!(item instanceof Weapon)) {
            System.out.println("That item cannot be equipped as a weapon");
            return;
        }

        Weapon weapon = (Weapon) item;

        if (equippedWeapon != null) {
            System.out.println("Unequipped " + equippedWeapon.getName());
        }

        equippedWeapon = weapon;
        System.out.println("Equipped " + equippedWeapon.getName() +
                " (+" + equippedWeapon.getDamage() + " attack damage)");
    }


    public void unequipWeapon() {
        if (equippedWeapon == null) {
            System.out.println("You don't have any weapon equipped");
            return;
        }

        System.out.println("Unequipped " + equippedWeapon.getName());
        equippedWeapon = null;
    }

    private Item findItemFromInventory(String itemName) {
        for (Item items : PlayerInventory) {
            if (items.getName().equalsIgnoreCase(itemName)) {
                return items;
            }
        }
        return null;
    }

    void loadPlayer() {
            Scanner fileinput=null;
            String fileName="User/player.txt";
            File file=new File(fileName);
            try{
                fileinput=new Scanner(file);
            }
            catch(FileNotFoundException e){
                System.out.println("player.txt is not found, please put back text file.");
                System.exit(0);
            }

            while(fileinput.hasNextLine()){
                String line=fileinput.nextLine();
                String[] split=line.split("/");

                //Create Variables based on what would be stored in file then set them.
                String TempCurrentRoom=split[0];
                setCurrentRoom(TempCurrentRoom);
                int TempHP=Integer.parseInt(split[1]);
                setHP(TempHP);
                int TempDMG=Integer.parseInt(split[2]);
                setAttackDMG(TempDMG);
                int TempDefense=Integer.parseInt(split[3]);
                setDefense(TempDefense);
                int TempEvasion=Integer.parseInt(split[4]);
                setEvasion(TempEvasion);
                int TempHunger=Integer.parseInt(split[5]);
                setHunger(TempHunger);
                int TempThrist=Integer.parseInt(split[6]);
                setThrist(TempThrist);
                boolean tempTime=Boolean.parseBoolean(split[7]);
                setDayorNight(tempTime);



                //Below this would be inventory, and equipment, will work later

            }

    }



    void avoid(Monster tempMonster) {
        //It going to be 95% chance to avoid monster encounter
        //5% chance to enter combat with monster
        //avoid combat successfully, DOES NOT despawn Monster
        Random rand=new Random();
        double chance=rand.nextDouble();

        if(!tempMonster.isAlive()){
            System.out.println("IT DEAD!");
        }
        else if(chance<0.50){
            //Debug message below to test out avoid mechanic
            System.out.println("You avoided combat");
        }
        else{
            Combat(tempMonster);
        }

    }


    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(100, hp));
    }

    public Weapon getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Weapon equippedWeapon) {
        this.equippedWeapon = equippedWeapon;
    }

    public Armor getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Armor equippedArmor) {
        this.equippedArmor = equippedArmor;
    }
    public void craftItem(String itemName) {

        if (itemName == null || itemName.isBlank()) {
            System.out.println("Usage: craft <item-name>");
            return;
        }

        // -----------------------------------
        // 1. FIND THE CRAFTABLE ITEM IN GAME
        // -----------------------------------
        CraftableItem craftItem = null;

        for (Item item : Game.ItemData) {
            if (item instanceof CraftableItem) {

                // Match by name or ID
                if (item.getName().equalsIgnoreCase(itemName) ||
                        item.getID().equalsIgnoreCase(itemName)) {

                    craftItem = (CraftableItem) item;
                    break;
                }
            }
        }


        if (craftItem == null) {
            System.out.println("❌ You cannot craft \"" + itemName + "\".");
            return;
        }


        craftItem.craft(itemName);
    }



    public  void Combat(Monster tempMonster) {
        System.out.println("Your HP" + getHP());
        System.out.println(tempMonster.displayerMonster());
        System.out.println("Monster HP: " + tempMonster.getHP());
        int MonsterHP = tempMonster.getHP();
        Scanner UserInput = new Scanner(System.in);
        while (true) {
            if(getHP()<=0){
                //Put Lose Method Here where player must reload or start game
            }//Make sure Monster is dead in room
            else if(MonsterHP<=0){
                tempMonster.setHP(0);
                //The Monster should be dead when false
                tempMonster.setAlive(false);
                break;
            }

            System.out.println("Your HP" + getHP());
            System.out.println("Commands:");
            String Command = UserInput.nextLine();
            if (Command.equalsIgnoreCase("Attack")) {
                MonsterHP -= this.getAttackDMG();
                setHP(this.getHP() - tempMonster.getAttackDMG());
            } else if (Command.equalsIgnoreCase("Stats")) {
                displayStats();
            } else if (Command.equalsIgnoreCase("Run")) {
                //Put random chance to just exit battle
                Random rand = new Random();
                double MonsterChance=rand.nextDouble(0,1);
                double PlayerChance=rand.nextDouble(0,1);
                if(MonsterChance<PlayerChance) {
                    break;
                }
                else{
                    System.out.println("You weren't able to run away");
                    setHP(this.getHP() - tempMonster.getAttackDMG());
                }
            } else if (Command.equalsIgnoreCase("Inventory")) {
                //Put inventory Here
                displayInventory();
            }
            else if(Command.equalsIgnoreCase("Help")) {
                showHelp();
            }
            else if(Command.equalsIgnoreCase("equip")) {
                //Equip here
            }
            else if(Command.equalsIgnoreCase("unequip")) {
                //Unequip Here
            }
            else{
                System.out.println("Incorrect Command");
            }
        }
    }

    //for user input STATS
//    public void showStats() {
//        System.out.println("Your current stats are:");
//        System.out.println("Health: " + hp + "/100");
//        System.out.println("Attack Damage: " + this.getAttackDMG());
//        if (equippedWeapon != null) {
//            //System.out.println("Current weapon: " + this.getEquippedWeapon());
//        }
//    }


}