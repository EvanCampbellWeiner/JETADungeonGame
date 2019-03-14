public abstract class Entity {
    private int x,y,z;

    Entity(int x, int y, int z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public void teleport(int x, int y, int z){
        this.x =x;
        this.y =y;
        this.z =z;
    }
    public abstract boolean interact(Character Attacker);
    public abstract void print();

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setZ(int z) {
        this.z = z;
    }
}

