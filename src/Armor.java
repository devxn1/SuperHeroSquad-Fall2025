public class Armor extends Item {
    private int defense;
    private int evasion;
    private int hpAdded;

    public Armor(String id, String name, String description, String roomID, int defense, int evasion, int hpAdded){
        super(id, name, description, roomID);
        this.defense = defense;
        this.evasion = evasion;
        this.hpAdded = hpAdded;
    }

    public int getDefense() {
        return defense;
    }
    public int getEvasion() {
        return evasion;
    }
    public int getHpAdded() {
        return hpAdded;
    }

    @Override
    public String getType() { return "Armor"; }

    @Override
    public void use(Object playerObj) {
        if (playerObj instanceof Player player) {
            player.setEquippedArmor(this);
            player.setDefense(player.getDefense() + defense);
            player.setHp(player.getHP() + hpAdded);
            System.out.println(this.getName() + " equipped!");
        }
    }
}