import java.util.*;

public class CraftableItem extends Weapon implements Craftable {

    private List<String> materials; // list of material IDs needed to craft this item

    public CraftableItem(String id, String name, String description,
                         List<String> roomID, int damage, int DOT, List<String> materials) {
        super(id, name, description, roomID, damage, DOT);
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
                System.out.println(" - UNKNOWN MATERIAL ID: " + matID);
            }
        }
    }

    @Override
    public void craft() {

        Player player = Game.player;  // your Game already holds a static player instance
        ArrayList<Item> inventory = player.getPlayerInventory();

        // -------- 1. CHECK IF PLAYER ALREADY HAS THE CRAFTED ITEM --------
        for (Item i : inventory) {
            if (i.getID().equalsIgnoreCase(this.getID())) {
                System.out.println("❌ You already crafted " + getName() + ".");
                return;
            }
        }

        // -------- 2. CHECK FOR ALL REQUIRED MATERIALS --------
        List<Item> requiredItems = new ArrayList<>();

        for (String matID : materials) {
            boolean found = false;

            for (Item invItem : inventory) {
                if (invItem.getID().equalsIgnoreCase(matID)) {
                    requiredItems.add(invItem);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("❌ Missing required material: " + matID);
                return;
            }
        }

        // -------- 3. REMOVE MATERIALS FROM INVENTORY --------
        for (Item usedMaterial : requiredItems) {
            inventory.remove(usedMaterial);
        }

        // -------- 4. ADD CRAFTED ITEM TO INVENTORY --------
        inventory.add(this);

        System.out.println("\n✅ Successfully crafted: " + getName());
        System.out.println("You now have a new weapon with:");
        System.out.println(" - Damage: " + getDamage());
        System.out.println(" - DOT: " + getDOT());
    }

    // Utility method to get an Item definition by ID from Game.ItemData
    private Item findItemByID(String id) {
        for (Item i : Game.ItemData) {
            if (i.getID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return null;
    }
}
