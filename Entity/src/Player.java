import java.util.Scanner;

public class Player extends Character{
    Scanner scanner = new Scanner(System.in);
    private int level;
    private int experance;

    Player(String Name, int x, int y, int z, Manager Management){
        super(Name,x,y,z,Management,10,1);
    }
    Player(String Name,int x,int y,int z, Manager Management,int armor, Weapon[] Swords, Consumable[] Potions, int exp, int level){
        super(Name,x,y,z,Management,(10+(level*2)),armor,Swords,Potions);
        this.experance=exp;
        this.level=level;
    }

    private void levelUp(){
        this.experance=0;
        level++;
        setMaxHealth(getMaxHealth()+2);
    }

    @Override
    public void gameOver() {
        System.out.print("You Were killed");
        setAlive(false);
        getMyManager().exitGame();
        //calls game manager to kill this case or respawn
    }

    @Override
    public void attack(Weapon Sword, Character Target) {
        super.attack(Sword, Target);
        experance++;
    }

    @Override
    public Weapon pickWeapon() {
        printWeapon();
        int pick;
        do{
            pick = scanner.nextInt();
        }while(!(pick<=0&&pick>=this.getSheath().length-1));
        return getWeaponBackpack(pick);
    }

    @Override
    public void startTurn() {
        int pick;
        boolean validator = true;

        if(experance<Math.pow(level,2)){
            levelUp();
        }
        do {
            getMyManager().getGameWorld().printFloor(getZ());
            System.out.print(" "+getName()+" lv:"+level+" ep:"+experance+"\n  1  \n4-x-2\n  3  \n5: Inventory\n0: ExitGame");
            print();
            pick = scanner.nextInt();
            validator = true;
            switch (pick) {
                case 0:
                    getMyManager().exitGame();
                    break;
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
                case 5:
                    validator = openInfintoryUsePotion();
                    break;
                default:
                    validator = false;
            }
        } while (!validator);
    }

}
