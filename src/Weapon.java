import java.util.List;

public class Weapon extends Item {
    private int damage;
    private int DOT;

    public Weapon(String id, String name, String description, List<String> roomID, int damage, int DOT) {
        super(id, name, description, roomID);
        this.damage = damage;
        this.DOT = DOT;
    }

    public int getDamage() {
        return damage;
    }

    public int getDOT() {
        return DOT;
    }

    @Override
    public String getType() {
        return "Weapon";
    }

    @Override
    public void use(Object playerObj) {
        if (playerObj instanceof Player player) {
            player.setEquippedWeapon(this);
            System.out.println(this.getName() + " equipped!");
        }
    }
}