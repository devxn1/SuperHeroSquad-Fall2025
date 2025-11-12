public class Monster extends Character{
    private int ID;
    private String Name;
    private String Description;
    private int IDLocation;



    public Monster(int HP, int attackDMG, int ID, String Name, String Description,int IDLocation){
        super(HP, attackDMG);
        this.ID = ID;
        this.IDLocation = IDLocation;
        this.Name = Name;
        this.Description = Description;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDLocation() {
        return IDLocation;
    }

    public void setIDLocation(int IDLocation) {
        this.IDLocation = IDLocation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    String displayerMonster(){
        return this.Name + " " +this.Description;
    }

}
