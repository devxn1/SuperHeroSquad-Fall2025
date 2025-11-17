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
    public void viewRecipeBook() {
        if (Journal.isEmpty()) {
            System.out.println("Your Recipe Book is empty");
            return;
        }

        System.out.println("\n=== Recipe Book ===");

        for (Recipe R : Journal) {
            System.out.println("\n" + R.getName());
            System.out.println("--------------------");

            for (String materialID : R.requiredMaterials) {
                Item materialItem = findItemByID(materialID);
                if (materialItem != null) {
                    System.out.println("  - " + materialItem.getName());
                } else {
                    System.out.println("  - " + materialID + " (Unknown Item)");
                }
            }
        }

        System.out.println("\n===================\n");
    }
    private Item findItemByID(String id) {
        for (Item it : Game.ItemData) {
            if (it.getID().equalsIgnoreCase(id)) {
                return it;
            }
        }
        return null;
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
    public static void PlayerMoveDirection(String direction) {

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
            System.out.println("\nA monster is here: " + nextRoom.getMonster().getName());
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



    //Fixed issues with Inventory saving with ChatGPT
    void savePlayer() {
        PrintWriter fileoutput;
        while(true){
            try{
                fileoutput=new PrintWriter("User/player.txt");
                //Going to print Room,HP,DMG,Defense,Evasion,Hunger,Thirst,(True or false) DayorNight
                //Into player.txt file
                fileoutput.print(getCurrentRoom()+"/"+getHP()+"/"+getAttackDMG()+"/"+getDefense());
                fileoutput.print("/"+getEvasion()+"/"+getHunger()+"/"+getThrist()+"/"+isDayorNight()+"/");
                //StringBuilder temp= new StringBuilder();
                List<Item> inv = getPlayerInventory();

                for (int i = 0; i < inv.size(); i++) {
                    fileoutput.print(inv.get(i).getName().trim());
                    if (i < inv.size() - 1) {
                        fileoutput.print(","); // inventory is comma-separated internally
                    }
                }

                // Newline for safety
                fileoutput.println();

                System.out.println("Player saved!");
                fileoutput.close();
                break;
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
        setAttackDMG(attackDMG+ equippedWeapon.getDamage());
        System.out.println("Equipped " + equippedWeapon.getName() +
                " (+" + equippedWeapon.getDamage() + " attack damage)");
    }


    public void unequipWeapon() {
        if (equippedWeapon == null) {
            System.out.println("You don't have any weapon equipped");
            return;
        }

        System.out.println("Unequipped " + equippedWeapon.getName());
        setAttackDMG(attackDMG- equippedWeapon.getDamage());
        equippedWeapon = null;
    }

    public void equipArmor(String itemName){
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Specify an armor name to equip.");
            return;
        }

        Item item = findItemFromInventory(itemName);

        if (item == null) {
            System.out.println("You don't have an item named \"" + itemName + "\"");
            return;
        }

        if (!(item instanceof Armor armor)) {
            System.out.println(item.getName() + " cannot be equipped as armor.");
            return;
        }

        // Already equipped?
        if (equippedArmor != null && equippedArmor.getName().equalsIgnoreCase(armor.getName())) {
            System.out.println("You already have " + armor.getName() + " equipped.");
            return;
        }

        // Unequip previous armor first
        if (equippedArmor != null) {
            System.out.println("Unequipped " + equippedArmor.getName());
        }

        equippedArmor = armor;
        setDefense(defense+armor.getDefense());

        System.out.println("Equipped " + armor.getName() +
                " (+" + armor.getDefense() + " defense)");
    }
    public void unequipArmor() {
        if (equippedArmor == null) {
            System.out.println("You don't have any armor equipped.");
            return;
        }

        System.out.println("Unequipped " + equippedArmor.getName());
        setDefense(defense-equippedArmor.getDefense());
        equippedArmor = null;
    }

    private Item findItemFromInventory(String itemName) {
        for (Item items : PlayerInventory) {
            if (items.getName().equalsIgnoreCase(itemName)) {
                return items;
            }
        }
        return null;
    }


    //USED AI for this Part to make so that Player class can heal during Combat
    public void useItem(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            System.out.println("Specify an item name to use.");
            return;
        }

        Item item = findItemFromInventory(itemName);

        if (item == null) {
            System.out.println("You don't have \"" + itemName + "\" in your inventory.");
            return;
        }

        // --- USE CONSUMABLE ---
        if (item instanceof Consumable consumable) {
            int healAmount = consumable.getHealing();

            int oldHP = getHP(); //Previous HP before Healing
            setHP(getHP() + healAmount);

            PlayerInventory.remove(item);

            System.out.println("You used " + consumable.getName() +
                    " and healed for " + healAmount + " HP.");
            System.out.println("HP: " + oldHP + " → " + getHP());

            return;
        }
    }
    private boolean inventoryContainsItem(String itemName) {
        for (Item i : PlayerInventory) {
            if (i.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    void loadPlayer() {

            String fileName = "User/player.txt";
            File file = new File(fileName);

            if (!file.exists()) {
                System.out.println("ERROR: player.txt not found in /User folder.");
                return;
            }

            try (Scanner fileinput = new Scanner(file)) {

                if (!fileinput.hasNextLine()) {
                    System.out.println("player.txt is empty.");
                    return;
                }

                String line = fileinput.nextLine();
                String[] split = line.split("/");

                if (split.length < 9) {
                    System.out.println("player.txt is missing required fields.");
                    return;
                }

                try {
                    // Base attributes
                    setCurrentRoom(split[0]);
                    setHP(Integer.parseInt(split[1]));
                    setAttackDMG(Integer.parseInt(split[2]));
                    setDefense(Integer.parseInt(split[3]));
                    setEvasion(Integer.parseInt(split[4]));
                    setHunger(Integer.parseInt(split[5]));
                    setThrist(Integer.parseInt(split[6]));
                    setDayorNight(Boolean.parseBoolean(split[7]));

                } catch (NumberFormatException e) {
                    System.out.println("player.txt has invalid number formatting.");
                    return;
                }


                if (!split[8].isEmpty()) {
                    String[] items = split[8].split("\\*,\\*");

                    for (String itemName : items) {
                        itemName = itemName.trim();
                        Item found = findItemFromInventory(itemName);

                        // Prevent duplicates
                        if (found != null && !inventoryContainsItem(itemName)) {
                            PlayerInventory.add(found);
                        }
                    }
                }

                if (split.length >= 10 && !split[9].isEmpty()) {
                    String weaponName = split[9].trim();
                    Item weapon = findItemFromInventory(weaponName);

                    // Ensure it exists in inventory before equipping
                    if (weapon != null && !inventoryContainsItem(weaponName)) {
                        PlayerInventory.add(weapon);
                    }

                    if (weapon != null) {
                        equipWeapon(weaponName);
                    }
                }

                if (split.length >= 11 && !split[10].isEmpty()) {
                    String armorName = split[10].trim();
                    Item armor = findItemFromInventory(armorName);

                    // Ensure it exists in inventory before equipping
                    if (armor != null && !inventoryContainsItem(armorName)) {
                        PlayerInventory.add(armor);
                    }

                    if (armor != null) {
                        equipArmor(armorName);
                    }
                }

                System.out.println("Player loaded successfully!");

            } catch (FileNotFoundException e) {
                System.out.println("player.txt could not be found. Put back in the User folder.");
            }


    }

    public void take(String itemName){
        for (Item tempkey : PlayerInventory) {
            if (tempkey.getName().equalsIgnoreCase(itemName)) {
                PlayerInventory.add(tempkey);
               // currentRoom.removeItem(tempkey);
                System.out.println(tempkey.getName() +" has been picked up from the room and successfully added to the player inventory.");
                return;
            }
            else {
                System.out.println("This item has not been found/exist in this room");
            }
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
        while (isAlive) {
            System.out.println("Your HP: " + getHP());
            System.out.println("Monster HP: " + MonsterHP);
            System.out.println("Commands:");
            String Command = UserInput.nextLine();
            if (Command.equalsIgnoreCase("Attack")) {
                MonsterHP -= this.getAttackDMG();
                int DealtDmg=tempMonster.getAttackDMG();
                DealtDmg-= getDefense();
                setHP(this.getHP() - DealtDmg);
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
            else if(Command.toLowerCase().startsWith("use ")){
                String item=Command.substring(4);
                Item isitThere =findItemFromInventory(item);

                if(isitThere == null){
                    System.out.println("You Don't Have it");
                }
                else if(isitThere instanceof Consumable){
                    useItem(item);
                }
                else{
                    System.out.println("Can't use this item during combat");
                }

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

            if(getHP()<=0){
                //Put Lose Method Here where player must reload or start game
            }//Make sure Monster is dead in room
            else if(MonsterHP<=0){
                tempMonster.setHP(0);
                //The Monster should be dead when false
                tempMonster.setAlive(false);
                isAlive=false;
                System.out.println(tempMonster.getName()+" is DEAD!");
                //tempMonster.drop(tempRoom);
                tempRoom.setMonster(tempMonster);

                Game.checkForGameCompletion();
                return;
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