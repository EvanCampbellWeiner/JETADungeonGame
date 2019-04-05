import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;
import javafx.event.ActionEvent;

public class Manager {

    public Character[] GameLoop;
    private int turn;
    public boolean continueGame;
    private World GameWorld;
    private int playerX, playerZ, playerY;
    private Random random=new Random();
public GUIController screenManager=new GUIController();
public ActionEvent event=new ActionEvent();

    public boolean playerLocation(int x, int y, int z){
        if(z==playerZ){
            return (x==playerX&&y==playerY);
        }else{
         return false;
        }
    }
    public void setPlayerLocation(int x, int y, int z){
        this.playerX=x;
        this.playerY=y;
        this.playerZ=z;
    }


    public void setGameLoop(Character enemy) {
        for(int i=0;i<GameLoop.length;i++){
            if((GameLoop[i].getX()==enemy.getX())&&(GameLoop[i].getY()==enemy.getY())&&(GameLoop[i].getZ()==enemy.getZ())){
                GameLoop[i].setAlive(false);
            }
        }
    }

    Manager(){

        turn=0;
        continueGame=true;
        this.GameLoop = new Character[0];
        //this.GameWorld = generatePreCreatedWorld();
    }
    Manager(GUIController ScreenManager){
        turn=0;
        continueGame=true;
        this.GameLoop = new Character[0];

        //this.GameWorld = generatePreCreatedWorld();
    }
    Manager(World Map){
        continueGame = true;
        turn=0;
        this.GameLoop = new Character[0];
        this.GameWorld=Map;

    }


    public void findValidPlacement(int zFloor, Entity placeObject){
        int pickX, pickY, bail=0;
        boolean sheaching=true;

        do {
            bail++;
            pickX = random(1,GameWorld.getFloor(zFloor).getFloor().length-1);
            pickY = random(1,GameWorld.getFloor(zFloor).getFloor()[0].length-1);
            if(!GameWorld.getFloor(zFloor).getFloor()[pickX][pickY].getWall()&&GameWorld.getFloor(zFloor).getFloor()[pickX][pickY].getEnemyLocation()==0){
                sheaching=false;
                placeObject.teleport(pickX,pickY,zFloor);
            }
        }while(sheaching&&bail<1000);
        if(bail>=1000){
            //throw error
            System.out.print("Placement Can't be fount Error in findValidPlacement");
        }
        //System.out.print("placement try's ="+bail);
    }


    public void generateRandomWorld(){
        int floorNumber = random(2,5), maxFloorSize=40, minFloorSize=20, maxRoomsPerFloor=6, minRoomsPerFloor=3;
        Floor[] Level = new Floor[floorNumber];
        for(int loop=0;loop<floorNumber;loop++){
            //System.out.print(loop);
            Level[loop] = new Floor(random(minFloorSize,maxFloorSize),random(minFloorSize,maxFloorSize));
            Level[loop].autoFillFloor(random(minRoomsPerFloor,maxRoomsPerFloor));

        }
        World randomWorld = new World(Level);
        this.GameWorld = randomWorld;
        this.autoRandomStairs();
        this.autoGenerateEnemys();
    }
    private void autoGenerateEnemys(){
        for(int floorNumber =0;floorNumber < GameWorld.getLevel().length;floorNumber++) {
            int enemyNumber = random(3, 15);
            for (int placementLoop = 0; placementLoop < enemyNumber; placementLoop++) {
                this.findValidPlacement(floorNumber, generateEnemy(random(0, 10)));
            }
        }
    }

    private void autoRandomStairs(){
        int pickXOne, pickYOne, pickXTwo,pickYTwo, bail;
        boolean sheaching;

        for(int loop=0; loop<GameWorld.getLevel().length;loop++){


            if(loop+1<GameWorld.getLevel().length) {
                bail=0;
                sheaching=true;
                System.out.print("Stairs ,("+loop+","+(loop+1)+")\n");
                do {
                    bail++;
                    pickXOne = random(0,GameWorld.getFloor(loop).getFloor().length-1);
                    pickYOne = random(0,GameWorld.getFloor(loop).getFloor()[0].length-1);
                    if(!GameWorld.getFloor(loop).getFloor()[pickXOne][pickYOne].getWall()&&GameWorld.getFloor(loop).getFloor()[pickXOne][pickYOne].getEnemyLocation()==0){
                        System.out.print("Stair's A ("+pickXOne+","+pickYOne+","+loop+")\n");
                        sheaching=false;
                    }
                }while(sheaching&&bail<1000);
                if(bail>=1000){
                    System.out.print("Error Can't place Stairs");
                }
                //System.out.print("placement try's ="+bail);

                sheaching=true;
                bail=0;
                do {
                    bail++;
                    pickXTwo = random(0,GameWorld.getFloor((loop+1)).getFloor().length-1);
                    pickYTwo = random(0,GameWorld.getFloor((loop+1)).getFloor()[0].length-1);
                    if(!GameWorld.getFloor((loop+1)).getFloor()[pickXTwo][pickYTwo].getWall()&&GameWorld.getFloor((loop+1)).getFloor()[pickXTwo][pickYTwo].getEnemyLocation()==0){
                        System.out.print("Stair's B ("+pickXTwo+","+pickYTwo+","+(loop+1)+")\n");
                        sheaching=false;
                    }
                }while(sheaching&&bail<1000);
                if(bail>=1000){
                    System.out.print("Error Can't place Stairs");
                }
                //System.out.print("placement try's ="+bail);
                this.generateStairs(pickXOne,pickYOne,loop,pickXTwo,pickYTwo,(loop+1));

                //System.out.print("Stair (" + loop + "-" + (loop + 1) + ")");
            }
        }
    }


    public void printMapWide(boolean printOnes, int drawDistance) {
        for(int loopY=playerY+drawDistance;loopY>playerY+((drawDistance*-1)-1);loopY--){
            System.out.print("\n");
            for(int loopX=playerX+((drawDistance*-1));loopX<playerX+(drawDistance+1);loopX++){
                if(!GameWorld.getTile(playerZ,loopX,loopY).getWall()) {
                    if(GameWorld.getTile(playerZ, loopX, loopY).getEnemyLocation()==0){
                        if(printOnes){System.out.print("1");}else{System.out.print(" ");}
                    }else{
                        //System.out.print(GameWorld.getTile(playerZ, loopX, loopY).getEnemyLocation());
                        System.out.print(GameLoop[GameWorld.getTile(playerZ,loopX,loopY).getEnemyLocation()-1].getType());
                    }

                }else{
                    System.out.print("0");
                }
            }
        }
    }


    public int[][] buildMap(int size)  {

    int Map[][] = new int[(size * 2) + 1][(size * 2) + 1];



    for (int loopY = (size * 2); loopY >= 0; loopY--) {
        //System.out.print("\n");
        for (int loopX = 0; loopX < ((size * 2) + 1); loopX++) {
            if (GameWorld.getTile(playerZ, playerX - size + loopX, playerY - size + loopY).getWall()) {
                //System.out.print("0");
                Map[loopY][loopX] = 0;

            } else {
                if (GameWorld.getTile(playerZ, playerX - size + loopX, playerY - size + loopY).getEnemyLocation() == 0) {
                    //System.out.print("1");
                    Map[loopY][loopX] = 1;
                } else {
                    //System.out.print(GameWorld.getTile(playerZ, loopX, loopY).getEnemyLocation());
                    Map[loopY][loopX] = GameLoop[GameWorld.getTile(playerZ, playerX - size + loopX, playerY - size + loopY).getEnemyLocation() - 1].getType();
                    //System.out.print(GameLoop[GameWorld.getTile(playerZ,playerX-size+loopX,playerY-size+loopY).getEnemyLocation()-1].getType());
                }
            }
        }
    }
    System.out.print("\n");
    //System.out.print();

    //ScreenManager.move(0);
 return(Map);

        //ScreenManager.move(2);
    }
    public World generatePreCreatedWorld(){
        this.GameWorld = new World(4);

        Floor RoomOne= new Floor(9,9);
        RoomOne.buildRectangle(7,7,1,1);

        Enemy Slime = generateEnemy(0);
        Slime.teleport(1,4,0);
        Enemy AnotherSlime = generateEnemy(0);
        AnotherSlime.teleport(7,4,0);

        this.addEntity(Slime);
        this.addEntity(AnotherSlime);
        this.generateStairs(4,7,0,1,1,1);

        Floor RoomTwo=new Floor(11,11);
        RoomTwo.buildRectangle(3,9,1,1);
        RoomTwo.buildRectangle(9,3,1,1);
        RoomTwo.buildRectangle(9,3,1,7);
        RoomTwo.buildRectangle(3,9,7,1);

        Enemy Zombie = generateEnemy(5);
        Zombie.teleport(1,9,1);
        Enemy AnotherZombie = generateEnemy(5);
        AnotherZombie.teleport(9,1,1);
        Enemy Goblin = generateEnemy(3);
        Goblin.teleport(8,3,1);
        Enemy AnotherGoblin = generateEnemy(3);
        AnotherGoblin.teleport(3,8,1);

        this.addEntity(Zombie);
        this.addEntity(AnotherZombie);
        this.addEntity(Goblin);
        this.addEntity(AnotherGoblin);
        this.generateStairs(9,9,1,1,6,2);

        Floor RoomThree= new Floor(13,13);
        RoomThree.buildRectangle(2,10,1,1);
        RoomThree.buildRectangle(11,2,1,1);
        RoomThree.buildRectangle(11,3,1,5);
        RoomThree.buildRectangle(11,2,1,10);
        RoomThree.buildRectangle(2,10,10,1);

        Enemy Bear1 = generateEnemy(7);
        Bear1.teleport(3,2,2);
        Enemy Bear2 = generateEnemy(7);
        Bear2.teleport(3,10,2);
        Enemy Bear3 = generateEnemy(7);
        Bear3.teleport(4,5,2);
        Enemy Bear4 = generateEnemy(7);
        Bear4.teleport(4,7,2);
        Enemy Bear5 = generateEnemy(7);
        Bear5.teleport(7,5,2);
        Enemy Bear6 = generateEnemy(7);
        Bear6.teleport(7,7,2);
        Enemy Bear7 = generateEnemy(7);
        Bear7.teleport(8,2,2);
        Enemy Bear8 = generateEnemy(7);
        Bear8.teleport(8,10,2);

        this.addEntity(Bear1);
        this.addEntity(Bear2);
        this.addEntity(Bear3);
        this.addEntity(Bear4);
        this.addEntity(Bear5);
        this.addEntity(Bear6);
        this.addEntity(Bear7);
        this.addEntity(Bear8);
        this.generateStairs(11,6,2,5,1,3);



        Floor RoomFour = new Floor(11,13);
        RoomFour.buildRectangle(9,3,1,1);
        RoomFour.buildRectangle(9,6,1,6);
        RoomFour.buildRectangle(3,2,4,4);

        Enemy Ghost1 = generateEnemy(4);
        Enemy Ghost2 = generateEnemy(4);
        Enemy Ghost3 = generateEnemy(4);
        Enemy Ghost4 = generateEnemy(4);
        Enemy Demon = generateEnemy(10);
        Enemy Vampire = generateEnemy(9);
        Ghost1.teleport(1,1,3);
        Ghost2.teleport(1,6,3);
        Ghost3.teleport(9,6,3);
        Ghost4.teleport(9,1,3);
        Demon.teleport(4,10,3);
        Vampire.teleport(6,10,3);


        GameWorld.customFloor(RoomOne,0);
        GameWorld.customFloor(RoomTwo,1);
        GameWorld.customFloor(RoomThree,2);
        GameWorld.customFloor(RoomFour,3);

        return GameWorld;
    }
    public Enemy generateEnemy(int selected){
        switch (selected){
            case 0:
                return new Enemy("Slime", 0,0 ,0 ,3,this, 2,0,generateWeapon(1));
            case 1:
                return new Enemy("Spider",0,0,0,4,this,3,1,generateWeapon(0));
            case 2:
                return new Enemy("Bat",0,0,0,5,this,3,1,generateWeapon(0));
            case 3:
                return new Enemy("Goblin",0,0,0,6,this,4,2,generateWeapon(3));
            case 4:
                return new Enemy("Ghost", 0,0,0,7,this,4,0,generateWeapon(2));
            case 5:
                return new Enemy("Zombie",0,0,0,8,this,8,1,generateWeapon(4));
            case 6:
                return new Enemy("Skeleton",0,0,0,9,this,5,1,generateWeapon(5));
            case 7:
                return new Enemy("Bear",0,0,0,10, this,10,3,generateWeapon(4));
            case 8:
                return new Enemy("Witch",0,0,0,11,this,4,1,generateWeapon(6));
            case 9:
                return new Vampire("Vampire",0,0,0,this,8,1,generateWeapon(7));
            case 10:
                return new Enemy("Demon",0,0,0,13,this,15,1,generateWeapon(8));
                default:
                    return new Enemy("Error Type Enemy Not Found",0,0,0,15,this,1000,0,generateWeapon(0));
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
    public int selectedConsumable(Consumable selected){
        switch (selected.getName()){
            case ("Band_Aid"):
                return 0;
            case ("Gauze's"):
                return 1;
            case ("Healing_Potion"):
                return 2;
            default:
                return -1;
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
    public int selectedWeapon(Weapon selected){
        switch(selected.getName()){
            case "Bite" :
                return 0;//low tier (Bat,Spider)
            case "Spit":
                return 1;//low tier (Slime)
            case "Smite":
                return 2;//low tier (ghost)
            case "Club":
                return 3;// easy (Goblin)
            case "HardBite":
                return 4;// standard Zombie + high Bear
            case "Sword":
                return 5;// standard tier Skillington's
            case "Book_of_Lightning":
                return 6;// high tier whitch
            case "LifeSteal":
                return 7;// difficult tier Vampier
            case "Spell_Bone_to_Ashe":
                return 8;// Demon
            case "The_Haxe":
                return 9;//debug weapon
            default:
                return -1;
        }
    }
    public void addEntity(Character Neo){
        Character[] NewGameLoop = new Character[GameLoop.length+1];
        System.arraycopy(GameLoop,0,NewGameLoop,0,GameLoop.length);
        NewGameLoop[NewGameLoop.length-1]= Neo;
        this.GameLoop = NewGameLoop;
        Neo.placement=GameLoop.length-1;
        GameWorld.getTile(Neo.getZ(),Neo.getX(),Neo.getY()).setEnemyLocation(NewGameLoop.length);

    }
    public void printFull(){
        for (int loop=0;loop<=GameLoop.length-1;loop++){
            GameLoop[loop].print();
        }
        GameWorld.printWorld();

    }
    public int RunGame(){
        int array=0;
        //ScreenManager.loadScene("SceneBuilder.GUI.fxml");
        do{
            //System.out.print(turn);

            if(GameLoop[turn].getZ()==playerZ) {
                if (GameLoop[turn].getAlive()) {
                    GameLoop[turn].startTurn();
                    //System.out.print();

                }
                //else {
                   // GameLoop[turn] = null;
            }
            //}
            //when you have a memory leak error tell me becouse you will
            iterateTurn();
        }while(continueGame);
        return(array);
        //loops thought GameLoop running RunTurn until exitGame is Called
    }
    private void iterateTurn(){
        //System.out.print("("+turn+"/"+getGameLoop().length+")");
        if(turn>=getGameLoop().length-1){
            turn=0;
            continueGame=false;
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
    public void exitGame(Player person,int check){
        try{

            Connection conn = DriverManager.getConnection("jdbc:sqlite:save1.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS \"Character\" (\n" +
                    "\t\"IDNumber\"\tINTEGER,\n" +
                    "\t\"Name\"\tTEXT UNIQUE,\n" +
                    "\t\"Health\"\tINTEGER,\n" +
                    "\t\"Armour\"\tINTEGER,\n" +
                    "\t\"Weapon1\"\tINTEGER,\n" +
                    "\t\"Weapon2\"\tINTEGER,\n" +
                    "\t\"Consumable1\"\tINTEGER,\n" +
                    "\t\"Consumable2\"\tINTEGER,\n" +
                    "\t\"Consumable3\"\tINTEGER,\n" +
                    "\t\"Exp\"\tINTEGER,\n" +
                    "\t\"Level\"\tINTEGER\n" +
                    ")");
           statement.execute( "CREATE UNIQUE INDEX IF NOT EXISTS idx_Character_IDNumber ON Character (IDNumber)");
            statement.execute("INSERT OR REPLACE INTO Character (IDNumber,Name, Health, Weapon1, Weapon2, Consumable1, Consumable2, Consumable3, Exp, Level) " +
                    "VALUES ('"+check+"','"+person.getName()+"','"+person.getMaxHealth()+"','"+ selectedWeapon(person.getWeaponBackpack(0))+"','"+ selectedWeapon(person.getWeaponBackpack(1))+
                    "','"+selectedConsumable(person.getConsumiblePocket(0))+"','"+ selectedConsumable(person.getConsumiblePocket(1))+"','"+selectedConsumable(person.getConsumiblePocket(2))+
                    "','"+person.getExperance()+"','"+person.getLevel()+"')");

            statement.close();
            conn.close();

        }catch (SQLException e){

            System.out.println("Something went wrong: " + e.getMessage());
        }//end catch bracket

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
    public boolean combat(Character Attacker, Character Defender){//return true is attacker wins
        do{
            Attacker.printSimpleCombat();
            Defender.printSimpleCombat();
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
        Stairs Down = new Stairs(x,y,z,x2,y2,z2,this,2);
        Stairs Up = new Stairs(x2,y2,z2,x,y,z,this,14);
        addEntity(Down);
        addEntity(Up);

    }

    public boolean combat(Character Attacker, Weapon Spike){
        Attacker.defence(Spike.getDamage(),Spike.getDamageType());
        return true;
    }
    private int random(int low,int high){
        if(high<low) {
            return (high + random.nextInt((low - high)));
        }else {
            return (low+random.nextInt((high-low)));
        }
    }
}
