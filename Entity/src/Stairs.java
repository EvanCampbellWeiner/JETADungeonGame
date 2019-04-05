public class Stairs extends Character{
    private int teleportX;
    private int teleportY;
    private int teleportZ;


    Stairs(int x, int y, int z, int teleportX, int teleportY, int teleportZ, Manager Management ,int type){
        super("Stairs",x,y,z,type,Management,1000,0);
        this.teleportX = teleportX;
        this.teleportY = teleportY;
        this.teleportZ=teleportZ;
        setfriendly(false);
    }

    @Override//Stairs Can't loot
    public void takeLoot(Weapon Looted) {
        //stairs don't loot
    }

    @Override// Stairs Cant Die
    public void gameOver() {

    }
    @Override// But stairs Still Exist?
    public void startTurn() {
        getMyManager().stillHere(getZ(),getX(),getY());
        //Stairs don't take turns
    }

    @Override// They Don't use Weaponds but they have Them
    public Weapon pickWeapon() {
        System.out.print("Error Attacking Stairs");
        return new Weapon(false,0,"Error");
    }

    @Override// Teleport on Interact
    public void interact(Character Attacker) {
        Attacker.setX(teleportX);
        Attacker.setY(teleportY);
        Attacker.setZ(teleportZ);
        Attacker.setUnmoved(false);
    }
}
