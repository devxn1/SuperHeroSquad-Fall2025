import java.util.ArrayList;
import java.util.List;

class Recipe extends Item {
    int NumberOfMaterials;
    ArrayList<Material> requiredMaterials;
    public Recipe(String id, String name, String description, List<String> location, int numberOfMaterials, ArrayList<Material> requiredMaterials) {
        super(id, name, description, location);
        this.NumberOfMaterials = numberOfMaterials;
        this.requiredMaterials = requiredMaterials;
    }

    void storeReceipe(){

    }
    void addRecipe(){
    }
    void CheckRecipe(){
    }
}
