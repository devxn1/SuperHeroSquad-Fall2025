public class Consumable extends Item {
    private int healing;

    public Consumable(String id, String name, String description, String roomID, int healing){
        super(id, name, description, roomID);
        this.healing = healing;
    }

    public int getHealing() {
        return healing;
    }

    @Override
    public String getType() { return "Consumable"; }

    @Override
    public void use(Object playerObj) {
        if (playerObj instanceof Player player) {
            int newHp = Math.min(player.getHP() + healing, 100);
            player.setHP(newHp);
            System.out.println(player.getHP() + " HP after using " + this.getName());
        }
    }
}