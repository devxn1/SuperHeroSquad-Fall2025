/**Class: Inventory
 * @author Carlos Matos
 * @version 1.0
 * Course:  ITEC3860 Fall 2025
 * Written: November 12, 2025
 * Purpose: Create Monster Class that will define the enemies in the game
 */

import java.util.ArrayList;
public class Monster extends Character{
    private String ID;
    private String Name;
    private String Description;
    private String IDLocation;
    boolean IsAlive=true;
    ArrayList<Item> MonsterInventory;



    public Monster( String ID, String Name, String Description,String IDLocation,int HP, int attackDMG, ArrayList<Item> MonsterInventory) {
        super(HP, attackDMG);
        this.HP=HP;
        this.attackDMG=attackDMG;
        this.ID = ID;
        this.IDLocation = IDLocation;
        this.Name = Name;
        this.Description = Description;
        this.IsAlive = IsAlive;
        this.MonsterInventory=MonsterInventory;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDLocation() {
        return IDLocation;
    }

    public void setIDLocation(String IDLocation) {
        this.IDLocation = IDLocation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    String displayerMonster(){
        return this.Name + "||" +this.Description;
    }

    public ArrayList<Item> getMonsterInventory() {
        return MonsterInventory;
    }

    public void setMonsterInventory(ArrayList<Item> monsterInventory) {
        MonsterInventory = monsterInventory;
    }

    //here you go Devin.
    public boolean isAlive() {
        return IsAlive;
    }

    public void setAlive(boolean alive) {
        IsAlive = alive;
    }
}
