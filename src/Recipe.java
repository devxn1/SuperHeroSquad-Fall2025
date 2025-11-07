import java.util.ArrayList;

public class Recipe extends Item {
    int NumberOfMaterials;
    ArrayList<Material> requiredMaterials;
    public Recipe(int id, String name, String description, int location, int numberOfMaterials, ArrayList<Material> requiredMaterials) {
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
