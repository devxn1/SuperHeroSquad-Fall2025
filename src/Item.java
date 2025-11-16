public class Item {
    private String id;
    private String name;
    private String description;
    private String roomID;

    public Item(String id, String name, String description, String roomID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.roomID = roomID;
    }

    public String getID() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getRoomID() { return roomID; }
    public void setRoomID(String roomID) { this.roomID = roomID; }

    // Concrete method now, can be overridden in subclasses
    public void use(Object player) {
        System.out.println("Using " + name);
    }

    public String getType() {
        return "Item";
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}