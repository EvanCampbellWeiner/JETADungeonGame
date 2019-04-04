public class Tile{
    //Declarations
    private boolean wall;
    private int enemyLocation;

    //creating tiles
    Tile(int enemy){
        this.wall=false;
        this.enemyLocation=enemy;
    }
    Tile(boolean wall){
        this.wall=wall;
        this.enemyLocation=0;
    }
    Tile(){
        this.wall=true;
        this.enemyLocation=0;
    }
    Tile(boolean wall,int enemy){
        this.wall=wall;
        this.enemyLocation=enemy;
    }

    //Setter for Wall
    public void setWall(boolean wall) {
        this.wall = wall;
    }

    //Getter for wall
    public boolean getWall() {
        return wall;
    }

    //Setter for enemy location
    public void setEnemyLocation(int enemy){this.enemyLocation=enemy;}

    //Getter for enemy location
    public int getEnemyLocation() {return enemyLocation;}
}