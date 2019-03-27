import java.util.Random;

public class Enemy extends Character{
    private Random random=new Random();

    Enemy(String Name,int x,int y,int z,Manager Management,int health,int armor,Weapon Sword){
        super(Name,x,y,z,Management,health,Sword,armor);
    }

    @Override
    public void takeLoot(Weapon Looted) {
        //enemy's don't loot
    }

    @Override
    public void gameOver() {
        setAlive(false);
        //call game manager to kill
    }

    @Override
    public Weapon pickWeapon() {
        return (getWeaponBackpack(0));
    }

    @Override
    public void startTurn() {
        //System.out.print("My Turn");
        int pick;
        boolean validator;
        do {
            validator=true;
            pick = random(1,5);
            switch (pick) {
                case 1:
                    validator = Move(getX(), (getY() + 1));
                    break;
                case 2:
                    validator = Move((getX() + 1), getY());
                    break;
                case 3:
                    validator = Move(getX(), (getY() - 1));
                    break;
                case 4:
                    validator = Move((getX() - 1), getY());
                    break;
                default:
                    validator = false;
            }
        }while(!validator);

    }

    private int random(int low,int high){
        if(high<low) {
            return (high + random.nextInt((low - high)));
        }else {
            return (low+random.nextInt((high-low)));
        }
    }
}
