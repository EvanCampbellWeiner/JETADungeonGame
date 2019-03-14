public class Manager {

    private Entity[] GameLoop;
    private World[] GameWorld;


    Manager(Player Neo){
        this.GameLoop = new Entity[1];
        GameLoop[0] = Neo;
        this.GameWorld = new World[1];
        GameWorld[0] = new World(2);
        Floor RoomOne= new Floor(8,8);
        RoomOne.autoFillFloor(1);
        Floor RoomTwo=new Floor(12,12);
        RoomTwo.buildRectangle(10,4,1,1);
        RoomTwo.buildRectangle(10,4,1,7);
        RoomTwo.buildRectangle(4,10,1,1);
        RoomTwo.buildRectangle(4,10,7,1);
        GameWorld[0].customFloor(RoomOne,0);
        GameWorld[0].customFloor(RoomTwo,1);
    }
    Manager(Player Neo, World[] Maps,Entity[]PlayerLoop){
        this.GameWorld=Maps;
        this.GameLoop = new Entity[PlayerLoop.length+1];
        GameLoop[0]=Neo;
        System.arraycopy(GameLoop,0,PlayerLoop,0,PlayerLoop.length+1);
    }



    public void addEnemy(Enemy Monster){
        Entity[] NewGameLoop = new Entity[GameLoop.length+1];
        System.arraycopy(GameLoop,0,NewGameLoop,0,GameLoop.length);
        NewGameLoop[NewGameLoop.length-1]= Monster;
        this.GameLoop = NewGameLoop;
    }
    public void addWorld(World NewWorld){
        World[] NewWorldArray = new World[GameWorld.length+1];
        System.arraycopy(GameWorld,0,NewWorldArray,0,GameWorld.length);
        NewWorldArray[NewWorldArray.length-1]=NewWorld;
        this.GameWorld = NewWorldArray;
    }



    public void PrintFull(){
        for (int loop=0;loop<=GameLoop.length-1;loop++){
            GameLoop[loop].print();
        }
        for(int loop=0;loop<=GameWorld.length-1;loop++){
            System.out.print("     GameWorld_#"+loop+"\n");
            GameWorld[loop].printWorld();
        }
    }

    public void RunGame(){

    }

    public World[] getGameWorld() {
        return GameWorld;
    }

    public Entity[] getGameLoop() {
        return GameLoop;
    }

    public boolean requestMove(Character Moving){
        return true;
    }

}
