public class Stairs extends Character{
    private int teleportX;
    private int teleportY;
    private int teleportZ;


    Stairs(int x, int y, int z, int teleportX, int teleportY, int teleportZ, Manager Management ){
        super("Stairs",x,y,z,Management,1000,0);
        this.teleportX = teleportX;
        this.teleportY = teleportY;
        this.teleportZ=teleportZ;
    }

    @Override
    public void takeLoot(Weapon Looted) {
        //stairs don't loot
    }

    @Override
    public void gameOver() {

    }
    @Override
    public void startTurn() {
        getMyManager().stillHere(getZ(),getX(),getY());
        //Stairs don't take turns
    }

    @Override
    public Weapon pickWeapon() {
        System.out.print("Error Attacking Stairs");
        return new Weapon(false,0,"Error");
    }

    @Override
    public void interact(Character Attacker) {
        Attacker.setX(teleportX);
        Attacker.setY(teleportY);
        Attacker.setZ(teleportZ);
        Attacker.setUnmoved(false);
    }
}
