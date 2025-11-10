public class Item {
    private int id;
    private String Name;
    private String Description;
    private int location;

    public Item(int id, String name, String description, int location) {
        this.id = id;
        this.Name = name;
        this.Description = description;
        this.location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }
}
