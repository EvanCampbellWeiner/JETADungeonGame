public class Trap extends Entity{
    private Weapon Spike;

    //Declaring trap with parameters
    Trap(Weapon Stick, int x, int y, int z, Manager Management){
        super(x,y,z,Management);
        this.Spike=Stick;
    }

    //this should never happen since entitys can't move
    public void interact(Entity Attacker) {
        System.out.print("Nah Tomorrow");
    }

    @Override
    //Can't kill a trap
    public void gameOver() {

    }
    @Override
    //Traps don't take turns
    public void startTurn() {
        getMyManager().stillHere(getZ(),getX(),getY());
    }

    @Override
    //Method to interact trap with character
    public void interact(Character Attacker) {
        System.out.print("Now");
        getMyManager().combat(Attacker, Spike);
    }

    @Override
    //Print function for trap
    public void print() {
        System.out.print("Trap ("+getX()+","+getY()+","+getZ()+") with");
        Spike.printWeapon();
    }
}