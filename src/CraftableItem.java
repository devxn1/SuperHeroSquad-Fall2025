import java.util.*;

public class CraftableItem extends Weapon implements Craftable {

    private List<String> materials;  // material IDs required to craft

    public CraftableItem(String id, String name, String description,
                         List<String> roomIDs, int damage, int DOT,
                         List<String> materials) {

        super(id, name, description, roomIDs, damage, DOT);
        this.materials = materials;
    }


    @Override
    public void GetMaterials() {
        System.out.println("\nMaterials needed to craft " + getName() + ":");

        for (String matID : materials) {
            Item mat = findItemByID(matID);

            if (mat != null) {
                System.out.println(" - " + mat.getName() + " (" + mat.getID() + ")");
            } else {
                System.out.println(" - UNKNOWN MATERIAL: " + matID);
            }
        }
    }


    @Override
    public void craft(String itemName) {

        Player player = Game.player;
        ArrayList<Item> inventory = player.getPlayerInventory();

        // 1. Check if player already owns crafted item
        for (Item item : inventory) {
            if (item.getID().equalsIgnoreCase(this.getID())) {
                System.out.println("❌ You already crafted " + getName() + ".");
                return;
            }
        }

        // 2. Check if player has all required materials
        List<Item> requiredItems = new ArrayList<>();

        for (String matID : materials) {

            Item foundMaterial = null;

            for (Item invItem : inventory) {
                if (invItem.getID().equalsIgnoreCase(matID)) {
                    foundMaterial = invItem;
                    break;
                }
            }

            if (foundMaterial == null) {
                System.out.println("❌ Missing material: " + matID);
                return;
            }

            requiredItems.add(foundMaterial);
        }

        // 3. Remove materials from inventory
        for (Item mat : requiredItems) {
            inventory.remove(mat);
        }

        // 4. Add the newly crafted item
        inventory.add(this);

        System.out.println("\n✅ Successfully crafted: " + getName());
        System.out.println(" - Damage: " + getDamage());
        System.out.println(" - DOT: " + getDOT());
    }

    // Get items from master item list
    private Item findItemByID(String id) {
        for (Item i : Game.ItemData) {
            if (i.getID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return null;
    }
}
