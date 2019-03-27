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
        Floor RoomOne= new Floor(9,9);
        RoomOne.buildRectangle(7,7,1,1);
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

    public Enemy generateEnemy(int selected){
        switch (selected){
            case 0:
                return new Enemy("Slime", 0,0 ,0 ,this, 2,0,generateWeapon(1));
            case 1:
                return new Enemy("Spider",0,0,0,this,3,1,generateWeapon(0));
            case 2:
                return new Enemy("Bat",0,0,0,this,3,1,generateWeapon(0));
            case 3:
                return new Enemy("Goblin",0,0,0,this,5,1,generateWeapon(3));
            case 4:
                return new Enemy("Ghost", 0,0,0,this,4,0,generateWeapon(2));
            case 5:
                return new Enemy("Zombie",0,0,0,this,8,0,generateWeapon(4));
            case 6:
                return new Enemy("Skeleton",0,0,0,this,5,1,generateWeapon(5));
            case 7:
                return new Enemy("Bear",0,0,0, this,12,0,generateWeapon(5));
            case 8:
                return new Enemy("Witch",0,0,0,this,4,0,generateWeapon(6));
            case 9:
                return new Enemy("Vampire",0,0,0,this,8,1,generateWeapon(7));
            case 10:
                return new Enemy("Demon",0,0,0,this,12,2,generateWeapon(8));
                default:
                    return new Enemy("Error Type Enemy Not Found",0,0,0,this,1000,0,generateWeapon(0));
        }
    }
    public Consumable generateConsumable(int selected){
        switch (selected){
            case 0:
                return new Consumable(4,"Band_Aid");
            case 1:
                return new Consumable(6,"Gauze's");
            case 2:
                return new Consumable(12, "Healing_potion");
            default:
                return new Consumable(0,"Error potion");
        }
    }
    public Weapon generateWeapon(int selected){
        switch(selected){
            case 0:
                return new Weapon(false,2,"Bite");//low tier (Bat,Spider)
            case 1:
                return new Weapon(true, 1, "Spit");//low tier (Slime)
            case 2:
                return new Weapon(true, 2,"Smite");//low tier (ghost)
            case 3:
                return new Weapon(false, 3, "Club");// easy (Goblin)
            case 4:
                return new Weapon(false,4,"Bite");// standard Zombie + high Bear
            case 5:
                return new Weapon(false,5,"Sword");// standard tier Skillington's
            case 6:
                return new Weapon(true, 4,"Book_of_Lighting");// high tier whitch
            case 7:
                return new Weapon(false, 6, "LifeSteal");// difficult tier Vampier
            case 8:
                return new Weapon(true,5, "Spell_Bone_to_Ashe");// Demon
            case 9:
                return new Weapon(true, 100, "The_Haxe");//debug weapon
            default:
                return new Weapon(false,0,"Error Weapon Failed to find Weapon");
        }
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
        do{
            if(Attacker.getAlive()){
                Attacker.attack(Attacker.pickWeapon(),Defender);
            }
            if(Defender.getAlive()){
                Defender.attack(Defender.pickWeapon(),Attacker);
            }
        }while(Attacker.getAlive()&&Defender.getAlive());

        if(!Defender.getAlive()){
            Attacker.takeLoot(Defender.getWeaponBackpack(0));
        }
        if(!Attacker.getAlive()){
            Defender.takeLoot(Attacker.getWeaponBackpack(0));
        }
        return true;
    }

    public void generateStairs(int x, int y, int z, int x2, int y2, int z2){
        Stairs Down = new Stairs(x,y,z,x2,y2,z2,this);
        Stairs Up = new Stairs(x2,y2,z2,x,y,z,this);
        addEntity(Down);
        addEntity(Up);

    }

    public boolean combat(Character Attacker,Weapon Spike){
        Attacker.defence(Spike.getDamage(),Spike.getDamageType());
        return true;
    }
}
