public class World {
    private Floor[] Level;

    World(int numberOfFloors){
        this.Level= new Floor[numberOfFloors];
        for(int loop=0;loop<=numberOfFloors-1;loop++){
            this.Level[loop]=new Floor(8,8);
        }
    }
    World(Floor[] Level){
        this.Level=Level;
    }

    //used by full print
    public void printWorld(){
        for(int loop=0;loop<=Level.length-1;loop++){
            System.out.print("lv_"+loop+"\n");
            Level[loop].printFloor();
        }
    }

    public Floor getFloor(int z){
        return Level[z];
    }
    
    public void customFloor(Floor Floor,int placement) {
        Level[placement] = Floor;
    }


    public Tile getTile(int z,int x,int y){
        return (Level[z].GetTile(x,y));

    }

    public Floor[] getLevel() {
        return Level;
    }
    public void setLevel(Floor[] level) {
        Level = level;
    }
}
