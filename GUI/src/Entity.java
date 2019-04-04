public abstract class Entity {
    private int x,y,z;
    private boolean alive;
    private Manager MyManager;

    Entity(int x, int y, int z, Manager Management){
        this.alive=true;
        this.x=x;
        this.y=y;
        this.z=z;
        this.MyManager = Management;
        //if(MyManager.getGameWorld().getTile(z, x, y).getEnemyLocation()==0){
            //System.out.print("("+x+","+y+") z="+z);
            //MyManager.getGameWorld().getTile(z, x, y).setEnemyLocation(2);
            //MyManager.getGameWorld().getLevel()[z].getFloor()[x][y].setEnemyLocation(2);
        //}
    }

    public void teleport(int x, int y, int z){
        this.x =x;
        this.y =y;
        this.z =z;
    }
    public abstract void interact(Character Attacker);
    //public abstract void interact(Entity Attacker);
    public abstract void print();
    public abstract void startTurn();
    public abstract void gameOver();

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getZ() {
        return z;
    }
    public boolean getAlive(){return alive;}
    public Manager getMyManager(){ return MyManager; }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setZ(int z) {
        this.z = z;
    }
    public void setAlive(boolean alive){this.alive=alive;}
    public void setMyManager(Manager Management){this.MyManager=Management;}
}

