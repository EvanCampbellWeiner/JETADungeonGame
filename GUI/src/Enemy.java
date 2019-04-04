import java.util.Random;

public class Enemy extends Character {
    //Declaring integers
    private Random random=new Random();
    private int lastSeen;
    private int memoryDecay;

    //Creating the enemy parameters
    Enemy(String Name, int x, int y, int z, int type, Manager Management, int health, int armor, Weapon Sword){
        super(Name,x,y,z,type,Management,health,armor,Sword);
        this.setfriendly(true);
        this.setplayer(false);
        getMyManager().addEntity(this);
    }

    @Override
    //Enemy's don't loot
    public void takeLoot(Weapon Looted) {
        //enemy's don't loot
    }

    @Override
    //Calls the game manager to kill
    public void gameOver() {
        setAlive(false);
        //call game manager to kill
    }

    @Override
    //Gives enemy their automatic weapon
    public Weapon pickWeapon() {
        return (getWeaponBackpack(0));
    }

    @Override
    //Enemy turns
    public void startTurn() {
        boolean hasMoved=false;
        final int memoryLength = 5;
        int pick;
        int attemptedMoves=0;

        if(memoryDecay<=0){
            lastSeen=0;
        }else{
            memoryDecay--;
        }

        do{
            attemptedMoves++;
            System.out.print("Move "+getName()+", "+attemptedMoves);
            if(lookStraight(1,0)){
                hasMoved =Move(getX()+1,getY());
                lastSeen=1;
                memoryDecay=memoryLength;
            }else if(lookStraight(0,1)){
                hasMoved =Move(getX(),getY()+1);
                lastSeen=2;
                memoryDecay=memoryLength;

            }else if(lookStraight(-1,0)){
                hasMoved =Move(getX()-1,getY());
                lastSeen=3;
                memoryDecay=memoryLength;

            }else if(lookStraight(0,-1)) {
                hasMoved = Move(getX(), getY() - 1);
                lastSeen = 4;
                memoryDecay = memoryLength;

            }else if(lastSeen!=0){
                switch (lastSeen){
                    case 1:
                        hasMoved =Move(getX()+1,getY());
                        break;
                    case 2:
                        hasMoved =Move(getX(),getY()+1);
                        break;
                    case 3:
                        hasMoved =Move(getX()-1,getY());
                        break;
                    case 4:
                        hasMoved = Move(getX(), getY() - 1);
                        break;
                        default:
                            System.out.print("Path Finding Error ="+lastSeen);
                }
            }else{
                pick = random(1,5);
                switch (pick) {
                    case 1:
                        hasMoved = Move(getX(), (getY() + 1));
                        break;
                    case 2:
                        hasMoved = Move((getX() + 1), getY());
                        break;
                    case 3:
                        hasMoved = Move(getX(), (getY() - 1));
                        break;
                    case 4:
                        hasMoved = Move((getX() - 1), getY());
                        break;
                    default:
                        hasMoved = false;
                }
            }
            if(attemptedMoves>5){
                hasMoved=true;
            }


        }while(!hasMoved);

        if(lookStraight(1,0)){
            lastSeen=1;
            memoryDecay = memoryLength;
        }else if(lookStraight(0,1)){
            lastSeen=2;
            memoryDecay = memoryLength;
        }else if(lookStraight(-1,0)){
            lastSeen =3;
            memoryDecay=memoryLength;

        }else if(lookStraight(0,-1)) {
            lastSeen = 4;
            memoryDecay = memoryLength;
        }
    }

    //Enemy view to check tiles ina row based on the coordinates
    private boolean lookStraight(int xChange, int yChange){
        boolean searching=false;
        int xCheak=0,yCheak=0;
        final int viewDistance=5;
        int loop=0;
        do{
            loop++;
            xCheak+=xChange;
            yCheak+=yChange;
            if(getMyManager().playerLocation(getX()+xCheak,getY()+yCheak,getZ())){
                searching=true;
            }
        }while(loop<=viewDistance);
        return searching;
    }

    //used for random movement
    private int random(int low,int high){
        if(high<low) {
            return (high + random.nextInt((low - high)));
        }else {
            return (low+random.nextInt((high-low)));
        }
    }
}
