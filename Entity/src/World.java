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

    public void printWorld(){
        for(int loop=0;loop<=Level.length-1;loop++){
            System.out.print("lv_"+loop+"\n");
            Level[loop].printFloor();
        }
    }
    public Floor getFloor(int z){
        return Level[z];
    }

    public void printFloor(int z){
        Level[z].printFloor();
    }

    public void addFloor(Floor NewFloor){
        Floor[] NewLevel = new Floor[Level.length+1];
        System.arraycopy(Level,0,NewLevel,0,Level.length);
        NewLevel[NewLevel.length-1]=NewFloor;
        this.Level=NewLevel;


        /*System.arraycopy(Sheath,0,NewWeaponArray,0,Sheath.length);
        NewWeaponArray[NewWeaponArray.length-1]=Sword;
        this.Sheath=NewWeaponArray;*/
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
