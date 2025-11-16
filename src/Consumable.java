public abstract class Consumable extends Item{
    private int healing;

    public Consumable(int id, String name, String description, int roomID, int healing){
        super(id, name, description, roomID);
        this.healing = healing;
    }

    public int getHealing(){
        return healing;
    }

    public String getType() {
        return "Consumable";
    }

    public void use(Object playerObj) {
        if (playerObj instanceof Player player) {
            int newHp = Math.min(player.getHP() + healing, 100);
            player.setHP(newHp);
            System.out.println(player.getHP() + " HP after using " + this.getName());
        } else {
            System.out.println("This item can only be used by a player.");
        }
    }
}