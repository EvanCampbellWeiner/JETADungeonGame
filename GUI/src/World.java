public class World {
    private Floor[] Level;

    //Method that creates the number of floors
    World(int numberOfFloors){
        this.Level= new Floor[numberOfFloors];
        for(int loop=0;loop<=numberOfFloors-1;loop++){
            this.Level[loop]=new Floor(8,8);
        }
    }

    //Adding level to array of floors
    World(Floor[] Level){
        this.Level=Level;
    }

    //Function that prints the world
    public void printWorld(){
        for(int loop=0;loop<=Level.length-1;loop++){
            System.out.print("lv_"+loop+"\n");
            Level[loop].printFloor();
        }
    }

    //Getter for integer z
    public Floor getFloor(int z){
        return Level[z];
    }

    //Function to print the integer z
    public void printFloor(int z){
        Level[z].printFloor();
    }

    //Method to add a new floor
    public void addFloor(Floor NewFloor){
        Floor[] NewLevel = new Floor[Level.length+1];
        System.arraycopy(Level,0,NewLevel,0,Level.length);
        NewLevel[NewLevel.length-1]=NewFloor;
        this.Level=NewLevel;


        /*System.arraycopy(Sheath,0,NewWeaponArray,0,Sheath.length);
        NewWeaponArray[NewWeaponArray.length-1]=Sword;
        this.Sheath=NewWeaponArray;*/
    }

    //Method to declare level one as regular level
    public void customFloor(Floor Floor,int placement) {
        Level[placement] = Floor;
    }

    //Getter for a tile on a floor
    public Tile getTile(int z,int x,int y){
        return (Level[z].GetTile(x,y));

    }

    //Method to get a level from an the array
    public Floor[] getLevel() {
        return Level;
    }

    //Setter for the level
    public void setLevel(Floor[] level) {
        Level = level;
    }
}
