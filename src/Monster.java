public class Monster extends Character{
    private int ID;
    private int Location;
    private String Name;
    private String Description;

    public Monster(int HP, int attackDMG, int ID, int Location, String Name, String Description){
        super(HP, attackDMG);
        this.ID = ID;
        this.Location = Location;
        this.Name = Name;
        this.Description = Description;

    }

    String displayerMonster(){
        return this.Name + " " +this.Description;
    }

}
