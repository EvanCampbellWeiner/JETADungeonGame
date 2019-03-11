public class Trap extends Entity{
    private int damage;
    Trap(int damage,int x,int y, int z){
        super(x,y,z);
    }

    @Override
    public boolean interact(Charator Attacker) {
        Attacker.defence(damage,false);
        return false;
    }

    @Override
    public void print() {
        System.out.print("Trap ("+getX()+","+getY()+","+getZ()+") "+damage+" damage");
    }
}