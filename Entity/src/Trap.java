public class Trap extends Entity{
    private Weapon Spike;

    Trap(Weapon Stick, int x, int y, int z, Manager Management){
        super(x,y,z,Management);
        this.Spike=Stick;
    }


    public void interact(Entity Attacker) {
        System.out.print("Nah Tomorrow");
        //this should never happen since entitys can't move
    }

    @Override
    public void gameOver() {
        //can't kill a trap leave this blank
    }
    @Override
    public void startTurn() {
        getMyManager().stillHere(getZ(),getX(),getY());
        //traps don't take turns
    }

    @Override
    public void interact(Character Attacker) {
        System.out.print("Now");
        getMyManager().combat(Attacker, Spike);
    }

    @Override
    public void print() {
        System.out.print("Trap ("+getX()+","+getY()+","+getZ()+") with");
        Spike.printWeapon();
    }
}