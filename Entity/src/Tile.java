public class Tile{
    private boolean wall;
    private int enemyLocation;
    //Add tile info here
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

    public void setWall(boolean wall) {
        this.wall = wall;
    }
    public boolean getWall() {
        return wall;
    }
    public void setEnemyLocation(int enemy){this.enemyLocation=enemy;}
    public int getEnemyLocation() {return enemyLocation;}
}