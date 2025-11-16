import java.util.*;


public class CraftableItem  extends Weapon implements Craftable {
    private ArrayList<Material> materials;
    public CraftableItem(int id, String name, String description, int roomID, int damage, int DOT, ArrayList<Material> materials) {
        super(id, name, description, roomID, damage, DOT);
        this.materials = materials;
    }

    @Override
    public void craft() {

    }
    @Override
    public void GetMaterials() {

    }
}
