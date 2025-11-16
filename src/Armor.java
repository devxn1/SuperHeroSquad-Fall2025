public abstract class Armor extends Item {
    private int defense;
    private int evasion;
    private int hpAdded;

    public Armor(int id, String name, String description, int roomID, int defense, int evasion, int hpAdded){
        super(id, name, description, roomID);
        this.defense = defense;
        this.evasion = evasion;
        this.hpAdded = hpAdded;
    }

    public int getDefense(){
        return defense;
    }

    public int getEvasion() {
        return evasion;
    }

    public int getHpAdded(){
        return hpAdded;
    }

    public String getType(){
        return "Armor";
    }

    public void use(Player player){
        if (player == null) {
            return;
        }
            player.setEquippedArmor(this);
            player.setDefense(player.getDefense() + defense);
            player.setHp(player.getHP() + hpAdded);
            System.out.println(player.getEquippedArmor().getName() + " equipped!");
    }
}