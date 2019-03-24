public class Manager {

    private Character[] GameLoop;
    private int turn;
    private boolean continueGame;
    private World GameWorld;

    Manager(){
        turn=0;
        continueGame=true;
        this.GameLoop = new Character[0];
        this.GameWorld = new World(3);
        Floor RoomOne= new Floor(8,8);
        RoomOne.autoFillFloor(1);
        Floor RoomTwo=new Floor(12,12);
        RoomTwo.buildRectangle(10,4,1,1);
        RoomTwo.buildRectangle(10,4,1,7);
        RoomTwo.buildRectangle(4,10,1,1);
        RoomTwo.buildRectangle(4,10,7,1);
        GameWorld.customFloor(RoomOne,0);
        GameWorld.customFloor(RoomTwo,1);
    }
    Manager(World Map){
        continueGame = true;
        turn=0;
        this.GameLoop = new Character[0];
        this.GameWorld=Map;
    }


    public void addEntity(Character Neo){
        Character[] NewGameLoop = new Character[GameLoop.length+1];
        System.arraycopy(GameLoop,0,NewGameLoop,0,GameLoop.length);
        NewGameLoop[NewGameLoop.length-1]= Neo;
        this.GameLoop = NewGameLoop;
        GameWorld.getTile(Neo.getZ(),Neo.getX(),Neo.getY()).setEnemyLocation(NewGameLoop.length);
    }
    public void PrintFull(){
        for (int loop=0;loop<=GameLoop.length-1;loop++){
            GameLoop[loop].print();
        }
        GameWorld.printWorld();
    }
    public void RunGame(){
        do{
            //System.out.print(turn);
            if(GameLoop[turn].getAlive()) {
                GameLoop[turn].startTurn();
            }
            iterateTurn();
        }while(continueGame);
        //loops thought GameLoop running RunTurn until exitGame is Called
    }
    private void iterateTurn(){
        //System.out.print("("+turn+"/"+getGameLoop().length+")");
        if(turn>=getGameLoop().length-1){
            turn=0;
            //GameWorld.printWorld();
        }else{
            turn++;
        }
    }
    public World getGameWorld() {
        return GameWorld;
    }
    public Entity[] getGameLoop() {
        return GameLoop;
    }
    public void exitGame(){
        continueGame=false;
    }
    public boolean teleport(int newZ, int newX, int newY, int currentZ,int currentX,int currentY){
        if(!GameWorld.getTile(newZ,newX,newY).getWall()) {//if your walling into a wall
            if (GameWorld.getTile(newZ, newX, newY).getEnemyLocation() == 0) {//if a enemy is standing there

                getGameWorld().getTile(newZ, newX, newY).setEnemyLocation(turn+1);

                getGameWorld().getTile(currentZ, currentX, currentY).setEnemyLocation(0);
                return true;//no wall and no enemy

            } else {

                GameLoop[GameWorld.getTile(newZ,newX,newY).getEnemyLocation()-1].interact(GameLoop[turn]);
                getGameWorld().getTile(currentZ, currentX, currentY).setEnemyLocation(0);
                return true;
            }

        }else{
            //System.out.print("invalid Move");
            return false;}//there is a wall
    }
    public void stillHere(int z,int x,int y){
        if(GameWorld.getTile(z, x, y).getEnemyLocation() == 0){
            getGameWorld().getTile(z, x, y).setEnemyLocation(turn+1);

        }
    }

   // public void reMove Character(Character killed){
    public boolean combat(Character Attacker,Character Defender){//return true is attacker wins
        boolean bothalive=true;
        do{
            if(Attacker.getAlive()){
                Attacker.attack(Attacker.pickWeapon(),Defender);
            }
            if(Defender.getAlive()){
                Defender.attack(Defender.pickWeapon(),Attacker);
            }

        }while(Attacker.getAlive()&&Defender.getAlive());

        return true;
    }

    public boolean combat(Character Attacker,Weapon Spike){
        Attacker.defence(Spike.getDamage(),Spike.getDamageType());
        return true;
    }
}
