/**Class: AmbushMonster
 * @author Devin Gomez
 * @version 1.0
 * Course:  ITEC3860 Fall 2025
 * Written: November 16, 2025
 * Purpose: Monster that can ambush the player when entering a room
 */

public class AmbushMonster extends Character {
    private String monsterID;
    private String name;
    private String description;
    private String roomID;
    private boolean isAlive;
    private double ambushChance;

    public AmbushMonster(int HP, int attackDMG, String monsterID, String name,
                         String description, String roomID, boolean isAlive,
                         double ambushChance) {
        super(HP, attackDMG);

        this.monsterID = monsterID;
        this.name = name;
        this.description = description;
        this.roomID = roomID;
        this.isAlive = isAlive;

        this.ambushChance = Math.max(0.0, Math.min(1.0, ambushChance));
    }

    public String getMonsterID() {
        return monsterID;
    }

    public void setMonsterID(String monsterID) {
        this.monsterID = monsterID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public double getAmbushChance() {
        return ambushChance;
    }

    public void setAmbushChance(double ambushChance) {
        this.ambushChance = Math.max(0.0, Math.min(1.0, ambushChance));
    }

    public boolean triggerAmbush() {
        if (!isAlive()) {
            return false;
        }

        return  Math.random() < ambushChance;
    }

    public void takeDamage(int damage) {
        HP -= damage;
        if (HP <= 0) {
            HP = 0;
            isAlive = false;
        }
    }




}
