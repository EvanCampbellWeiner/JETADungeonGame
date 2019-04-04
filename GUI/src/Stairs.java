public class Stairs extends Character{
    //Declaring coordanits as integers
    private int teleportX;
    private int teleportY;
    private int teleportZ;

    //Method that Creates stairs with parameters
    Stairs(int x, int y, int z, int teleportX, int teleportY, int teleportZ, Manager Management ,int type){
        super("Stairs",x,y,z,type,Management,1000,0);
        this.teleportX = teleportX;
        this.teleportY = teleportY;
        this.teleportZ=teleportZ;
        setfriendly(false);
    }

    @Override
    //Stairs don't loot
    public void takeLoot(Weapon Looted) {
    }

    @Override
    //If stairs die, but they don't
    public void gameOver() {
    }

    @Override
    //Stairs don't take turns
    public void startTurn() {
        getMyManager().stillHere(getZ(),getX(),getY());
    }

    @Override
    //Error check for picking up stairs weapon
    public Weapon pickWeapon() {
        System.out.print("Error Attacking Stairs");
        return new Weapon(false,0,"Error");
    }

    @Override
    //Method to use stairs
    public void interact(Character Attacker) {
        Attacker.setX(teleportX);
        Attacker.setY(teleportY);
        Attacker.setZ(teleportZ);
        Attacker.setUnmoved(false);
    }
}
