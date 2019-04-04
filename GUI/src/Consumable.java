public class Consumable extends Item {

    // Declaring restore as integer
    private int restore;

    //Creating consumable
    Consumable(int heal, String Name) {
        super(Name);
        this.restore = heal;
    }

    //Print Function for restoring health
    public String printString() {
        return ("" + getName() + " heals " + restore);
    }

    //Print function for restoring health
    public void printEffect() {
        System.out.print("  > " + getName() + " heals " + restore + "\n");
    }

    //method used when gtting restored
    public int use() {
        return restore;
    }
}