public abstract class Entity {

    //Declarations
    private int x,y,z;
    private boolean alive;
    private Manager MyManager;

    //Method to create Creating entity
    Entity(int x, int y, int z,Manager Management){
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

    //Method to use teleport
    public void teleport(int x, int y, int z){
        this.x =x;
        this.y =y;
        this.z =z;
    }
    //Abstract for interact
    public abstract void interact(Character Attacker);

    //public abstract void interact(Entity Attacker);

    //Abstract for print
    public abstract void print();

    //Abstract for start Turn
    public abstract void startTurn();

    //Abstract for GameOver
    public abstract void gameOver();

    //Getter for x coordinate
    public int getX() {
        return x;
    }

    //Getter for y coordinate
    public int getY() {
        return y;
    }

    //Getter for z coordinate
    public int getZ() {
        return z;
    }

    //Getter for Alive
    public boolean getAlive(){return alive;}

    //Getter for My manager
    public Manager getMyManager(){ return MyManager; }

    //Setter for x coordinate
    public void setX(int x) {
        this.x = x;
    }

    //Setter for y coordinate
    public void setY(int y) {
        this.y = y;
    }

    //Setter for z coordinate
    public void setZ(int z) {
        this.z = z;
    }

    //Setter for Alive
    public void setAlive(boolean alive){this.alive=alive;}

    //Setter for My manager
    public void setMyManager(Manager Management){this.MyManager=Management;}
}

